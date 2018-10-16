package mx.ine.sif.dao.impl;

import mx.ine.sif.dao.DAOPruebasPATInterface;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoPruebasPAT")
public class DAOPruebasPAT implements DAOPruebasPATInterface {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(DAOPruebasPAT.class);

	@Override
	public String funcionPrueba() {

		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select descripcion from c_estatus_pat where id_estatus_pat = 100");

		String sello = (String) query.uniqueResult();

		return "DAO Correcto " + sello;
	}

}
