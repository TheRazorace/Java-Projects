package pingpong;

public class PingPong{
	public static Counter counter1 = new Counter(0);
	public static Counter counter2 = new Counter(0);
	
	public static void main(String args[]) throws Exception{
		
		ThreadCreate ping = new ThreadCreate("<ping>", 50, counter1);
		ThreadCreate pong = new ThreadCreate("_pong_", 50, counter2);
		
		pong.setPriority(9);
		//pong.setPriority(3);
		ping.start();
		pong.start();
		ping.join();
		pong.join();
		
		if ((!ping.isAlive()) && (!pong.isAlive())) {
			System.out.println("Ping count: " + counter1.count);
			System.out.println("Pong count: " + counter2.count);		
		}
		
	}
}