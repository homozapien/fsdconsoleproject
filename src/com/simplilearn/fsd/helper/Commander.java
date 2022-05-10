package com.simplilearn.fsd.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.simplilearn.fsd.abstraction.*;


public class Commander {
	private FileController fileHandler;

	public Commander(final String rootPath) {
		this.createFileHandler(rootPath);
	}

	public String createRootDirectory() throws IOException {
		try {
			return this.fileHandler.createRootDirectory();
		} catch (IOException e) {
			throw e;
		}
	}

	public void createTempFilesInDirectory(int tempfileCount) throws IOException 
	{
		try {
			((FileHandler) (this.fileHandler)).createTempFilesInDirectory(tempfileCount);
		} catch (IOException e) {
			throw e;
		}

	}

	public void addFileToWorkingDirector(String filepath) throws IOException 
	{
		try 
		{
			this.fileHandler.addFileToFolder(Paths.get(filepath));
		} 
		catch (IOException e) {
			throw e;
		}

	} 
	
	public boolean checkIfFileExistInDirectory(String filepath) 
	{
			return this.fileHandler.checkIfFileExistInDirectory(Paths.get(filepath));
	}
	
	public void deleteFileFromDirectory(String filepath) throws IOException 
	{
		try 
		{
			this.fileHandler.deleteFileInFolder(Paths.get(filepath));
		} 
		catch (IOException e) {
			throw e;
		}

	}
	
	public List<String> retrieveSortedFileNames() throws IOException {
		try
		{
			return ((FileHandler) (this.fileHandler)).retrieveSortedFileNames();
		} 
		catch (IOException e) {
			throw e;
		}
	}

	private void createFileHandler(String rootPath) 
	{
		this.fileHandler = new FileHandler(rootPath);

	}

}
