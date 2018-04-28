package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	Button next;
	
	@FXML
	Button cancel;
	
	public void initialize() {
		next.setOnAction(this::onNextButton);
		cancel.setOnAction(this::onCancelButton);
	}
	
	public void onNextButton(ActionEvent event) {
		String question1 = q1.getText().trim();
		String question2 = q2.getText().trim();
		String answer1 =  ans1.getText().trim();
		String answer2 = ans2.getText().trim();
		
		if(question1.isEmpty() && question2.isEmpty() && answer1.isEmpty() && answer2.isEmpty()) {
			
		}
		
		if(question1.equals("")) {
			q1.setStyle("-fx-text-inner-color: red;");
			q1.setText("Insert Question 1");
		}
		if(question2.equals("")) {
			q1.setStyle("-fx-text-inner-color: red;");
			q1.setText("Insert Question 1");
		}
		if(answer1.equals("")) {
			q1.setStyle("-fx-text-inner-color: red;");
			q1.setText("Insert Question 1");
		}
		if(answer2.equals("")) {
			q1.setStyle("-fx-text-inner-color: red;");
			q1.setText("Insert Question 1");
		}
	}
	
	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage)cancel.getScene().getWindow());
	}
}
