package com.simplilearn.fsd.helper;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

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

	public void createDefaultFilesInDirectory(int tempfileCount) throws IOException {
		try {
			((FileHandler) (this.fileHandler)).createDefaultFilesInDirectory(tempfileCount);
		} catch (IOException e) {
			throw e;
		}

	}

	public void addFileToWorkingDirector(String filepath) throws FileAlreadyExistsException, IOException {
		try {
			this.fileHandler.addFileToFolder(Paths.get(filepath));
		} catch (FileAlreadyExistsException fae) {
			throw fae;
		} catch (IOException e) {
			throw e;
		}

	}

	public boolean checkIfFileExistInDirectory(String filepath) {
		return this.fileHandler.checkIfFileExistInDirectory(Paths.get(filepath));
	}

	public void deleteFileFromDirectory(String filepath) throws NoSuchFileException, DirectoryNotEmptyException, IOException {
		try 
		{
			this.fileHandler.deleteFileInFolder(Paths.get(filepath));
		} 
		catch(NoSuchFileException     e )
		{
			throw e;
		}
		catch(DirectoryNotEmptyException dne )
		{
			throw dne;
		}
		catch(IOException   e )
		{
			throw e;
		}

	}

	public TreeMap<String, Path> retrieveSortedFileNames() {
		return ((FileHandler) (this.fileHandler)).retrieveSortedFileNames();
	}

	private void createFileHandler(String rootPath) {
		this.fileHandler = new FileHandler(rootPath);

	}

}
