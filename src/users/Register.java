package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register {

	private String username;
	private String password;
	// private String question1;
	// private String answer1;
	// private String question2;
	// private String answer2;

	private Connection con;
	private ResultSet result;
	private PreparedStatement prepare;

	public Register(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.194.158.90:3306/login?useSSL=false", "varit", "varit");
			// con =
			// DriverManager.getConnection("jdbc:mysql://35.200.180.73:3306/login",
			// "varit", "varit");
		} catch (Exception e) {
		}
		this.username = username;
		this.password = password;
	}

	public boolean checkID() {
		try {
			prepare = con.prepareStatement("SELECT `username`, `password` from `users` WHERE `username` = ?");
			prepare.setString(1, username);
			result = prepare.executeQuery();

			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}

	public void createID(String question, String answer) {
		try {
			String sql = "INSERT INTO `users` (`password`, `username`, `question`, `answer`) VALUES ('" + password
					+ "','" + username + "', '" + question + "', '" + answer + "')";
			prepare = con.prepareStatement(sql);

			// prepare = con.prepareStatement("INSERT INTO users (username,
			// password) VALUES ("+ username +", "+ password +");");
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			// result = prepare.executeQuery();

		} catch (Exception e) {
			// do nothing
		}
	}

	public static void main(String[] args) {
		Register kong = new Register("okerza123", "paulza123");
		// System.out.println(kong.checkID());
		kong.createID("question", "answer");
		// System.out.println("INSERT INTO `users` (`password`, `username`)
		// VALUES ('" + password + "', '" + username + "')");
	}
}
