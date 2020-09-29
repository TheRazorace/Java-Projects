package pc;

public class Producer extends Thread{
	private Buffer b;
	
	Producer(Buffer b){
		this.b = b;
	}
	
	public void run(){
		for(int i=1; i<100; i++){
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException e){
				System.out.println("Σφάλμα! Προέκυψε διακοπή.");
			}
			b.put(i);
			//System.out.println("Ο παραγωγός παρήγαγε το αγαθό " + i);
			
		}
	}
}
