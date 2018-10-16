package mx.ine.sif.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.IdClass;

import mx.ine.sif.dao.DAOUsuarioLoginInterface;
import mx.ine.sif.dto.DTOUsuarioLogin;
import mx.ine.sif.util.Constantes;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoUsuarioLogin")
public class DAOUsuarioLogin  implements DAOUsuarioLoginInterface {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	

	private static final Logger logger = Logger.getLogger( DAOUsuarioLogin.class);
	
	@Override
	public DTOUsuarioLogin DAOconsultarUsuarioLogin(DTOUsuarioLogin dtoUsuarioLogin, String rol ) {		
		try {
			// Metodo que registra en bitacora a los usuarios logueados en SIF
			//P_Validar_Usuario_LDAP( pUsuarioLDPA , pEstadoVisibilidad , pSistema , pGrupo )
			Query pl = sessionFactory.getCurrentSession().createSQLQuery("{call P_Validar_Usuario_LDAP(?,?,?,?)}");
			pl.setString(0, dtoUsuarioLogin.getUsuario());//pUsuarioLDPA
			pl.setInteger(1, dtoUsuarioLogin.getIdEstado());//pEstadoVisibilidad
			pl.setInteger(2, Constantes.IDSISTEMA);//pSistema
			pl.setString(3, rol);//pGrupo
			pl.executeUpdate();			
			
			//Consulta para identificar el id de grupo 
			String qry_usr_rol= "SELECT Id_Grupo" 
								+" FROM ADMIN_GRUPOS"
								+" WHERE UPPER(NOMBRE_GRUPO) = :rolLdap"; 
			 
			SQLQuery usr_rol = sessionFactory.getCurrentSession().createSQLQuery(qry_usr_rol);
			//usr_rol.setParameter("psistema", Constantes.IDSISTEMA);
			usr_rol.setParameter("rolLdap", rol);
			 
			List<BigDecimal> roles = usr_rol.list();				
			
			for(BigDecimal arreglo:roles){
					String idRol = (arreglo!= null )? arreglo.toString():String.valueOf(0);					
					dtoUsuarioLogin.setIdRol(Integer.parseInt(idRol));
			}
			
			//Consulta para identificar estado de visivilidad en sistema(puede ser igual o diferente a el de LDAP) SIF y el id usuario asignado en SIF
			String qry_datos  =   "SELECT usuarios.id_estado_visibilidad, usuarios.id_usuario  "					  
					  			+ "FROM SIF.usuarios "
					  			+ "WHERE usuarios.usuario_ldap = :usuario  AND usuarios.Id_Grupo = :idGrupo";
			 
			 SQLQuery datos_usr = sessionFactory.getCurrentSession().createSQLQuery(qry_datos);
			 datos_usr.setParameter("usuario", dtoUsuarioLogin.getUsuario());
			 datos_usr.setParameter("idGrupo", dtoUsuarioLogin.getIdRol());
			 
			
			 List<Object[]> datos = datos_usr.list();
			 
			 for(Object[] arreglo:datos){
				 String idEstadoVisibilidad = (arreglo[0]!= null )? arreglo[0].toString():String.valueOf(0);
				 String idUsuario =(arreglo[1]!= null )? arreglo[1].toString():String.valueOf(0);
				 dtoUsuarioLogin.setIdEstadoVisibilidad(Integer.parseInt(idEstadoVisibilidad));
				 dtoUsuarioLogin.setIdUsuario(Integer.parseInt(idUsuario));
			 }
			 
			// Consulta que identidica las contabiluades  asignadas a a usuario logueado
			 String qry_cont =  " SELECT   UC.ID_CONTABILIDAD_ORD "
			 		   +" FROM SIF.USUARIOS_CONTA_ORD UC"
			 		   +" WHERE  UC.ID_USUARIO = :usuario";
			 
			 SQLQuery contabilidades = sessionFactory.getCurrentSession().createSQLQuery(qry_cont);
			 contabilidades.setParameter("usuario", dtoUsuarioLogin.getIdUsuario());
			 
			 
			 List<BigDecimal> conta = contabilidades.list();
			 ArrayList<Integer> idsContbilidadesAsociadas = new ArrayList<Integer>();
			 
			 for(BigDecimal arreglo:conta){
				 idsContbilidadesAsociadas.add(arreglo.intValue());
			 }
			 dtoUsuarioLogin.setIdsContbilidadesAsociadas(idsContbilidadesAsociadas);
			 			 
		} catch (NumberFormatException e) {
			logger.error("NUMBERFORMAT: " + e);
			e.printStackTrace();
		} catch (HibernateException e) {
			logger.error("HIBERNATE EXC: " + e);
			e.printStackTrace();
		}
		
		return dtoUsuarioLogin;
	}

	@Override
	public int consultarSujetosObligados(int idSO, int idTSO) {
		
		// Metodo  para idenficar el idSO asiganado en el sistema SIF para un usuario de SO relacion con PK de asosciaciones organos directivos 
		
		String qry_usr_so=  "SELECT ID_SUJETO_OBLIGADO " 
				+" FROM SIF.C_SUJETOS_OBLIGADOS "
				+" WHERE ID_ASOCIACION = :idso AND TIPO_ASOCIACION = :idtso"; 

		SQLQuery usr_SO = sessionFactory.getCurrentSession().createSQLQuery(qry_usr_so);
		usr_SO.setParameter("idso", idSO);
		usr_SO.setParameter("idtso", idTSO);
		
		BigDecimal so= (BigDecimal) ((usr_SO.uniqueResult()!= null )? usr_SO.uniqueResult():new BigDecimal("0"));
		
		return so.intValue();
	}
	
