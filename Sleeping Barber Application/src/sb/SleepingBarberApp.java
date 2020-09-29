package sb;

import java.awt.Color;
import gui.BarberGui;

public class SleepingBarberApp {
	static BarberGui gui;
	
	//Κλάσεις για Α.Α στις θέσεις 1, 2, 3 αντίστοιχα
	static Entrance    en  = new Entrance();
	static WaitingRoom wr  = new WaitingRoom();
	static ServiceRoom sr  = new ServiceRoom();

	public static void main(String[] args) {
		gui = new BarberGui();
		gui.setBackground(Color.darkGray);
		
		generateThreads();
	}
	
	public static void generateThreads() {
		
		//Δημιουργία νημάτων
		Barber barber   = new Barber(gui, wr, Color.green);
		Person p1  = new Person(0, Color.white, gui, 1200, barber, en, wr, sr);
		Person p2  = new Person(1, Color.green, gui, 1100, barber, en, wr, sr);
		Person p3  = new Person(2, Color.red, gui, 1500, barber, en, wr, sr);
		Person p4  = new Person(3, Color.yellow, gui, 1450, barber, en, wr, sr);
		Person p5  = new Person(4, Color.blue, gui, 1200, barber, en, wr, sr);
		Person p6  = new Person(5, Color.lightGray, gui, 900, barber, en, wr, sr);
		Person p7  = new Person(6, Color.cyan, gui, 1000, barber, en, wr, sr);
		Person p8  = new Person(7, Color.magenta, gui, 800, barber, en, wr, sr);
		Person p9  = new Person(8, Color.orange, gui, 1200, barber, en, wr, sr);
		Person p10 = new Person(9, Color.pink, gui, 1600, barber, en, wr, sr);
		
		//Ξεκίνημα νημάτων
		barber.start();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
		p8.start();
		p9.start();
		p10.start();
	}

}

