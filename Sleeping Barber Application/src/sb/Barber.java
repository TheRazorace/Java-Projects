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
	
	//M������ run() ��� barber
	public void run() {
		while(true) {
			
			checkIfStoreIsEmpty();
			threadSleep(1000);
		}
	}
	
	//������� ��� ��������� ��� barber
	public void takeAbreak() {
		
		moveToBreakPosition();
		announceLimitChange(3);
		available = false;
		
		threadSleep(2000);
		
		returnToWork();
	}
	
	//������� ��� ��������� ��� ������� ��� barber
	public void returnToWork() {
		
		currentHaircuts = 0;
		moveToWorkPosition();
		announceLimitChange(4);
		available = true;
	}
	
	//������� ��� ������ ��� �� ���� ����� � ��� ��� ���������
	public void checkIfItsBreakTime() {
		
		if(currentHaircuts == haircutsTillBreak-1){
			announceLimitChange(3);
		}
		if(currentHaircuts == haircutsTillBreak){
			takeAbreak();
		}
	}
	
	//������� ���������� ��� �������� ���� ��� sleep()
	public void cutMyHair(int time) {

		threadSleep(time);
	}
	
	//������� ���������� ��� ������� ��� ������ ��� ����� ��� waiting room, ���� �� ������ �� ����������� ���� � barber
	public void announceLimitChange(int newLimit) {
		
		WaitingRoom.wr_limit = newLimit;
	}
	
	//������� ����������� barber ���� ���� ��� ���������
	public void moveToBreakPosition() {
		
		barberIcon.setBounds(215,200, 20, 40);
		gui.add(barberIcon);
	}
	
	//������� ����������� barber ���� ���� ��������
	public void moveToWorkPosition() {
		
		barberIcon.setBounds(271,57, 30, 30);
		gui.add(barberIcon);
	}
	
	//��������� ��� ����� �� sleep()
	public void threadSleep(int time) {
		
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//������� �� � ���� ��� waiting room ����� ���� ���� �� ����� ��������� � �������
	public void checkIfStoreIsEmpty() {
		
		if(WaitingRoom.wr_queue == 0) {
			takeAbreak();
		}
	}

}

	