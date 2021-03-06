/*
 * This class reads in the CSV file and generates the probability of the Y values and the X values
 * Columns: Gender, Business, Job, Address, Studies, Entrepreneur
 * 
 * readStudentData() takes in the csv file and returns the data as List of ArrayLists
 * 
 * Author: John Woods
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
	
	
	// Method to convert the CSV file into an Array
	public List<String[]> readStudentData()
	{
		int fcount = 0;
		int mcount = 0;
		
		String line = "";
		List<String[]> values = new ArrayList<>();	// Array of arrays
		
		
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

	
}
