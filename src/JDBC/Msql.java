package JDBC;

import java.sql.*;

public class Msql {

	public static void main(String[] args) {
		try {                  //(driver name)
			//Class.forName("com.mysql.jdbc.Driver"); // To register the driver 
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees",  "root", "15091999anu");
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("select emp_no from employees limit 10");
			while(r.next())
				System.out.println(r.getInt("emp_no"));
			c.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
