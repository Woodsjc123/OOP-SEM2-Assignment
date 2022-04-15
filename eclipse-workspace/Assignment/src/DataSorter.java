	/*
	 * 	The DataSorter class takes in the raw data processed from the csv file from FileProcessor and converts
	 *  it into useful data in the form of arrays of frequencies and probabilities.
	 * 
	 *  DataSorter is constructed with just the values from FileProcessor with an array called csvvalues
	 * 
	 * 	There are 5 methods
	 * 	DataCounter() takes in the csvvalues ArrayList and counts the frequencies of each row, and putting them into the
	 *  frequencies ArrayList.
	 *  
	 *  XValuesProbability() takes in the frequency ArrayList and generates the probability of all the X values,
	 *  putting them into the ArrayList XValues
	 *  
	 *  YValuesProbability() takes in the frequency ArrayList and generates the probability of all the Y values,
	 *  putting them into the ArrayList YValues
	 * 
	 *  XgivenY() takes in the csvvalues ArrayList and counts all the X values where Y is true, 
	 *  that is where the entrepreneur column is "Yes", and puts the values into the ArrayList XgivenY
	 *  
	 *  XgivenYProbability() takes in the ArrayList XgivenY and calculates the probability of all the XgivenY
	 *  values, and puts the results into the ArrayList XgivenYProbability
	 *  
	 *  Author: John Woods
	 * 
	 * 
	 */


