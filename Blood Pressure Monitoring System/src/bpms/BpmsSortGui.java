package bpms;

import java.awt.*;
import javax.swing.*;

//Gui για επιλογή τρόπου ταξινόμησης

public class BpmsSortGui extends JFrame{
	
	//Αρχικοποίηση των απαραίτητων κουμπιών, labels και text fields
	
	private static final long serialVersionUID = 1L;
	public static JFrame sortFrame;
	
	public static JButton      okButton       = new JButton (" OK ");
	public static JButton     sortTime        = new JButton ("Ταξινόμηση βάσει χρόνου καταχώρησης");
	public static JButton      sortId         = new JButton ("Ταξινόμηση βάσει αλφαβητικής σειράς");
	public static JButton    sortSystolic     = new JButton ("Ταξινόμηση βάσει συστολικής πίεση");
	public static JButton    sortDiastolic    = new JButton ("Ταξινόμηση βάσει διαστολικής πίεση");
	public static JButton      sortRate       = new JButton ("Ταξινόμηση βάσει σφυγμών ανά λεπτό");
	
	public static JLabel   status3Text   = new JLabel ("    ");
	
	//Constructor
	public BpmsSortGui(int userType) {
		//Δημιουργία του frame
		sortFrame = new CreateFrame("List Sort", 345, 270, 780, 450, Color.lightGray);
		
		//Δημιουργία layout και τοποθέτηση κουμπιών, labels και text fields
		sortFrame.getContentPane().setLayout(new FlowLayout());
		sortFrame.getContentPane().add(sortTime);
		sortFrame.getContentPane().add(sortId);
		sortFrame.getContentPane().add(sortSystolic);
		sortFrame.getContentPane().add(sortDiastolic);
		sortFrame.getContentPane().add(sortRate);	
		sortFrame.getContentPane().add(status3Text);
		sortFrame.getContentPane().add(okButton);
		
		//Καθορισμός ενεργειών των κουμπιών
		//Κάθε κουμπί αντιστοιχεί σε μία διαφορετική μέθοδο ταξινόμησης των στοιχείων
		if(userType == 1) {
			sortTime.addActionListener(event -> Entries.sortTime());
			sortId.addActionListener(event -> Entries.sortId());
			sortSystolic.addActionListener(event -> Entries.sortSystolic());
			sortDiastolic.addActionListener(event -> Entries.sortDiastolic());
			sortRate.addActionListener(event -> Entries.sortRate());
		}
		else {
			sortTime.addActionListener(event -> ClientEntries.sortTime());
			sortId.addActionListener(event -> ClientEntries.sortId());
			sortSystolic.addActionListener(event -> ClientEntries.sortSystolic());
			sortDiastolic.addActionListener(event -> ClientEntries.sortDiastolic());
			sortRate.addActionListener(event -> ClientEntries.sortRate());
		}
		
		okButton.addActionListener(event -> sortFrame.dispose());
	}
}
