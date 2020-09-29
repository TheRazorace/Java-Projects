package pc_sem;

import java.util.concurrent.Semaphore;

public class ProducerConsumerSem {

	public static void main(String[] args) {
		BufferSem b = new BufferSem();
		Semaphore n = new Semaphore(0);
		Semaphore s = new Semaphore(1);
		
		ProducerSem p1 = new ProducerSem(b, n, s, 1);
		ConsumerSem c1 = new ConsumerSem(b, n, s, 1);
		ProducerSem p2 = new ProducerSem(b, n, s, 2);
		ConsumerSem c2 = new ConsumerSem(b, n, s, 2);
		
		p2.start();
		c2.start();
		p1.start();
		c1.start();

	}
}

