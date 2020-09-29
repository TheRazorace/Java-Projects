package pc_sem;

public class BufferSem {
	private int value = 99;
	private String str = "99";
	
	public void put(int val){
		value = val;
		str = String.valueOf(val);
	}
	
	public int getVal(){
		return value;
	}
	
	public String getStr(){
		return str;
	}
	
}
