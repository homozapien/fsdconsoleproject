package com.simplilearn.fsd.main;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.simplilearn.fsd.helper.Commander;
import com.simplilearn.fsd.helper.Constant;
import com.simplilearn.fsd.helper.Utility;



public class FileHandlingConsoleApp {

private static Commander commander;
	
	public static void main(String[] args) 
	{
	   
		//Display welcome Message
		Utility.displayWelcomeDetails();
		
		//display usage information
		Utility.displaySummaryInfo();
		
		//Do you want to continue the execution?
		Utility.promptForContinuation();
		
		System.out.println("....You chose to continue to execution......  ");
		
		String workingDir = args.length > 1 ? args[0] : Constant.ROOT;
		
		commander = new Commander(workingDir); 
		
		String demopath = createRootDirectory();
		
		System.out.println("\nWorking Root directory will be created in: " );
		System.out.println("\"" + demopath +"\"");
		
		//enter the number of random files to initially create
		int tempfileCount = Utility.promptForIntegerInput(Constant.FILENUM);
		
		if(tempfileCount > 0)
		{
			createTempFilesInDirectory(tempfileCount);
		}	
		
		//Display file in working directory		
		displaySortedFileNames();
		
		String userInput = "";
		do
		{
			userInput = Utility.promptForUserAction();
			
			processUserInput(userInput);
		}
		while(!userInput.equalsIgnoreCase("EXIT"));
		
		if(userInput.equalsIgnoreCase("EXIT"))
		{
		System.out.println("******************************************************************************************");
		System.out.println(" System will exit! Bye bye...... ");
		System.exit(-1);
		
		}	
			
		
				
	}
	
	private static String createRootDirectory()
	{
		String demopath = null;
		try 
		{
			 demopath = commander.createRootDirectory();
			
		} 
		catch (IOException exception)
		{
			exception.printStackTrace(System.out);
		}
		
		return demopath;
		
	}
	
	private static void createTempFilesInDirectory(int tempfileCount)
	{
		try
		{
		commander.createTempFilesInDirectory(tempfileCount);
		} 
		catch (IOException exception)
		{
			exception.printStackTrace(System.out);
		}
	}
	
	
	private static void displaySortedFileNames()
	{
		try
		{
			List<String> fileNameList=  commander.retrieveSortedFileNames();
			
			if(fileNameList.isEmpty())
			{
			   System.out.println("\nWorking directory is empty, 0 file found");	
			}
			else
			{
				System.out.printf("\nWorking directory contains %d files shown as sorted list: ", fileNameList.size());
				Collections.sort(fileNameList);
				//System.out.println("\n");
				fileNameList.forEach(System.out::println);
			}
		} 
		catch (IOException exception)
		{
			exception.printStackTrace(System.out);
		}
		
		
	}
	
	
	private static void processUserInput(String input)
	{
		switch(input.toUpperCase())
		{
		case "ADD":
			System.out.println("**********************ADDITION ACTION IN PROGRESS*******");
			break;
		case "DELE":
			System.out.println("**********************DELETION ACTION IN PROGRESS*******");
			break;
		case "DISP":
			System.out.println("**********************DISPLAY ACTION IN PROGRESS*******");
			displaySortedFileNames();
			break;
		case "SEAR":
			System.out.println("**********************SEARCH ACTION IN PROGRESS*******");
			break;
		case "RETR":
			System.out.println("**********************RETRIEVAL ACTION IN PROGRESS*******");
			break;
			
		default:
				
			
		
		}
		
	}

}
