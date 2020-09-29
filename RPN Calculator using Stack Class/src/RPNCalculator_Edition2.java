import java.util.Scanner;

public class RPNCalculator_Edition2 {
	static String exp;
    static String input;
    static int inputType;
    static Scanner sc;
    Stack stack = new Stack();
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Reverse Polish Notation Calculator");
        
        exp= getExpression();
        
        sc = new Scanner(exp);
        sc.useDelimiter(" ");
        while(sc.hasNext()) {
        	input = sc.next();
        	inputType = getOp(); 
        	
        	if (inputType == 0) {
        		Stack.push(Integer.parseInt(input));
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
	
	private static void add() {
    	int result = Stack.pop() + Stack.pop();
    	Stack.push(result);
    }
    
    private static void sub() {
    	int result = - Stack.pop() + Stack.pop();
    	Stack.push(result);
    }
    
    private static void mul() {
    	int result = Stack.pop() * Stack.pop();
    	Stack.push(result);
    }
    
    private static void div() {
    	int num1 = Stack.pop();
    	int num2 = Stack.pop();
    	int result = num2 / num1;
    	Stack.push(result);
    }
    
    private static void equ() {
    	int result = Stack.pop();
    	System.out.println("Result = " + result);
    }
}


