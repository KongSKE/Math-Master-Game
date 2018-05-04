package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import users.Account;

public class RegisterController2 {

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

	public void initialize() {
		finish.setOnAction(this::onFinishButton);
		cancel.setOnAction(this::onCancelButton);
		checkQuestion.setTextFill(red);
		checkAns.setTextFill(red);
	}

	public void onFinishButton(ActionEvent event) {
		Account account = Account.getInstance();
		String q = question.getText().trim();
		String ans = answer.getText().trim();
		
		if(!q.isEmpty() && !ans.isEmpty()) {
			account.setQuestion(q);
			account.setAnswer(ans);
			account.createID();
			GameUISceneChange.LOGIN.changeScene((Stage) finish.getScene().getWindow());
		}else {
			if(q.isEmpty()) {
				checkQuestion.setText("Please insert your question");
			} else {
				checkQuestion.setText("");
			}
			if(ans.isEmpty()) {
				checkAns.setText("Please insert your answer");
			} else {
				checkAns.setText("");
			}
		}
	}

	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage) cancel.getScene().getWindow());
	}

//	public String getQuestion(String question) {
//		return question;
//	}
//
//	public String getAnswer(String answer) {
//		return answer;
//	}
}
