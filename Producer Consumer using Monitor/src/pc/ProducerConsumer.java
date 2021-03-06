package pc;

public class ProducerConsumer {
	public static void main(String[] args){
		Buffer buffer = new Buffer();
		
		Producer p1 = new Producer(buffer, 1);
		Consumer c1 = new Consumer(buffer, 1);
		Producer p2 = new Producer(buffer, 2);
		Consumer c2 = new Consumer(buffer, 2);
		
		p1.start();
		p2.start();
		c1.start();
		c2.start();	
	}

}
