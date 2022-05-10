package com.simplilearn.fsd.abstraction;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileController 
{
    
	public String createRootDirectory() throws IOException;
	
	public void addFileToFolder(Path filepath) throws IOException;
	
	public boolean searchFileInFolder(String filename);
	
	public void deleteFileInFolder(String filename);
	
	public void destroyRootFolder(Path path) throws IOException;
	
}
