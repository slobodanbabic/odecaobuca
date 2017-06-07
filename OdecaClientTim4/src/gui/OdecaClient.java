package gui;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import beans.OdecaEjbTim4;
import beans.OdecaTim4I;


public class OdecaClient {
	private OdecaTim4I remoteEjb;
	
	private OdecaTim4I get() throws NamingException
	{
		if (remoteEjb == null)
		{
			InitialContext ctx = new InitialContext();			
			String name= "ejb:/OdecaServerTim4//" + OdecaEjbTim4.class.getSimpleName()+ "!" + OdecaTim4I.class.getName() + "?stateful";
			remoteEjb = (OdecaTim4I) ctx.lookup(name);
		}
		return remoteEjb;
	}
	
	public static void main(String[] args) {
		OdecaClient client = new OdecaClient();
		try{
			client.remoteEjb = client.get();
		} catch (NamingException ex){
			String msg = ex.getClass().getName() + ":\n" + ex.getMessage();
			System.out.println(msg);
		}
		
		String str=client.remoteEjb.registracija("Para", "Peric", "pera@gmail.com", "peki", "peki", new byte[]{});
		System.out.println(str);
		if(client.remoteEjb.login("peki", "peki"))
			System.out.println("uspesno sam se logovo");
		

	}

}
