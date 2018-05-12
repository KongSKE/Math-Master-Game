package calculadolaGame;

import java.util.Scanner;

import player.Player;

/**
 * Test Calculadola game on console.
 * 
 * @author Dacharat Pankong
 *
 */
public class CalculadolaTest {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void start(Calculadola c) {
		
		while (true) {
			System.out.println(c.getGameQuestion());
			System.out.println("Enter you answer : ");
			double ans = Double.parseDouble(sc.nextLine());
			if (c.checkAnswer(ans+"")) {
				System.out.println("Correct!!");
			} else {
				System.out.printf("Wrong!!  Answer is %.2f\n", c.getAnswer());
			}
			System.out.println("Play again?(y/n): ");
			String again = sc.nextLine();
			if (again.equalsIgnoreCase("n"))
				break;

		}
		
		System.out.println("Game end!!");

	}
	
	public static void main(String[] args) {
		start(new Calculadola(new Player("Player1")));
	}

}
