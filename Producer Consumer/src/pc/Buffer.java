package pc;

public class Buffer {
	private int value = 0;
	private String str = "0";
	//private boolean empty = true;
	
	public void put(int val){
		//while (!empty);
		//empty = false;
		value = val;
		str = String.valueOf(val);
	}
	public int getVal(){
		//while(empty);
		//empty = true;
		return value;
	}
	
	public String getStr(){
		//while(empty);
		//empty = true;
		return str;
	}

}

