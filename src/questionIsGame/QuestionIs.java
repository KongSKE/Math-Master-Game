package questionIsGame;

import java.util.Random;

public class QuestionIs {

	private Random random = new Random();
	private String firstOperation;
	private String secondOperation;

	private int firstNumber;
	private int secondNumber;
	private int thirdNumber;
	private int answer;

	public QuestionIs() {
		setQuestion();
	}

	public void setQuestion() {

		firstOperation = setOperation(random.nextInt(3));
		secondOperation = setOperation(random.nextInt(3));
		firstNumber = random.nextInt(9);
		secondNumber = random.nextInt(9);
		thirdNumber = random.nextInt(9);

	}

	public int getAnswer() {

		answer = findAnswer(findAnswer(firstNumber, secondNumber, firstOperation), thirdNumber, secondOperation);

		return answer;

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

	public boolean checkAnswer(int first, int second, int third) {
		if (secondOperation.equals("*") || secondOperation.equalsIgnoreCase("/"))
			return answer == findAnswer(findAnswer(second, third, secondOperation), first, firstOperation);
		return answer == findAnswer(findAnswer(first, second, firstOperation), third, secondOperation);
	}

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
