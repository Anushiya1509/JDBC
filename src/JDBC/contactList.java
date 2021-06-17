package JDBC;

import java.sql.*;
import java.util.Scanner;

public class contactList {
	
	private static Scanner s = new Scanner(System.in);
	
	private static String url;
	// url  = "jdbc:mysql://localhost:3306/demo";
	private static String user;
	// user = "student";
	private static String password;
	// password = "student";
	private static String dropDB = "DROP DATABASE IF EXISTS contactlist";
	private static String createDB = "CREATE DATABASE IF NOT EXISTS contactlist";
	
	private static void insert(Connection connection) throws SQLException {
		
			PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts(Name, Phone_Number, Email_ID, Address) VALUES(?, ?, ?, ?)");
			
			do {
				
				System.out.print("Enter Name : ");
				statement.setString(1, s.nextLine());
				
				System.out.print("Enter Phone_Number : ");
				statement.setLong(2, s.nextLong());
				s.nextLine();
				
				System.out.print("Enter Email_id : ");
				statement.setString(3, s.nextLine());
				
				System.out.print("Enter Address : ");
				statement.setString(4, s.nextLine());
				
				int rowsAffected = 0;
				rowsAffected = statement.executeUpdate();
				System.out.println(rowsAffected + " row(s) affected.");
				
				System.out.print("Do you want to insert more data? ");
				String str = s.nextLine();
				if(str.charAt(0)=='n' || str.charAt(0)=='N')
					break;
				
			}while(true);
		
	}
	
	private static void delete(Connection connection) throws SQLException {
		
			PreparedStatement statement = connection.prepareStatement("DELETE FROM contacts WHERE Name = ?");
			
			do {
				
				System.out.print("Enter Name : ");
				statement.setString(1, s.nextLine());
				
				int rowsAffected = 0;
				rowsAffected = statement.executeUpdate();
				System.out.println(rowsAffected + " row(s) affected.");
				
				System.out.print("Do you want to delete more data? ");
				String str = s.nextLine();
				if(str.charAt(0)=='n' || str.charAt(0)=='N')
					break;
				
			}while(true);
		
	}
	
	private static void updateName(Connection connection) throws SQLException {
		
			System.out.println();
			
			PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET Name = ? WHERE Contact_ID = ?");
			
			System.out.print("Enter Contact ID : ");
			statement.setInt(2, s.nextInt());
			s.nextLine();
			
			System.out.print("Enter new name : ");
			statement.setString(1, s.nextLine());
			
			int rowsAffected = 0;
			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " row(s) affected");
		
			System.out.println();
	
	}
	
	private static void updatePhoneNumber(Connection connection) throws SQLException {
		
			System.out.println();
			
			PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET Phone_Number = ? WHERE Contact_ID = ?");
			
			System.out.print("Enter Contact ID : ");
			statement.setInt(2, s.nextInt());
			s.nextLine();
			
			System.out.print("Enter new Phone Number : ");
			statement.setLong(1, s.nextInt());
			
			int rowsAffected = 0;
			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " row(s) affected");
			
			System.out.println();
		
	}

	private static void updateEmailID(Connection connection) throws SQLException {
		
			System.out.println();
			
			PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET Email_ID = ? WHERE Contact_ID = ?");
			
			System.out.print("Enter Contact ID : ");
			statement.setInt(2, s.nextInt());
			s.nextLine();
			
			System.out.print("Enter new Email ID : ");
			statement.setString(1, s.nextLine());
			
			int rowsAffected = 0;
			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " row(s) affected");
			
			System.out.println();
			
	}

	private static void updateAddress(Connection connection) throws SQLException {
		
			System.out.println();
			
			PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET Address = ? WHERE Contact_ID = ?");
			
			System.out.print("Enter Contact ID : ");
			statement.setInt(2, s.nextInt());
			s.nextLine();
			
			System.out.print("Enter new Address : ");
			statement.setString(1, s.nextLine());
			
			int rowsAffected = 0;
			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " row(s) affected");
			
			System.out.println();

	}
	
	private static void update(Connection connection) throws SQLException {
		
		System.out.println();
		
		System.out.print("1-Change Name\n2-Change Phone Number\n3-Change Email ID\n4-Change Address\nEnter your choice : ");
		int choice = s.nextInt();
		s.nextLine();
		
		switch(choice) {
		
		case 1:
			updateName(connection);
			break;
			
		case 2:
			updatePhoneNumber(connection);
			break;
			
		case 3:
			updateEmailID(connection);
			break;
			
		case 4:
			updateAddress(connection);
			break;
			
		default:
			System.out.println("Invalid choice");
			
		}
		
		System.out.println();
		
	}
	
	private static int printInstructions() {
		
		System.out.print("1-Insert\n2-Delete\n3-Update\n4-Commit\n5-RollBack\n6-Print resultset\n0-Exit\nEnter your choice : ");
		
		int choice = s.nextInt();
		s.nextLine();
		
		return choice;
		
	}
	
	private static void printResultSet(Statement statement) throws SQLException {

			ResultSet resultset = statement.executeQuery("SELECT * FROM contacts");
			
			while(resultset.next())
				System.out.println(resultset.getInt("Contact_ID") + " " +
								   resultset.getString("Name") + " " + 
								   resultset.getLong("Phone_Number") + " "+ 
								   resultset.getString("Email_ID") + " " + 
								   resultset.getString("Address"));
			
	}
	
	private static void choice(int choice, Connection connection, Statement statement) throws SQLException {
		
		System.out.println();
		
		switch(choice) {
		
		case 1:
			insert(connection);
			break;
			
		case 2:
			delete(connection);
			break;
			
		case 3:
			update(connection);
			break;
			
		case 4:
			connection.commit();
			System.out.println("Transaction commited");
			break;
		
		case 5:
			connection.rollback();
			System.out.println("Transaction rolledback");
			break;
			
		case 6:
			printResultSet(statement);
			break;
			
		case 0:
			System.exit(0);
			
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		int rowsAffected;
		
		try {
			
			System.out.print("Enter url: ");
			url = s.next();
			
			System.out.print("Enter user: ");
			user = s.next();
			
			System.out.print("Enter password: ");
			password = s.next();
			
			Connection connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			
			Statement statement = connection.createStatement();
			
			rowsAffected = statement.executeUpdate(dropDB);
			if(rowsAffected==1) {
				System.out.println("Database dropped");
				rowsAffected = 0;
			}
			
			rowsAffected = statement.executeUpdate(createDB);
			if(rowsAffected==1) {
				System.out.println("Database created");
				rowsAffected = 0;
			}
			
			statement.executeUpdate("USE contactlist");
			
			statement.executeUpdate("CREATE TABLE contacts "
					  + "("
					  + "	Contact_ID INTEGER AUTO_INCREMENT,"	
					  + "	Name VARCHAR(255) NOT NULL,"
					  + "	Phone_Number BIGINT NOT NULL,"
					  + "	Email_ID VARCHAR(255), "
					  + "	Address VARCHAR(255),"
					  + "PRIMARY KEY(Contact_ID),"
					  + "UNIQUE KEY(Phone_Number))");
			System.out.println("contacts table created");
	
			while(true) {
				int choice = printInstructions();
				choice(choice, connection, statement);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
