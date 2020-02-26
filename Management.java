import java.util.Random;
import java.util.Scanner;

public class Management {
	
	Random rnd = new Random();
	Scanner scn = new Scanner(System.in);
	
	public int p1_guess, p2_guess, difficulty;
	
	public String p1_answer, p2_answer;
	
	CircularQueue computer_solution = new CircularQueue(5);
	
	public void run() {
		
		while (true) {
			
			int p1_score = 0, p2_score = 0;
			
			System.out.println("Difficulties : Easy - Medium - Hard - Brutal");
			
			while (true) {
				
				System.out.print("Please select difficulty : ");
				String difficulty_selection = scn.nextLine();
				
				if (difficulty_selection.equalsIgnoreCase("Easy")) {
					difficulty = 9;
					break;
				}
				else if (difficulty_selection.equalsIgnoreCase("Medium")) {
					difficulty = 6;
					break;
				}
				else if (difficulty_selection.equalsIgnoreCase("Hard")) {
					difficulty = 3;
					break;
				}
				else if (difficulty_selection.equalsIgnoreCase("Brutal")) {
					difficulty = 0;
					break;
				}
				else
					System.out.println("Invalid input !");
			}
			int round = 1;
			while(p1_score < 100 && p2_score < 100) {
				
				Question que = new Question();
				
				System.out.println("\n--- Round " + round + " ----------------------\n");
				que.display();
				System.out.print("Duration : ");
				
				int timer = 30;
				long time1 = System.currentTimeMillis();
				long time2 = 0;
				System.out.print(timer + " ");
				do {
					time2 = System.currentTimeMillis();
					if (time2 - time1 >= 1000) {
						System.out.print(--timer + " ");
						time1 = time2;
					}
				}
				while (timer != 0);
				
				System.out.print("\n\n---------------------------------\n\nResult numbers\n	Player : ");
				
				computerAnswer(que);
				boolean over_time = true, wrong_input = false;
				long time_check = System.currentTimeMillis();
				
				try {
					p1_guess = Integer.parseInt(scn.nextLine());
				}
				catch(NumberFormatException invalid_input) {
					System.out.println("Invalid input ! Your guess will be accepted as 0 !");
					p1_guess = 0;
					wrong_input = true;
				}
				
				if (System.currentTimeMillis() - time_check > 5000 && wrong_input == false) {
					System.out.println("Your answer is invalid because it took over 5 seconds to enter your answer !");
					over_time = false;
				}
				System.out.println("	Computer : " + p2_guess + "\n");
				
				if (Math.abs(p1_guess - que.getTarget_number()) < Math.abs(p2_guess - que.getTarget_number()) && over_time) {
					
					System.out.println("Enter player's solution : ");
					
					time_check = System.currentTimeMillis();
					p1_answer = scn.nextLine();
					if (System.currentTimeMillis() - time_check > 15000) {
						
						System.out.println("Your answer is invalid because it took over 15 seconds to enter your solution !");
						
						System.out.println("Computer's solution steps : ");
						
						while(!computer_solution.isEmpty()) {
							Operation op = (Operation) computer_solution.dequeue();
							op.display();
						}
						
						System.out.println("\n--- Correct\n");
						
						if (Math.abs(p2_guess - que.getTarget_number()) <= 10)
							p2_score += 15 - Math.abs(p2_guess - que.getTarget_number());
						else
							p2_score += 5;
					}
					else {
						
						int evaluation = Postfix_Evaluation(Infix_to_Postfix(p1_answer), que);
						
						System.out.println("Result : " + evaluation);
						
						if (evaluation == p1_guess) {
							
							System.out.println("\n--- Correct\n");
							
							if (Math.abs(p1_guess - que.getTarget_number()) <= 10)
								p1_score += 15 - Math.abs(p1_guess - que.getTarget_number());
							else
								p1_score += 5;
						}
						else {
							
							System.out.println("Your solution is incorrect ! Computer will get those points !");
							
							if (Math.abs(p1_guess - que.getTarget_number()) <= 10)
								p2_score += 15 - Math.abs(p1_guess - que.getTarget_number());
							else
								p2_score += 5;
						}
					}
					System.out.println("Player score   : " + p1_score + "\nComputer score : " + p2_score);
				}
				else if (Math.abs(p1_guess - que.getTarget_number()) > Math.abs(p2_guess - que.getTarget_number()) || !over_time) {
					
					System.out.println("Computer's solution steps : ");
					
					while(!computer_solution.isEmpty()) {
						Operation op = (Operation) computer_solution.dequeue();
						op.display();
					}
					
					System.out.println("\n--- Correct\n");
					
					if (Math.abs(p2_guess - que.getTarget_number()) <= 10)
						p2_score += 15 - Math.abs(p2_guess - que.getTarget_number());
					else
						p2_score += 5;
					
					System.out.println("Player score   : " + p1_score + "\nComputer score : " + p2_score);
				}
				else {
					
					System.out.println("Enter player's solution : ");
					
					time_check = System.currentTimeMillis();
					p1_answer = scn.nextLine();
					if (System.currentTimeMillis() - time_check > 15000)
						System.out.println("Your answer is invalid because it took over 15 seconds to enter your solution !");
					else {
						
						int evaluation = Postfix_Evaluation(Infix_to_Postfix(p1_answer), que);
						
						System.out.println("Result : " + evaluation);
						
						if (evaluation == p1_guess) {
							
							System.out.println("\n--- Correct\n");
							
							if (Math.abs(p1_guess - que.getTarget_number()) <= 10)
								p1_score += 15 - Math.abs(p1_guess - que.getTarget_number());
							else
								p1_score += 5;
						}
						else {
							
							System.out.println("Your solution is incorrect ! Computer will get those points !");
							
							if (Math.abs(p1_guess - que.getTarget_number()) <= 10)
								p2_score += 15 - Math.abs(p1_guess - que.getTarget_number());
							else
								p2_score += 5;
						}
					}
					
					System.out.println("Computer's solution steps : ");
					
					while(!computer_solution.isEmpty()) {
						Operation op = (Operation) computer_solution.dequeue();
						op.display();
					}
					
					System.out.println("\n--- Correct\n");
					
					if (Math.abs(p2_guess - que.getTarget_number()) <= 10)
						p2_score += 15 - Math.abs(p2_guess - que.getTarget_number());
					else
						p2_score += 5;
					
					System.out.println("Player score   : " + p1_score + "\nComputer score : " + p2_score);
				}
				round++;
			}
			if (p1_score > p2_score)
				System.out.println("Player won !");
			else if (p2_score > p1_score)
				System.out.println("Computer won !");
			else
				System.out.println("It is a draw !");
			
			String play_again;
			
			System.out.println("If you want to play again please enter 'y' or 'Y', you can enter anything else to quit.");
			
			play_again = scn.nextLine();
			
			if (!play_again.equalsIgnoreCase("y"))
				break;
		}
	}
	
