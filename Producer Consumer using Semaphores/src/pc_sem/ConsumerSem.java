package pc_sem;

import java.util.concurrent.Semaphore;

public class ConsumerSem extends Thread{
	private BufferSem b;
	Semaphore n;
	Semaphore s;
	
	private int cval;
	private String cstr;
	static private int interleavings = 0;
	static private int overwrites = 0;
	static private int threadsFinished = 0;
	private int threadNumber;
	
	ConsumerSem(BufferSem b, Semaphore n, Semaphore s, int threadNumber){
		this.b = b;
		this.n = n;
		this.s = s;
		this.threadNumber = threadNumber;
	}
	
	public void run(){
		for(int i=1; i<20; i++){
			try{
				Thread.sleep(100);
				n.acquire();
				s.acquire();
			}
			catch(InterruptedException e){
				System.out.println("Σφάλμα! Προέκυψε διακοπή.");
			}
			
			cval = b.getVal();
			cstr = b.getStr();
			
			System.out.println("Ο καταναλωτής " + threadNumber + " κατανάλωσε το αγαθό " + cval + " με όνομα " + cstr);
			
			if(cval != Integer.parseInt(cstr)) {
				//System.out.println("Παρατηρήθηκε παράνομο interleaving στην επανάληψη " + i);
				interleavings ++;
			}
			
			if(cval != i) {
				//System.out.println("Παρατηρήθηκε ovewrite στην επανάληψη " + i);
				overwrites ++;
			}
			s.release();
		}
		
		threadsFinished ++;
		if(threadsFinished == 2) {
			if(interleavings + overwrites == 0) {
				System.out.println("\nΤο πρόγραμμα ολοκληρώθηκε χωρίς σφάλματα.");
			}
			
			System.out.println("\nΤο πρόγραμμα ολοκληρώθηκε με " + interleavings + " παράνομα interleavings.");
			System.out.println("\nΤο πρόγραμμα ολοκληρώθηκε με " + overwrites + " overwrites.");
		}
	}
}
