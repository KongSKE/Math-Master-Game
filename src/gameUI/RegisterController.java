package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
	PasswordField password;

	@FXML
	PasswordField repassword;

	@FXML
	Label checkusername;

	@FXML
	Label checkpass;

	@FXML
	Label checkrepass;

	@FXML
	public void initialize() {
		next.setOnAction(this::onNextButton);
		cancel.setOnAction(this::onCancelButton);
		username.setPromptText("Insert username");
		password.setPromptText("Insert password");
		repassword.setPromptText("Insert repassword");
		checkusername.setStyle("-fx-text-inner-color: red;");
		checkpass.setStyle("-fx-text-inner-color: red;");
		checkrepass.setStyle("-fx-text-inner-color: red;");
		checkusername.setVisible(true);
		checkpass.setVisible(true);
		checkrepass.setVisible(true);
	}

	public void onNextButton(ActionEvent event) {
		String name = username.getText().trim();
		String pass = password.getText().trim();
		String repass = repassword.getText().trim();
		if (!name.isEmpty() && !pass.isEmpty() && !repass.isEmpty()) {
			if (pass.equals(repass) && pass.length() >= 6) {
				GameUISceneChange.REGISTER2.changeScene((Stage) next.getScene().getWindow());
			}
		} else {
			if (name.isEmpty() || name.length() < 8) {
				if(name.length() < 8) {
					checkusername.setText("username must contain at least 8 characters");
					System.out.println("1");
				}
				checkusername.setText("");
				System.out.println("username");
			}
			if (pass.isEmpty() || pass.length() < 8) {
				if(pass.length() < 8) {
					checkpass.setText("password must contain at least 8 characters");
					System.out.println("2");
				}
				checkpass.setText("");
				System.out.println("password");
			}
			if (repass.isEmpty() || repass.length() < 8) {
				if(!repass.equals(pass)){
					checkrepass.setText("repassword is not a same as password.");
					System.out.println("3");
				}
				checkrepass.setText("");
				System.out.println("repass");
			}
		}
	}

	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage) cancel.getScene().getWindow());
	}
}
