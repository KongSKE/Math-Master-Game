package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ForgetPassword {
	
	private static Connection con;
	private ResultSet result;
	private PreparedStatement prepare;
	private Statement statement;
	
	public ForgetPassword() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.194.158.90:3306/login?useSSL=false", "varit", "varit");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean checkUser(String username) {
		try {
			prepare = con.prepareStatement("SELECT `username` from `users` WHERE `username` = ?");
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
	
	public String getQuestion(String username) {
		try {
			prepare = con.prepareStatement(
					"SELECT `question` from `users` WHERE `username` = ? ");
			prepare.setString(1, username);
			System.out.println(12345);
			result = prepare.executeQuery();
			System.out.println("check answer");
			if (result.next()) {
				String question = result.getString("question");
				System.out.println("it true");
				return question;
			}
			System.out.println(1231);
		} catch (Exception e) {
			// do nothing
		}
		return "";
	}
	
	public boolean checkAnswer(String username, String answer) {
		try {
			prepare = con.prepareStatement(
					"SELECT `username`, `answer` from `users` WHERE `username` = ? AND `answer` = ?");
			prepare.setString(1, username);
			prepare.setString(2, answer);
			System.out.println(12345);
			result = prepare.executeQuery();
			System.out.println("check answer");
			if (result.next()) {
				System.out.println("it true");
				return true;
			}
			System.out.println(1231);
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}

	public void rePassword(String username, String newpass) {
		try {
			String sql = "UPDATE `users` SET `password` = '"+newpass+"' WHERE `username` = '"+username+"'";
			System.out.println(sql);
			Statement statement = con.createStatement();
//			System.out.println("wrong");
			statement.executeUpdate(sql);
			System.out.println("Repassword success");
		} catch (Exception e) {
			// do nothing
		}
	}
	
	public static void main(String[] args) {
		ForgetPassword forget = new ForgetPassword();
//		forget.rePassword("oker5555", "kong123");
		forget.checkAnswer("jackrevive", "non");
//		System.out.println(forget.checkAnswer("jackrevive", "non"));
		System.out.println(forget.getQuestion("kongSKE14"));
	}
}
