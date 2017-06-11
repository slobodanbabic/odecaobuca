package gui;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.OdecaEjbTim4;
import beans.OdecaTim4I;

public class Remote {
	
	private static OdecaTim4I remoteEjb;
	
	public static OdecaTim4I getRemote(){
		if (remoteEjb == null) {			
			String name = "ejb:/OdecaServerTim4//" + OdecaEjbTim4.class.getSimpleName() + "!" + OdecaTim4I.class.getName() + "?stateful";
			try{				
				InitialContext ctx = new InitialContext();
				remoteEjb = (OdecaTim4I) ctx.lookup(name);
			}catch (NamingException ex) {
				String msg = ex.getClass().getName() + ":\n" + ex.getMessage();			
				System.out.println(msg);
			}
			
		}
		return remoteEjb;
	}
}