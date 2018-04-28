package makeIt24Game;

import java.util.Random;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class MakeIt24 {

	private Random random = new Random();
	private ExprEvaluator e;

	private int number1;
	private int number2;
	private int number3;
	private int number4;

	private String firstOperation;
	private String secondOperation;
	private String thirdOperation;
	private String solution;

	// private List<Double> number = new ArrayList<Double>();

	public MakeIt24() {
		
		e = new ExprEvaluator();
		getQuestion();
	}

	public void getQuestion() {

		// do {
		// number1 = random.nextInt(8) + 1;
		// number2 = random.nextInt(8) + 1;
		// number3 = random.nextInt(8) + 1;
		// number4 = random.nextInt(8) + 1;
		// do24(number1, number2, number3, number4);
		// } while (solution.isEmpty());
		solution = "";
		while(true) {
			number1 = random.nextInt(8) + 1;
			number2 = random.nextInt(8) + 1;
			number3 = random.nextInt(8) + 1;
			number4 = random.nextInt(8) + 1;
			do24(number1, number2, number3, number4);
			if(!solution.isEmpty())
				break;
		}
	}

	// public boolean isPosibleTo24(List<Double> num) {
	//
	// if (num.size() == 0)
	// return false;
	// if (num.size() == 1)
	// return num.get(0) == 24;
	//
	// for (int i = 0; i < num.size(); i++) {
	// for (int j = 0; j < num.size(); j++) {
	// if (i != j) {
	// List<Double> newNumber = new ArrayList<Double>();
	// for (int k = 0; k < num.size(); k++) {
	// if (k != i && k != j)
	// newNumber.add(num.get(k));
	// }
	//
	// for (int k = 0; k < 4; k++) {
	// if (k % 2 == 1 && j > i)
	// continue;
	// if (k == 0)
	// newNumber.add(num.get(i) + num.get(j));
	// else if (k == 1)
	// newNumber.add(num.get(i) - num.get(j));
	// else if (k == 2)
	// newNumber.add(num.get(i) * num.get(j));
	// else if (k == 3)
	// if (num.get(j) > 0)
	// newNumber.add(num.get(i) / num.get(j));
	// else
	// continue;
	// if (isPosibleTo24(newNumber))
	// return true;
	// newNumber.remove(newNumber.size() - 1);
	// }
	// }
	// }
	// }
	//
	// return false;
	// }

	public void do24(int number12, int number22, int number32, int number42) {

		int[] num = { number12, number22, number32, number42 };
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

	public String getSolution() {
		return solution;
	}

	public int getNumber1() {
		return number1;
	}

	public int getNumber2() {
		return number2;
	}

	public int getNumber3() {
		return number3;
	}

	public int getNumber4() {
		return number4;
	}

	public String getFirstOperation() {
		return firstOperation;
	}

	public String getSecondOperation() {
		return secondOperation;
	}

	public String getThirdOperation() {
		return thirdOperation;
	}

}
