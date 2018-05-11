package player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scoreboard {

	private static Connection con;
	private ResultSet result;
	private PreparedStatement prepare;
	private Statement statement;

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
			prepare = con.prepareStatement("SELECT `"+game+"` from `users` WHERE `username` = ? ");
			prepare.setString(1, username);
			System.out.println(12345);
			result = prepare.executeQuery();
			if (result.next()) {
				userscore = result.getInt(game);
				System.out.println("score");
					String sql = "UPDATE `users` SET `" + game + "` = '" + (userscore+10) + "' WHERE `username` = '"
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

	public static void main(String[] args) {
		Scoreboard board = new Scoreboard();
		board.addScore("kongSKE14", "scoreCal");
	}
}

// calculadola board:
// select username, calculadolaRank limit 5
// from users
// order by calculadolaRank desc

// questionIs board:
// select username, questionIsScore limit 5
// from users
// order by questionIsScore desc

// makeIt24 board:
// select username, makeIt24Score limit 5
// from users
// order by makeIt24Score desc
