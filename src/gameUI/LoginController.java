package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	 
	@FXML
	Button login;
	
	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	
	@FXML
	TextField passtext;
	
	@FXML
	Button register;
	
	@FXML
	Button forget;
	
	@FXML
	CheckBox showpass;
	
	@FXML
	public void initialize() {
		login.setOnAction(this::onLoginClicked);
		register.setOnAction(this::onRegisterClicked);
		forget.setOnAction(this::onForgetClicked);
		showpass.setOnAction(this::onCheckBoxClicked);
		username.setPromptText("Your username");
		password.setPromptText("Your password");
		passtext.setVisible(false);
	}
	
	public void onLoginClicked(ActionEvent event) {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage)login.getScene().getWindow());
	}
	
	public void onCheckBoxClicked(ActionEvent event) {
		if(showpass.isSelected()) {
			password.setVisible(false);
			setTextShowPassword(password.getText().trim());
			passtext.setVisible(true);
		}else {
			passtext.setVisible(false);
			setTextShowPassword(password.getText().trim());
			password.setVisible(true);
		}
	}
	
	public void onRegisterClicked(ActionEvent event) {
		GameUISceneChange.REGISTER.changeScene((Stage)register.getScene().getWindow());
	}
	
	public void setTextShowPassword(String pass) {
		passtext.setText(pass);
	}
	
	public void onForgetClicked(ActionEvent evnet) {
		
	}
}
