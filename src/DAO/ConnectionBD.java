package DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class ConnectionBD {

	public static String HOST= "localhost";
	public static String user =  "root" ; 
    public static  String password =  "";
    public static String protocole =  "jdbc:mysql:" ;
    public static String DATABASE  =  "projet?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    public static String port = "3306";
    private  Connection con;
    
	
	public ConnectionBD() {
		try {
		       
	         Class c = Class.forName("com.mysql.jdbc.Driver") ;
	         Driver pilote = (Driver)c.newInstance() ;
	      
	         DriverManager.registerDriver(pilote);
	       
	         String conString = protocole +  "//" + HOST +  ":" + port +  "/" + DATABASE ;
	          con = DriverManager.getConnection(
	            conString, user, password) ;
	         
	          
	      }  catch (Exception e) {
	    	  System.out.println(e);
	      }
	}
	public  Connection connexion() {
		 
		 return this.con;
	}

}
