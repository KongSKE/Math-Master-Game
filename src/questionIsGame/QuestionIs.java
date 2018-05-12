package questionIsGame;

import java.util.Random;

import org.matheclipse.core.eval.ExprEvaluator;

import player.Game;

/**
 * QuestionIs game is the game that will give player an answer and player will
 * find the question that make answer equal game answer.
 * 
 * @author Dacharat Pankong
 *
 */
public class QuestionIs implements Game {

	private Random random = new Random();
	private String firstOperation;
	private String secondOperation;

	private int firstNumber;
	private int secondNumber;
	private int thirdNumber;
	private int answer;

	private ExprEvaluator e;

	/**
	 * Initialize game.
	 */
	public QuestionIs() {
		getQuestion();
		e = new ExprEvaluator();
	}

	/**
	 * Set game question.
	 */
	@Override
	public void getQuestion() {
		firstOperation = setOperation(random.nextInt(3));
		secondOperation = setOperation(random.nextInt(3));
		firstNumber = random.nextInt(9);
		secondNumber = random.nextInt(9);
		thirdNumber = random.nextInt(9);
	}

	/**
	 * Check answer.
	 */
	@Override
	public boolean checkAnswer(String ans) {
		return e.evaluate(ans).toString().equals(answer + "");
	}

	/**
	 * Return answer of this game.
	 * 
	 * @return answer of this game.
	 */
	public int getAnswer() {
		if (secondOperation.equals("*") || secondOperation.equalsIgnoreCase("/"))
			return answer = findAnswer(firstNumber, findAnswer(secondNumber, thirdNumber, secondOperation),
					firstOperation);
		return answer = findAnswer(findAnswer(firstNumber, secondNumber, firstOperation), thirdNumber, secondOperation);
	}

	/**
	 * Find answer from 2 number.
	 * 
	 * @param first
	 * @param second
	 * @param operation
	 * @return answer
	 */
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

	/**
	 * Set operation from number.
	 * 
	 * @param number
	 * @return operation
	 */
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

	/**
	 * Return solution of this game.
	 * 
	 * @return solution of this game.
	 */
	public String getQuestionIsSolution() {
		return String.format("%d %s %d %s %d = %d", firstNumber, firstOperation, secondNumber, secondOperation,
				thirdNumber, answer);
	}

	/**
	 * Return first operation.
	 * 
	 * @return first operation.
	 */
	public String getFirstOperation() {
		return firstOperation;
	}

	/**
	 * Return second operation.
	 * 
	 * @return second operation.
	 */
	public String getSecondOperation() {
		return secondOperation;
	}

	/**
	 * Return first number. 
	 * 
	 * @return first number.
	 */
	public int getFirstNumber() {
		return firstNumber;
	}

	/**
	 * Return second number. 
	 * 
	 * @return second number.
	 */
	public int getSecondNumber() {
		return secondNumber;
	}

	/**
	 * Return third number. 
	 * 
	 * @return third number.
	 */
	public int getThirdNumber() {
		return thirdNumber;
	}

}
