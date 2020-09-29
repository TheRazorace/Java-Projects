package bpms;

import java.awt.*;
import java.util.LinkedList;
import java.io.*;
import javax.swing.*;

//Gui για menu

public class BpmsServerGui extends JFrame {
	public static LinkedList<Data> l = new LinkedList<Data>();

	// Δημιουργία στιγμιοτύπων των υπόλοιπων gui που καλούνται από το menu gui

	private static final long serialVersionUID = 1L;
	public static JFrame mainFrame;
	public static BpmsSortGui sort;
	public static BpmsSearchGui search;

	// Αρχικοποίηση των απαραίτητων

	public static MenuLabel title1;
	public static MenuButton buttonShow;
	public static MenuButton buttonSort;
	public static MenuButton buttonFind;
	public static MenuButton buttonDel;
	public static MenuButton buttonLoadFromClient;
	public static MenuButton buttonLoad;
	public static MenuButton buttonClearBook;
	public static MenuButton buttonClearDisplay;

	public static MenuLabel fileLabel;
	public static JTextField fileField;

	public static TextArea log = new TextArea("", 10, 1, TextArea.SCROLLBARS_BOTH);

	// Constructor
		BpmsServerGui() {
		//Δημιουργία του frame
		mainFrame = new CreateFrame("Blood Pressure Book Server", 1337, 700, 10, 10, Color.BLUE);
		
		//Δημιουργία layout και τοποθέτηση κουμπιών και text areas
		title1 = new MenuLabel("Blood Pressure Book", 163, 20, 200, 30, mainFrame);
		buttonShow = new MenuButton("Εμφάνιση BPBook ", 30, 60, 400, 30, mainFrame);
		buttonSort = new MenuButton("Ταξινόμηση BPBook", 30, 100, 400, 30, mainFrame);
		buttonFind = new MenuButton("Εύρεση μέτρησης στο BPBook", 30, 140, 400, 30, mainFrame);
		buttonDel = new MenuButton("Διαγραφή μέτρησης από το BPBook", 30, 180, 400, 30, mainFrame);
		buttonLoadFromClient = new MenuButton("Προσθήκη μετρήσεων από Client", 30, 220, 400, 30, mainFrame);
		buttonLoad = new MenuButton("Φόρτωση μετρήσεων από αρχείο", 30, 260, 400, 30, mainFrame);
		buttonClearBook = new MenuButton("Εκκαθάριση BPBook", 30, 300, 400, 30, mainFrame);
		buttonClearDisplay= new MenuButton("Εκκαθάριση οθόνης μετρήσεων", 30, 340, 400, 30, mainFrame);
		
		fileLabel = new MenuLabel("Όνομα Server Book:", 460, 600, 150, 40, mainFrame);
		fileField = new MenuTextField(600, 600, 400, 40, mainFrame);
		fileField.setText("serverBook.bpms");
		
		
		log.setBounds(450, 20, 850, 550);
		mainFrame.add(log);
		log.setEditable(false);
		
		
		//Καθορισμός ενεργειών των κουμπιών
		
		buttonShow.addActionListener(event -> Entries.display(Entries.sortType));
		buttonSort.addActionListener(event -> sort = new BpmsSortGui(1));
		buttonFind.addActionListener(event -> search = new BpmsSearchGui(1, false));
		buttonDel.addActionListener(event -> search = new BpmsSearchGui(1, true));
		buttonLoadFromClient.addActionListener(event -> {
			try {
				Entries.loadFromClient();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		buttonLoad.addActionListener(event -> Entries.loadFromFile());
		buttonClearBook.addActionListener(event -> {
			try {
				Entries.clearBook();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		buttonClearDisplay.addActionListener(event -> log.setText(""));
		}
		
}		
		

