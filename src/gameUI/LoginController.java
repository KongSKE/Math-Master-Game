package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	 
	@FXML
	Button login;
	
	@FXML
	TextField username;
	
	@FXML
	TextField password;
	
	@FXML
	Button register;
	
	@FXML
	Button forget;
	
	@FXML
	CheckBox showpass;
	
	@FXML
	public void initialize() {
		login.setOnAction(this::onLoginClicked);
		
	}
	
	public void onLoginClicked(ActionEvent event) {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage)login.getScene().getWindow());
	}
}
