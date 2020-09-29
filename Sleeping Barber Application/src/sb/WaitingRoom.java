package sb;


public class WaitingRoom {
	static int wr_queue = 0;
	static int wr_limit = 3;
	
	//������� ������� ��� ���� 2 (waiting room)
	public synchronized void enterWaitingRoom(Person person, Entrance en) {
		
		//��� � ���� ����� ���������� � ��� ��� �� ����, � person ���������
		while(wr_queue >= wr_limit) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println("Exception:" + e.toString());
			}
		}
		
		//������, ���������
		person.placePerson(person, 2);
		en.getInStore();
		wr_queue++;
	}
	
	//������� ������ ��� �� ���� 2 (waiting room)
	public synchronized void exitWaitingRoom() {
		
		wr_queue--;
		notifyAll();
	}
}
