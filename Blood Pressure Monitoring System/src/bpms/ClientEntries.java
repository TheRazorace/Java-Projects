package bpms;

import java.util.LinkedList;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Comparator;

//Κλάση που περιέχει τις απαραίτητες μεθόδους για τις διάφορες λειτουργίες του προγράμματος
public class ClientEntries {

	// Η αποθήκευση των δεδομένων γίνεται μέσω της κλάσης LinkedList που υλοποιεί
	// διασυνδεδεμένη λίστα
	// Η λίστα list είναι η λίστα χρονολογικής ταξινόμησης
	// Οποιαδήποτε άλλη ταξινόμηση επιλεχθεί γίνεται στη λίστα listSorted
	
	public static LinkedList<Data> measurement = new LinkedList<Data>();
	public static LinkedList<Data> clientList = new LinkedList<Data>();
	public static LinkedList<Data> clientListSorted = new LinkedList<Data>();

	// Δήλωση δύο μεταβλητών που δείχνουν τον τύπο της ταξινόμησης
	public static String sortType = "Χρονολογική";
	public static int sortChoose = 0;

	// Δημιουργία του String που εμφανίζεται στο πάνω μέρος της οθόνης και δείχνει
	// τις κατηγορίες
	public static String categories = "Επώνυμο" + "\t\t|" + "Συστολική πίεση (mmHg)" + "\t\t|"
			+ "Διαστολική πίεση (mmHg)" + "\t\t|" + "Σφυγμοί ανά λεπτό" + "\t|" + "Ημερομηνία" + "\t|" + "Ώρα" + "\n";

	// Μέθοδος εκτύπωσης των καταχωρήσεων στον main gui
	// Ανάλογα με την επιλεγμένη από τον χρήστη ταξινόμησηση, γίνεται η απαραίτητη
	// ταξινόμηση και εκτυπώνεται η λίστα
	public static void display(String s) {
		BpmsClientGui.log.append("\nΤαξινόμηση: " + s + "\n" + categories);
		if (sortChoose == 0) {
			for (Data person : clientList) {
				BpmsClientGui.log.append(person + "\n");
			}
			BpmsClientGui.log.append("\n");
			return;
		} else if (sortChoose == 1) {
			sortId();
		} else if (sortChoose == 2) {
			sortSystolic();
		} else if (sortChoose == 3) {
			sortDiastolic();
		} else if (sortChoose == 4) {
			sortRate();
		}
		for (Data person : clientListSorted) {
			BpmsClientGui.log.append(person + "\n");
		}
		BpmsClientGui.log.append("\n");

	}

	// Οι ταξινομήσεις γίνονται με την κλάση Comparator
	// Χρονολογική -> Τύπωση αρχικής λίστας
	public static void sortTime() {
		sortType = "Χρονολογική";
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει χρόνου καταχώρησης ολοκληρώθηκε");
		sortChoose = 0;
	}

	// Bάσει αλφαβητικής σειρά
	public static void sortId() {
		clientListSorted.sort(Comparator.comparing(Data::getId));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει αλφαβητικής σειρά ολοκληρώθηκε");
		sortType = "Αλφαβητική";
		sortChoose = 1;
	}

	// Bάσει συστολικής πίεσης
	public static void sortSystolic() {
		clientListSorted.sort(Comparator.comparingInt(Data::getSystolic));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει συστολικής πίεσης ολοκληρώθηκε");
		sortType = "Αύξουσα συστολική πίεση";
		sortChoose = 2;
	}

	// Bάσει διαστολικής πίεσης
	public static void sortDiastolic() {
		clientListSorted.sort(Comparator.comparing(Data::getDiastolic));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει διαστολικής πίεσης ολοκληρώθηκε");
		sortType = "Αύξουσα διαστολική πίεση";
		sortChoose = 3;
	}

	// Bάσει σφυγμών ανά λεπτό
	public static void sortRate() {
		clientListSorted.sort(Comparator.comparing(Data::getRate));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει σφυγμών ανά λεπτό ολοκληρώθηκε");
		sortType = "Αύξον αριθμός σφυγμών ανά λεπτό";
		sortChoose = 4;
	}

	// Μέθοδος νέας καταχώρησης
	public static void add(int addType) {
		BpmsEntryGui.statusText.setText("");

		try {
			// Καλείται ο constructor της Data
			Data person = new Data(BpmsEntryGui.userIdTextField.getText(), BpmsEntryGui.systolicTextField.getText(),
					BpmsEntryGui.diastolicTextField.getText(), BpmsEntryGui.heartRateTextField.getText());

			// Αν δεν επιστρέψει κάποια null τιμή σημαίνει ότι τα δεδομένα ήταν έγκυρα και
			// μπορύν να προστεθούν
			if (person.userId != null) {
				if(addType == 1) {
					clientList.add(person);
					clientListSorted.add(person);
					display(sortType);
				}
				else {
					measurement.add(person);
				}		
				BpmsEntryGui.userIdTextField.setText("");
				BpmsEntryGui.systolicTextField.setText("");
				BpmsEntryGui.diastolicTextField.setText("");
				BpmsEntryGui.heartRateTextField.setText("");
				BpmsEntryGui.statusText.setText("");
			}

		} // Εύρεση εξαίρεσης
		catch (DataException error) {
			BpmsEntryGui.statusText.setText(error.toString());
		}
		BpmsEntryGui.statusText.setText("");

	}

