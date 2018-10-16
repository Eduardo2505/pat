package mx.ine.sif.as.impl;


import mx.ine.sif.as.ASPruebasPATInterface;
import mx.ine.sif.dao.DAOPruebasPATInterface;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Scope("prototype")
@Service("asPruebasPAT")
public class ASPruebasPAT implements ASPruebasPATInterface {

	private static final Logger logger = Logger.getLogger(ASPruebasPAT.class);
	
	@Autowired
	@Qualifier("daoPruebasPAT")
	private DAOPruebasPATInterface daoPruebasPAT;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public String funcionPrueba() {
		// TODO Auto-generated method stub
		return "AS Correcto "+daoPruebasPAT.funcionPrueba();
	}
}