	public void computerAnswer(Question que) {
		
		int dif = difficulty;
		int try_counter = 0;
		
		while (Math.abs(p2_guess - que.getTarget_number()) > dif) {
			
			while(!computer_solution.isEmpty())
				computer_solution.dequeue();
			
			int[] A = {que.getLittle1(), que.getLittle2(), que.getLittle3(), que.getLittle4(), 
					que.getLittle5(), que.getBig()};
			
			int i = 0;
			while (i < 5) {
				
				if(Math.abs(p2_guess - que.getTarget_number()) > dif) {
					
					int first_index = rnd.nextInt(6);
					
					int second_index = rnd.nextInt(6);
					
					while(first_index == second_index)
						second_index = rnd.nextInt(6);
					
					
					int op = rnd.nextInt(4);
					
					if (A[first_index] != 0 && A[second_index] != 0) {
						
						if (op == 0) {
							Operation o = new Operation(A[first_index], A[second_index], '*');
							computer_solution.enqueue(o);
							A[first_index] *= A[second_index];
							A[second_index] = 0;
							p2_guess = A[first_index];
							i++;
						}
						else if (op == 1 && A[first_index] >= A[second_index]) {
							Operation o = new Operation(A[first_index], A[second_index], '/');
							computer_solution.enqueue(o);
							A[first_index] /= A[second_index];
							A[second_index] = 0;
							p2_guess = A[first_index];
							i++;
						}
						else if (op == 1 && A[first_index] < A[second_index]) {
							Operation o = new Operation(A[second_index], A[first_index], '/');
							computer_solution.enqueue(o);
							A[second_index] /= A[first_index];
							A[first_index] = 0;
							p2_guess = A[second_index];
							i++;
						}
						else if (op == 2) {
							Operation o = new Operation(A[first_index], A[second_index], '+');
							computer_solution.enqueue(o);
							A[first_index] += A[second_index];
							A[second_index] = 0;
							p2_guess = A[first_index];
							i++;
						}
						else if (op == 3 && A[first_index] > A[second_index]) {
							Operation o = new Operation(A[first_index], A[second_index], '-');
							computer_solution.enqueue(o);
							A[first_index] -= A[second_index];
							A[second_index] = 0;
							p2_guess = A[first_index];
							i++;
						}
						else if (op == 3 && A[first_index] < A[second_index]) {
							Operation o = new Operation(A[second_index], A[first_index], '-');
							computer_solution.enqueue(o);
							A[second_index] -= A[first_index];
							A[first_index] = 0;
							p2_guess = A[second_index];
							i++;
						}
					}
				}
				else
					break;
			}
			try_counter++;
			if (try_counter == 10000) {
				dif++;
				try_counter = 0;
			}
		}
	}
	
