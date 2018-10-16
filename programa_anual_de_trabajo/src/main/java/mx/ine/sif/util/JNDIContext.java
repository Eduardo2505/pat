package mx.ine.sif.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIContext {
	private static Context context  = null;

	static{
		try {
			context = new InitialContext ();
		} catch (NamingException e) {
			e.printStackTrace();
		}          
	}

	public static Object getEntry( String name ){
		Object object = null;
		try {
			object = context.lookup( name );
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return object;
	}
}
