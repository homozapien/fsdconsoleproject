package com.simplilearn.fsd.helper;

import java.util.Scanner;

public class Utility 
{
  
	public static void displayWelcomeDetails()
	{
		   System.out.println("******************************************************************************************");	
		   System.out.println(String.format("\tName:%s",   Constant.NAME));
		   System.out.println(String.format("\tEmail:%s",  Constant.EMAIL));
		   System.out.println(String.format("\tCourse:%s", Constant.COURSE));
		   System.out.println(String.format("\tCohort:%s", Constant.COHORT));
		   System.out.println(String.format("\tDescription:%s", Constant.APPTYPE));
		   System.out.println("******************************************************************************************");
		
	}
	
	public static void displaySummaryInfo()
	{
		System.out.println("\n***************************** Brief Execution Flow **********************************");
		System.out.println("\nStep 1: Root Path is created based on command-line argument during startup!");
		System.out.println("\nStep 2: If no argument is provided, the java user directory forms the root path ");
		System.out.println("\nStep 3: Up to 6 files may initially be created in a folder of the root path !");
		System.out.println("\nStep 4: The current state of working directory may be displayed intermittently!");
		System.out.println("\nStep 5: Execution is heavily reliant on console inputs by the user");
		System.out.println("*****************************************************************************************");
	}
	
	public static void promptForContinuation()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n\n"+Constant.PROMPT);
		String input = scanner.next();
		
		if(!input.toUpperCase().equals("Y"))
		{
			   scanner.close();
			   System.out.println("Application is exited, Good Bye!");
			   System.exit(-1);
		}
		
	}

	public static int promptForIntegerInput()
	{
		int count = Constant.INPUT_COUNT;
		
		Scanner scanner = new Scanner(System.in);	
		
		System.out.println("\n" + Constant.FILENUM);
		
		while (count-- > 0 && scanner.hasNextInt())
		{
			int input = scanner.nextInt();
			
			if(input >= Constant.MIN_SEED_FILES && input <= Constant.MAX_SEED_FILES)
			{
				return input;
			}
			else
			{
				System.out.println("Input value out of range, you have " + count + " failed attempt(s) before system exit! ");
			}			
		}
		
		return 0;
		
	}
	
	public static String promptForConsoleFileName()
	{
        Scanner scanner = new Scanner(System.in);	
		
		System.out.println("\n" + Constant.FILENAME);
	
		return scanner.next();
			
	}
	
	
	public static String promptForUserAction()
	{
		int count = Constant.INPUT_COUNT;
		
		Scanner scanner = new Scanner(System.in);	
		
		System.out.println("\n" + getConsoleInputGuide());
		
		while (count-- > 0 && scanner.hasNext())
		{
			String input = scanner.next();
			
			if(checkFileOperationEnum(input))
			{
				return input;
			}
			else
			{
				System.out.println("Invalid user input detected, you have " + count + " failed attempt(s) before system exit! ");
			}		
		}
		
		return Constant.FileOperation.EXIT.toString();
		
	}
	
	private static boolean checkFileOperationEnum(String input) {

	    for (Constant.FileOperation option : Constant.FileOperation.values()) {
	        if (option.name().equalsIgnoreCase(input)) {
	            return true;
	        }
	    }

	    return false;
	}
	
	private static String getConsoleInputGuide()
	{
		StringBuffer buffer = new StringBuffer("\n\n********************** Console User Input Guide *************************");
		buffer.append(String.format("\n %s to add a user specified file to the root folder", Constant.FileOperation.ADD))
		      .append(String.format("\n %s to delete a user specified file from the root folder", Constant.FileOperation.DELE))
		      .append(String.format("\n %s to search a user for specified file from the root folder", Constant.FileOperation.SEAR))
		      .append(String.format("\n %s to display files in the root folder",    Constant.FileOperation.DISP))
		      .append(String.format("\n %s to retrieve a user specified file from the root folder", Constant.FileOperation.RETR))
		      .append(String.format("\n %s to exit or terminate application", Constant.FileOperation.EXIT));
		
		return buffer.toString();
	}
	
}
