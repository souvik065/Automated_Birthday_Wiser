package Wisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.birthday.manage.CheckDate;
import com.birthday.manage.Person;
import com.birthday.manage.PersonDao;



public class MainProg {
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		//birthdayCheckSchedular.RunSchedule();
		//Thread backgroundThread = new Thread(birthdayCheckSchedular::RunSchedule);
        //backgroundThread.start();	
        while (true) {
            System.out.println("PRESS 1 to ADD Birthday Person");
            System.out.println("PRESS 2 to DELETE Birthday Person");
            System.out.println("PRESS 3 to Display All Birthday Persons");
            System.out.println("PRESS 4 to Update Birthdays");
            System.out.println("PRESS 5 to UpComming Birthdays");
            System.out.println("PRESS 6 to Exit.");
            
            try {
                int c = Integer.parseInt(br.readLine());

                if (c == 1) {
                    // add student...
                	
                	// Prompt the Person's Name
                	System.out.print("Enter Person Name : ");
                	String personName = br.readLine();

                    // Prompt the user for a date input
                    System.out.print("Enter a date (yyyy-MM-dd): ");
                    String birthdate = scanner.nextLine();

                    // Define a DateTimeFormatter to parse the input into a LocalDate object
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    

                    try {
                        // Parse the user input into a LocalDate object
                        LocalDate date = LocalDate.parse(birthdate, formatter);
                        System.out.println("You entered: " + birthdate);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                    
                    // Prompt the Person's Email
                	System.out.print("Enter Person Email : ");
                	String email = br.readLine();

                    Person p = new Person(personName,birthdate,email);
                    boolean answer = PersonDao.insertPersonToDB(p);
                    if(answer)
                    {
                    	System.out.println("Student is added successfully");
                    }else {
                    	System.out.println("Something went wrong try again...");
                    }
                    System.out.println(p);
                  
                } else if (c == 2) {
                	// Delete Person
                    System.out.println("Enter Person's ID to Delete");
                    int personId=Integer.parseInt(br.readLine());
                    boolean f=PersonDao.deletePerson(personId);
                    if(f)
                    {
                    	System.out.println("Deleted...");
                    }else {
                    	System.out.println("Somethingwent wrong...");
                    }
                    
                } else if (c == 3) {
                    // display students
                	PersonDao.showAllPersons();
               
                } else if (c == 4) {
                	System.out.println("Enter Person's ID to Update");
                    int personId=Integer.parseInt(br.readLine());
                	try {
						PersonDao.updatePerson(personId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                } else if(c==5){
                	// UpComming Birthday Wisher
                	CheckDate.BirthdayCheck();
                }else if(c==6){
                	// exit
                    break;
                }else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
		
		}
		 

	}
}
