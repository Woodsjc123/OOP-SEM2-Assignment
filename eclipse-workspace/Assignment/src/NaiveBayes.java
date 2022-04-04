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
	
	public void calculateNaiveBayes(boolean[] XTraits) {
		
		FileProcessor file1 = new FileProcessor(this.fileName);
		file1.openFile();
		
		ArrayList<Double> XColumns 			= new ArrayList<Double>();
		ArrayList<Double> DenominatorArray 	= new ArrayList<Double>();
		ArrayList<Double> XYColumns 		= new ArrayList<Double>();
		ArrayList<Double> NumeratorArray 	= new ArrayList<Double>();
		
		double numerator = 1;
		double denominator = 1;
		double result;
		
	
		DataSorter data1 = new DataSorter(file1.readStudentData());
		data1.DataCounter();
		

		XColumns = data1.XValuesProbability();
		
		data1.YValuesProbability();

		data1.XgivenY();
		XYColumns = data1.XgivenYProbability();
		
		int i = 0;

		
		for(i=0; i<10; i++) {
			
			if(XTraits[i] == true) {
				NumeratorArray.add(XYColumns.get(i));
				DenominatorArray.add(XColumns.get(i));
			}	
		}
		
		NumeratorArray.add(data1.getEntrepreneursProb());
	
		
		
		for(i=0; i<(NumeratorArray.size()); i++) {
			
			numerator *= NumeratorArray.get(i);
		}
		
		
		for(i=0; i<(DenominatorArray.size()); i++) {
			
			denominator *= DenominatorArray.get(i);
		}

		
		System.out.println(numerator);
		System.out.println(denominator);
		
		result = numerator / denominator;
		
		System.out.println(result);

	}
}
