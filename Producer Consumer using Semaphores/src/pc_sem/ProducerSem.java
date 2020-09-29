package pc_sem;

import java.util.concurrent.Semaphore;

public class ProducerSem extends Thread{
	private BufferSem b;
	private int threadNumber;
	Semaphore n;
	Semaphore s;
	
	ProducerSem(BufferSem b, Semaphore n, Semaphore s, int threadNumber){
		this.b = b;
		this.n = n;
		this.s = s;
		this.threadNumber = threadNumber;
	}
	
	public void run(){
		for(int i=1; i<20; i++){
			try{
				Thread.sleep(100);	
				s.acquire();
			}
			catch(InterruptedException e){
				System.out.println("Σφάλμα! Προέκυψε διακοπή.");
			}

			System.out.println("Ο παραγωγός "+ threadNumber + " παρήγαγε το αγαθό " + i);
			b.put(i);
			
			s.release();
			n.release();
			
		}
	}
}
