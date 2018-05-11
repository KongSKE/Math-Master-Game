package users;

public class Account {
	
	static Account account = new Account();
	private String username;
	private String password;
	private String question;
	private String answer;
	
	public Account(){
		
	}
	
	public static Account getInstance() {
		return account;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setpassword(String password) {
		this.password = password;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void createID() {
		Register id = new Register(username,password);
		id.createID(question, answer);
	}
	
	public void rePassword() {
		ForgetPassword forget = new ForgetPassword();
		forget.rePassword(username,password);
	}
}
