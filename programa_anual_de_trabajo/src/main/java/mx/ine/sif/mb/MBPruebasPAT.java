package mx.ine.sif.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import mx.ine.sif.bsd.BSDPruebasPATInterface;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mbPruebasPAT")
@Scope("session")
@ManagedBean
public class MBPruebasPAT extends MBAdminPermisos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4330481666388893610L;

	private static final Logger logger = Logger.getLogger(MBPruebasPAT.class);
	
	@Autowired
	@Qualifier("bsdPruebasPAT")
	private  transient BSDPruebasPATInterface bsdPruebasPAT;

	public void funcionPrueba() {
		logger.info("MB Correcto "+ bsdPruebasPAT.funcionPrueba());
	}

}
