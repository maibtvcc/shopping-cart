package shopping_cart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCUtil {
	private static Connection connection=null;
	
	private static String url = "jdbc:mysql://uj0ffcflipppmjov:neTnQXzejM78y3j7ijct@bgkw5keudgvt1ll0urj7-mysql.services.clever-cloud.com:3306/bgkw5keudgvt1ll0urj7";
	private static String user = "uj0ffcflipppmjov";
	private static String password = "neTnQXzejM78y3j7ijct";
	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		connection = DriverManager.getConnection(url ,user, password);
		return connection;
	}
	
	 public static void closeConnection(Connection c){
	        try{
	            if(c!=null) c.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	 }
	 
	 public static void printInfo(Connection c) throws SQLException {
	        if (c!=null) {
	            System.out.println(c.getMetaData());
	        }
	 }
}


