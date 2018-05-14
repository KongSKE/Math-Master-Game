package users;

import java.util.HashMap;

/**
 * Account store data of users
 * @author Varit Assavavisidchai
 *
 */
public class Account {

	static Account account = new Account();
	private String username;
	private String password;
	private String question;
	private String answer;
	private HashMap<String, Integer> userscore = new HashMap<String, Integer>();

	public Account() {

	}

	/**
	 * Singleton
	 * @return
	 */
	public static Account getInstance() {
		return account;
	}

	/**
	 * Set username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Set passworrd
	 * @param password
	 */
	public void setpassword(String password) {
		this.password = password;
	}

	/**
	 * Set Question
	 * @param question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Set Answer
	 * @param answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * create account 
	 */
	public void createID() {
		Register id = new Register(username, password);
		id.createID(question, answer);
	}

	/**
	 * Change your password
	 */
	public void rePassword() {
		ForgetPassword forget = new ForgetPassword();
		forget.rePassword(username, password);
	}

	/**
	 * Set top 5 player which has the most score
	 * @param player
	 */
	public void setTopplayer(HashMap<String, Integer> player) {
		this.userscore = player;
	}

	/**
	 * Get data of top 5 player
	 * @return
	 */
	public HashMap<String, Integer> getTopplayer() {
		return userscore;
	}

	/**
	 * clear data
	 */
	public void clearTopPlayer() {
		this.userscore.clear();
	}
}
