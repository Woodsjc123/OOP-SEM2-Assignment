package com.assignment.OOP;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Control {

	public static void main(String[] args) {
		int i = 0;
		int gender = 0;
		
		FileProcessor file1 = new FileProcessor("MLdata.csv");
		file1.openFile();
		List<String[]> values = new ArrayList<>();
		
		values = file1.readStudentData();
		//System.out.println(values.get(3)[0]);

		while(values.get(0)[0] != "Female" || values.get(0)[0] != "Male") {
			i++;
		}
		System.out.println(i);

		
		while(values.get(i)[0] != null) {
			i++;
			gender++;
		}
		
		System.out.println(gender);
	}

}
