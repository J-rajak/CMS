package backend;
import java.sql.*;

	public class Connections {
		static Connection con;
		
		public static Connection getConnection() {
			
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+Config.hostName+":"+ Config.dbPort +"/"+ Config.dbName, Config.dbUser, Config.dbPass);
		} catch(Exception e) {
			System.out.println("" + e);
		}
		return con;

	    }

	}
