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
				System.out.println("������! �������� �������.");
			}
			
			cval = b.getVal();
			cstr = b.getStr();
			
			System.out.println("� ����������� " + threadNumber + " ���������� �� ����� " + cval + " �� ����� " + cstr);
			
			if(cval != Integer.parseInt(cstr)) {
				//System.out.println("������������ �������� interleaving ���� ��������� " + i);
				interleavings ++;
			}
			
			if(cval != i) {
				//System.out.println("������������ ovewrite ���� ��������� " + i);
				overwrites ++;
			}
			s.release();
		}
		
		threadsFinished ++;
		if(threadsFinished == 2) {
			if(interleavings + overwrites == 0) {
				System.out.println("\n�� ��������� ������������ ����� ��������.");
			}
			
			System.out.println("\n�� ��������� ������������ �� " + interleavings + " �������� interleavings.");
			System.out.println("\n�� ��������� ������������ �� " + overwrites + " overwrites.");
		}
	}
}
