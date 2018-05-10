package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	
	@FXML
	public void initialize() {
		cancel.setOnAction(this::OnCancelAction);
		
	}
	
	public void OnEnterAction(ActionEvent event) {
		
	}
	
	public void OnNextAction(ActionEvent event) {
		GameUISceneChange.FORGET2.changeScene((Stage)next.getScene().getWindow(), "");
	}
	
	public void OnCancelAction(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage)cancel.getScene().getWindow(), "");
	}
}
