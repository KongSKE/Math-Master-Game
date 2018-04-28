package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	
	@FXML
	Button next;
	
	@FXML
	Button cancel;
	
	@FXML
	TextField username;
	
	@FXML
	TextField password;
	
	@FXML
	TextField repassword;
	
	@FXML
	public void initialize() {
		next.setOnAction(this::onNextButton);
		cancel.setOnAction(this::onCancelButton);
	}
	
	public void onNextButton(ActionEvent event) {
		String name = username.getText().trim();
		String pass = password.getText().trim();
		String repass = repassword.getText().trim();
		if(name.equals("") && pass.equals("") && repass.equals("")) {
			GameUISceneChange.REGISTER2.changeScene((Stage)next.getScene().getWindow());
		}
		if(!name.equals("")) {
			username.setStyle("-fx-text-inner-color: red;");
			username.setText("Insert username");
		}
		if(!pass.equals("")) {
			password.setStyle("-fx-text-inner-color: red;");
			password.setText("Insert password");
		}
		if(!repass.equals("")) {
			repassword.setStyle("-fx-text-inner-color: red;");
			repassword.setText("Insert repassword");
		}
		username.setText("-fx-text-inner-color: black;");
		password.setText("-fx-text-inner-color: black;");
		repassword.setText("-fx-text-inner-color: black;");
	}
	
	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage)cancel.getScene().getWindow());
	}
}
