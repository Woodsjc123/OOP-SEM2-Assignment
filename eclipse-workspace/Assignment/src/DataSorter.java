package com.assignment.OOP;

import java.util.AbstractMap;
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
	public ArrayList DataCounter() {
		int i = 0;
		int datastart = 0;		// Index of actual data start, after column names
		
		long total = 0;			// Total number of students	
		long fcount = 0;		// Number of female students		
		long business = 0;		// Number of students whose parents own a business	
		long job = 0;			// Number of students who have a part time job	
		long urban = 0;			// Number of students with an urban address
		long studies = 0;		// Number of students who study business	
		long entrepreneur = 0;	// Number of students who became entrepreneurs
		
		ArrayList<Long> frequency = new ArrayList<Long>();
		
		
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
		
		
		// Calculating how many student's parents own a business
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[1].trim().contentEquals("Yes")) {
				business++;
			}	
		}	
		
		
		// Calculating how many students have part time jobs
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[2].trim().contentEquals("Yes")) {
				job++;
			}	
		}
		
		
		// Calculating how many urban addresses there are
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[3].trim().contentEquals("Urban")) {
				urban++;
			}	
	
		}
		
		
		// Calculating how many students study business
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[4].trim().contentEquals("Yes")) {
				studies++;
			}			
		}
		
		
		// Calculating how many students became entrepreneurs
		for(i = datastart; i < values.size(); i++) {			
			if(values.get(i)[5].trim().contentEquals("Yes")) {
				entrepreneur++;
			}				
		}
		
		frequency.add(total);
		frequency.add(fcount);
		frequency.add(business);
		frequency.add(job);
		frequency.add(urban);
		frequency.add(studies);
		frequency.add(entrepreneur);
		
		
		return frequency;
	}
	
	
	// Method that takes in the frequency of Y values and calculates their probability
	public ArrayList YValues(ArrayList<Long> frequency) {
		
		ArrayList<Double> yvalues = new ArrayList<Double>();

		double total = frequency.get(0);					// Total number of students	

		
		double entrepreneur = ((frequency.get(6)) / total);	// Probability of student who became entrepreneurs
		double noentrepreneur = (1-entrepreneur);			// Probability of student who did not become entrepreneurs
		
		yvalues.add(entrepreneur);
		yvalues.add(noentrepreneur);

		
		return yvalues;
	}
	
	
	// Method that takes in the frequency of X value traits and calculates their probability
	public ArrayList XValues(ArrayList<Long> frequency) {
		
		ArrayList<Double> xvalues = new ArrayList<Double>();

		double total = frequency.get(0);					// Total number of students	
		
		double fcount = ((frequency.get(1)) / total);		// Probability of female student
		double mcount = (1-fcount);							// Probability of male student
		
		double business = ((frequency.get(2)) / total);		// Probability student's parents own a business
		double nobusiness = (1-business);					// Probability student's parents do not own a business
		
		double job = ((frequency.get(3)) / total);			// Probability student has a part time job
		double nojob = (1-job);								// Probability student does not have a part time job
		
		double urban = ((frequency.get(4)) / total);		// Probability of student with an urban address
		double rural = (1-urban);							// Probability of student with a rural address
		
		double studies = ((frequency.get(5)) / total);		// Probability of student who studies business
		double nostudies = (1-studies);						// Probability of student who does not study business
		
		
		xvalues.add(fcount);
		xvalues.add(mcount);
		xvalues.add(business);
		xvalues.add(nobusiness);
		xvalues.add(job);
		xvalues.add(nojob);
		xvalues.add(urban);
		xvalues.add(rural);
		xvalues.add(studies);
		xvalues.add(nostudies);

		return xvalues;
	}
}
