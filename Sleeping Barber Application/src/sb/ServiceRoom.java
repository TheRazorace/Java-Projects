package sb;

public class ServiceRoom {
	
	//������� ������� ��� ���� 3 (service room)
	public synchronized void enterServiceRoom(Person person, Barber barber, WaitingRoom wr, int time) {
		
		person.placePerson(person, 3);
		wr.exitWaitingRoom();
		
		barber.cutMyHair(time);
		barber.currentHaircuts++;
		
		exitServiceRoom(person, barber);
	}
	
	//������� ������ ��� �� ���� 3 (service room)
	public void exitServiceRoom(Person person, Barber barber) {
		
		person.placePerson(person, 0);
		
		barber.checkIfItsBreakTime();
	}
}
