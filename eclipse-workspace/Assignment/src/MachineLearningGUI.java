/*
 * Class to display the GUI for the machine learning Assignment.
 * It displays two radio buttons for each column, e.g. One button for female and one for male.
 * One can only be selected at a time for each column.
 * 
 * Author: John Woods
 */


package com.assignment.OOP;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MachineLearningGUI extends JFrame implements ActionListener {

	private ButtonGroup 	gender, business, job, address, studies;
	private JRadioButton 	female, male, hasBusiness, noBusiness, hasJob, noJob, urban, rural, doesStudy, noStudies;
    private JButton 		submit, clear;
    private JPanel 			panel1, panel2, panel3, panel4, panel5, panel6;

    NaiveBayes alg1 = new NaiveBayes("Book1.csv");
    
    
    MachineLearningGUI(String title) {
    	
    	JFrame frame = new JFrame(title);
    	
    	frame.setSize(400,500);
    	frame.setLayout(new GridLayout(6, 2, 5, 1));
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Stop the program when the window is closed
    	
    	
    	gender 		= new ButtonGroup();
    	business 	= new ButtonGroup();
    	job 		= new ButtonGroup();
    	address 	= new ButtonGroup();
    	studies 	= new ButtonGroup();
 	
    	female 		= new JRadioButton("Female");
    	male 		= new JRadioButton("Male");
    	hasBusiness = new JRadioButton("Parents own a business");
    	noBusiness 	= new JRadioButton("No business");
    	hasJob 		= new JRadioButton("Has a part time job");
    	noJob 		= new JRadioButton("Does not have a part time job");
    	urban 		= new JRadioButton("Lives in an urban address");
    	rural 		= new JRadioButton("Lives in an rural address");
    	doesStudy 	= new JRadioButton("Studies business");
    	noStudies 	= new JRadioButton("Does not study business");


    	gender.add(female);
    	gender.add(male);
    	
    	business.add(hasBusiness);
    	business.add(noBusiness);
    	
    	job.add(hasJob);
    	job.add(noJob);
    	
    	address.add(urban);
    	address.add(rural);
    	
    	studies.add(doesStudy);
    	studies.add(noStudies);
    	
    	submit = new JButton("Submit traits");
    	submit.addActionListener(this);
    	
    	clear = new JButton("Clear selection");
    	clear.addActionListener(this);
    	
    	panel1 = new JPanel();	// Gender buttons
    	panel1.add(female);
    	panel1.add(male);
    	frame.add(panel1);
    	
    	panel2 = new JPanel();	// Parents business buttons
    	panel2.add(hasBusiness);
    	panel2.add(noBusiness);
    	frame.add(panel2);
    	
    	panel3 = new JPanel();	// Part time job buttons
    	panel3.add(hasJob);
    	panel3.add(noJob);
    	frame.add(panel3);
    	
    	panel4 = new JPanel();	// Address buttons
    	panel4.add(urban);
    	panel4.add(rural);
    	frame.add(panel4);
    	
    	panel5 = new JPanel();	// Studies business buttons
    	panel5.add(doesStudy);
    	panel5.add(noStudies);
    	frame.add(panel5);
    	
    	panel6 = new JPanel();	// Submit / Clear selection
    	panel6.add(submit);
    	panel6.add(clear);
    	frame.add(panel6);
    	
    	
	  	frame.setVisible(true);

    }

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			
			int i = 0;
			boolean noValues = true;
			
			boolean isFemale = female.isSelected();
			boolean isMale = male.isSelected();
			
			boolean doesHaveBusiness = hasBusiness.isSelected();
			boolean doesNotHaveBusiness = noBusiness.isSelected();
			
			boolean doesHaveJob = hasJob.isSelected();
			boolean doesNotHaveJob = noJob.isSelected();
			
			boolean urbanAddress = urban.isSelected();
			boolean ruralAddress = rural.isSelected();

			boolean studiesBusiness = doesStudy.isSelected();
			boolean doesNotStudyBusiness = noStudies.isSelected();
			
			
			boolean[] XTraits = {isFemale, isMale, doesHaveBusiness, doesNotHaveBusiness, doesHaveJob, 
								 doesNotHaveJob, urbanAddress, ruralAddress, studiesBusiness, doesNotStudyBusiness};
			
			for(i=0; i<10; i++) {
				
				if( XTraits[i] == true) {
					noValues = false;
				}
			}
			
			if(noValues == true) {
				JOptionPane.showMessageDialog(submit, "Please select at least one option");
				throw new IllegalStateException("No options selected");		
			}
			
			
			NaiveBayes alg1 = new NaiveBayes("MLdata.csv");
			
			String output = alg1.calculateNaiveBayes(XTraits);
			
			JOptionPane.showMessageDialog(submit, output);
		}
		
		if(e.getSource() == clear) {
			
			gender.clearSelection();
			business.clearSelection();
			job.clearSelection();
			address.clearSelection();
			studies.clearSelection();
		}
	}

}
