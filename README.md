# OOP-SEM2-Assignment
This is my OOP semester 2 2022 Java assignment: Machine learning model, using Naïve Bayes

Warning: The maths is a bit weird in this one. I never did manage to understand what I was doing wrong in my calculations to start getting values over one.


# CLASSES
* FileProcessor      - Takes CSV file and turns values into arrays
* DataSorter         - Takes arrays from FileProcessor and counts the values, putting them into seperate arrays for calculating with. 
* MachineLearningGUI - Displays a GUI with radio buttons to the user to display what traits the student they wish to input has. 
* NaiveBayes         - Takes arrays from DataSorter and uses the NaiveBayes algorithm to first test the model's accuracy, then takes the users input from the GUI to calculate that specific student's probability of becoming an entrepreneur.
* Control            - Instantiates 

# FileProcessor
This class is where the program takes in the values from the csv file given to it and inputs the values into a list of arrays to be workable in other classes
 * This class reads in the CSV file and generates the probability of the Y values and the X values
 * Columns: Gender, Business, Job, Address, Studies, Entrepreneur
 * readStudentData() takes in the csv file and returns the data as List of ArrayLists

# DataSorter
The DataSorter class takes in the raw data processed from the csv file from FileProcessor and converts it into useful data in the form of arrays of frequencies and probabilities.
DataSorter is constructed with just the values from FileProcessor with an array called csvvalues

There are 5 methods:

	 *  DataCounter() takes in the csvvalues ArrayList and counts the frequencies of each row, and putting them into the
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
   
   
# MachineLearningGUI
Displays the GUI to allow the user to input what traits the student they want to predict has. 
Uses groups of radio buttons to allow the user to input their values without selecting conflicting traits.
This class also instantiates the NaiveBayes class with the CSV file name.

# NaiveBayes
Takes arrays from DataSorter and uses the NaiveBayes algorithm to first test the model's accuracy, then takes the users input from the GUI to calculate that specific student's probability of becoming an entrepreneur.


# Core Functionality
All the core functionality detailed in the assignment spec is present, this includes:
  * The training set is done dynamically and can work with a different csv file with many more rows, as long as it has the same columns
  * The model tests itself and records its accuracy.
  * The user can input different traits of the student using the GUI.


# Optional Functionality
  * The model can work with any Train/Test ratio with one change to a variable.
  * The GUI uses radio buttons and infromation is fed back to the user on the accuracy of the model

# If I had more time
  * I would implement support for different kinds of CSV files altogether, with different classes for different CSV files extending from DataSorter and implementing their own methods for counting different files with different columns. 
  * More advanced GUI features like being able to dynamically set the train/test ratio from the GUI

# Demonstration
https://youtu.be/yHC2nbRP9xI

# Repository
https://github.com/Woodsjc123/OOP-SEM2-Assignment
