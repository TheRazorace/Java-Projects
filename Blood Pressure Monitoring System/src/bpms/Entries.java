package bpms;

import java.util.LinkedList;
import java.io.*;
import java.io.IOException;
import java.util.Comparator;

//Κλάση που περιέχει τις απαραίτητες μεθόδους για τις διάφορες λειτουργίες του προγράμματος
public class Entries {

	// Η αποθήκευση των δεδομένων γίνεται μέσω της κλάσης LinkedList που υλοποιεί
	// διασυνδεδεμένη λίστα
	// Η λίστα list είναι η λίστα χρονολογικής ταξινόμησης
	// Οποιαδήποτε άλλη ταξινόμηση επιλεχθεί γίνεται στη λίστα listSorted

	public static LinkedList<Data> temp = new LinkedList<Data>();
	public static LinkedList<Data> list = new LinkedList<Data>();
	public static LinkedList<Data> listSorted = new LinkedList<Data>();

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
		BpmsServerGui.log.append("\nΤαξινόμηση: " + s + "\n" + categories);
		if (sortChoose == 0) {
			for (Data person : list) {
				BpmsServerGui.log.append(person + "\n");
			}
			BpmsServerGui.log.append("\n");
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
		for (Data person : listSorted) {
			BpmsServerGui.log.append(person + "\n");
		}
		BpmsServerGui.log.append("\n");

	}

	// Οι ταξινομήσεις γίνονται με την κλάση Comparator
	// Χρονολογική -> Τύπωση αρχισκής λίστας
	public static void sortTime() {
		sortType = "Χρονολογική";
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει χρόνου καταχώρησης ολοκληρώθηκε");
		sortChoose = 0;
	}

	// Bάσει αλφαβητικής σειρά
	public static void sortId() {
		listSorted.sort(Comparator.comparing(Data::getId));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει αλφαβητικής σειρά ολοκληρώθηκε");
		sortType = "Αλφαβητική";
		sortChoose = 1;
	}

	// Bάσει συστολικής πίεσης
	public static void sortSystolic() {
		listSorted.sort(Comparator.comparingInt(Data::getSystolic));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει συστολικής πίεσης ολοκληρώθηκε");
		sortType = "Αύξουσα συστολική πίεση";
		sortChoose = 2;
	}

	// Bάσει διαστολικής πίεσης
	public static void sortDiastolic() {
		listSorted.sort(Comparator.comparing(Data::getDiastolic));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει διαστολικής πίεσης ολοκληρώθηκε");
		sortType = "Αύξουσα διαστολική πίεση";
		sortChoose = 3;
	}

	// Bάσει σφυγμών ανά λεπτό
	public static void sortRate() {
		listSorted.sort(Comparator.comparing(Data::getRate));
		BpmsSortGui.status3Text.setText("Η ταξινόμηση βάσει σφυγμών ανά λεπτό ολοκληρώθηκε");
		sortType = "Αύξον αριθμός σφυγμών ανά λεπτό";
		sortChoose = 4;
	}

