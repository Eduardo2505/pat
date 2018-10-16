package mx.ine.sif.bsd.impl;

import mx.ine.sif.as.ASPruebasPATInterface;
import mx.ine.sif.bsd.BSDPruebasPATInterface;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("bsdPruebasPAT")
@Scope("prototype")
public class BSDPruebasPAT implements BSDPruebasPATInterface {



	private static final Logger logger = Logger.getLogger(BSDPruebasPAT.class);
	
	@Autowired
	@Qualifier("asPruebasPAT")
	private ASPruebasPATInterface asPruebasPAT;

	@Override
	public String funcionPrueba() {
		// TODO Auto-generated method stub
		return "BSD Correcto "+asPruebasPAT.funcionPrueba();
	}

	
}
