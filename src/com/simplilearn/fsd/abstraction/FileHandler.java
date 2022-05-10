package com.simplilearn.fsd.abstraction;

import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.simplilearn.fsd.helper.Constant;

import java.io.IOException;



public class FileHandler implements FileController {

	private final String rootPath; // this is the path where we would create the files
	private Path path = null;
	final StringBuffer buffer = new StringBuffer(Constant.COURSE)
	                    .append("\n" + Constant.COHORT)
	                    .append("\n" + Constant.APPTYPE)
	                    .append("\n\n" + Constant.APPTYPE);
	                  
	Charset charset = Charset.forName("US-ASCII");

	public FileHandler(String rootPath) 
	{
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
			if (path == null)
			{
				this.createRootDirectory();
			}
		
			for (int index = 1; index <= tempfileCount; index++ )
			{
				Path temp = Files.createTempFile(path, Constant.FILE_PREFIX, Constant.FILE_SUFFIX);
				this.addFileToFolder(temp);			
			}
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			throw e;
		}
	}

	@Override
	public void addFileToFolder(Path filepath) throws IOException
	{
		try 
		{
			
			String str =buffer.append("\n created at " + new Timestamp(System.currentTimeMillis()).toString()) .toString();

            byte[] buff = str.getBytes(charset);

				
			if(!Files.exists(filepath))
			{
				Files.createFile(filepath);
			}
			
			Files.write(filepath, buff);				
						
		} 
		catch (FileAlreadyExistsException fae) 
		{
			// TODO Auto-generated catch block
			throw new IOException(fae);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			throw e;
		}
	}

	@Override
	public boolean checkIfFileExistInDirectory(Path filepath)
	{
		return Files.exists(filepath);
	}

	@Override
	public void deleteFileInFolder(Path filepath) throws IOException
	{
		try
		{
				Files.delete(filepath);
		}
		catch (NoSuchFileException nsf) 
		{
		    throw new IOException(nsf);
		} 
		catch (DirectoryNotEmptyException dne) {
			throw new IOException(dne);
		}
		catch(IOException exception)
		{
			throw exception;
		}

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
