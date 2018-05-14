package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import users.Account;
import users.ForgetPassword;

/**
 * Forget Controller page 1
 * 
 * @author Varit Assavavisidchai
 *
 */
public class ForgetController1 extends Contoller{

	@FXML
	Label checkUsername;
	
	@FXML
	TextField username;
	
	@FXML
	TextField answer;
	
	@FXML
	Label answerTag;
	
	@FXML
	Label questionTag;
	
	@FXML
	Label question;
	
	@FXML
	Label checkAns;
	
	@FXML
	Button enter;
	
	@FXML
	Button next;
	
	@FXML
	Button cancel;
	
	Color red = Color.RED;
	Account account = Account.getInstance();
	ForgetPassword forget = new ForgetPassword();
	
	/**
	 * Initialize
	 */
	@FXML
	public void initialize() {
		cancel.setOnAction(this::OnCancelAction);
		enter.setOnAction(this::OnEnterAction);
		next.setOnAction(this::OnNextAction);
		answer.setVisible(false);
		answerTag.setVisible(false);
		questionTag.setVisible(false);
		checkUsername.setTextFill(red);
		checkAns.setTextFill(red);
	}
	
	/**
	 * Action when clicked enter
	 * @param event
	 */
	public void OnEnterAction(ActionEvent event) {
		String user = username.getText().trim();
		if(forget.checkUser(user)) {
			account.setUsername(user);
			username.setDisable(false);
			enter.setDisable(false);
			answer.setVisible(true);
			answerTag.setVisible(true);
			questionTag.setVisible(true);
			question.setText(forget.getQuestion(user));
		}else {
			checkUsername.setText("This username does not exist");
		}
	}
	
	/**
	 * Action when clicked next button
	 * @param event
	 */
	public void OnNextAction(ActionEvent event) {
		String user = username.getText().trim();
		String ans = answer.getText().trim(); 
		if(forget.checkAnswer(user, ans)){
			GameUISceneChange.FORGET2.changeScene((Stage)next.getScene().getWindow(), "");
		} else {
			checkAns.setText("Your answer is incorrect.");
		}
	}
	
	/**
	 * Action when clicked cancel button
	 * @param event
	 */
	public void OnCancelAction(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage)cancel.getScene().getWindow(), "");
	}
}
