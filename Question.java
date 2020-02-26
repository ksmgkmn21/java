import java.util.Random;

public class Question {
	
	Random rnd = new Random();
	
	int target_number, little1, little2, little3, little4, little5, big;

	public Question() {
		
		target_number = rnd.nextInt(900) + 100;
		little1 = rnd.nextInt(9) + 1;
		little2 = rnd.nextInt(9) + 1;
		little3 = rnd.nextInt(9) + 1;
		little4 = rnd.nextInt(9) + 1;
		little5 = rnd.nextInt(9) + 1;
		big = (rnd.nextInt(4) + 1) * 25;
	}
	
	public int getTarget_number() {
		return target_number;
	}

	public int getLittle1() {
		return little1;
	}

	public int getLittle2() {
		return little2;
	}

	public int getLittle3() {
		return little3;
	}

	public int getLittle4() {
		return little4;
	}

	public int getLittle5() {
		return little5;
	}

	public int getBig() {
		return big;
	}
	
	public void display() {
		System.out.println("Target Number : " + target_number + "\n\nNumbers : " + little1 + " " + little2 
		+ " " + little3 + " " + little4 + " " + little5 + " " + big + "\n");
	}
}