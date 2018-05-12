package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import users.Account;
import users.Register;

public class RegisterController extends Contoller {

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

	Color red = Color.RED;

	@FXML
	public void initialize() {
		next.setOnAction(this::onNextButton);
		cancel.setOnAction(this::onCancelButton);
		username.setPromptText("Insert username");
		password.setPromptText("Insert password");
		repassword.setPromptText("Insert repassword");
		checkusername.setTextFill(red);
		checkpass.setTextFill(red);
		checkrepass.setTextFill(red);
		checkusername.setVisible(true);
		checkpass.setVisible(true);
		checkrepass.setVisible(true);
	}

	public void onNextButton(ActionEvent event) {
		String name = username.getText().trim();
		String pass = password.getText().trim();
		String repass = repassword.getText().trim();

		Account account = Account.getInstance();
		Register register = new Register(name, pass);

		if (name.isEmpty()) {
			checkusername.setText("Insert your username please");
		}

		if (pass.isEmpty()) {
			checkpass.setText("Insert your password please");
		}

		if (repass.isEmpty()) {
			checkrepass.setText("Insert yout repassword please");
		}

		if (!name.isEmpty() && !pass.isEmpty() && !repass.isEmpty()) {
			if (pass.equals(repass) && pass.length() >= 8 && name.length() >= 8) {
				if (!register.checkID()) {
					account.setUsername(name);
					account.setpassword(pass);
					GameUISceneChange.REGISTER2.changeScene((Stage) next.getScene().getWindow(), "");
				} else {
					checkusername.setText("Your user already exist");
				}
			} else {
				if (name.length() < 8) {
					checkusername.setText("username must contain at least 8 characters");
				} else {
					checkusername.setText("");
				}

				if (pass.length() < 8) {
					checkpass.setText("password must contain at least 8 characters");
				} else {
					checkpass.setText("");
				}

				if (!repass.equals(pass)) {
					checkrepass.setText("repassword is not a same as password.");
				} else {
					checkrepass.setText("");
				}
			}
		} else {
			if (!name.isEmpty()) {
				if (name.length() < 8) {
					checkusername.setText("username must contain at least 8 characters");
					System.out.println("1");
				} else {
					checkusername.setText("");
				}

			}
			if (!pass.isEmpty()) {
				if (pass.length() < 8) {
					checkpass.setText("password must contain at least 8 characters");
					System.out.println("2");
				} else {
					checkpass.setText("");
				}
			}
			if (!repass.isEmpty()) {
				if (!repass.equals(pass)) {
					checkrepass.setText("repassword is not a same as password.");
					System.out.println("3");
					if (!pass.isEmpty()) {
						checkpass.setText("");
					}
				} else {
					checkrepass.setText("");
				}
			}
		}

	}

	public void onCancelButton(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage) cancel.getScene().getWindow(), "");
	}
}
