/*
 * Class that takes in arrays of data from DataSorter, tests how accurate the model is, and then calculates the probability
 * of the student with the user selected traits of becoming an entrepreneur. 
 * 
 * Author: John Woods
 */

package com.assignment.OOP;

import java.util.ArrayList;

public class NaiveBayes {
	// Attributes
	String fileName;
	
	public String getFileName() {
		return fileName;
	}
	
	public NaiveBayes(String fileName) {
		this.fileName = fileName;
	}
	
	// Methods
	
	public String calculateNaiveBayes(boolean[] XTraits) {
		
		FileProcessor file1 = new FileProcessor(getFileName());
		file1.openFile();
		
		ArrayList<Double> XColumns 			 	= new ArrayList<Double>();	// All possible X value probabilities
		ArrayList<Double> XYColumns 		 	= new ArrayList<Double>();	// All XY value probabilities
		ArrayList<Double> InverseXYColumns 	 	= new ArrayList<Double>();	// Inverse XY values
		
		ArrayList<Double> NumeratorArray 	 	= new ArrayList<Double>();	// The values that will multiply together to give the numerator
		ArrayList<Double> InverseNumeratorArray = new ArrayList<Double>();	// The inverse of the numerator

		ArrayList<Double> DenominatorArray 	 	= new ArrayList<Double>();	// What X values will multiply to be the denominator
		ArrayList<String> row 					= new ArrayList<String>();	// An individual row of data used for testing purposes

		
		double numerator 	= 1;	
		double denominator 	= 1;	
		double result;				// The product of the numerator / denominator
		double inverse 		= 1;	// The inverse of the numerator to compare to 
		double inverseResult;		// The inverse / denominator
		
		int i = 0;
		int j = 0;
			
		float right 	= 0;	// How many times the model predicted a student right
		float wrong 	= 0;	// How many times the model predicted wrong
		float total 	= 0;	// How many predictions were made
		float accuracy 	= 0;	// How accurate the model is
	
	
		DataSorter data1 = new DataSorter(file1.readStudentData());
		data1.DataCounter(0.7);	// How much of the data will be taken for training vs testing
		

		XColumns = data1.XValuesProbability(data1.getFrequencies());
		
		data1.YValuesProbability(data1.getFrequencies());

		// XY values where the entrepreneur column was "Yes"
		XYColumns 		 = data1.XgivenYProbability(data1.XgivenY("Yes"), data1.getEntrepreneurs());
		// XY values where the entrepreneur column was "No"
		InverseXYColumns = data1.XgivenYProbability(data1.XgivenY("No"), data1.getNotEntrepreneurs());
		
		
		// Testing data accuracy
		for(i=data1.getEndTrain(); i<(data1.size); i++) {
			
			row = data1.rowReader(i);

			// Checking to see what X/XY values are needed in the arrays and putting the inverse in the inverse array
			
			if(row.get(0).trim().contentEquals("Female")) {
				NumeratorArray.add(XYColumns.get(0));
				InverseNumeratorArray.add(XYColumns.get(1));
				DenominatorArray.add(XColumns.get(0));
			}
			else {
				NumeratorArray.add(XYColumns.get(1));
				InverseNumeratorArray.add(XYColumns.get(0));
				DenominatorArray.add(XColumns.get(1));
			}
			
			if(row.get(1).trim().contentEquals("Yes")) {
				NumeratorArray.add(XYColumns.get(2));
				InverseNumeratorArray.add(XYColumns.get(3));
				DenominatorArray.add(XColumns.get(2));
			}
			else {
				NumeratorArray.add(XYColumns.get(3));
				InverseNumeratorArray.add(XYColumns.get(2));
				DenominatorArray.add(XColumns.get(3));
			}
			
			if(row.get(2).trim().contentEquals("Yes")) {
				NumeratorArray.add(XYColumns.get(4));
				InverseNumeratorArray.add(XYColumns.get(5));
				DenominatorArray.add(XColumns.get(4));
			}
			else {
				NumeratorArray.add(XYColumns.get(5));
				InverseNumeratorArray.add(XYColumns.get(4));
				DenominatorArray.add(XColumns.get(5));
			}
			
			if(row.get(3).trim().contentEquals("Urban")) {
				NumeratorArray.add(XYColumns.get(6));
				InverseNumeratorArray.add(XYColumns.get(7));
				DenominatorArray.add(XColumns.get(6));
			}
			else {
				NumeratorArray.add(XYColumns.get(7));
				InverseNumeratorArray.add(XYColumns.get(6));
				DenominatorArray.add(XColumns.get(7));
			}
			
			if(row.get(4).trim().contentEquals("Yes")) {
				NumeratorArray.add(XYColumns.get(8));
				InverseNumeratorArray.add(XYColumns.get(9));
				DenominatorArray.add(XColumns.get(8));
			}
			else {
				NumeratorArray.add(XYColumns.get(9));
				InverseNumeratorArray.add(XYColumns.get(8));
				DenominatorArray.add(XColumns.get(9));
			}
			
			// Adding the Y value probability at the end
			NumeratorArray.add(data1.getEntrepreneursProb());
			InverseNumeratorArray.add(1-(data1.getEntrepreneursProb()));

			// Multiplying all the values in the array together
			for(j=0; j<5; j++) {
				
				numerator *= NumeratorArray.get(j);
			}
			
			for(j=0; j<5; j++) {
				
				inverse *= InverseNumeratorArray.get(j);
			}		
			
			for(j=0; j<5; j++) {
				
				denominator *= DenominatorArray.get(j);
			}
			
			result = numerator / denominator;
			
			inverseResult = inverse / denominator;
			
			// If the model predicted correctly
			if(inverseResult > result && row.get(5).contentEquals("No")) {

				right++;
			}
			
			else if(result > inverseResult && row.get(5).contentEquals("Yes")) {
				
				right++;
			}
			
			// If the model did not predict correctly
			else {
			
				wrong++;
			}

			
			NumeratorArray.clear();
			InverseNumeratorArray.clear();
			DenominatorArray.clear();
		}
		
		// Re-initialising variables back to 1
		numerator 	= 1;
		denominator = 1;
		inverse		= 1;
		
		total = right + wrong;
		
		accuracy = right / total;
		
		System.out.println("This model has an accuracy of: " + accuracy + "%");
		
		
		// User selection
		for(i=0; i<10; i++) {
			
			if(XTraits[i] == true) {	// If the user selected this trait in the GUI
				
				NumeratorArray.add(XYColumns.get(i));
				InverseNumeratorArray.add(InverseXYColumns.get(i));
				DenominatorArray.add(XColumns.get(i));
			}	
		}
		
		NumeratorArray.add(data1.getEntrepreneursProb());
		
		InverseNumeratorArray.add(1-(data1.getEntrepreneursProb()));
	
		
		
		for(i=0; i<(NumeratorArray.size()); i++) {
			
			numerator *= NumeratorArray.get(i);
		}
		
		
		for(i=0; i<(DenominatorArray.size()); i++) {
			
			denominator *= DenominatorArray.get(i);
		}
		

		for(i=0; i<(InverseNumeratorArray.size()); i++) {
			
			inverse *= InverseNumeratorArray.get(i);
		}
		
		result = numerator;	
		//result = numerator / denominator;	// Denominator not needed since inverse shares common denominator here
		inverseResult = inverse;
		//inverseResult = inverse / denominator;
		
		System.out.println(result);
		System.out.println(inverseResult);
		
		
		if(inverseResult > result) {

			return "This student is not likely to become an entrepreneur";
		}
		
		else {
			
			return "This student is likely to become an entrepreneur";
		}

	}

	
}
