package makeIt24Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakeIt24 {

	private Random random = new Random();

	private double number1;
	private double number2;
	private double number3;
	private double number4;
	private String firstOperation;
	private String secondOperation;
	private String thirdOperation;

	private List<Double> number = new ArrayList<Double>();

	public MakeIt24() {
		getQuestion();
	}

	public void getQuestion() {

		// do {
		number1 = random.nextInt(8) + 1;
		number2 = random.nextInt(8) + 1;
		number3 = random.nextInt(8) + 1;
		number4 = random.nextInt(8) + 1;
		number.add(number1);
		number.add(number2);
		number.add(number3);
		number.add(number4);
		// System.out.println(number1 + " " + number2 + " " + number3 + " " + number4);
		// } while (!makeNumberSum24());
	}

	public boolean isPosibleTo24(List<Double> num) {

		if (num.size() == 0)
			return false;
		if (num.size() == 1)
			return num.get(0) == 24;

		for (int i = 0; i < num.size(); i++) {
			for (int j = 0; j < num.size(); j++) {
				if (i != j) {
					List<Double> newNumber = new ArrayList<Double>();
					for (int k = 0; k < num.size(); k++) {
						if (k != i && k != j)
							newNumber.add(num.get(k));
					}

					for (int k = 0; k < 4; k++) {
						if (k % 2 == 1 && j > i)
							continue;
						if (k == 0)
							newNumber.add(num.get(i) + num.get(j));
						else if (k == 1)
							newNumber.add(num.get(i) - num.get(j));
						else if (k == 2)
							newNumber.add(num.get(i) * num.get(j));
						else if (k == 3)
							if (num.get(j) > 0)
								newNumber.add(num.get(i) / num.get(j));
							else
								continue;
						if (isPosibleTo24(newNumber))
							return true;
						newNumber.remove(newNumber.size() - 1);
					}
				}
			}
		}

		return false;
	}

	public double getAnswer(String operation1, String operation2, String operation3) {
		if (operation2.equals("*") || operation2.equals("/") && operation3.equals("*") || operation3.equals("/"))
			return findAnswer(number1, findAnswer(findAnswer(number2, number3, operation2), number4, operation3),
					operation1);
		else if (operation2.equals("*") || operation2.equals("/"))
			return findAnswer(findAnswer(number1, findAnswer(number2, number3, operation2), operation1), number4,
					operation3);
		else if (operation3.equals("*") || operation3.equals("/"))
			return findAnswer(findAnswer(number3, number4, operation3), findAnswer(number1, number2, operation1),
					operation2);
		return findAnswer(findAnswer(findAnswer(number1, number2, operation1), number3, operation2), number4,
				operation3);
	}

	public double findAnswer(double first, double second, String operation) {
		double ans = 0;

		if (operation.equals("+"))
			ans = first + second;
		else if (operation.equals("-"))
			ans = first - second;
		else if (operation.equals("*"))
			ans = first * second;
		else if (operation.equals("/"))
			ans = first / second;

		return ans;
	}

	public boolean is24(String operation1, String operation2, String operation3) {
		return 24 == getAnswer(operation1, operation2, operation3);
	}

	public String getSolution() {
		return String.format("%d %s %d %s %d %s %d = %d", number1, firstOperation, number2, secondOperation, number3,
				thirdOperation, number4, 24);
	}

	public double getNumber1() {
		return number1;
	}

	public double getNumber2() {
		return number2;
	}

	public double getNumber3() {
		return number3;
	}

	public double getNumber4() {
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