package com.assignment.OOP;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class DataSorter {
	
	// Attributes
	long size			 = 0;
	private int endTrain = 0;
	private long total 	 = 0;
	private double entrepreneurs 	 = 0;
	private double notEntrepreneurs  = 0;
	private double entrepreneursProb = 0;
	

	private List<String[]> csvvalues 			 = new ArrayList<>();
	private ArrayList<Long> frequencies 		 = new ArrayList<Long>();
	private ArrayList<Long> testingFrequencies 	 = new ArrayList<Long>();
	
	
	// Constructor
	public DataSorter(List<String[]> values) {
		this.csvvalues = values;
	}

	// Getters and Setters
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		
		if(total < 1) {
			System.out.println("Invalid value; there can not be less than 1 student");
		}
		else {
			this.total = total;
		}
	}
	
	public void setEntrepreneurs(long entrepreneurs) {
		this.entrepreneurs = entrepreneurs;
	}
	
	public double getEntrepreneurs() {
		return entrepreneurs;
	}

	public void setNotEntrepreneurs(double notEntrepreneurs) {
		this.notEntrepreneurs = notEntrepreneurs;
	}
	
	public double getNotEntrepreneurs() {
		return notEntrepreneurs;
	}

	public double getEntrepreneursProb() {
		return entrepreneursProb;
	}

	public void setEntrepreneursProb(double entrepreneursProb) {
		this.entrepreneursProb = entrepreneursProb;
	}

	
	// General frequencies
	public ArrayList<Long> getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(ArrayList<Long> frequencies) {
		this.frequencies = frequencies;
	}	

	public int getEndTrain() {
		return endTrain;
	}

	public void setEndTrain(int endTrain) {
		this.endTrain = endTrain;
	}

	
	// Methods

	// Method to take array of data and generate two ArrayLists, one for training the model and one for testing the accuracy
	public ArrayList<Long> DataCounter(double trainPercent) {
		
		int i 				= 0;
		int dataStart 		= 0;	// Index of actual data start, after column names
		int endTrain		= 0;	// Index of data to be tested
		
		long total 			= 0;	// Total number of students	
		long fcount 		= 0;	// Number of female students		
		long business 		= 0;	// Number of students whose parents own a business	
		long job 			= 0;	// Number of students who have a part time job	
		long urban 			= 0;	// Number of students with an urban address
		long studies 		= 0;	// Number of students who study business	
		long entrepreneur 	= 0;	// Number of students who became entrepreneurs
		
		ArrayList<Long> frequency = new ArrayList<Long>();
				
		// Skips column labels
		while(csvvalues.get(i)[0].trim().contentEquals("Female") == false && csvvalues.get(i)[0].trim().contentEquals("Male") == false) {
			i++;
		}
		
		dataStart = i;
		
		this.size = csvvalues.size();

		total = (csvvalues.size()-dataStart);	 // Calculates total number of students
		setTotal(total);
		
		endTrain = (int) (total * trainPercent); // Calculates the end of the training portion of the data
		setEndTrain(endTrain);
		
		// Calculating gender
		for(i = dataStart; i < endTrain; i++) {			
			if((csvvalues.get(i)[0].trim().contentEquals("Female"))) {
				fcount++;
			}
		}
		
		// Calculating how many student's parents own a business
		for(i = dataStart; i < endTrain; i++) {			
			if(csvvalues.get(i)[1].trim().contentEquals("Yes")) {
				business++;
			}	
		}		
		
		// Calculating how many students have part time jobs
		for(i = dataStart; i < endTrain; i++) {			
			if(csvvalues.get(i)[2].trim().contentEquals("Yes")) {
				job++;
			}	
		}
		
		// Calculating how many urban addresses there are
		for(i = dataStart; i < endTrain; i++) {			
			if(csvvalues.get(i)[3].trim().contentEquals("Urban")) {
				urban++;
			}	
		}
		
		// Calculating how many students study business
		for(i = dataStart; i < endTrain; i++) {			
			if(csvvalues.get(i)[4].trim().contentEquals("Yes")) {
				studies++;
			}			
		}
		
		// Calculating how many students became entrepreneurs
		for(i = dataStart; i < endTrain; i++) {			
			if(csvvalues.get(i)[5].trim().contentEquals("Yes")) {
				entrepreneur++;
			}				
		}
		
		frequency.add(fcount);
		frequency.add(business);
		frequency.add(job);
		frequency.add(urban);
		frequency.add(studies);
		frequency.add(entrepreneur);	
		
		setFrequencies(frequency);
		setEntrepreneurs(entrepreneur);
		setNotEntrepreneurs(total-entrepreneur);
		
		double dTotal = total;
		
		setEntrepreneursProb(entrepreneur / dTotal);
		
		return frequency;
	}

	
	// Method that reads back individual rows
	public ArrayList<String> rowReader(int i) {
		
		ArrayList<String> row = new ArrayList<String>();
		
		row.add(csvvalues.get(i)[0].trim());
		row.add(csvvalues.get(i)[1].trim());
		row.add(csvvalues.get(i)[2].trim());
		row.add(csvvalues.get(i)[3].trim());
		row.add(csvvalues.get(i)[4].trim());
		row.add(csvvalues.get(i)[5].trim());
		
		return row;
	}



	// Method to calculate the frequencies of X values given Y
	public ArrayList<Long> XgivenY(String Y) {
		
		int i = 0;
		int datastart = 0;		// Index of actual data start, after column names
		
		long fcount = 0;		// Number of female students given they are an entrepreneur	
		long business = 0;		// Number of students whose parents own a business given they are an entrepreneur		
		long job = 0;			// Number of students who have a part time job given they are an entrepreneur	
		long urban = 0;			// Number of students with an urban address given they are an entrepreneur	
		long studies = 0;		// Number of students who study business given they are an entrepreneur	
		
		ArrayList<Long> XgivenY = new ArrayList<Long>();
				
		// Skips column labels
		while(csvvalues.get(i)[0].trim().contentEquals("Female") == false && csvvalues.get(i)[0].trim().contentEquals("Male") == false) {
			i++;
		}
		
		datastart = i;
		
		// Calculating female students given that they are an entrepreneur
		for(i = datastart; i < csvvalues.size(); i++) {			
			if((csvvalues.get(i)[0].trim().contentEquals("Female")) && (csvvalues.get(i)[5].trim().contentEquals(Y))) {
				fcount++;
			}
		}
		
		// Calculating how many student's parents own a business
		for(i = datastart; i < csvvalues.size(); i++) {			
			if((csvvalues.get(i)[1].trim().contentEquals("Yes")) && (csvvalues.get(i)[5].trim().contentEquals(Y))) {
				business++;
			}	
		}		

		// Calculating how many students have part time jobs
		for(i = datastart; i < csvvalues.size(); i++) {			
			if((csvvalues.get(i)[2].trim().contentEquals("Yes")) && (csvvalues.get(i)[5].trim().contentEquals(Y))) {
				job++;
			}	
		}

		// Calculating how many urban addresses there are
		for(i = datastart; i < csvvalues.size(); i++) {			
			if((csvvalues.get(i)[3].trim().contentEquals("Urban")) && (csvvalues.get(i)[5].trim().contentEquals(Y))) {
				urban++;
			}	
		}
		
		// Calculating how many students study business
		for(i = datastart; i < csvvalues.size(); i++) {			
			if((csvvalues.get(i)[4].trim().contentEquals("Yes")) && (csvvalues.get(i)[5].trim().contentEquals(Y))) {
				studies++;
			}			
		}

		XgivenY.add(fcount);
		XgivenY.add(business);
		XgivenY.add(job);
		XgivenY.add(urban);
		XgivenY.add(studies);

		//setXgivenY(XgivenY);
		
		return XgivenY;
	}
	
	
	// Method that takes in the frequency of Y values and calculates their probability
	public ArrayList<Double> YValuesProbability(ArrayList<Long> frequencies) {
		
		ArrayList<Double> yvalues = new ArrayList<Double>();

		double total = getTotal();	// Total number of students	

		
		double entrepreneur = ((frequencies.get(5)) / total);	// Probability of student who became entrepreneurs
		double noentrepreneur = (1-entrepreneur);			// Probability of student who did not become entrepreneurs
		
		yvalues.add(entrepreneur);
		yvalues.add(noentrepreneur);
		
		return yvalues;
	}
	
	
	// Method that takes in the frequency of X value traits and calculates their probability
	public ArrayList<Double> XValuesProbability(ArrayList<Long> frequencies) {
		
		ArrayList<Double> xvalues = new ArrayList<Double>();

		double total = getTotal();							// Total number of students	
		
		double fcount = ((frequencies.get(0)) / total);		// Probability of female student
		double mcount = (1-fcount);							// Probability of male student
		
		double business = ((frequencies.get(1)) / total);		// Probability student's parents own a business
		double nobusiness = (1-business);					// Probability student's parents do not own a business
		
		double job = ((frequencies.get(2)) / total);			// Probability student has a part time job
		double nojob = (1-job);								// Probability student does not have a part time job
		
		double urban = ((frequencies.get(3)) / total);		// Probability of student with an urban address
		double rural = (1-urban);							// Probability of student with a rural address
		
		double studies = ((frequencies.get(4)) / total);		// Probability of student who studies business
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
	
	public ArrayList<Double> XgivenYProbability(ArrayList<Long> XgivenYValues, double Y) {
		
		ArrayList<Double> xgivenyprob = new ArrayList<Double>();
				
		double fcount = ((XgivenYValues.get(0)) / Y);	// Probability of female student given they are an entrepreneur
		double mcount = (1-fcount);						// Probability of male student given they are an entrepreneur
		
		double business = ((XgivenYValues.get(1)) / Y);	// Probability student's parents own a business given they are an entrepreneur
		double nobusiness = (1-business);				// Probability student's parents do not own a business given they are an entrepreneur
		
		double job = ((XgivenYValues.get(2)) / Y);		// Probability student has a part time job given they are an entrepreneur
		double nojob = (1-job);							// Probability student does not have a part time job given they are an entrepreneur
		
		double urban = ((XgivenYValues.get(3)) / Y);	// Probability of student with an urban address given they are an entrepreneur
		double rural = (1-urban);						// Probability of student with a rural address given they are an entrepreneur
		
		double studies = ((XgivenYValues.get(4)) / Y);	// Probability of student who studies business given they are an entrepreneur
		double nostudies = (1-studies);					// Probability of student who does not study business given they are an entrepreneur
		
		
		xgivenyprob.add(fcount);
		xgivenyprob.add(mcount);
		xgivenyprob.add(business);
		xgivenyprob.add(nobusiness);
		xgivenyprob.add(job);
		xgivenyprob.add(nojob);
		xgivenyprob.add(urban);
		xgivenyprob.add(rural);
		xgivenyprob.add(studies);
		xgivenyprob.add(nostudies);
		
		//setXgivenYProbability(xgivenyprob);

		return xgivenyprob;
	}
	
}
