package gui;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.OdecaEjbTim4;
import beans.OdecaTim4I;

public class Remote {
	
	private static OdecaTim4I remoteEjb;
	
	public static OdecaTim4I getRemote() throws NamingException {
		if (remoteEjb == null) {
			InitialContext ctx = new InitialContext();
			String name = "ejb:/OdecaServerTim4//" + OdecaEjbTim4.class.getSimpleName() + "!"
					+ OdecaTim4I.class.getName() + "?stateful";
			remoteEjb = (OdecaTim4I) ctx.lookup(name);
		}
		return remoteEjb;
	}
}
