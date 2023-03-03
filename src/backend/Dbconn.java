package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconn {

	private static final Connection connection = getConnection();

	private static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + Config.hostName + ":" + Config.dbPort + "/" + Config.dbName, Config.dbUser, Config.dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Statement getStatement() {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}