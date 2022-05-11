package com.simplilearn.fsd.main;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.util.Collections;
import java.util.List;

import com.simplilearn.fsd.helper.Commander;
import com.simplilearn.fsd.helper.Constant;
import com.simplilearn.fsd.helper.Utility;

public class FileConsoleApp {
	private static Commander commander;
	private static String workingPath;

	public static void main(String[] args) {

		try
		{
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
		} 
		catch (Exception exception) 
		{
			System.err.println(exception.getMessage());
			System.out.println("Execution will be terminated, System will exit! Bye bye...... ");
			System.exit(-1);			
		}

	}

	private static String createRootDirectory() throws Exception {
		String demopath = null;
		try {
			demopath = commander.createRootDirectory();

		} catch (IOException exception) {
			throw new Exception(exception);
		}

		return demopath;

	}

	private static void createTempFilesInDirectory(int tempfileCount) throws Exception {
		try
		{
			commander.createDefaultFilesInDirectory(tempfileCount);
		} 
		catch (IOException exception) 
		{
			throw new Exception(exception);
		}
	}

	private static void displaySortedFileNames() throws Exception {
		try {
			List<String> fileNameList = commander.retrieveSortedFileNames();

			if (fileNameList.isEmpty()) {
				System.out.println("\nWorking directory is empty, 0 file found! ");
			} else {
				System.out.printf("\nWorking directory contains %d files shown as sorted list: \n",
						fileNameList.size());
				Collections.sort(fileNameList);
				fileNameList.forEach(System.out::println);
			}
		} catch (Exception exception) {
			throw new Exception(exception);
		}

	}

	private static void processUserConsoleInput(String input) throws Exception {

		switch (input.toUpperCase()) {
		case "ADD":
			System.out.println("**********************ADDITION ACTION IN PROGRESS*******");
			try 
			{
				commander.addFileToWorkingDirector(getFileNameFromConsoleInput());
				System.out.println("File successfully created in workign directory! ");
				System.out.println("Enter thd DISP input to view the latest content of working directory ");
			}
			catch (FileAlreadyExistsException exception) 
			{
				exception.printStackTrace(System.out);
			}
			catch (Exception exception) 
			{
				throw exception;
			}
			break;
		case "DELE":
			System.out.println("**********************DELETION ACTION IN PROGRESS*******");
			try {
				commander.deleteFileFromDirectory(getFileNameFromConsoleInput());
				System.out.println("File successfully deleted in working directory! ");
				System.out.println("Enter thd DISP input to view refreshed working directory ");
			} 
			catch (Exception exception) 
			{
				throw exception;
			}
			break;
		case "DISP":
			System.out.println("**********************DISPLAY ACTION IN PROGRESS*******");
			try 
			{
				displaySortedFileNames();
			} 
			catch (Exception exception) 
			{
				throw exception;
			}
			break;
		case "SEAR":
			System.out.println("**********************SEARCH ACTION IN PROGRESS*******");
			try {
				String filename = getFileNameFromConsoleInput();
				boolean found = commander.checkIfFileExistInDirectory(filename);
				if (found)
					System.out.println("The entered filename " + filename + " exists in the working directory ");
				else
					System.out
							.println("The entered filename " + filename + " does not exist in the working directory ");
			} catch (Exception exception) {
				throw exception;
			}
			break;
		case "RETR":
			System.out.println("**********************RETRIEVAL ACTION IN PROGRESS*******");
			try {
				String filename2Retr = Utility.promptForConsoleFileName();

			} catch (Exception exception) {
				throw exception;
			}
			break;
		default:

		}
		

	}

	private static String getFileNameFromConsoleInput() {
		String filename = Utility.promptForConsoleFileName();
		return workingPath + FileSystems.getDefault().getSeparator() + filename + Constant.FILE_SUFFIX;
	}

}
