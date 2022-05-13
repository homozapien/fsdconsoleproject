package com.simplilearn.fsd.abstraction;

import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Stream;

import com.simplilearn.fsd.helper.Constant;

import java.io.File;
import java.io.IOException;



public class FileHandler implements FileController {

	private final String rootPath; 
	private Path path = null;
	
	private TreeMap<String, Path> filenameMap;
	
	final StringBuffer buffer = new StringBuffer(Constant.COURSE)
	                    .append("\n" + Constant.COHORT)
	                    .append("\n" + Constant.APPTYPE)
	                    .append("\n\n" + Constant.APPTYPE);
	                  
	Charset charset = Charset.forName("US-ASCII");

	public FileHandler(String rootPath) 
	{
		this.rootPath    = rootPath;
		this.filenameMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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

	
	public void createDefaultFilesInDirectory(int tempfileCount) throws IOException
	{
		try 
		{
			if (this.path == null)
			{
				this.createRootDirectory();
			}
		
			for (int index = 1; index <= tempfileCount; index++ )
			{
				String filePath = this.path + FileSystems.getDefault().getSeparator() + this.generateRandomString() + Constant.FILE_SUFFIX;
				this.addFileToFolder(Paths.get(filePath));			
			}
			
		} 
		catch (IOException e) 
		{
			throw e;
		}
	}
	
	
	@Override
	public void addFileToFolder(Path filepath) throws FileAlreadyExistsException, IOException 
	{
		try 
		{
		  Files.createFile(filepath);
		  String str =buffer.append("\n created at " + new Timestamp(System.currentTimeMillis()).toString()) .toString();
	      byte[] buff = str.getBytes(charset);
		  Files.write(filepath, buff);	
		  String key = removeFileExtension(filepath);
		  this.filenameMap.put(key, filepath);
						
		} 
		catch (FileAlreadyExistsException fae) 
		{
			throw fae;
		}
		catch (IOException e) 
		{
			throw e;
		}
	}

	@Override
	public boolean checkIfFileExistInDirectory(Path filepath)
	{
		 String key = removeFileExtension(filepath);
		 return this.filenameMap.containsKey(key);
	}
	
	@Override
	public File retrieveFileFromDirectory(Path filepath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TreeMap<String, Path> retrieveSortedFileNames() 
	{
		return this.filenameMap;		
	}


	@Override
	public void deleteFileInFolder(Path filepath) throws IOException
	{
		try
		{
			 String key = removeFileExtension(filepath);
		     Files.delete(filepath);			 				
			 this.filenameMap.remove(key);
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

	
	private void destroyRootFolder(Path path) throws IOException
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
			
			this.filenameMap.clear();
		} 
		catch (IOException e)
		{
			throw e;
		}

	}
	
	private String removeFileExtension(Path filepath) 
	{
		String filename = filepath.getFileName().toString();
	   return filename.substring(0, filename.lastIndexOf("."));
	}	
	
	
	private  String generateRandomString() {
		    int leftLimit = 48; 
		    int rightLimit = 122; 
		    int targetStringLength = 10;
		    Random random = new Random();

		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();

		    return generatedString;
		}

	
		
}
