/*
 * This class reads in the CSV file and generates the probability of the Y values and the X values
 * Columns: Gender, Business, Job, Address, Studies, Entrepreneur
 * 
 */


package com.assignment.OOP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {
	// Default file location is the project directory
	
	// attributes
	String fileName;	// all methods can see this
	File myFile;
	
	
	// Constructor
	public FileProcessor(String fileName)
	{
		this.fileName = fileName;
	}
	
	
	// other methods / behaviours
	public void openFile()
	{
		myFile = new File(fileName);
	}
	
	
	// Method to count the occurrences of 
	public List<String[]> readStudentData()
	{
		int fcount = 0;
		int mcount = 0;
		String line = "";
		List<String[]> values = new ArrayList<>();
		
		
		//ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>(6);
		
		try 
		{
			BufferedReader myReader = new BufferedReader(new FileReader(myFile));
			
			while((line = myReader.readLine()) != null)
			{
				values.add(line.split(","));
			}
			myReader.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("error caught" + e.getMessage());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("error caught" + e.getMessage());
		}
		
		return values;
	}
	
	
	
	public String readFile()
	{
		String line = "";
		
		
		try {
			Scanner myScanner = new Scanner(myFile);
			
			while(myScanner.hasNextLine()) 
			{
				line = myScanner.nextLine();
				System.out.println(line);
			}
			myScanner.close();
			
		}
		
		
		catch(FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("error caught" + e.getMessage());
		}
		
		return line;
	}
	
	
	// method to read a single line from the file
	public boolean checkString(String check) {
		
		check = check.trim();
		
		String line = "";
		boolean exists = false;
		
		try {
			Scanner myScanner = new Scanner(myFile);
			
			while(myScanner.hasNextLine() && exists == false) 
			{
				line = myScanner.nextLine();
				line = line.trim();
				
				if(line.equals(check)) {	// checks to see if role exists in role.txt
					exists = true;
				}
				
			}		
			myScanner.close();

		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("error caught" + e.getMessage());
		}
		
		return exists;
	}
	
	
	// getter and setters
	
}
