package onlineMode;

/**
 * Packet that collect data to sent.
 * 
 * @author Dacharat Pankong
 *
 */
public class Packet {

	/**
	 * Score data of game.
	 * 
	 * @author Dacharat Pankong
	 *
	 */
	public static class ScoreData extends Packet {
		public int score;
		public String name;
	}

	/**
	 * Question data of game.
	 * 
	 * @author Dacharat Pankong
	 *
	 */
	public static class QuestionData extends Packet {
		public String queston;
		public double answer;
	}

}
