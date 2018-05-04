package gameUI;

import java.util.Optional;

import calculadolaGame.Calculadola;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import onlineMode.GameClient;

public class CalculadolaGameController {

	@FXML
	Label questionLabel;
	@FXML
	TextField answerText;
	@FXML
	Label resultLabel;
	@FXML
	Label player1NameLabel;
	@FXML
	Label player1Scorelabel;
	@FXML
	Label player2NameLabel;
	@FXML
	Label player2ScoreLabel;
	@FXML
	ProgressBar timeCountdownProgress;

	private Calculadola calculadora;
	private int questionNumber;
	private TimeCounter timeCount;
	private int playerScore;
	private Task<Void> sleeper;

	private GameClient client;

	public void initialize() {
		answerText.setOnAction(this::onAnswerEnter);
		answerText.setEditable(false);

		calculadora = new Calculadola();
		changeQuestion();
		playerScore = 0;
		questionNumber = 0;
		player1Scorelabel.setText("Score: " + playerScore);

	}

	public void setGameClient(GameClient client) {
		this.client = client;
	}

	public void changeQuestion() {
		sleeper = new TimeDelay();
		sleeper.setOnSucceeded(this::changeAllOutput);
		new Thread(sleeper).start();
	}

	public void changeAllOutput(WorkerStateEvent event) {
		if (questionNumber < 2) {
			resultLabel.setText("");
			timeCount = new TimeCounter(10);
			timeCount.setOnSucceeded(this::timeUpDisplay);
			answerText.setEditable(true);
			questionLabel.setText(calculadora.getGameQuestion());
			questionNumber++;

			timeCountdownProgress.progressProperty().bind(timeCount.progressProperty());
			new Thread(timeCount).start();
		} else
			gameEnd();
	}

	public void timeUpDisplay(WorkerStateEvent event) {
		if (timeCount.getTime() == 0) {
			resultLabel.setText("Time Up!!");
			resultLabel.setTextFill(Color.RED);
			changeQuestion();
		}

	}

	public void onAnswerEnter(ActionEvent event) {
		String answerFromText = answerText.getText().trim();
		if (answerFromText.isEmpty()) {
			return;
		}
		timeCount.cancel();
		try {
			Double answer = Double.parseDouble(answerFromText);
			if (calculadora.checkAnswer(answer + "")) {
				resultLabel.setText("Correct!!");
				resultLabel.setTextFill(Color.GREEN);
				playerScore += timeCount.getTime();
			} else {
				resultLabel.setText(String.format("Wrong!! Answer: %.2f", calculadora.getAnswer()));
				resultLabel.setTextFill(Color.RED);
			}
			client.sendScore(playerScore);
			answerText.setEditable(false);
			answerText.clear();
			changeQuestion();
		} catch (NumberFormatException e) {

		}
	}

	public void displayScore(Object currentScore) {
		String[] message = ((String) currentScore).split(" ");
		String playerName = message[0];
		String score = message[1];
		if (playerName.equals("Player1")) {
			player1Scorelabel.setText(score);
		} else {
			player2ScoreLabel.setText(score);
		}
	}

	// public void setScore(Map<String, Integer> player) {
	// if (player.keySet().toString().equals("Player1")) {
	// player1Scorelabel.setText(player.get("Player1") + "");
	// } else if (player.keySet().toString().equals("Player2")) {
	// player2ScoreLabel.setText(player.get("Player2") + "");
	// }
	// }
	//
	// private void sendScore() {
	// GameClient.sendScore("Player1", playerScore);
	// }

	public void backToHome() {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) questionLabel.getScene().getWindow());
	}

	public void gameEnd() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog with Custom Actions");
		alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Play this again");
		ButtonType buttonTypeTwo = new ButtonType("Back to Home");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			initialize();
		} else if (result.get() == buttonTypeTwo) {
			backToHome();
		}
	}

}
