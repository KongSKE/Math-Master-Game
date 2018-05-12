package questionIsGame;

import java.util.Scanner;

/**
 * Test QuestionIs game on console.
 * 
 * @author Dacharat Pankong
 *
 */
public class QuestionIsTest {

	private static Scanner sc = new Scanner(System.in);

	public static void start(QuestionIs qi) {

		String[] ans = new String[3];
		int first;
		int second;
		int third;
		String again = "0";
		while (!again.equalsIgnoreCase("n")) {
			System.out.println(
					"__ " + qi.getFirstOperation() + " __ " + qi.getSecondOperation() + " __ = " + qi.getAnswer());
			System.out.println("Question is ? (Input 3 number [0 0 0]) : ");
			ans = sc.nextLine().split(" ");
			first = Integer.parseInt(ans[0]);
			second = Integer.parseInt(ans[1]);
			third = Integer.parseInt(ans[2]);
			if (qi.checkAnswer(first + qi.getFirstNumber() + second + qi.getSecondNumber() + third + ""))
				System.out.println("Correct!!");
			else
				System.out.println("Wrong!!" + qi.getQuestionIsSolution());

			System.out.println("Play again?(y/n): ");
			again = sc.nextLine();

			qi.getQuestion();

		}
	}

	public static void main(String[] args) {
		start(new QuestionIs());
	}

}
