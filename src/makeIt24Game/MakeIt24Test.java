package makeIt24Game;

import java.util.Scanner;

public class MakeIt24Test {

	private static Scanner sc;

	public static void start(MakeIt24 makeIt24) {

		sc = new Scanner(System.in);
		String[] ans = new String[3];
		String first;
		String second;
		String third;
		while (true) {
			System.out.println("Hello");
			System.out
					.println(makeIt24.getNumber1() + "__" + makeIt24.getNumber2() + " " + makeIt24.getSecondOperation()
							+ " " + makeIt24.getNumber3() + "__" + makeIt24.getNumber4() + " = 24");
			System.out.println("Operation[+ + +]: ");
			ans = sc.nextLine().split(" ");
			first = ans[0];
			second = ans[1];
			third = ans[2];
			if (makeIt24.is24(first, second, third))
				System.out.println("Correct!! " + makeIt24.getSolution());
			else
				System.out.println("Wrong!! " + makeIt24.getSolution());
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
