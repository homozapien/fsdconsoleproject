package com.simplilearn.fsd.abstraction;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.simplilearn.fsd.helper.Constant;

import java.io.IOException;



public class FileHandler implements FileController {

	private final String rootPath; // this is the path where we would create the files
	private Path path = null;

	public FileHandler(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public String createRootDirectory() throws IOException {
		try 
		{
			Path dir = Paths.get(rootPath + Constant.MYPATH);
			
			if (Files.exists(dir)) 
			{ 
				this.destroyRootFolder(dir);
			}

			path = Files.createDirectory(dir);

			if (Files.isDirectory(path)) 
			{
				return path.toString();
			} 
			else
			{
				throw new IOException("Exception thrown while creating the Root Directory for demo: " + rootPath);
			}

		} catch (IOException exception) 
		{
			throw exception;
		}
	}

	public void createTempFilesInDirectory(int tempfileCount) throws IOException
	{
		
		try 
		{
			String s = new StringBuffer(Constant.COURSE)
					          .append("\n" + Constant.COHORT)
					          .append("\n" + Constant.APPTYPE)
					          .append("\n\n" + Constant.APPTYPE)
					          .toString();
			
			Charset charset = Charset.forName("US-ASCII");
			
			byte[] buff = s.getBytes(charset);
			
			if (path == null)
			{
				this.createRootDirectory();
			}
		
			for (int index = 1; index <= tempfileCount; index++ )
			{
				Path temp = Files.createTempFile(path, Constant.FILE_PREFIX, Constant.FILE_SUFFIX);
				Files.write(temp, buff);				
			}
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			throw e;
		}
	}

	@Override
	public void addFileToFolder(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean searchFileInFolder(String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteFileInFolder(String filename) 
	{
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void destroyRootFolder(Path path) throws IOException
	{

		try (Stream<Path> stream = Files.list(path))
		{
			stream.forEach(file -> {
			             try
			             {
			               Files.delete(file);
			             }
			             catch (Exception e) 
			             {
			            	e.printStackTrace(System.out);
			     		}
			         }
					);
			        
			Files.delete(path);
		} 
		catch (IOException e)
		{
			throw e;
		}

	}
	
	public List<String> retrieveSortedFileNames() throws IOException
	{
		if (!Files.exists(path)) 
		{ 
			return Collections.emptyList();
		}
		else
		{
			try (Stream<Path> stream = Files.list(path)) 
			{
		        return stream
		          .filter(file -> !Files.isDirectory(file))
		          .map(Path::getFileName)
		          .map(Path::toString)
		          .collect(Collectors.toList());
		    }
			catch (IOException e)
			{
				throw e;
			}
		}		
		
	}

}
