package com.simplilearn.fsd.helper;

public class Constant 
{
   public static final String NAME     =  "\t\tBakau Onafuwa";
   public static final String EMAIL    =  "\t\tbakau.onafuwa@softgineer.com";
   public static final String COURSE   =  "\t\tPG FSD Implement OOPS using JAVA with Data Structures and Beyond ";
   public static final String COHORT   =  "\t\tMARCH 2022 ";
   public static final String APPTYPE  =  "\tInteractive Console Application";
   public static final String PROMPT   =  "Enter y or Y to continue execution, any other character to exit application";
   public static final String FILENAME   =  "Enter the desired filename to Add or Delete or Retrieve to/from the working directory ";
   public static final String FILENUM  = "Enter the number of random files to create initially in working directory, valid range 0 to 6 ";
   public static final String ROOT     =  System.getProperty("user.dir");
   public static final String MYPATH   =  "/foobar";
   public static final String FILE_PREFIX = "demo";
   public static final String FILE_SUFFIX = ".txt";
   public static final int INPUT_COUNT = 3; //used to control inavlid user console inuput 
   public static final int MIN_SEED_FILES = 0; //Note: negative Value is not validated
   public static final int MAX_SEED_FILES = 6; //value to keep small for prototyping
   
   
   
   enum FileOperation
   {
	   ADD,       //for Addition of a file
	   DELE,     //for deletion of a file
	   DISP,     //for display of all file
	   SEAR,	//check if a file exist in folder
	   RETR,	//Retrieve a file  
	   EXIT    //terminate application
   }
   
  
   
}
