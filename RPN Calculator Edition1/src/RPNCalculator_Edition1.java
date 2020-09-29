import java.util.Scanner;

public class RPNCalculator_Edition1 {
	static String exp;
    static String input;
    static int inputType;
    static Scanner sc;
    static int [] stack;
    static int sp=0; 
    
	public static void main(String[] args) {
		 // TODO Auto-generated method stub
        System.out.println("Reverse Polish Notation Calculator");
        stack = new int[20];
        exp= getExpression();
        
        sc = new Scanner(exp);
        sc.useDelimiter(" ");
        while(sc.hasNext()) {
        	input = sc.next();
        	inputType = getOp(); 
        	
        	if (inputType == 0) {
        		push(Integer.parseInt(input));
        	}
        	else {
        		if (input.equals("+")) {
        			add();
        		}else if (input.equals("-")) {
        			sub();
        		}else if (input.equals("*")) {
        			mul();
        		}else if (input.equals("/")) {
        			div();
        		}else if (input.equals("=")) {
        			equ();
        		}else 
        			System.out.println("Error! Wrong operand/character used.");
        	}
        }
        sc.close();
	}
	
	private static String getExpression() {
		System.out.print("Type an expression: ");
	    Scanner in = new Scanner(System.in);
	    String ss = in.nextLine();
	    in.close();
	    return ss;
    }
	
	private static int getOp() {
    	if (Character.isDigit(input.charAt(0))){
    		return 0;
    	}
    	else {
    		return 1;
    	}
    }
    	
	private static void push(int data) {
		if(sp == 21) {
			System.out.println("Error! Stack is full.");
		}
		else {
			stack[sp] = data;
			sp ++;
		}
	}
	
	private static int pop() {
		int num;
		sp--;
		if (sp<0)
			System.out.println("Error! Not enough numbers in stack for operation to succed.");
		num = stack[sp];
		stack[sp] = 0;
		return num;
	}
	
    private static void add() {
    	int result = pop() + pop();
    	push(result);
    }
    
    private static void sub() {
    	int result = - pop() + pop();
    	push(result);
    }
    
    private static void mul() {
    	int result = pop() * pop();
    	push(result);
    }
    
    private static void div() {
    	int num1 = pop();
    	int num2 = pop();
    	int result = num2 / num1;
    	push(result);
    }
    
    private static void equ() {
    	int result = pop();
    	System.out.println("Result = " + result);
    }	
}