	// Μέθοδος νέας καταχώρησης
	public static void add() {
		BpmsEntryGui.statusText.setText("");

		try {
			// Καλείται ο constructor της Data
			Data person = new Data(BpmsEntryGui.userIdTextField.getText(), BpmsEntryGui.systolicTextField.getText(),
					BpmsEntryGui.diastolicTextField.getText(), BpmsEntryGui.heartRateTextField.getText());

			// Αν δεν επιστρέψει κάποια null τιμή σημαίνει ότι τα δεδομένα ήταν έγκυρα και
			// μπορύν να προστεθούν
			if (person.userId != null) {
				list.add(person);
				listSorted.add(person);
				display(sortType);
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

		for (Data person : list) {
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
		if (list.size() == 0) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχουν καταχωρήσεις.");
		}

		// Έλεγχος αν υπάρχει καταχώρηση με το όνομα που δίνει ο χρήστης
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχει καταχώρηση με αυτό το επώνυμο.");
		}

		else {

			// Σκανάρισμα λίστας και εύρεση επωνύμου
			BpmsServerGui.log.append("Αναζητούμενη καταχώρηση:\n" + categories);
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// Αποθήκευση θέσης επωνύμου
					index = i;

					// Εύρεση όων των άλλων στοιχείων της καταχώρησης με στιγμιότυπο person και
					// εκτύπωση
					for (Data person : list) {
						if (s == index) {
							BpmsServerGui.log.append(person + "\n");
							BpmsSearchGui.status2Text.setText("Η καταχώρηση βρέθηκε επιτυχώς.");

						}
						s++;
					}
					s = 0;
				}
			}
			BpmsServerGui.log.append("\n");
		}

	}

	// Μέθοδος διαγραφής στοιχείου
	public static void delete() throws IOException {
		BpmsSearchGui.status2Text.setText("");

		// Έλεγχος αν η λίστα είναι κενή
		if (list.size() == 0) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχουν καταχωρήσεις.");
		}

		// 'Ελεγχος αν υπάρχει το επώνυμο
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("Σφάλμα! Δεν υπάρχει καταχώρηση με αυτό το επώνυμο.");
		}

		// Σκανάρισμα λίστας και εύρεση επωνύμου
		else {
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// Διαγραφή και εκτύπωση της υπόλοιπης λίστας
					list.remove(i);
					listSorted.remove(i);
					display(sortType);
					BpmsSearchGui.status2Text.setText("Η καταχώρηση διαγράφηκε επιτυχώς.");

				}
			}
			BpmsSearchGui.searchTextField.setText("");
			saveToFile();
		}
	}
	
	//Αποθήκευση δεδομένων σε φάκελο
	public static void saveToFile() throws IOException {

		if (BpmsServerGui.fileField.getText().length() == 0) {
			BpmsServerGui.log.append("\nΣφάλμα! Συμπληρώστε το όνομα του φακέλου.\n");
		}

		else {
			try {
				FileOutputStream fos = new FileOutputStream(BpmsServerGui.fileField.getText());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				fos.close();
				BpmsServerGui.log.append("\nΟι μετρήσεις αποθηκεύτηκαν επιτυχώς στο Server Book!\n");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	//Φόρτωση δεδομένων από φάκελο
	@SuppressWarnings("unchecked")
	public static void loadFromFile() {

		// Έλεγχος αν η λίστα είναι κενή
		if (list.size() == 0) {
			BpmsServerGui.log.append("\nΣφάλμα! Δεν υπάρχουν καταχωρήσεις.\n\n");
		}

		else if (BpmsServerGui.fileField.getText().length() == 0) {
			BpmsServerGui.log.append("\nΣφάλμα! Συμπληρώστε το όνομα του φακέλου.\n\n");
		}

		try {
			FileInputStream fis = new FileInputStream(BpmsServerGui.fileField.getText());
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (LinkedList<Data>) ois.readObject();
			listSorted = list;
			ois.close();
			BpmsServerGui.log.append("\nΟι μετρήσεις φορτώθηκαν επιτυχώς από το Server Book!\n\n");
		} catch (Exception e) {
			BpmsServerGui.log.append("\nΣφάλμα! Δεν βρέθηκε αρχείο με τέτοιo όνομα.\n\n");
			System.out.println(e);
		}

	}
	
	//Εκκαθάριση μετρήσεων
	public static void clearBook() throws IOException {
		list = new LinkedList<Data>();
		listSorted = new LinkedList<Data>();
		saveToFile();
		BpmsServerGui.log.append("\nΤο βιβλίο μετρήσεων εκκαθαρίστηκε!\n");
	}
	
	//Φόρτωση μετρήσεων από τον client
	public static void loadFromClient() throws ClassNotFoundException, IOException {
		StartClient.clientSendList();
		saveToFile();
	}
	
	//Eύρεση μετρήσεων που ζητάει ο client
	public static void findName(String name) {

		// Στην μεταβλητή index αποθηκεύεται η θέση της αναζητουμένης καταχώρησης
		int index;
		int s = 0;

		// Έλεγχος αν η λίστα είναι κενή
		if (list.size() == 0) {
			BpmsServerGui.log.append("Σφάλμα! Δεν υπάρχουν καταχωρήσεις.");
		}

		// Έλεγχος αν υπάρχει καταχώρηση με το όνομα που δίνει ο χρήστης
		else if (isInList(name) == false) {
			BpmsServerGui.log.append("Σφάλμα! Δεν υπάρχει καταχώρηση με αυτό το επώνυμο.");
		}

		else {

			// Σκανάρισμα λίστας και εύρεση επωνύμου
			BpmsServerGui.log.append("Αναζητούμενη καταχώρηση:\n" + categories);
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// Αποθήκευση θέσης επωνύμου
					index = i;

					// Εύρεση όων των άλλων στοιχείων της καταχώρησης με στιγμιότυπο person και
					// εκτύπωση
					for (Data person : list) {
						if (s == index) {
							BpmsServerGui.log.append(person + "\n");
							temp.add(person);
							BpmsSearchGui.status2Text.setText("Η καταχώρηση βρέθηκε επιτυχώς.");

						}
						s++;
					}
					s = 0;
				}
			}
			BpmsServerGui.log.append("\n");
		}
	}
	
	//Διαγραφή μετρήσεων που ζητάει ο client
	public static void deleteName(String name) throws IOException {

		// Έλεγχος αν η λίστα είναι κενή
		if (list.size() == 0) {
			BpmsServerGui.log.append("Σφάλμα! Δεν υπάρχουν καταχωρήσεις.");
		}

		// 'Ελεγχος αν υπάρχει το επώνυμο
		else if (isInList(name) == false) {
			BpmsServerGui.log.append("Σφάλμα! Δεν υπάρχει καταχώρηση με αυτό το επώνυμο.");
		}

		// Σκανάρισμα λίστας και εύρεση επωνύμου
		else {
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(name) == 0) {

					// Διαγραφή και εκτύπωση της υπόλοιπης λίστας
					list.remove(i);
					listSorted.remove(i);
					display(sortType);
					BpmsServerGui.log.append("Η καταχώρηση διαγράφηκε επιτυχώς.");

				}
			}
			saveToFile();
		}
	}

}
