package player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import users.Account;

public class Scoreboard {

	private static Connection con;
	private ResultSet result;
	private PreparedStatement prepare;
	private Statement statement;
	private static HashMap<String,Integer> topplayer = new HashMap();
	private Account account = Account.getInstance();

	public Scoreboard() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.194.158.90:3306/login?useSSL=false", "varit", "varit");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void addScore(String username, String game) {
		int userscore;
		try {
			prepare = con.prepareStatement("SELECT `" + game + "` from `users` WHERE `username` = ? ");
			prepare.setString(1, username);
			System.out.println(12345);
			result = prepare.executeQuery();
			if (result.next()) {
				userscore = result.getInt(game);
				System.out.println("score");
				String sql = "UPDATE `users` SET `" + game + "` = '" + (userscore + 10) + "' WHERE `username` = '"
						+ username + "'";
				System.out.println(sql);
				Statement statement = con.createStatement();
				// System.out.println("wrong");
				statement.executeUpdate(sql);
				System.out.println("Update Score success");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void questionIsBoard() {
		account.clearTopPlayer();
		String username;
		int userscore;
		try {
			prepare = con
					.prepareStatement("SELECT username, scoreQuestion FROM users" +" ORDER BY scoreQuestion DESC");
			result = prepare.executeQuery();
			while (result.next()) {
				username = result.getString("username");
				userscore = result.getInt("scoreQuestion"); 
				System.out.println("score QuestionIs");
				topplayer.put(username,userscore);
				System.out.println("Update Top5 score success");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		account.setTopplayer(topplayer);
	}
	
	public void makeit24Board() {
		account.clearTopPlayer();
		String username;
		int userscore;
		try {
			prepare = con
					.prepareStatement("SELECT username, score24 FROM users" +" ORDER BY score24 DESC");
			result = prepare.executeQuery();
			while (result.next()) {
				username = result.getString("username");
				userscore = result.getInt("score24");
				System.out.println("score MakeIt24");
				topplayer.put(username, userscore);
				System.out.println("Update Top5 score success");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		account.setTopplayer(topplayer);
	}
	
	public void calculadolaBoard() {
		account.clearTopPlayer();
		String username;
		int userscore;
		try {
			prepare = con
					.prepareStatement("SELECT username, scoreCal FROM users" +" ORDER BY scoreCal DESC");
			result = prepare.executeQuery();
			while (result.next()) {
				username = result.getString("username");
				userscore = result.getInt("scoreCal");
				System.out.println("score Calculadola");
				topplayer.put(username, userscore);
				System.out.println("Update Top5 score success");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		account.setTopplayer(topplayer);
	}
	
//	public String getPlayerScore(String username, String game) {
//		try {
//			prepare = con
//					.prepareStatement("SELECT username, "+game+" FROM users" +" WHERE username = "+ username);
//			result = prepare.executeQuery();
//			if (result.next()) {
//				userscore = result.getString("username");
//				System.out.println("score MakeIt24");
//				topplayer.add();
//				System.out.println("get player score success");
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		account.setTopplayer(topplayer);
//	}

	public static void main(String[] args) {
		Scoreboard board = new Scoreboard();
		board.addScore("kongSKE14", "scoreCal");
		board.calculadolaBoard();
		System.out.println(topplayer);
	}
}