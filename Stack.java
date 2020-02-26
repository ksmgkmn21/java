public class Stack {

	private int top;
	private Object[] elements;

	Stack(int capacity) {
		elements = new Object[capacity];
		top = -1;
	}

	void push(Object data) {
		top++;
		elements[top] = data;
	}

	public Object pop() {
		Object retData = elements[top];
		top--;
		return retData;
	}

	public Object peek() {
		return elements[top];
	}

	boolean isFull() {
		return (top + 1 == elements.length);
	}

	boolean isEmpty() {
		return (top == -1);
	}

	int size() {
		return top + 1;
	}
	
	void display() {
		Stack temp = new Stack(6);
		
		for(int i = 0; i < 5 - size(); i++)
			System.out.println("|   |");
		
		while(!isEmpty()) {
			if (Character.isDigit(String.valueOf(peek()).charAt(0))) {
				if ((int) peek() / 100 != 0)
					System.out.println("|" + peek() + "|");
				else if ((int) peek() / 10 != 0)
					System.out.println("|" + peek() + " |");
				else
					System.out.println("| " + peek() + " |");
			}
			else
				System.out.println("| " + peek() + " |");
			
			temp.push(pop());
		}
		System.out.println("-----\n");
		while(!temp.isEmpty())
			push(temp.pop());
	}
}