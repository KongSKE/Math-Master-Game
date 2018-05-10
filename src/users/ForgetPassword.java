package users;

public class ForgetPassword {

	static ForgetPassword forget = new ForgetPassword();
	
	public ForgetPassword() {
		
	}
	
	public static ForgetPassword getInstance() {
		return forget;
	}
}
