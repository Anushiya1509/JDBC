package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
	
	public static void main(String[] a) {
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String password = "student";
		try {
			Connection myConnection = DriverManager.getConnection(url, user, password);
			System.out.println("Database connection successfulll!");
			Statement myStatement = myConnection.createStatement();
			ResultSet resultSet = myStatement.executeQuery("select last_name, first_name from employees");
			while(resultSet.next())
				System.out.println(resultSet.getString("last_name") + " " + resultSet.getString("first_name"));
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
