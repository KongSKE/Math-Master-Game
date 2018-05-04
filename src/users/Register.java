package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register {

	private String username;
	private String password;
	private String question1;
	private String answer1;
	private String question2;
	private String answer2;

	private Connection con;
	private ResultSet result;
	private PreparedStatement prepare;

	public Register(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "");
			System.out.println("1111");
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.username = username;
		this.password = password;
	}
	
	public boolean checkID() {
		try {
			prepare = con.prepareStatement(
					"SELECT `username`, `password` from `passes` WHERE `username` = ? AND `password` = ?");
			prepare.setString(1, username);
			prepare.setString(2, password);
			System.out.println(12345);
			result = prepare.executeQuery();

			System.out.println(12345);

			
			if (result.next()) {
				return true;
			}
			System.out.println(1231);
		} catch (Exception e) {
			//do nothing
		}
		return false;
	}
}
