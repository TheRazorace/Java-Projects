
public class Stack {
	static int [] stack = new int [20];
	static int sp = 0;
	
	public static void push(int data) {
		if(sp == 21) {
			System.out.println("Error! Stack is full.");
		}
		else {
			stack[sp] = data;
			sp ++;
		}
	}
	
	public static int pop() {
		int num;
		sp--;
		if (sp<0)
			System.out.println("Error! Not enough numbers in stack for operation to succed.");
		num = stack[sp];
		stack[sp] = 0;
		return num;
	}
	
}
