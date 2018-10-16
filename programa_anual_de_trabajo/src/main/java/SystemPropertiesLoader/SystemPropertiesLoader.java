package SystemPropertiesLoader;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import mx.ine.sif.util.JNDIContext;

@Singleton
@Startup
public class SystemPropertiesLoader {

	 @PostConstruct
     void initialize() {
          Properties mySystemProperties = new Properties();
          mySystemProperties.put("picketlink.identityurl.loginutf", JNDIContext.getEntry("java:/picketlink/identityurl/loginutf") );
          mySystemProperties.put("picketlink.serviceurl.pat", JNDIContext.getEntry("java:/picketlink/serviceurl/pat") );
          System.getProperties().putAll(mySystemProperties);
     }
}
