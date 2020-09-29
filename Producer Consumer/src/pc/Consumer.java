package pc;

public class Consumer extends Thread{
	private Buffer b;
	private int cval;
	private String cstr;
	private int iError = 0;
	private int oError = 0;
	
	Consumer(Buffer b){
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
			
			cval = b.getVal();
			cstr = b.getStr();
			//System.out.println("Ο καταναλωτής κατανάλωσε το αγαθό " + cval + " με όνομα " + cstr);
			
			if(cval != Integer.parseInt(cstr)) {
				System.out.println("Παρατηρήθηκε παράνομο interleaving στην επανάληψη " + i);
				iError ++;
			}
			
			if(cval != i) {
				System.out.println("Παρατηρήθηκε ovewrite στην επανάληψη " + i);
				oError ++;
			}
		}
		
		if(iError + oError == 0) {
			System.out.println("\nΤο πρόγραμμα ολοκληρώθηκε χωρίς σφάλματα.");
		}
		System.out.println("\nΤο πρόγραμμα ολοκληρώθηκε με " + iError + " παράνομα interleavings.");
		System.out.println("\nΤο πρόγραμμα ολοκληρώθηκε με " + oError + " overwrites.");
	}
}
