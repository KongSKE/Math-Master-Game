package player;

/**
 * Interface for 3 mini game.
 * 
 * @author Dacharat Pankong
 *
 */
public interface Game {

	/**
	 * Set game question
	 */
	public void getQuestion();
	
	/**
	 * Check player answer if it equal game answer or not.
	 * 
	 * @param ans
	 * @return
	 */
	public boolean checkAnswer(String ans);
}
