package bpms;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

//Gui για menu

public class BpmsClientGui extends JFrame {

	// Δημιουργία στιγμιοτύπων των υπόλοιπων gui που καλούνται από το menu gui

	private static final long serialVersionUID = 1L;
	public static JFrame clientFrame;
	public static BpmsEntryGui entry;
	public static BpmsEntryGui entry2;
	public static BpmsSortGui sort;
	public static BpmsSearchGui search;
	public static BpmsSearchGui search2;
	public static BpmsSearchGui search3;
	public static BpmsSearchGui search4;

	// Αρχικοποίηση των απαραίτητων

	public static MenuLabel title1;
	public static MenuLabel title2;
	public static MenuLabel title3;
	public static MenuButton buttonInsert;
	public static MenuButton buttonFind;
	public static MenuButton buttonDelete;

	public static MenuButton buttonDisplay;
	public static MenuButton buttonSort;
	public static MenuButton buttonLoadServerBook;
	public static MenuButton buttonClearLocalBook;
	public static MenuButton buttonClearDisplay;

	public static MenuButton buttonServerBookLoad;
	public static MenuButton buttonServerBookSave;
	public static MenuButton buttonServerBookClear;
	public static MenuButton buttonServerInsert;
	public static MenuButton buttonServerFind;
	public static MenuButton buttonServerDelete;

	public static MenuLabel fileLabel;
	public static MenuTextField fileField;

	public static TextArea log = new TextArea("", 10, 1, TextArea.SCROLLBARS_BOTH);

	BpmsClientGui(){

		// Δημιουργία του frame
		clientFrame = new CreateFrame("Blood Pressure Book Client", 1337, 700, 20, 20, Color.GREEN);

		// Δημιουργία layout και τοποθέτηση κουμπιών και text areas
		title1 = new MenuLabel("BPMeasurement", 165, 20, 200, 40, clientFrame);
		buttonInsert = new MenuButton("Προσθήκη μέτρησης ", 30, 50, 400, 30, clientFrame);
		buttonFind = new MenuButton("Εύρεση μέτρησης", 30, 85, 400, 30, clientFrame);
		buttonDelete = new MenuButton("Διαγραφή μέτρησης", 30, 120, 400, 30, clientFrame);

		title2 = new MenuLabel("Local BPBook", 175, 150, 100, 40, clientFrame);
		buttonDisplay = new MenuButton("Εμφάνιση Local Book", 30, 185, 400, 30, clientFrame);
		buttonSort = new MenuButton("Ταξινόμησηση Local Book", 30, 220, 400, 30, clientFrame);
		buttonLoadServerBook = new MenuButton("Φόρτωση του Server Book", 30, 255, 400, 30, clientFrame);
		buttonClearLocalBook = new MenuButton("Εκκαθάριση Local Book", 30, 290, 400, 30, clientFrame);
		buttonClearDisplay = new MenuButton("Εκκαθάριση οθόνης μετρήσεων", 30, 325, 400, 30, clientFrame);

		title3 = new MenuLabel("Server BPBook", 175, 355, 100, 40, clientFrame);
		buttonServerBookLoad = new MenuButton("Φόρτωση Local Book στον Server", 30, 390, 400, 30, clientFrame);
		buttonServerBookSave = new MenuButton("Φόρτωση Server Book στον client", 30, 425, 400, 30, clientFrame);
		buttonServerBookClear = new MenuButton("Εκκαθάριση Server Book", 30, 460, 400, 30, clientFrame);
		buttonServerInsert = new MenuButton("Προσθήκη μέτρησης στο Server Book", 30, 495, 400, 30, clientFrame);
		buttonServerFind = new MenuButton("Εύρεση μέτρησης στο Server Book", 30, 530, 400, 30, clientFrame);
		buttonServerDelete = new MenuButton("Διαγραφή μέτρησης του Server Book", 30, 565, 400, 30, clientFrame);

		fileLabel = new MenuLabel("Όνομα Local Book:", 460, 600, 150, 40, clientFrame);
		fileField = new MenuTextField(600, 600, 400, 40, clientFrame);
		fileField.setText("localBook.bpms");

		log.setBounds(450, 20, 850, 550);
		clientFrame.add(log);
		log.setEditable(false);

		// Καθορισμός ενεργειών των κουμπιών

		buttonInsert.addActionListener(event -> entry = new BpmsEntryGui(2));
		buttonFind.addActionListener(event -> search = new BpmsSearchGui(2, false));
		buttonDelete.addActionListener(event -> search2 = new BpmsSearchGui(2, true));

		buttonDisplay.addActionListener(event -> ClientEntries.display(ClientEntries.sortType));
		buttonSort.addActionListener(event -> sort = new BpmsSortGui(2));
		buttonLoadServerBook.addActionListener(event -> {
			try {
				ClientEntries.loadFromServer();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		buttonClearLocalBook.addActionListener(event -> {
			try {
				ClientEntries.clearBook();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		buttonClearDisplay.addActionListener(event -> log.setText(""));
		
		buttonServerBookLoad.addActionListener(event -> {
			try {
				ClientEntries.loadToServer();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		});
		buttonServerBookSave.addActionListener(event -> {
			try {
				ClientEntries.loadFromServer();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		
		buttonServerBookClear.addActionListener(event -> {
			try {
				StartClient.clientClearServerBook();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});		
		buttonServerInsert.addActionListener(event -> entry2 = new BpmsEntryGui(1));
		buttonServerFind.addActionListener(event -> search3 = new BpmsSearchGui(3, false));
		buttonServerDelete.addActionListener(event -> search4 = new BpmsSearchGui(3, true));

	}

}
