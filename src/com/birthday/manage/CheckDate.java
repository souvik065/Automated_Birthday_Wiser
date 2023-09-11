package com.birthday.manage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Wisher.CP;

public class CheckDate {
	private static Connection con = CP.createC();
	
	public static void BirthdayCheck()
	{
		String wishes[];
		int rows;
		PreparedStatement ps;
		// Get the current date
        LocalDate currentDate = LocalDate.now();
        // Calculate the date that is one day ahead of the current date
        LocalDate tomorrow = currentDate.plusDays(1);
        
		try {
			
			String q="Select * from birthdays";
			
			Statement stmt=con.createStatement();
			ResultSet set= stmt.executeQuery(q);
			while(set.next())
			{
				int id=set.getInt(1);
				String name=set.getString(2);
				String birthdateStr=set.getString(3);
				String wishesStr=set.getString("wishes");
				
				
				if(wishesStr == null)
            	{
            		wishesStr = " ";
            	}
				
				
				// Parse the user's birthdate
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate birthdate = LocalDate.parse(birthdateStr, formatter);
		        
		        // Check if the user's birthday is tomorrow
		        if (birthdate.getMonth() == tomorrow.getMonth() && birthdate.getDayOfMonth() == tomorrow.getDayOfMonth()) {
		            
		        	if(wishesStr!=null&&wishesStr.contains(String.valueOf(tomorrow.getYear())))
		            {
		            	
		            }else {
		            	//String to ="23mcab03@gmail.com";
		            	//String from = "bhattacharyasouvik065@gmail.com";
		            	//String subject = "Birthday Wishing";
		            	//String text="Happy Birthday "+name;
		            	//boolean b = EmailSender.sendEmail(to,from,subject,text);
		            	/*if(b) {
		            		System.out.println("Email is sent Successfully.");
		            	}else {
		            		System.out.println("Something went wrong while sending email.");
		            	}*/
		            	System.out.println("Tomorrow has "+name+"'s Birthday");
		            	
		            	wishesStr = wishesStr + ","+String.valueOf(currentDate.getYear());
		            	
		                // Remove the leading comma
		                wishesStr = wishesStr.replaceFirst(",", " ");
		               
		                
		            	
		            	String updateQuery = "Update birthdays set wishes = ? where personId = "+id;
		            	
		            	ps = con.prepareStatement(updateQuery);
						ps.setString(1,wishesStr);
						
						rows = ps.executeUpdate();
		            }
		            
		        } else {
		            
		        }
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

        

        

        
	}

}
