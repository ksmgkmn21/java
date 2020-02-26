public class Answer {
	
	Question que = new Question();
	Queue solution_steps = new Queue(4);
	int close_number;
	
	public Answer (Question que_in, Queue solution_steps_in, int close_number_in) {
		
		que = que_in;
		solution_steps = solution_steps_in;
		close_number = close_number_in;
	}

	public Question getQue() {
		return que;
	}

	public Queue getSolution_steps() {
		return solution_steps;
	}

	public int getClose_number() {
		return close_number;
	}
}