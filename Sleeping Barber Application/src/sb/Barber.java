package sb;

import java.awt.Button;
import java.awt.Color;
import gui.BarberGui;

public class Barber extends Thread{
	Button barberIcon = new Button();
	WaitingRoom wr;
	private BarberGui gui;
	
	int haircutsTillBreak = 5;
	int currentHaircuts = 0;
	boolean available = false;
	
	Barber(BarberGui gui, WaitingRoom wr, Color color){
		barberIcon.setBackground(color);
		this.gui = gui;
		this.wr = wr;
	}
	
	//Mέθοδος run() του barber
	public void run() {
		while(true) {
			
			checkIfStoreIsEmpty();
			threadSleep(1000);
		}
	}
	
	//Μέθοδος για διάλειμμα του barber
	public void takeAbreak() {
		
		moveToBreakPosition();
		announceLimitChange(3);
		available = false;
		
		threadSleep(2000);
		
		returnToWork();
	}
	
	//Μέθοδος για επιστροφή στη δουλειά του barber
	public void returnToWork() {
		
		currentHaircuts = 0;
		moveToWorkPosition();
		announceLimitChange(4);
		available = true;
	}
	
	//Μέθοδος για έλεγχο του αν έχει έρθει η ώρα για διάλειμμα
	public void checkIfItsBreakTime() {
		
		if(currentHaircuts == haircutsTillBreak-1){
			announceLimitChange(3);
		}
		if(currentHaircuts == haircutsTillBreak){
			takeAbreak();
		}
	}
	
	//Μέθοδος κουρέματος που επιτελεί απλά ένα sleep()
	public void cutMyHair(int time) {

		threadSleep(time);
	}
	
	//Μέθοδος ενημέρωσης των πελατών για αλλαγή του ορίου στο waiting room, ώστε να μπορεί να ξεκουραστεί εκεί ο barber
	public void announceLimitChange(int newLimit) {
		
		WaitingRoom.wr_limit = newLimit;
	}
	
	//Μέθοδος τοποθέτησης barber στην θέση για διάλειμμα
	public void moveToBreakPosition() {
		
		barberIcon.setBounds(215,200, 20, 40);
		gui.add(barberIcon);
	}
	
	//Μέθοδος τοποθέτησης barber στην θέση εργασίας
	public void moveToWorkPosition() {
		
		barberIcon.setBounds(271,57, 30, 30);
		gui.add(barberIcon);
	}
	
	//Συνάρτηση που κάνει το sleep()
	public void threadSleep(int time) {
		
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Έλεγχος αν η ουρά στο waiting room είναι κενή ώστε να κάνει διάλειμμα ο κουρέας
	public void checkIfStoreIsEmpty() {
		
		if(WaitingRoom.wr_queue == 0) {
			takeAbreak();
		}
	}

}

	