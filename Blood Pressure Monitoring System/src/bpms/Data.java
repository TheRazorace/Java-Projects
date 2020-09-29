package bpms;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

//Κλάση επεξεργασίας των δεδομένων

public class Data implements Serializable{
	
	//Αρχικοποίση των δεδομένων σε μορφή String
	//Χρησιμοποίηση των κλάσεων LocalDate και LocalTime για εύρεση πραγματικής τοπικής ημερομηνίας και ώρας
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private LocalTime time;
	
	private String systolicBp;
	private String diastolicBp;
	private String heartRate;
	public  String userId;
	private String dateString;
	private String timeString;
	
	//Boolean μεταβλητή για έλεγχο εγκυρότητας των δεδομένων που εισάγονται από τον χρήστη
	private boolean correctData;
	
	//Constructor no.1 που δίνει κενά Strings στα δεδομένα
	public Data() {
		dateString = "";
		timeString = "";
		systolicBp = "";
		diastolicBp = "";
		heartRate = "";
		userId = "";
	}
	
	//Constructor no.2 που θέτει στα δεδομένα τις τιμές που δίνει ο χρήστης
	public Data(String userId, String systolicBp, String diastolicBp, String heartRate) throws DataException{
		
		//Η τιμή του correctData καθορίζεται από την συνάρτηση dataEvaluation
		correctData = dataEvaluation(userId, systolicBp, diastolicBp, heartRate);
		
		//Αν τα δεδομένα είναι έγκυρα ο constructor μπορεί να συνεχίσει και να αποθηκεύσει τις τιμές
		if (correctData == true) {
			this.userId = userId;
			this.systolicBp = systolicBp;
			this.diastolicBp = diastolicBp;
			this.heartRate = heartRate;
			
			//Εύρεση πραγματηκής ημερομηνίας και ώρας...
			date = LocalDate.now();
			time = LocalTime.now();
			
			//... και μετατροπή σε επιθυμητή μορφή με την κλάση DateTimeFormatter
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.dateString = date.format(formatter1);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
			this.timeString = time.format(formatter2);
		}	
	}
	
	//Συνάρτηση που εξετάζει την εγκυρότητα των στοιχείων που δίνονται
	private boolean dataEvaluation(String userId, String systolicBp, String diastolicBp, String heartRate) {
		
		//Σβήσιμο κενών ανάμεσα στα δεδομένα με την συνάρτηση trim
		userId.trim();
		systolicBp.trim();
		diastolicBp.trim();
		heartRate.trim();
		
		//Η μεταβλητή permit δίχενι την εγκυρότητα
		//Αρχικά τίθεται true
		//Αν υπάρχει κάποιο λάθος, γίνεται false και εξηγείται στον χρήστη ο λόγος που τα δεδομένα είναι μη έγκυρα
		boolean permit = true;
		
		//Έλεγχος αν κάποια από τα πεδία δεδομένων είναι κενά
		if((userId.length() == 0) || (systolicBp.length() == 0) ||
				(diastolicBp.length() == 0) || (heartRate.length() == 0)){
			permit = false;
			BpmsEntryGui.statusText.setText("Σφάλμα! Τα πεδία δεν πρέπει να είναι κενά.");	
		}
		
		//Έλεγχος αν το πλαίσιο επωνύμου περίχει μόνο γράμματα. Αν όχι, είναι μη έγκυρο
		char[] idChars = userId.toCharArray();
	    for(char c : idChars){
	        if(!Character.isLetter(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("Σφάλμα! Το επώνυμο πρέπει να περιέχει μόνο γράμματα.");
	        }
	    }
	    
	    //Έλεγχος αν το πλαίσιο συστολικής πίεσης περίχει μόνο αριθμούς. Αν όχι, είναι μη έγκυρο
		char[] sysChars = systolicBp.toCharArray();
	    for(char c : sysChars){
	        if(!Character.isDigit(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("Σφάλμα! Η πίεση πρέπει να αναγράφεται με αριθμούς.");
	        }
	    }
	    
	    //Έλεγχος αν το πλαίσιο διαστολικής πίεσης περίχει μόνο αριθμούς. Αν όχι, είναι μη έγκυρο
		char[] diaChars = diastolicBp.toCharArray();
	    for(char c : diaChars){
	        if(!Character.isDigit(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("Σφάλμα! Η πίεση πρέπει να αναγράφεται με αριθμούς.");
	        }
	    }
	    
	    //Έλεγχος αν το πλαίσιο σφυγμών ανά λεπτό περίχει μόνο αριθμούς. Αν όχι, είναι μη έγκυρο
		char[] rateChars = heartRate.toCharArray();
	    for(char c : rateChars){
	        if(!Character.isDigit(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("Σφάλμα! Οι σφυγμοί πρέπει να αναγράφονται με αριθμούς.");
	        }
	    }
	    
	    //Επιστροφή της μεταβλητής 
	    return permit;
	}
	
	//Get και Set συναρτήσεις
	//Χρησιμεύουν για τις sort μεθόδους και κάποιες είναι για μελλοντικές εκδόσεις
	public int getSystolic() {
		return Integer.parseInt(systolicBp);
	}
	
	public int getDiastolic() {
		return Integer.parseInt(diastolicBp);
	}
	
	public int getRate() {
		return Integer.parseInt(heartRate);
	}
	
	public String getId() {
		return userId.toUpperCase();
	}
	
	public void setSystolic(String systolicBp) {
		this.systolicBp = systolicBp;
	}
	
	public void setDiastolic(String diastolicBp) {
		this.diastolicBp = diastolicBp;
	}
	
	public void setRate(String heartRate) {
		this.heartRate = heartRate;
	}
	
	public void setId(String userId) {
		this.userId = userId;
	}
	
	//Μέθοδος για επιθυμητή εκτύπωση των περιεχομένων της διασυνδεδεμένης λίστας στην οποία αποθηκεύονται τα δεδομένα
	public String toString() {
		return userId + "\t\t\t|" + systolicBp + "\t\t\t\t|" + diastolicBp + "\t\t\t\t|" + heartRate + "\t\t\t|" + dateString + "\t|" + timeString ;
	}
}
