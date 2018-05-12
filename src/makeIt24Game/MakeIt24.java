package makeIt24Game;

import java.util.Random;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import player.Game;

/**
 * MakeIt24 is game that will give 4 numbers and player will use +,-,*,/ with
 * these numbers to make it result equal 24.
 * 
 * @author Dacharat Pankong
 *
 */
public class MakeIt24 implements Game {

	private Random random = new Random();
	private ExprEvaluator e;

	private int number1;
	private int number2;
	private int number3;
	private int number4;
	
	private String solution;

	/**
	 * Initialize this game.
	 */
	public MakeIt24() {

		e = new ExprEvaluator();
		getQuestion();
	}

	/**
	 * Set question for this game it will check it these 4 numbers can make to 24 or
	 * not. If not, it will find new number.
	 */
	@Override
	public void getQuestion() {

		solution = "";
		while (true) {
			number1 = random.nextInt(8) + 1;
			number2 = random.nextInt(8) + 1;
			number3 = random.nextInt(8) + 1;
			number4 = random.nextInt(8) + 1;
			do24(number1, number2, number3, number4);
			if (!solution.isEmpty())
				break;
		}
	}

	/**
	 * Check player answer if it equal 24 or not.
	 */
	@Override
	public boolean checkAnswer(String ans) {
		return e.evaluate(ans).toString().equals("24");
	}

	/**
	 * Generate solution from 4 numbers.
	 * 
	 * @param number1
	 * @param number2
	 * @param number3
	 * @param number4
	 */
	public void do24(int number1, int number2, int number3, int number4) {

		int[] num = { number1, number2, number3, number4 };
		String[] ops = { "+", "-", "*", "/" };

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < num.length; k++) {
					if (k == i || k == j)
						continue;
					int l = 1 + 2 + 3 + 0 - i - j - k;
					for (int p = 0; p < num.length; p++) {
						for (int q = 0; q < num.length; q++) {
							for (int r = 0; r < num.length; r++) {

								for (int p1o = 1; p1o < 4; p1o++) {
									for (int p1c = p1o + 1; p1c < 5; p1c++) {
										for (int iSpecial = 1; iSpecial <= 2; iSpecial++) {
											int p2o;
											int p2c;
											if (iSpecial == 1 && p1o == 1 && p1c == 2) {
												p2o = 3;
												p2c = 4;
											} else {
												p2o = 0;
												p2c = 0;
											}

											String expr;
											expr = p1o == 1 ? "(" : "";
											expr += num[i] + ops[p] + (p1o == 2 ? "(" : "");
											expr += num[j] + (p1c == 2 ? ")" : "");
											expr += ops[q] + (p1o == 3 || p2o == 3 ? "(" : "");
											expr += num[k] + (p1c == 3 ? ")" : "");
											expr += ops[r] + num[l] + (p1c == 4 || p2c == 4 ? ")" : "");

											IExpr result = e.evaluate(expr);
											if (result.toString().equals("24")) {
												this.solution = expr;
												return;
											}
										}
									}
								}
							}
						}
					}
				}

			}
		}
	}

	/**
	 * Return solution of this game.
	 * 
	 * @return solution of this game. 
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @return number1
	 */
	public int getNumber1() {
		return number1;
	}

	/**
	 * @return number2
	 */
	public int getNumber2() {
		return number2;
	}

	/**
	 * @return number3
	 */
	public int getNumber3() {
		return number3;
	}

	/**
	 * @return number4
	 */
	public int getNumber4() {
		return number4;
	}

}
