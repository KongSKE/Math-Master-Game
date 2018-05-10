package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ForgetController2 {

	@FXML
	TextField password;
	
	@FXML
	TextField repassword;
	
	@FXML
	Label checkpass;
	
	@FXML
	Label checkrepass;
	
	@FXML
	Button confirm;
	
	@FXML
	Button cancel;
	
	private Color red = Color.RED;
	
	@FXML
	public void initialize() {
		confirm.setOnAction(this::OnConfirmAction);
		cancel.setOnAction(this::OnCancelAction);
		
		checkpass.setTextFill(red);
		checkrepass.setTextFill(red);
	}
	
	public void OnConfirmAction(ActionEvent event) {
		String pass = password.getText().trim();
		String repass = repassword.getText().trim();
		if(!pass.isEmpty() && !repass.isEmpty()) {
			if(pass.length() >= 8 && pass.equals(repass)) {
				
			}
			if(pass.length() < 8) {
				checkpass.setText("password must contain at least 8 characters");
			}
			if(!pass.equals(repass)) {
				checkrepass.setText("repassword is not a same as password");
			}
		}
		
	}
	
	public void OnCancelAction(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage)cancel.getScene().getWindow());
	}
}
