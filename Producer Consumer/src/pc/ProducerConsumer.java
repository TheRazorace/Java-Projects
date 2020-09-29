package pc;

public class ProducerConsumer {

	public static void main(String[] args) {
		Buffer b = new Buffer();
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);
		
		p.start();
		c.start();
	}

}

