package player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import users.Account;

public class Scoreboard {

	private static Connection con;
	private ResultSet result;
	private PreparedStatement prepare;
	// private Statement statement;
	private static HashMap<String, Integer> topplayer = new HashMap<String, Integer>();
	private Account account = Account.getInstance();

	private boolean isHighScore = false;
	public static final String CALCULADOLA_SCORE = "scoreCal";
	public static final String QUESTION_SCORE = "scoreQuestion";
	public static final String MAKEIT24_SCORE = "score24";

	public Scoreboard() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.194.158.90:3306/login?useSSL=false", "varit", "varit");
		} catch (Exception e) {
		}
	}

	public void addScore(String username, String game) {
		int userscore;
		try {
			prepare = con.prepareStatement("SELECT `" + game + "` from `users` WHERE `username` = ? ");
			prepare.setString(1, username);
			result = prepare.executeQuery();
			if (result.next()) {
				userscore = result.getInt(game);
				String sql = "UPDATE `users` SET `" + game + "` = '" + (userscore + 10) + "' WHERE `username` = '"
						+ username + "'";
				Statement statement = con.createStatement();
				// System.out.println("wrong");
				statement.executeUpdate(sql);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void questionIsBoard() {
		account.clearTopPlayer();
		String username;
		int userscore;
		try {
			prepare = con.prepareStatement(
					"SELECT username, scoreQuestion FROM users " + " ORDER BY scoreQuestion DESC LIMIT 5");
			result = prepare.executeQuery();
			while (result.next()) {
				username = result.getString("username");
				userscore = result.getInt("scoreQuestion");
				topplayer.put(username, userscore);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		account.setTopplayer((HashMap<String, Integer>) sortByValue(topplayer));
	}

	public void makeit24Board() {
		account.clearTopPlayer();
		String username;
		int userscore;
		try {
			prepare = con.prepareStatement("SELECT username, score24 FROM users " + " ORDER BY score24 DESC LIMIT 5");
			result = prepare.executeQuery();
			while (result.next()) {
				username = result.getString("username");
				userscore = result.getInt("score24");
				topplayer.put(username, userscore);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		account.setTopplayer((HashMap<String, Integer>) sortByValue(topplayer));
	}

	public void calculadolaBoard() {
		account.clearTopPlayer();
		String username;
		int userscore;
		try {
			prepare = con.prepareStatement("SELECT username, scoreCal FROM users " + " ORDER BY scoreCal DESC LIMIT 5");
			result = prepare.executeQuery();
			while (result.next()) {
				username = result.getString("username");
				userscore = result.getInt("scoreCal");
				topplayer.put(username, userscore);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		account.setTopplayer((HashMap<String, Integer>) sortByValue(topplayer));
	}

	public Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

		// 1. Convert Map to List of Map
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		Collections.reverse(list);

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public void newHighScore(String username, String game, int score) {
		int userscore;
		isHighScore = false;
		try {
			prepare = con.prepareStatement(
					"SELECT username, " + game + " FROM users" + " WHERE `username` = '" + username + "'");
			result = prepare.executeQuery();
			if (result.next()) {
				userscore = result.getInt(game);
				if (userscore < score) {
					String sql1 = "UPDATE `users` SET `" + game + "` = '" + score + "' WHERE `username` = '" + username
							+ "'";
					Statement statement1 = con.createStatement();
					statement1.executeUpdate(sql1);

					isHighScore = true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		account.setTopplayer(topplayer);
	}

	public boolean isHighScore() {
		return isHighScore;
	}

	public static void main(String[] args) {
		Scoreboard board = new Scoreboard();
		// board.addScore("kongSKE14", "scoreCal");
		board.calculadolaBoard();
		// System.out.println(topplayer);
		board.newHighScore("Kanchanok", "scoreQuestion", 99999);
	}
}