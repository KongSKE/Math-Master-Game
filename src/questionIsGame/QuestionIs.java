package questionIsGame;

import java.util.Random;

import org.matheclipse.core.eval.ExprEvaluator;

import player.Game;

public class QuestionIs implements Game {

	private Random random = new Random();
	private String firstOperation;
	private String secondOperation;

	private int firstNumber;
	private int secondNumber;
	private int thirdNumber;
	private int answer;

	private ExprEvaluator e;

	public QuestionIs() {
		getQuestion();
		e = new ExprEvaluator();
	}

	@Override
	public void getQuestion() {
		firstOperation = setOperation(random.nextInt(3));
		secondOperation = setOperation(random.nextInt(3));
		firstNumber = random.nextInt(9);
		secondNumber = random.nextInt(9);
		thirdNumber = random.nextInt(9);
	}

	@Override
	public boolean checkAnswer(String ans) {
		return e.evaluate(ans).toString().equals(answer + "");
	}
	
	public int getAnswer() {
		if (secondOperation.equals("*") || secondOperation.equalsIgnoreCase("/"))
			return answer = findAnswer(firstNumber, findAnswer(secondNumber, thirdNumber, secondOperation),
					firstOperation);
		return answer = findAnswer(findAnswer(firstNumber, secondNumber, firstOperation), thirdNumber, secondOperation);
	}

	public int findAnswer(int first, int second, String operation) {
		int ans = 0;

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

	public String setOperation(int number) {
		String operation = "";
		switch (number) {
		case 0:
			operation = "+";
			break;
		case 1:
			operation = "-";
			break;
		case 2:
			operation = "*";
			break;
		case 3:
			operation = "/";
			break;
		default:
			break;
		}
		return operation;
	}
//
//	public boolean checkAnswer(int first, int second, int third) {
//		if (secondOperation.equals("*") || secondOperation.equalsIgnoreCase("/"))
//			return answer == findAnswer(first, findAnswer(second, third, secondOperation), firstOperation);
//		return answer == findAnswer(findAnswer(first, second, firstOperation), third, secondOperation);
//	}

	public String getQuestionIsSolution() {
		return String.format("%d %s %d %s %d = %d", firstNumber, firstOperation, secondNumber, secondOperation,
				thirdNumber, answer);
	}

	public String getFirstOperation() {
		return firstOperation;
	}

	public String getSecondOperation() {
		return secondOperation;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public int getThirdNumber() {
		return thirdNumber;
	}

}
