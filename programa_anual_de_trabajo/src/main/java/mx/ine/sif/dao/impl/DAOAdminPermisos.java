package mx.ine.sif.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.ine.sif.dao.DAOAdminPermisosInterface;
import mx.ine.sif.dto.DTOUsuarioLogin;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.type.IntegerType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("daoAdminPermisos")
@Scope("prototype")
public class DAOAdminPermisos extends DAOGenericSif<DTOUsuarioLogin, Integer>
		implements DAOAdminPermisosInterface {

	private static final Logger logger = Logger.getLogger(DAOAdminPermisos.class);

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Integer> getPermisosEnModulo(int idSistema, int idModulo,
			int rolUsuario, int anio) {
		List<Integer> permisos = new ArrayList<Integer>();
		try {
			
			String sql = "SELECT id_accion AS accion"
					+ " FROM admin_permisos p  WHERE "
					+ " p.id_sistema=? AND p.id_modulo=? AND p.ID_GRUPO = ? and p.ejercicio= ?";

			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			sqlQuery.setInteger(0, idSistema);
			sqlQuery.setInteger(1, idModulo);
			sqlQuery.setInteger(2, rolUsuario);
			sqlQuery.setInteger(3, anio);
			sqlQuery.addScalar("accion", IntegerType.INSTANCE);

			List<Map> permisosMap = sqlQuery.list();

			if (permisosMap.size() > 0) {
				for (int i = 0; i < permisosMap.size(); i++) {
					permisos.add((Integer) permisosMap.get(i).get("accion"));
				}
			}

			return permisos;
		} catch (Exception e) {
			logger.debug("ERROR:" + e.getMessage());

			e.printStackTrace();

			return null;
		}
	}

}
