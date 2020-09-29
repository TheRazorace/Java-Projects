package pc;

public class Consumer extends Thread{
	private Buffer buffer;
	
	private int val;
	private int threadNumber;
	private int reps = 10;
	
	public Consumer(Buffer buffer, int threadNumber){
		this.buffer = buffer;
		this.threadNumber = threadNumber;
	}
	
	public void run() {
		for (int i = 0; i < reps ; i++) {
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.err.println(e.toString());
			}
			
			val = buffer.get();
			System.out.println("Ο καταναλωτής " + threadNumber + " κατανάλωσε το αγαθό " + val);
			
		}
	}
}
