package calculadolaGame;

import java.util.Random;

import player.Game;
import player.Player;

/**
 * Calculadola game is fast calculate game.
 * 
 * @author Dacharat Pankong
 *
 */
public class Calculadola implements Game {

	private Player player;

	private Random random = new Random();
	private MathStyle math;
	private int first;
	private int second;
	private String question;

	/**
	 * Initialize Calculadola game with player name.
	 * 
	 * @param player
	 */
	public Calculadola(Player player) {
		this.player = player;
	}

	/**
	 * Set game question.
	 */
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

	/**
	 * Check player answer is equal this game answer or not.
	 */
	@Override
	public boolean checkAnswer(String answer) {
		if (Double.parseDouble(answer) == math.getAnswer())
			return true;
		else {
			return answer.equals(String.format("%.2f", getAnswer()));
		}
	}

	/**
	 * Return question of this game.
	 *  
	 * @return  Return question of this game.
	 */
	public String getGameQuestion() {
		getQuestion();
		return question;
	}

	/**
	 * Return answer of this game.
	 * 
	 * @return answer of this game.
	 */
	public double getAnswer() {
		return math.getAnswer();
	}
	
	/**
	 * Return player name.
	 * 
	 * @return player name.
	 */
	public String getPlayerName() {
		return player.getName();
	}

}
