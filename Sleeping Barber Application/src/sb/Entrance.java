package sb;

public class Entrance {
		
	static int en_queue = 0;
	static int en_limit = 3;
	
	//������� ������� ��� ���� 1 (entrance)
	public synchronized void reachEntrance(Person person) {
		
		//��� � ���� ����� ���������� � ��� ��� �� ����, � person ���������
		while(en_queue >= en_limit) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println("Exception:" + e.toString());
			}
		}
		
		//������, ���������
		person.placePerson(person, 1);
		en_queue++;
	}
	
	//������� ������ ��� �� ���� 1 (entrance)
	public synchronized void getInStore() {
		
		en_queue--;
		notifyAll();
	}
}
