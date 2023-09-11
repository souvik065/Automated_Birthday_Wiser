package Wisher;

import java.sql.Connection;
import java.sql.DriverManager;


public class CP {
static Connection con;
	
	public static Connection createC() {
		try {
			
			// Load the driver 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//create the connection...
			String user="root";
			String password="123456";
			String url = "jdbc:mysql://localhost:3306/birthday_wisher";
			
			con = DriverManager.getConnection(url, user, password);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	

}
