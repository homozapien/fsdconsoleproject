package com.simplilearn.fsd.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.TreeMap;

import com.simplilearn.fsd.helper.Commander;
import com.simplilearn.fsd.helper.Constant;
import com.simplilearn.fsd.helper.Utility;

public class FileConsoleApp {
	private static Commander commander;
	private static String workingPath;

	public static void main(String[] args) {

		try {
			// Display welcome Message
			Utility.displayWelcomeDetails();

			// display usage information
			Utility.displaySummaryInfo();

			// Do you want to continue the execution, a redundant question
			Utility.promptForContinuation();

			String workingDir = args.length == 1 ? args[0] : Constant.ROOT;

			commander = new Commander(workingDir);

			workingPath = createRootDirectory();

			System.out.println("\nWorking Root directory will be created in: ");
			System.out.println("\"" + workingPath + "\"");

			// enter the number of random files to initially create
			int tempfileCount = Utility.promptForIntegerInput();

			if (tempfileCount > 0) {
				createTempFilesInDirectory(tempfileCount);
			}

			// Display file in working directory
			displaySortedFileNames();

			String userInput = "";
			do {
				userInput = Utility.promptForUserAction();
				processUserConsoleInput(userInput);
			} while (!userInput.equalsIgnoreCase("EXIT"));

			if (userInput.equalsIgnoreCase("EXIT")) {
				System.out.println(
						"****************************************************************************************");
				System.out.println(" Execution will be terminated, System will exit! Bye bye...... ");
			}
		} catch (Exception exception) {
			System.err.println(exception);
			System.out.println("Execution will be terminated, System will exit! Bye bye...... ");
			System.exit(-1);
		}

	}

	private static String createRootDirectory() throws Exception {
		String demopath = null;
		try 
		{
			demopath = commander.createRootDirectory();

		} 
		catch (IOException exception) 
		{
			throw new Exception(exception);
		}

		return demopath;

	}

	private static void createTempFilesInDirectory(int tempfileCount) throws Exception {
		try {
			commander.createDefaultFilesInDirectory(tempfileCount);
		} 
		catch (IOException exception) 
		{
			System.err.println(exception);
			//throw new Exception(exception);
		}
	}

	private static void displaySortedFileNames() throws Exception {
		try
		{
			TreeMap<String, Path> fileNameMap = commander.retrieveSortedFileNames();

			if (fileNameMap.isEmpty()) {
				System.out.println("\nWorking directory is empty, 0 file found! ");
			} else {
				System.out.printf("\nWorking directory contains %d files shown as sorted list: \n", fileNameMap.size());
				// Collections.sort(fileNameList);
				fileNameMap.forEach((key, value) -> {
					System.out.println(key + "  -   " + value);
				});
			}
		} catch (Exception exception) {
			throw new Exception(exception);
		}

	}

	private static void processUserConsoleInput(String input) throws Exception {

		try {
			switch (input.toUpperCase()) {
			case "ADD":
				System.out.println("**********************ADDITION ACTION IN PROGRESS*******");

				String filenameadd = getFileNameFromConsoleInput();
				if (filenameadd != null) {
					commander.addFileToWorkingDirector(filenameadd);
					System.out.println("File successfully created in workign directory! ");
					System.out.println("Enter thd DISP input to view the latest content of working directory ");
				} 
				else 
				{
					System.out.println("\nUnable to process Addition command, please try this operation again with valid filename");
				}

				break;
			case "DELE":
				System.out.println("**********************DELETION ACTION IN PROGRESS*******");

				String filenamedelete = getFileNameFromConsoleInput();
				if (filenamedelete != null) {
					commander.deleteFileFromDirectory(filenamedelete);
					System.out.println("File successfully deleted in working directory! ");
					System.out.println("Enter thd DISP input to view refreshed working directory ");

				} 
				else 
				{
					System.out.println("\nUnable to process Deletion command, please try this operation again with valid filename");
				}

				break;
			case "DISP":
				System.out.println("**********************DISPLAY ACTION IN PROGRESS*******");

				displaySortedFileNames();

				break;
			case "SEAR":
				System.out.println("**********************SEARCH ACTION IN PROGRESS*******");

				String filenamesearch = getFileNameFromConsoleInput();
				if (null != filenamesearch) {

					if (commander.checkIfFileExistInDirectory(filenamesearch))
						System.out.println(
								"The entered filename " + filenamesearch + " exists in the working directory ");
					else
						System.out.println(
								"The entered filename " + filenamesearch + " does not exist in the working directory ");
				} 
				else 
				{
					System.out.println("\nUnable to process Search command, please try this operation again with valid filename");
				}

				break;
			case "RETR":
				System.out.println("**********************RETRIEVAL ACTION IN PROGRESS*******");
				String filename2Retr = Utility.promptForConsoleFileName();
				if (null != filename2Retr) {

					Path file =  commander.retrieveFileFromDirectory(filename2Retr);
					
					if(file != null)
					{
						System.out.println("*************** Start Show Content of File " + filename2Retr + " *****************");
						
						          printFileContent( file);
						          
						System.out.println("*************** End of Show Content of File " + filename2Retr + " *****************");
					}										
					else
					{
						System.out.println("The entered filename " + filename2Retr + " does not exist in the working directory and can't be retrieved ");
								
					}
				} 
				else 
				{
					System.out.println("\nUnable to process Search command, please try this operation again with valid filename");
				}
				

				break;
			default:

			}
		} catch (NoSuchFileException nsf) 
		{
			System.err.println(nsf);
			System.err.println("Try the command again with valid user input!");
		} 
		catch (DirectoryNotEmptyException dne) 
		{
			System.err.println(dne);
			System.err.println("Try the command again with valid user input!");
		} catch (FileAlreadyExistsException exception) 
		{
			System.err.println(exception);
			System.err.println("Try the command again with valid user input!");
		} 
		catch (Exception exception) 
		{
			throw exception;
		}

	}

	private static void printFileContent(final Path file)
	{
	
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}
	
	private static String getFileNameFromConsoleInput() {
		String filename = Utility.promptForConsoleFileName();
		if (null == filename) {
			return null;
		}
		return workingPath + FileSystems.getDefault().getSeparator() + filename;
	}

}
