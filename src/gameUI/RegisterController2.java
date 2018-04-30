package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegisterController2 {

	@FXML
	TextField q1;

	@FXML
	TextField q2;

	@FXML
	TextField ans1;

	@FXML
	TextField ans2;

	@FXML
	Button finish;

	@FXML
	Button cancel;

	@FXML
	Label checkQ1;

	@FXML
	Label checkQ2;

	@FXML
	Label checkAns1;

	@FXML
	Label checkAns2;

	Color red = Color.RED;

	public void initialize() {
		finish.setOnAction(this::onFinishButton);
		cancel.setOnAction(this::onCancelButton);
		checkQ1.setTextFill(red);
		checkQ2.setTextFill(red);
		checkAns1.setTextFill(red);
		checkAns2.setTextFill(red);
	}

	public void onFinishButton(ActionEvent event) {
		String question1 = q1.getText().trim();
		String question2 = q2.getText().trim();
		String answer1 = ans1.getText().trim();
		String answer2 = ans2.getText().trim();

		if (!question1.isEmpty() && !question2.isEmpty() && !answer1.isEmpty() && !answer2.isEmpty()) {

			GameUISceneChange.LOGIN.changeScene((Stage) finish.getScene().getWindow());
		}

		if (question1.equals("")) {
			checkQ1.setText("Please insert your question");
		} else {
			checkQ1.setText("");
		}
		if (question2.equals("")) {
			checkAns1.setText("Please answer your question");
		} else {
			checkAns1.setText("");
		}
		if (answer1.equals("")) {
			checkQ2.setText("Please insert your question");
		} else {
			checkQ2.setText("");
		}
		if (answer2.equals("")) {
			checkAns2.setText("Please answer your question");
		} else {
			checkAns2.setText("");
		}
	}

	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage) cancel.getScene().getWindow());
	}

	public String getQuestion(String question) {
		return question;
	}

	public String getAnswer(String answer) {
		return answer;
	}
}