	public String Infix_to_Postfix(String input) {
		
		input = input.replaceAll(" ", "");
		
		System.out.println("Infix to postfix conversion steps :\n");
		
		String output = "";
		
		Stack s = new Stack(10);
		
		int step = 1;
		
		for (int i = 0; i < input.length(); i++) {
			
			if (Character.isDigit(input.charAt(i))) {
				
				if (i + 1 < input.length()) {
					if (Character.isDigit(input.charAt(i + 1))) {
						if (i + 2 < input.length()) {
							if (Character.isDigit(input.charAt(i + 2))) {
								output += input.charAt(i);
								output += input.charAt(i + 1);
								output += input.charAt(i + 2) + " ";
								i += 2;
							}
							else {
								output += input.charAt(i);
								output += input.charAt(i + 1) + " ";
								i++;
							}
						}
						else {
							output += input.charAt(i);
							output += input.charAt(i + 1) + " ";
							i++;
						}
					}
					else
						output += input.charAt(i) + " ";
				}
				else
					output += input.charAt(i) + " ";
			}
			else if (input.charAt(i) != '+' && input.charAt(i) != '-'
					&& input.charAt(i) != '*' && input.charAt(i) != '/' && input.charAt(i) != '('
					&& input.charAt(i) != ')')
				return "";
			else {
				if (input.charAt(i) == '(')
					s.push(input.charAt(i));
				
				else if (input.charAt(i) == ')') {
					while (!s.isEmpty() && (char) s.peek() != '(')
						output += s.pop() + " ";
	                
					s.pop();
				}
				
				else {
					
					if (!s.isEmpty() && ((char) s.peek() == '*' || (char) s.peek() == '/')) {
						output += s.pop() + " ";
						s.push(input.charAt(i));
					}
					else
						s.push(input.charAt(i));
				}
				
				System.out.println("Step " + step + " - " + input + "\n");
				System.out.println(output + "\n");
				s.display();
				step++;
			}
		}
		while(!s.isEmpty())
			output += s.pop() + " ";
		
		System.out.println("Step " + step + " - " + input + "\n");
		System.out.println(output + "\n");
		s.display();
		
		System.out.println("Postfix expression : " + output + "\n");
		
		return output;
	}
	
