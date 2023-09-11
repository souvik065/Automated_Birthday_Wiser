package com.birthday.manage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


import Wisher.*;

public class PersonDao {
	private static Connection con = CP.createC();
	static Scanner scanner = new Scanner(System.in);
	
	public static boolean insertPersonToDB(Person st)
	{
		boolean f = false;
		try {
			// JDBC Code
			Connection con = CP.createC();
			String q="Insert Into birthdays(Name,Birthdate) values(?,?)";
			
			PreparedStatement  pstmt = con.prepareStatement(q);
			
			// set the value of parameter
			pstmt.setString(1,st.getPersonName());
			pstmt.setString(2,st.getBirthdate());
			
			//execute...
			pstmt.executeUpdate();
			f=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
		
		
	}
	
	public static void showAllPersons() {

		
		try {
			// JDBC Code
			
			String q="Select * from birthdays";
			
			Statement stmt=con.createStatement();
			ResultSet set= stmt.executeQuery(q);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			while(set.next())
			{
				int id=set.getInt(1);
				String name=set.getString(2);
				String birthdate=set.getString(3);
				String wishes=set.getString("wishes");
				
				System.out.print("ID : "+id+" | ");
				System.out.print("Name : "+name+" | ");
				System.out.print("Birthdate : "+birthdate+" | ");
				System.out.print("Wishes : "+wishes+" | ");
				System.out.println();
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static boolean deletePerson(int personId) {
		
		boolean f = false;
		try {
			// JDBC Code
			Connection con = CP.createC();
			String q="Delete From birthdays where personId=?";
			
			PreparedStatement  pstmt = con.prepareStatement(q);
			
			// set the value of parameter
			pstmt.setInt(1,personId);
			
			//execute...
			pstmt.executeUpdate();
			f=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void updatePerson(int personId) throws SQLException {
		
		int rows;
		PreparedStatement ps;
		String sql = "Select * from birthdays where personId="+personId;
		
		Statement statement = con.createStatement();
		
		ResultSet result = statement.executeQuery(sql);
		
		if(result.next())
		{
			// fetching all the values
			int perId = result.getInt("personId");
			String name = result.getString("Name");
			String birthdate = result.getString("Birthdate");
			String email = result.getString("email");
			
			//Printing all the fields
			System.out.println("personId : "+perId);
			System.out.println("Name : "+name);
			System.out.println("Birthdate : "+birthdate);
			System.out.println("Email : "+email);
			
			System.out.println("What do you want to update ? ");
			System.out.println("1. Name ");
			System.out.println("2. Birthdate ");
			System.out.println("2. Email ");
			
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			// SQL Query to be performed in this switch case as the user Requirements.
			String sqlQuery = "Update birthdays set ";
			switch(choice) {
			case 1:
				// Update Person's Name
				System.out.print("Enter new name");
				String newName = scanner.nextLine();
				sqlQuery = sqlQuery + "Name = ? where personId = "+personId;
				ps = con.prepareStatement(sqlQuery);
				ps.setString(1,newName);
				
				rows = ps.executeUpdate();
				if(rows > 0)
				{
					System.out.println("Record update successfully.");
				}
				break;
			case 2:
				// Update Person's Birthdate.
				
				// Prompt the user for a date input
                System.out.print("Enter a New date (yyyy-MM-dd): ");
                String newBirthdate = scanner.nextLine();

                // Define a DateTimeFormatter to parse the input into a LocalDate object
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                try {
                    // Parse the user input into a LocalDate object
                    LocalDate bdate = LocalDate.parse(newBirthdate, formatter);
                    System.out.println("You entered: " + bdate);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                }

				
				sqlQuery = sqlQuery + "Birthdate = ? where personId = "+personId;
				ps = con.prepareStatement(sqlQuery);
				ps.setString(1,newBirthdate);
				
				rows = ps.executeUpdate();
				if(rows > 0)
				{
					System.out.println("Record update successfully.");
				}
				
				break;
			default:
				break;
			}
			
		}else {
			System.out.println("Record not found...");
		}
	}

	
}
