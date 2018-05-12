package gameUI;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import onlineMode.GameClient;

public class ChooseMiniGameController extends Contoller {

	@FXML
	Button calculadolaGameButton;
	@FXML
	Button questionIsGameButton;
	@FXML
	Button makeIt24GameButton;
	@FXML
	Label playerName;
	@FXML
	Button logoutButton;
	@FXML
	Button scoreboardButton;

	private String name;

	@FXML
	public void initialize() {
		calculadolaGameButton.setOnAction(this::onCalculadolaGameButtonClicked);
		questionIsGameButton.setOnAction(this::onQuestionIsGameButtonClicked);
		makeIt24GameButton.setOnAction(this::onmakeIt24GameButtonClicked);
		logoutButton.setOnAction(this::onLogoutButtonClicked);
		scoreboardButton.setOnAction(this::onScoreboardButtonClicked);

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				name = getName();
				playerName.setText(name);
				System.out.println("Namee: " + name);
			}
		});
	}

	public void onCalculadolaGameButtonClicked(ActionEvent event) {

		try {
			System.out.println(name);
			System.out.println((Stage) calculadolaGameButton.getScene().getWindow());
			new GameClient(name, (Stage) calculadolaGameButton.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onQuestionIsGameButtonClicked(ActionEvent event) {

		GameUISceneChange.QUESTIONIS.changeScene((Stage) questionIsGameButton.getScene().getWindow(), name);
	}

	public void onmakeIt24GameButtonClicked(ActionEvent event) {

		GameUISceneChange.MAKEIT24.changeScene((Stage) makeIt24GameButton.getScene().getWindow(), name);
	}
	
	public void onLogoutButtonClicked(ActionEvent event) {
		GameUISceneChange.LOGIN.changeScene((Stage) logoutButton.getScene().getWindow(), "");
	}
	
	public void onScoreboardButtonClicked(ActionEvent event) {
		GameUISceneChange.SCOREBOARD.changeScene((Stage) scoreboardButton.getScene().getWindow(), name);
	}

}