	// Μέθοδος εύρεσης αν το στοιχείο είναι ήδη στη λίστα με ένα for loop
	private static boolean isInList(String idString) {
		boolean inList = false;

		for (Data person : clientList) {
			if (person.getId().compareToIgnoreCase(idString) == 0) {
				inList = true;
			}
		}
		return inList;
	}

	// Μέθοδος εύρεσης καταχώρησης και εκτύοωση αποκλειστικά αυτής
	public static void find() {
		BpmsSearchGui.status2Text.setText("");

		// Στην μεταβλητή index αποθηκεύεται η θέση της αναζητουμένης καταχώρησης
		int index;
		int s = 0;

		// Έλεγχος αν η λίστα είναι κενή
		if (clientList.size() == 0) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχουν καταχωρήσεις.");
		}

		// Έλεγχος αν υπάρχει καταχώρηση με το όνομα που δίνει ο χρήστης
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχει καταχώρηση με αυτό το επώνυμο.");
		}

		else {

			// Σκανάρισμα λίστας και εύρεση επωνύμου
			BpmsClientGui.log.append("Αναζητούμενη καταχώρηση:\n" + categories);
			for (int i = 0; i < clientList.size(); i++) {
				String current = clientList.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// Αποθήκευση θέσης επωνύμου
					index = i;

					// Εύρεση όων των άλλων στοιχείων της καταχώρησης με στιγμιότυπο person και
					// εκτύπωση
					for (Data person : clientList) {
						if (s == index) {
							BpmsClientGui.log.append(person + "\n");
							BpmsSearchGui.status2Text.setText("Η καταχώρηση βρέθηκε επιτυχώς.");
							
						}
						s++;
					}
					s = 0;
				}
			}
			BpmsClientGui.log.append("\n");
		}

	}

	// Μέθοδος διαγραφής στοιχείου
	public static void delete() throws IOException {
		BpmsSearchGui.status2Text.setText("");

		// Έλεγχος αν η λίστα είναι κενή
		if (clientList.size() == 0) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχουν καταχωρήσεις.");
		}

		// 'Ελεγχος αν υπάρχει το επώνυμο
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχει καταχώρηση με αυτό το επώνυμο.");
		}

		// Σκανάρισμα λίστας και εύρεση επωνύμου
		else {
			for (int i = 0; i < clientList.size(); i++) {
				String current = clientList.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// Διαγραφή και εκτύπωση της υπόλοιπης λίστας
					clientList.remove(i);
					clientListSorted.remove(i);
					display(sortType);
					BpmsSearchGui.status2Text.setText("Η καταχώρηση διαγράφηκε επιτυχώς.");

				}
			}BpmsSearchGui.searchTextField.setText("");
			saveToFile();
		}
	}
	
	//Αποθήκευση δεδομένων σε φάκελο
	public static void saveToFile() throws IOException {

		if (BpmsClientGui.fileField.getText().length() == 0) {
			BpmsClientGui.log.append("\nΣφάλμα! Συμπληρώστε το όνομα του φακέλου.\n");
		}

		else {
			try {
				FileOutputStream fos = new FileOutputStream(BpmsClientGui.fileField.getText());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(clientList);
				fos.close();
				BpmsClientGui.log.append("\nΟι μετρήσεις αποθηκεύτηκαν επιτυχώς στο Local Book!\n");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	//Φόρτωση δεδομένων από φάκελο
	@SuppressWarnings("unchecked")
	public static void loadFromFile() {

		// Έλεγχος αν η λίστα είναι κενή
		if (clientList.size() == 0) {
			BpmsClientGui.log.append("\nΣφάλμα! Δεν υπάρχουν καταχωρήσεις.\n");
		}

		else if (BpmsClientGui.fileField.getText().length() == 0) {
			BpmsClientGui.log.append("\nΣφάλμα! Συμπληρώστε το όνομα του φακέλου.\n");
		}

		try {
			FileInputStream fis = new FileInputStream(BpmsClientGui.fileField.getText());
			ObjectInputStream ois = new ObjectInputStream(fis);
			clientList = (LinkedList<Data>) ois.readObject();
			clientListSorted = clientList;
			ois.close();
			display(sortType);
			BpmsClientGui.log.append("\nΟι μετρήσεις φορτώθηκαν επιτυχώς από το Local Book!\n");
		} catch (Exception e) {
			BpmsClientGui.log.append("\nΣφάλμα! Δεν βρέθηκε αρχείο με τέτοιo όνομα.\n");
			System.out.println(e);
		}

	}
	
	//Εκκαθάριση μετρήσεων
	public static void clearBook() throws IOException {
		clientList = new LinkedList<Data>();
		clientListSorted = new LinkedList<Data>();
		saveToFile();
		BpmsClientGui.log.append("\nΤο βιβλίο μετρήσεων εκκαθαρίστηκε!\n");
	}
	
	//Προσθήκη μετρήσεων τοπικά και αποθήκευση σε φάκελο
	public static void addAndSave() throws IOException {
		add(1);
		saveToFile();
	}
	
	//Φόρτωση αρχείου μετρήσεων από τον server
	public static void loadFromServer() throws UnknownHostException, IOException, ClassNotFoundException {
		StartClient.clientGetList();
		saveToFile();
	}
	
	//Φόρτωση μετρήσεων στον server
	public static void loadToServer() throws ClassNotFoundException, IOException {
		StartClient.clientSendList();
	}
	
	//Προσθήκη μέτρησης στον server
	public static void addToServer() throws UnknownHostException, IOException {
		add(2);
		StartClient.clientSendMeasurement();
		measurement = new LinkedList<Data>();
	}

}
