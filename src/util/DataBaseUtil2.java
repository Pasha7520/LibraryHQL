package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;




import com.mysql.jdbc.Driver;

import cons.Constants;

public class DataBaseUtil2 {
	private static Connection connection ;
	private static Driver driver;
	
		public DataBaseUtil2() throws SQLException{
			
		}
		
		
		
		public static void init() throws SQLException{
			if(connection == null){
			driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(Constants.URL,Constants.NAME, Constants.PASSWORD);
			
			}
		}
		
		public static Connection getConnection(){
			return connection;
			
		}
		@Override
		protected void finalize () {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
}