	@Override
	public int consultarSujetosObligadosIND(int idSO, int idTSO,String usuario,int idrol)  {
	
		// Metodo  para idenficar el idSO asiganado en el sistema SIF para un cdatoind o aspirante
		
		String qry_usr_cdato= " SELECT cdato.ID_CANDIDATO"
					+ " FROM SIF.HISTORICO_CANDIDATOS cdato" 
					+ " JOIN SIF.USUARIOS usu  ON  usu.USUARIO_LDAP =:usuario AND usu.ID_GRUPO =:idrol"
					+ " WHERE  usu.ID_USUARIO = cdato.ID_USUARIO_CREACION";

		SQLQuery usr_cdato = sessionFactory.getCurrentSession().createSQLQuery(qry_usr_cdato);
		usr_cdato.setParameter("usuario", usuario);
		usr_cdato.setParameter("idrol", idrol);
		
		BigDecimal cdato= (BigDecimal) ((usr_cdato.list().get(0)!= null )? usr_cdato.list().get(0):new BigDecimal("0"));
		
		
		String qry_usr_so=  "SELECT ID_SUJETO_OBLIGADO " 
				+" FROM SIF.C_SUJETOS_OBLIGADOS "
				+" WHERE ID_ASOCIACION = :idso AND TIPO_ASOCIACION = :idtso AND  ID_CANDIDATO = :idCdato"; 

		SQLQuery usr_SO = sessionFactory.getCurrentSession().createSQLQuery(qry_usr_so);
		usr_SO.setParameter("idso", idSO);
		usr_SO.setParameter("idtso", idTSO);
		usr_SO.setParameter("idCdato", cdato.intValue());
		
		BigDecimal so = (BigDecimal) ((usr_SO.uniqueResult()!= null )? usr_SO.uniqueResult():new BigDecimal("0"));
		
		return so.intValue();
	}
	
	@Override
	public List<Object[]> consultaEtapasMenu(Integer ejercicio, Integer grupo)  {
	
		Integer sistema = Constantes.IDSISTEMA;
		
		 String q= "select distinct "
					+ "e.ID_ETAPA, "
					+ "e.nombre_etapa "
					+ " from admin_menus ad  left join admin_permisos adp on"
					+ " ad.EJERCICIO = adp.EJERCICIO and ad.ID_SISTEMA = adp.ID_SISTEMA and ad.ID_ETAPA = adp.ID_ETAPA and ad.ID_MODULO = adp.ID_MODULO and"
					+ " ad.ID_ACCION = adp.ID_ACCION left join admin_etapas e on ad.ID_ETAPA = e.ID_ETAPA and adp.ID_ETAPA = e.ID_ETAPA left join admin_mod_ejercicios eje_mod on "
					+ " ad.EJERCICIO = eje_mod.EJERCICIO and ad.ID_SISTEMA = eje_mod.ID_SISTEMA and  "
					+ " ad.ID_ETAPA = eje_mod.ID_ETAPA and ad.ID_MODULO = eje_mod.ID_MODULO where "
					+ " ad.EJERCICIO= :ejercicio and ad.ID_SISTEMA= :sistema"
					+ " and adp.ID_GRUPO= :grupo and adp.PRINCIPAL=1 and ad.VISIBILIDAD=1 and eje_mod.VISIBILIDAD=1 order by e.ID_ETAPA";
		 
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(q);
		query.setParameter("ejercicio", ejercicio);
		query.setParameter("grupo", grupo);
		query.setParameter("sistema", sistema);
		
		List<Object[]> etapas = query.list();
		
		return etapas;
	}
	
	@Override
	public List<Object[]> consultaMenu(Integer ejercicio, Integer grupo)  {
	
		
		Integer sistema = Constantes.IDSISTEMA;
		
		String q= "select distinct "
				+ " ad.ID_ETAPA,"
				+ " ad.ID_MODULO,"
				+ " ad.ID_ACCION,"
				+ " ad.URL_MODULO,"
				+ " m.NOMBRE_MODULO, "
				+ " eje_mod.orden "
				+ " from admin_menus ad left join admin_permisos adp on"
				+ " ad.EJERCICIO = adp.EJERCICIO and ad.ID_SISTEMA = adp.ID_SISTEMA and ad.ID_ETAPA = adp.ID_ETAPA and ad.ID_MODULO = adp.ID_MODULO and"
				+ " ad.ID_ACCION = adp.ID_ACCION left join admin_modulos m on ad.ID_MODULO = m.ID_MODULO and adp.ID_MODULO = m.ID_MODULO "
				+ " left join admin_mod_ejercicios eje_mod on ad.EJERCICIO = eje_mod.EJERCICIO and ad.ID_SISTEMA = eje_mod.ID_SISTEMA and "
				+ " ad.ID_ETAPA = eje_mod.ID_ETAPA and ad.ID_MODULO = eje_mod.ID_MODULO where "
				+ " ad.EJERCICIO= :ejercicio and ad.ID_SISTEMA= :sistema and adp.ID_GRUPO= :grupo and adp.PRINCIPAL=1 and ad.VISIBILIDAD=1 and eje_mod.VISIBILIDAD=1 "
				+ " order by ad.ID_ETAPA,eje_mod.orden,ad.ID_MODULO";

		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(q);
		query.setParameter("ejercicio", ejercicio);
		query.setParameter("grupo", grupo);
		query.setParameter("sistema", sistema);
		
		List<Object[]> etapas = query.list();
		
		return etapas;
	}

}