	public int Postfix_Evaluation(String input, Question que) {
		
		if (input != "") {
			
			System.out.println("Postfix evaluation steps :\n");
			
			try {
				int step = 1;
				Stack s = new Stack(20);
				int[] A = {que.getLittle1(), que.getLittle2(), que.getLittle3(), que.getLittle4(), 
						que.getLittle5(), que.getBig()};
				
				for (int i = 0; i < input.length(); i++) {
					
					if (Character.isDigit(input.charAt(i))) {
						if (Character.isDigit(input.charAt(i + 1))) {
								if (Character.isDigit(input.charAt(i + 2))) {
									
									boolean input_check = false;
									
									for (int j = 0; j < A.length; j++) {
										
										if (Integer.parseInt(input.substring(i, i + 3)) == A[j]) {
											A[j] = 0;
											input_check = true;
											break;
										}
									}
									
									if (!input_check) {
										System.out.println("Invalid solution !");
										return 0;
									}
									
									s.push(Integer.parseInt(input.substring(i, i + 3)));
									i += 2;
								}
								else {
									
									boolean input_check = false;
									
									for (int j = 0; j < A.length; j++) {
										
										if (Integer.parseInt(input.substring(i, i + 2)) == A[j]) {
											A[j] = 0;
											input_check = true;
											break;
										}
									}
									
									if (!input_check) {
										System.out.println("Invalid solution !");
										return 0;
									}
									
									s.push(Integer.parseInt(input.substring(i, i + 2)));
									i++;
								}
						}
						else {
							
							boolean input_check = false;
							
							for (int j = 0; j < A.length; j++) {
								
								if (Integer.parseInt(input.substring(i, i + 1)) == A[j]) {
									A[j] = 0;
									input_check = true;
									break;
								}
							}
							
							if (!input_check) {
								System.out.println("Invalid solution !");
								return 0;
							}
							
							s.push(Integer.parseInt(input.substring(i, i + 1)));
						}
					}
					else if (input.charAt(i) == '+') {
						
						if (i > 1)
							if (Character.isDigit(input.charAt(i - 2))) {
								
								System.out.println("Step " + step + " - " + input + "\n");
								System.out.println(input + "\n");
								s.display();
								step++;
							}
						
						s.push((int) s.pop() + (int) s.pop());
						
						System.out.println("Step " + step + " - " + input + "\n");
						System.out.println(input + "\n");
						s.display();
						step++;
					}
					else if (input.charAt(i) == '-') {
						
						if (i > 1)
							if (Character.isDigit(input.charAt(i - 2))) {
								
								System.out.println("Step " + step + " - " + input + "\n");
								System.out.println(input + "\n");
								s.display();
								step++;
							}
						
						int k = (int) s.pop();
						s.push((int) s.pop() - k);
						
						System.out.println("Step " + step + " - " + input + "\n");
						System.out.println(input + "\n");
						s.display();
						step++;
					}
					else if (input.charAt(i) == '*') {
						
						if (i > 1)
							if (Character.isDigit(input.charAt(i - 2))) {
								
								System.out.println("Step " + step + " - " + input + "\n");
								System.out.println(input + "\n");
								s.display();
								step++;
							}
						
						s.push((int) s.pop() * (int) s.pop());
						
						System.out.println("Step " + step + " - " + input + "\n");
						System.out.println(input + "\n");
						s.display();
						step++;
					}
					else if (input.charAt(i) == '/') {
						
						if (i > 1)
							if (Character.isDigit(input.charAt(i - 2))) {
								
								System.out.println("Step " + step + " - " + input + "\n");
								System.out.println(input + "\n");
								s.display();
								step++;
							}
						
						int k = (int) s.pop();
						s.push((int) s.pop() / k);
						
						System.out.println("Step " + step + " - " + input + "\n");
						System.out.println(input + "\n");
						s.display();
						step++;
					}
				}
					return (int) s.pop();
			}
			catch (NullPointerException invalid_solution) {
				System.out.println("Invalid solution !");
				return 0;
			}
			catch (ArrayIndexOutOfBoundsException invalid_solution) {
				System.out.println("Invalid solution !");
				return 0;
			}
		}
		else
			return 0;
	}
}