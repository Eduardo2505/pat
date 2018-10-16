package mx.ine.sif;

import java.io.File;
import java.security.AccessController;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


/**
 * @author Luis "guichosun" del Campo
 * @copyright Dirección de sistemas - IFE
 */
public class ConfigServletContext implements ServletContextListener {

	private static Logger log = Logger.getLogger(ConfigServletContext.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			String glusterfs;
			Context initialContext = new InitialContext();
			
			glusterfs = (String)initialContext.lookup("util/glusterFS");
			//String envTmp = glusterfs.endsWith("/")?glusterfs:glusterfs+"/";
			
			if(glusterfs==null){
				log.info("ruta gluster vacia.");
				glusterfs="/";
				System.setProperty("glusterFS", glusterfs);
				System.setProperty("gluster.bases.dir", System.getProperty("glusterFS"));
			}else if(glusterfs.equalsIgnoreCase("/")){
				/**
				 * Si la ruta que esta definida en el JNDI es "/" significa que es un servidor de inter/intra de produccion.
				 * Entonces la ruta para gestion se crea con ese valor y la constante gestion3 para quedar de la siguiente manera
				 *	/gestion3
				 */
				log.info("ruta gluster de app-inter/app-intra.");
				System.setProperty("glusterFS",glusterfs );
				System.setProperty("gluster.bases.dir", System.getProperty("glusterFS"));
				
			}else if(glusterfs.equalsIgnoreCase("/dataglusterfs")){
				/**
				 * Si la ruta que esta definida en el JNDI es "/dataglusterfs" significa que es un servidor de pruebas jb5.
				 * Entonces la ruta para gestion se crea con ese valor,una diagonal y la constante gestion3 para quedar de la siguiente manera
				 *	/dataglustefs/gestion3
				 */
				glusterfs = glusterfs + File.separator;
				log.info("ruta gluster de jbossEAP5pruebas.");
				System.setProperty("glusterFS",glusterfs );
				System.setProperty("gluster.bases.dir", System.getProperty("glusterFS"));
			}else if (glusterfs.trim().isEmpty()){
				log.info("ruta gluster vacia.");
				glusterfs="/";
				System.setProperty("glusterFS",glusterfs );
				System.setProperty("gluster.bases.dir", System.getProperty("glusterFS"));
			}else{
				//glusterfs = glusterfs + File.separator;
				log.info("ruta gluster de jbossEAP5pruebas.");
				System.setProperty("glusterFS",glusterfs );
				System.setProperty("gluster.bases.dir", System.getProperty("glusterFS"));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
