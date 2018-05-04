package users;

import java.sql.*;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Login {

	private String username;
	private String password;
	private Connection con;
	private ResultSet result;
	private PreparedStatement prepare;

	public Login(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.194.158.90:3306/login?useSSL=false", "varit", "varit");
//			con = DriverManager.getConnection("jdbc:mysql://35.200.180.73:3306/login", "varit", "varit");
			System.out.println("1111");
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.username = username;
		this.password = password;
	}

	public boolean match() {
		try {
			prepare = con.prepareStatement(
					"SELECT `username`, `password` from `users` WHERE `username` = ? AND `password` = ?");
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
	
	public static void main(String[] args) {
		Login login = new Login("oker5555", "okerza123");
		System.out.println(login.match());
	}
}
