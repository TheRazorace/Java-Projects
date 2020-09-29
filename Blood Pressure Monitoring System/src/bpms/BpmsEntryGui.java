package bpms;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

//Gui για καταχώρηση στοιχείων

public class BpmsEntryGui extends JFrame{
	
	//Αρχικοποίηση των απαραίτητων κουμπιών, labels και text fields
	
	private static final long serialVersionUID = 1L;
	public static JFrame entryFrame;
	
	public static JLabel       systolicLabel     = new JLabel ("  Συστολική Πίεση:     ");
	public static JLabel       diastolicLabel    = new JLabel ("  Διαστολική Πίεση:   ");
	public static JLabel       heartRateLabel    = new JLabel ("  Σφυγμοί ανα Λεπτό: ");
	public static JLabel        userIdLabel      = new JLabel ("        Επώνυμο:            ");
	
	public static JTextField  systolicTextField  = new JTextField(10);
	public static JTextField  diastolicTextField = new JTextField(10);
	public static JTextField  heartRateTextField = new JTextField(10);
	public static JTextField  userIdTextField    = new JTextField(10);
	
	public static JButton       submitButton     = new JButton ("Αποθήκευση");
	public static JButton       clearButton      = new JButton ("Εκκαθάριση");
	public static JLabel         statusText      = new JLabel ("   ");
	
	//Constructor 
	public BpmsEntryGui(int userType) {
		//Δημιουργία του frame
		entryFrame = new CreateFrame("Data Entry", 360, 250, 780, 450, Color.lightGray);
		
		//Δημιουργία layout και τοποθέτηση κουμπιών, labels και text fields
		entryFrame.getContentPane().setLayout(new FlowLayout());
		entryFrame.getContentPane().add(userIdLabel);
		entryFrame.getContentPane().add(userIdTextField);
		entryFrame.getContentPane().add(systolicLabel);
		entryFrame.getContentPane().add(systolicTextField);
		entryFrame.getContentPane().add(diastolicLabel);
		entryFrame.getContentPane().add(diastolicTextField);
		entryFrame.getContentPane().add(heartRateLabel);
		entryFrame.getContentPane().add(heartRateTextField);
		entryFrame.getContentPane().add(submitButton);
		entryFrame.getContentPane().add(clearButton);
		entryFrame.getContentPane().add(statusText);
		
		//Καθορισμός ενεργειών των κουμπιών
		
		if(userType == 1) {
			submitButton.addActionListener(event -> {
				try {
					ClientEntries.addToServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else {
			submitButton.addActionListener(event -> {
				try {
					ClientEntries.addAndSave();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		clearButton.addActionListener(event -> clear());
	}
	
	//Συνάρτηση καθαρισμού των πεδίων
	private void clear() {
		userIdTextField.setText("");
		systolicTextField.setText("");
		diastolicTextField.setText("");
		heartRateTextField.setText("");
	}
}
