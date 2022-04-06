/*
 * Class that takes in 
 * 
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
		
		FileProcessor file1 = new FileProcessor(this.fileName);
		file1.openFile();
		
		ArrayList<Double> XColumns 			= new ArrayList<Double>();
		ArrayList<Double> DenominatorArray 	= new ArrayList<Double>();
		ArrayList<Double> XYColumns 		= new ArrayList<Double>();
		ArrayList<Double> InverseXYColumns 	= new ArrayList<Double>();
		ArrayList<Double> NumeratorArray 	= new ArrayList<Double>();
		
		ArrayList<Double> InverseNumeratorArray = new ArrayList<Double>();

		
		double numerator = 1;
		double denominator = 1;
		double result;
		double inverse = 1;
		double inverseResult;
		
	
		DataSorter data1 = new DataSorter(file1.readStudentData());
		data1.DataCounter();
		

		XColumns = data1.XValuesProbability();
		
		data1.YValuesProbability();

		
		XYColumns 		 = data1.XgivenYProbability(data1.XgivenY("Yes"), data1.getEntrepreneurs());
		InverseXYColumns = data1.XgivenYProbability(data1.XgivenY("No"), data1.getNotEntrepreneurs());
		
		
		int i = 0;

		
		for(i=0; i<10; i++) {
			
			if(XTraits[i] == true) {
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
		
		
		result = numerator / denominator;
		
		inverseResult = inverse / denominator;
		
		System.out.println(result);
		System.out.println(inverseResult);
		
		
		if(inverseResult > result) {

			return "No";
		}
		
		else {
			
			return "Yes";
		}

	}
}
