/*
 * Class that takes in 
 * 
 */

package com.assignment.OOP;

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
	
	
	public void generateData() {
		
		FileProcessor file1 = new FileProcessor(this.fileName);
		file1.openFile();
		
	
		DataSorter data1 = new DataSorter(file1.readStudentData());
		data1.DataCounter();
		data1.XValuesProbability();
		data1.YValuesProbability();

		data1.XgivenY();
		data1.XgivenYProbability();

		System.out.println(data1.getXgivenYProbability());

	}
}
