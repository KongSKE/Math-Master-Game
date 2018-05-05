package calculadolaGame;

import java.util.Random;

import player.Game;
import player.Player;

public class Calculadola implements Game {

	private Player player;

	private Random random = new Random();
	private MathStyle math;
	private int first;
	private int second;
	private String question;

	public Calculadola(Player player) {
		this.player = player;
	}

	@Override
	public void getQuestion() {
		this.first = random.nextInt(100) + 1;
		this.second = random.nextInt(100) + 1;
		int style = random.nextInt(4) + 1;
		switch (style) {
		case 1:
			math = new MathStyleAdd(first, second);
			break;
		case 2:
			math = new MathStyleMinus(first, second);
			break;
		case 3:
			math = new MathStyleMultiply(first, (second % 10) + 2);
			break;
		case 4:
			math = new MathStyleDivide(first, (second % 9) + 2);
		default:
			break;
		}
		question = math.toString();
	}

	@Override
	public boolean checkAnswer(String answer) {
		if (Double.parseDouble(answer) == math.getAnswer())
			return true;
		else {
			return answer.equals(String.format("%.2f", getAnswer()));
		}
	}

	public String getGameQuestion() {
		getQuestion();
		return question;
	}

	public double getAnswer() {
		return math.getAnswer();
	}
	
	public String getPlayerName() {
		return player.getName();
	}

}
