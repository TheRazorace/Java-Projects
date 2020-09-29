package pc;

public class Buffer {
	private int value = 50;
	private boolean empty = true;
	
	public synchronized void put(int val) {
		while(!empty) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println("Exception:" + e.toString());
			}
		}
		value = val;
		empty = false;
		System.out.print("");
		notifyAll();
	}
	
	public synchronized int get() {
		int value;
		while(empty) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println("Exception:" + e.toString());
			}
		}
		
		value = this.value;
		empty = true;
		System.out.print("");
		notifyAll();
		return value;
	}
}
