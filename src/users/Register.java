package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register {

	private static String username;
	private static String password;
	private String question1;
	private String answer1;
	private String question2;
	private String answer2;

	private Connection con;
	private ResultSet result;
	private PreparedStatement prepare;
	private Statement statement;

	public Register(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.194.158.90:3306/login?useSSL=false", "varit", "varit");
			// con =
			// DriverManager.getConnection("jdbc:mysql://35.200.180.73:3306/login",
			// "varit", "varit");
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.username = username;
		this.password = password;
	}

	public boolean checkID() {
		try {
			prepare = con.prepareStatement("SELECT `username`, `password` from `users` WHERE `username` = ?");
			prepare.setString(1, username);
			System.out.println(12345);
			result = prepare.executeQuery();

			System.out.println(12345);

			if (result.next()) {
				return true;
			}
			System.out.println(1231);
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}

	public void createID(String question, String answer) {
		try {
			String sql = "INSERT INTO `users` (`password`, `username`, `question`, `answer`) VALUES ('" + password
					+ "','" + username + "', '"+ question + "', '" + answer + "')";
			prepare = con.prepareStatement(sql);

			// prepare = con.prepareStatement("INSERT INTO users (username,
			// password) VALUES ("+ username +", "+ password +");");
			System.out.println(sql);
			System.out.println(11111111);
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			// result = prepare.executeQuery();

			System.out.println("Create ID success");
		} catch (Exception e) {
			// do nothing
		}
	}

	public static void main(String[] args) {
		Register kong = new Register("okerza123", "paulza123");
		// System.out.println(kong.checkID());
		kong.createID("question" , "answer");
		// System.out.println("INSERT INTO `users` (`password`, `username`)
		// VALUES ('" + password + "', '" + username + "')");
	}
}
