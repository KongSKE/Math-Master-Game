package makeIt24Game;

import java.util.Scanner;

/**
 * Test MakeIt24 on console.
 * 
 * @author Dacharat Pankong
 *
 */
public class MakeIt24Test {

	private static Scanner sc;

	public static void start(MakeIt24 makeIt24) {

		sc = new Scanner(System.in);

		while (true) {
			System.out.println("Operation[+ + +]: ");
			// ans = sc.nextLine();

			// if (makeIt24.is24(first, second, third))
			// System.out.println("Correct!! " + makeIt24.getSolution());
			// else
			// System.out.println("Wrong!! " + makeIt24.getSolution());
			System.out.println(makeIt24.getSolution());
			System.out.println("Play again ? (y/n): ");
			String again = sc.nextLine();
			if (again.equals("n"))
				break;
			makeIt24.getQuestion();

		}
	}

	public static void main(String[] args) {
		start(new MakeIt24());
	}
}
