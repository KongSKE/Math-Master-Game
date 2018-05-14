package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import users.Account;

/**
 * Register Controller page 2
 * 
 * @author Varit Assavavisidchai
 *
 */
public class RegisterController2 extends Contoller {

	@FXML
	TextField question;

	@FXML
	TextField answer;

	@FXML
	Button finish;

	@FXML
	Button cancel;

	@FXML
	Label checkQuestion;

	@FXML
	Label checkAns;

	Color red = Color.RED;

	/**
	 * Initialize
	 */
	public void initialize() {
		finish.setOnAction(this::onFinishButton);
		cancel.setOnAction(this::onCancelButton);
		checkQuestion.setTextFill(red);
		checkAns.setTextFill(red);
	}

	/**
	 * Action when clicked finish button
	 * @param event
	 */
	public void onFinishButton(ActionEvent event) {
		Account account = Account.getInstance();
		String q = question.getText().trim();
		String ans = answer.getText().trim();

		if (!q.isEmpty() && !ans.isEmpty()) {
			account.setQuestion(q);
			account.setAnswer(ans);
			account.createID();
			GameUISceneChange.LOGIN.changeScene((Stage) finish.getScene().getWindow(), "");
		} else {
			if (q.isEmpty()) {
				checkQuestion.setText("Please insert your question");
			} else {
				checkQuestion.setText("");
			}
			if (ans.isEmpty()) {
				checkAns.setText("Please insert your answer");
			} else {
				checkAns.setText("");
			}
		}
	}

	/**
	 * Action when clicked cancel button
	 * @param event
	 */
	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage) cancel.getScene().getWindow(), "");
	}
}
