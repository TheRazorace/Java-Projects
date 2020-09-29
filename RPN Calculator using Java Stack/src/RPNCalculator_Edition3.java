import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator_Edition3 {
	static String exp;
    static String input;
    static int inputType;
    static Scanner sc;
    static Stack<Integer> stack = new Stack<Integer>();
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Reverse Polish Notation Calculator");
		System.out.println("Type q to turn calculator off.");
		while (true) {
			exp = getExpression();
			if (exp.equals("q")) {
				System.out.println("Calculator turned off.");
				break;
			}
	        
	        sc = new Scanner(exp);
	        sc.useDelimiter(" ");
	        while(sc.hasNext()) {
	        	input = sc.next();
	        	inputType = getOp(); 
	        	
	        	if (inputType == 0) {
	        		stack.push(Integer.parseInt(input));
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
		sc = new Scanner(exp);
		sc.close();
        
	}
	
	private static String getExpression() {
		System.out.print("Type an expression: ");
	    Scanner in = new Scanner(System.in);
	    String ss = in.nextLine();
	    if (ss.equals("q")) {
			in.close();
		}
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
    	int result = stack.pop() + stack.pop();
    	stack.push(result);
    }
    
    private static void sub() {
    	int result = - stack.pop() + stack.pop();
    	stack.push(result);
    }
    
    private static void mul() {
    	int result = stack.pop() * stack.pop();
    	stack.push(result);
    }
    
    private static void div() {
    	int num1 = stack.pop();
    	int num2 = stack.pop();
    	int result = num2 / num1;
    	stack.push(result);
    }
    
    private static void equ() {
    	int result = stack.pop();
    	System.out.println("Result = " + result);
    }
}



