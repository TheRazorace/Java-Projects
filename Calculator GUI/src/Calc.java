
import java.util.Stack;

public class Calc {
	static CalculatorGui gui;
	static Operand op;
	static Stack <Double> stack = new Stack <Double>();

	public static void main(String[] argv) {
		op = new Operand();
		gui = new CalculatorGui(op);
	}
}



class Operand {
	int n = 0;

	void addDigit(char digit) {
		n = n * 10 + Character.getNumericValue(digit);
	}

	void complete() {
		Calc.stack.push((double) n);
		n = 0;
		System.out.println(Calc.stack.toString());
	}
}

