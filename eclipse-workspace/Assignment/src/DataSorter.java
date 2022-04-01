package com.assignment.OOP;

import java.util.ArrayList;
import java.util.List;

public class DataSorter {
	
	// Attributes
	private List<String[]> values = new ArrayList<>();

	
	// Constructor
	public DataSorter(List<String[]> values) {
		this.values = values;
	}
	
	
	// Method to take array of data and generate frequencies 
	public void DataCounter() {
		int i = 0;
		int datastart = 0;		// Index of actual data start, after column names
		
		long total = 0;			// Total number of students
		
		long fcount = 0;		// Number of female students
		long mcount = 0;		// Number of male students
		
		long business = 0;		// Number of students whose parents own a business
		long nobusiness = 0;	// Number of students whose parents do not own a business
		
		long job = 0;			// Number of students who have a part time job
		long nojob = 0;			// Number of students who do not have a part time job
		
		long urban = 0;			// Number of students with an urban address
		long rural = 0;			// Number of students with a rural address

		long studies = 0;		// Number of students who study business
		long nostudies = 0;		// Number of students who do not study business
		
		long entrepreneur = 0;	// Number of students who became entrepreneurs
		long noentrepreneur = 0;// Number of students who did not become entrepreneurs
		
		
		// Skips column labels
		while(values.get(i)[0].contentEquals("Female") == false && values.get(i)[0].contentEquals("Male") == false) {
			i++;
		}
		
		datastart = i;

		total = (values.size()-i);	// Calculates total number of students
		
		
		// Calculating gender
		for(i = datastart; i < values.size(); i++) {			
			if((values.get(i)[0].trim().contentEquals("Female"))) {
				fcount++;
			}
		}
		mcount = total-fcount;	// Number of male students = Total number of students - female students
		
		
		// Calculating how many student's parents own a business
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[1].trim().contentEquals("Yes")) {
				business++;
			}	
		}	
		nobusiness = total-business;
		
		
		// Calculating how many students have part time jobs
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[2].trim().contentEquals("Yes")) {
				job++;
			}	
		}
		nojob = total-job;
		
		
		// Calculating how many urban addresses there are
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[3].trim().contentEquals("Urban")) {
				urban++;
			}	
	
		}
		rural = total-urban;
		
		
		// Calculating how many students study business
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[4].trim().contentEquals("Yes")) {
				studies++;
			}			
		}
		nostudies = total-studies;
		
		
		// Calculating how many students became entrepreneurs
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[4].trim().contentEquals("Yes")) {
				entrepreneur++;
			}				
		}
		noentrepreneur = total-entrepreneur;
		
		System.out.println("Total: " + total + "\nMales: " + mcount + "\nFemales: " + fcount);
		System.out.println("\nStudents with business: " + business + "\nStudents without business: " + nobusiness);
		System.out.println("\nStudents with part time job: " + job + "\nStudents without job: " + nojob);
		System.out.println("\nStudents with urban address: " + urban + "\nStudents with rural address: " + rural);
		System.out.println("\nStudents who study business: " + studies + "\nStudents who do not study business: " + nostudies);

	}
	
	public void DataReader() {
		
	}
	
	
}
