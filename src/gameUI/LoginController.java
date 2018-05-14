package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import users.Login;

/**
 * Login Controller
 * @author Varit Assavavisidchai
 *
 */
public class LoginController extends Contoller {

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
	Label status;

	private Color red = Color.RED;

	/**
	 * Initialize
	 */
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

	/**
	 * Action when clicked login button
	 * @param event
	 */
	public void onLoginClicked(ActionEvent event) {
		if (showpass.isSelected()) {
			Login account = new Login(username.getText(), passtext.getText());
			if (account.match()) {
				GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) login.getScene().getWindow(), username.getText());
			} else {
				status.setTextFill(red);
				status.setText("Username or password is incorrect.");
				username.setText("");
				password.setText("");
			}
		} else {
			Login account = new Login(username.getText(), password.getText());
			if (account.match()) {
				GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) login.getScene().getWindow(), username.getText());
			} else {
				status.setTextFill(red);
				status.setText("Username or password is incorrect.");
				username.setText("");
				password.setText("");
			}
		}
	}

	/**
	 * Action when check on checkbox.
	 * @param event
	 */
	public void onCheckBoxClicked(ActionEvent event) {
		if (showpass.isSelected()) {
			password.setVisible(false);
			passtext.setText(password.getText().trim());
			passtext.setVisible(true);
		} else {
			passtext.setVisible(false);
			password.setText(password.getText().trim());
			password.setVisible(true);
		}
	}

	/**
	 * Action when clicked register button
	 * @param event
	 */
	public void onRegisterClicked(ActionEvent event) {
		GameUISceneChange.REGISTER.changeScene((Stage) register.getScene().getWindow(), "");
	}

	/**
	 * Action when clicked forget button
	 * @param evnet
	 */
	public void onForgetClicked(ActionEvent evnet) {
		GameUISceneChange.FORGET1.changeScene((Stage) forget.getScene().getWindow(), "");
	}
}
