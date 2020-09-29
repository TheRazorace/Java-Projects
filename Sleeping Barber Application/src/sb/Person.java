package sb;

import java.awt.Button;
import java.awt.Color;

import gui.BarberGui;

public class Person extends Thread{
	Button icon = new Button();
	private BarberGui gui;
	private Barber barber;
	private WaitingRoom wr;
	private ServiceRoom sr;
	private Entrance en;
	
	int id  = 0;
	int haircutDuration;
	
	Person(int id, Color color, BarberGui gui, int haircutDuration, Barber barber, Entrance en, WaitingRoom wr, ServiceRoom sr){
		this.id = id;
		icon.setBackground(color);
		icon.setLabel(String.valueOf(id));
		this.gui = gui;
		this.haircutDuration = haircutDuration;
		this.barber = barber;
		this.en = en;
		this.wr = wr;
		this.sr = sr;
	}
	
	//Μέθοδος run() του person με 4 ενέργειες για τον καθένα
	public void run() {
		while(true) {

			wanderOutside();
			goToEntrance();
			goToWaitingRoom();
			goToServiceRoom();			
		}
	}
	
	//Μέθοδος για θέση 0
	public void wanderOutside() {
		
		placePerson(this, 0);
		stayStill();
	}
	
	//Μέθοδος για θέση 1
	public void goToEntrance() {
			
		en.reachEntrance(this);
		stayStill();	
	}
	
	//Μέθοδος για θέση 2
	public void goToWaitingRoom() {
			
		wr.enterWaitingRoom(this, en);
		stayStill();		
	}
	
	//Μέθοδος για θέση 3
	public void goToServiceRoom() {
		
		while(!barber.available) {
			stayStill();
		}
		sr.enterServiceRoom(this, barber, wr, haircutDuration);
		stayStill();	
	}
	
	//Μέθοδος sleep() για καλύτερη απεικόνιση του person στο gui
	public void stayStill() {
		
		try {
			sleep((int)(Math.random()* 2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Μέθοδος τοποθέτησης του person στο gui
	public void placePerson(Person person, int position) {
		
		person.icon.setBounds(BarberGui.pos[person.id][position].x,BarberGui.pos[person.id][position].y, 20, 20); 	
		person.gui.add(person.icon);	
	}

}

