package pingpong;

public class ThreadCreate extends Thread{
	String string;
	int delay;
	private Counter counter;
	
	ThreadCreate(String string, int delay, Counter counter){
		this.string = string;
		this.delay = delay;
		this.counter = counter;
	}
	
	public void run() {
		for (int i=0; i<30 ; i++) {
			System.out.println (string);
			counter.add();
			try {
		    	sleep (delay);
			} catch (InterruptedException ex) {
				return;
			} 
		}
	}
}
