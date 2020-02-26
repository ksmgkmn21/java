public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Numbers !\nIn this game, 5 small (1-9) and 1 big (25-50-75-100)"
				+ " numbers are given to players.\nThe aim is reaching to this target number in 30 seconds"
				+ " by doing operations with the given numbers only.\nThe player found the closest number"
				+ " to the target enters his/her solution, if the numbers are equal they bot enter their "
				+ "solutions.\nScoring :\nIf the diffirence is less than or equals to 10; point = Abs(Target number"
				+ " - Calculated number)\nIf the difference is greater than 10; point = 5\n"
				+ "You will play against computer (AI) and you can select the difficulty.\n");
		
		
		Management m = new Management();
		m.run();
	}
}