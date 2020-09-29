package pc;

public class Producer extends Thread{
	private Buffer buffer;
	private int threadNumber;
	
	private int reps = 10;
	
	public Producer(Buffer buffer, int threadNumber){
		this.buffer = buffer;
		this.threadNumber = threadNumber;
	}
	
	public void run() {
		for(int i = 0; i<reps; i++) {
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.err.println(e.toString());
			}
			
			buffer.put(i);
			System.out.println("Ο παραγωγός "+ threadNumber + " παρήγαγε το αγαθό " + i);
			

		}
	}
}
