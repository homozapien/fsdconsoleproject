package com.simplilearn.fsd.abstraction;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;


public interface FileController 
{  
	public String createRootDirectory() throws IOException;
	
	public void addFileToFolder(Path filepath) throws FileAlreadyExistsException, IOException;
	
	public boolean checkIfFileExistInDirectory(Path filepath);
	
	public void deleteFileInFolder(Path filepath) throws NoSuchFileException, DirectoryNotEmptyException, IOException;	
	
	public File retrieveFileFromDirectory(Path filepath) throws IOException;
}
