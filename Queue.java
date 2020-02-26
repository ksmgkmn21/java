public class Queue {
	
	private int rear, front;
	private Operation[] elements;
	
	public Queue(int capacity) {
		elements = new Operation[capacity];
		rear = -1;
		front = 0;
	}
	
	void enqueue(Operation data) {
		rear++;
		elements[rear] = data;
	}
	
	Operation dequeue() {
		Operation retData = elements[front];
		elements[front] = null;
		front++;
		return retData;
	}
	
	public Operation peek() {
		return elements[front];
	}
	
	public boolean isEmpty() {
		return rear < front;
	}
	
	public boolean isFull() {
		return (rear + 1 == elements.length);
	}
	
	public int size() {
		return rear - front + 1;
	}
}