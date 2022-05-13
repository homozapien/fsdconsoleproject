package com.simplilearn.fsd.helper;

public class Constant 
{
   public static final String NAME     =  "\t\tBakau Onafuwa";
   public static final String EMAIL    =  "\t\tbakau.onafuwa@softgineer.com";
   public static final String COURSE   =  "\t\tPG FSD Implement OOPS using JAVA with Data Structures and Beyond ";
   public static final String COHORT   =  "\t\tMARCH 2022 ";
   public static final String APPTYPE  =  "\tInteractive Console Application";
   public static final String PROMPT   =  "Enter y or Y to continue execution, any other character to exit application";
   public static final String FILENAME =  "Enter the desired filename to Add or Delete or Retrieve to/from the working directory; ";
   public static final String FILEXTENSION = "Allowed file extensions here: .tmp or .txt or .log";
   public static final String FILENUM  =  "Enter the number of random files to create initially in working directory, valid range 0 to 6 ";
   public static final String ROOT     =  System.getProperty("user.dir");
   public static final String MYPATH   =  "/workingpath";
   public static final String FILE_SUFFIX = ".txt";
   public static final int INPUT_COUNT = 3; 
   public static final int MIN_SEED_FILES = 0; 
   public static final int MAX_SEED_FILES = 6; 
   
   
   
   enum FileOperation
   {
	   ADD,       //Command to Add a file to a working directory
	   DELE,     //Command to delete a file from working directory
	   DISP,     //COmmand to retrieve a file and display it is content
	   SEAR,	//Command to Check if a file exist in Working directory
	   RETR,	//Command to retrieve all files from the working directory  
	   EXIT    //Command to terminate or exit application
   }
   
  
   
}
