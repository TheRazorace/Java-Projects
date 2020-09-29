package sb;

public class Entrance {
		
	static int en_queue = 0;
	static int en_limit = 3;
	
	//Μέθοδος εισόδου στη θέση 1 (entrance)
	public synchronized void reachEntrance(Person person) {
		
		//Όσο η ουρά είναι μεγαλύτερη ή ίση από το όριο, ο person περιμένει
		while(en_queue >= en_limit) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println("Exception:" + e.toString());
			}
		}
		
		//Έπειτα, συνεχίζει
		person.placePerson(person, 1);
		en_queue++;
	}
	
	//Μέθοδος εξόδου από τη θέση 1 (entrance)
	public synchronized void getInStore() {
		
		en_queue--;
		notifyAll();
	}
}
