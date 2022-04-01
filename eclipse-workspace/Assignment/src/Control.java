package com.assignment.OOP;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Control {

	public static void main(String[] args) {
		
		FileProcessor file1 = new FileProcessor("MLdata.csv");
		file1.openFile();
		
		DataSorter data1 = new DataSorter(file1.readStudentData());
		System.out.println(data1.XValues(data1.DataCounter()));
		System.out.println(data1.YValues(data1.DataCounter()));

	}

}
