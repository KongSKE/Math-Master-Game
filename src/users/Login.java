package users;

import java.sql.*;

/**
 * Login
 * 
 * @author Varit Assavavisidchai
 *
 */
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
			// con = DriverManager.getConnection("jdbc:mysql://35.200.180.73:3306/login",
			// "varit", "varit");
		} catch (Exception e) {
		}
		this.username = username;
		this.password = password;
	}

	/**
	 * Check username and password
	 * 
	 * @return true if your user name and password are in database
	 */
	public boolean match() {
		try {
			prepare = con.prepareStatement(
					"SELECT `username`, `password` from `users` WHERE `username` = ? AND `password` = ?");
			prepare.setString(1, username);
			prepare.setString(2, password);
			result = prepare.executeQuery();

			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}

	public static void main(String[] args) {
		// Login login =
		new Login("oker5555", "okerza123");
	}
}
