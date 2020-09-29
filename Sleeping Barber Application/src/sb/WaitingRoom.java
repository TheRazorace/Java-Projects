package sb;


public class WaitingRoom {
	static int wr_queue = 0;
	static int wr_limit = 3;
	
	//Μέθοδος εισόδου στη θέση 2 (waiting room)
	public synchronized void enterWaitingRoom(Person person, Entrance en) {
		
		//Όσο η ουρά είναι μεγαλύτερη ή ίση από το όριο, ο person περιμένει
		while(wr_queue >= wr_limit) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println("Exception:" + e.toString());
			}
		}
		
		//Έπειτα, συνεχίζει
		person.placePerson(person, 2);
		en.getInStore();
		wr_queue++;
	}
	
	//Μέθοδος εξόδου από τη θέση 2 (waiting room)
	public synchronized void exitWaitingRoom() {
		
		wr_queue--;
		notifyAll();
	}
}
