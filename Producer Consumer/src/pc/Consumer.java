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
				System.out.println("������! �������� �������.");
			}
			
			cval = b.getVal();
			cstr = b.getStr();
			//System.out.println("� ����������� ���������� �� ����� " + cval + " �� ����� " + cstr);
			
			if(cval != Integer.parseInt(cstr)) {
				System.out.println("������������ �������� interleaving ���� ��������� " + i);
				iError ++;
			}
			
			if(cval != i) {
				System.out.println("������������ ovewrite ���� ��������� " + i);
				oError ++;
			}
		}
		
		if(iError + oError == 0) {
			System.out.println("\n�� ��������� ������������ ����� ��������.");
		}
		System.out.println("\n�� ��������� ������������ �� " + iError + " �������� interleavings.");
		System.out.println("\n�� ��������� ������������ �� " + oError + " overwrites.");
	}
}
