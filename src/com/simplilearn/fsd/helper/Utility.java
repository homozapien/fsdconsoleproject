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
		System.out.println("\nStep 2: If no argument is provided, the java user directory forms the working path ");
		System.out.println("\nStep 3: Up to 6 files may initially be created in a folder of the working path !");
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
				System.out.println("Input value out of range, you have " + count + " attempt(s) before the default value is used! ");
			}			
		}
		
		return 0;
		
	}
	
	public static String promptForConsoleFileName()
	{
        Scanner scanner = new Scanner(System.in);
        int count = Constant.INPUT_COUNT;
		
		System.out.println("\n" + Constant.FILENAME);
		System.out.println(Constant.FILEXTENSION);
		
		while (count-- > 0 && scanner.hasNext())
		{
	
            String input = scanner.next();
			
			if(validateStringFilenames(input))
			{
				return input;
			}
			else
			{
				System.out.println("Invalid filename entered, you have " + count + " failed attempt(s) before system exit! ");
			}
		}
		
		return null;
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
	
	private static boolean validateStringFilenames(String filename)
	{
		 String REGEX_PATTERN = "^[A-za-z0-9]{1,255}\\.(tmp|txt|log)$";
          
		 if (filename == null || filename.isEmpty()) 
		 {
		        return false;
		 }
		 {
		    return filename.toLowerCase().matches(REGEX_PATTERN);
		 }
		
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
		buffer.append(String.format("\nEnter %s to add a user specified file to the working folder", Constant.FileOperation.ADD))
		      .append(String.format("\nEnter %s to delete a user specified file from the working folder", Constant.FileOperation.DELE))
		      .append(String.format("\nEnter %s to search a user specified file in the working folder", Constant.FileOperation.SEAR))
		      .append(String.format("\nEnter %s to rertrieve a user specified file and display it contents",    Constant.FileOperation.DISP))
		      .append(String.format("\nEnter %s to show all the files in the working working folder", Constant.FileOperation.RETR))
		      .append(String.format("\nEnter %s to exit or terminate application", Constant.FileOperation.EXIT));
		
		return buffer.toString();
	}
	
}
