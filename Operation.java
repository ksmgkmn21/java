public class Operation {
	
	int operand1, operand2, result;
	char operation;
	
	Operation(int operand1_in, int operand2_in, char operation_in) {
		
		operand1 = operand1_in;
		operand2 = operand2_in;
		operation = operation_in;
		
		if (operation == '+')
			result = operand1 + operand2;
		else if (operation == '-')
			result = operand1 - operand2;
		else if (operation == '*')
			result = operand1 * operand2;
		else if (operation == '/')
			result = operand1 / operand2;
	}

	public int getResult() {
		return result;
	}
	
	public void display() {
		System.out.println(operand1 + " " + operation + " " + operand2 + " = " + result);
	}
}