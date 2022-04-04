package com.assignment.OOP;

import java.awt.FlowLayout;
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
    private JButton 		submit;
    private JPanel 			panel1, panel2, panel3, panel4, panel5, panel6;
    private JLabel 			label; 
    
    
    
    NaiveBayes alg1 = new NaiveBayes("MLdata.csv");
    
    MachineLearningGUI(String title) {
    	
    	super(title);
    	setSize(400,500);
    	setLayout(new FlowLayout());
    	
    	
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
    	
    	
    	panel1 = new JPanel();
    	panel1.add(female);
    	panel1.add(male);
    	add(panel1);
    	
    	panel2 = new JPanel();
    	panel2.add(hasBusiness);
    	panel2.add(noBusiness);
    	add(panel2);
    	
    	panel3 = new JPanel();
    	panel3.add(hasJob);
    	panel3.add(noJob);
    	add(panel3);
    	
    	panel4 = new JPanel();
    	panel4.add(urban);
    	panel4.add(rural);
    	add(panel4);
    	
    	panel5 = new JPanel();
    	panel5.add(doesStudy);
    	panel5.add(noStudies);
    	add(panel5);
    	
    	panel6 = new JPanel();
    	panel6.add(submit);
    	add(panel6);
    	
    	
	  	setVisible(true);

    }

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			
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
			
			
			NaiveBayes alg1 = new NaiveBayes("MLdata.csv");
			
			alg1.calculateNaiveBayes(XTraits);
		}
	}

}
