package gameUI;

import java.util.Optional;

import javafx.application.Platform;
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

	private TimeCounter timeCount;
	private int playerScore;
	private Task<Void> sleeper;

	private GameClient client;
	private String question;
	private double answer;

	public void initialize() {
		answerText.setOnAction(this::onAnswerEnter);
		answerText.setEditable(false);

		playerScore = 0;
		player1Scorelabel.setText("Score: " + playerScore);
		
	}

	public void setPlayerName(String name) {
		player1NameLabel.setText(name);
	}

	public void receiveQuestion(String question, double answer) {

		this.question = question;
		this.answer = answer;

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("Controller get Question " + question);
				if (question.equals("End") && answer == 0) {
					gameEnd();
				} else {
					changeQuestion();
				}
			}
		});
	}

	public void sentPlayerScore(String name, int score) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				player2NameLabel.setText(name);
				player2ScoreLabel.setText("Score: " + score);
			}
		});
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
		resultLabel.setText("");
		timeCount = new TimeCounter(10);
		timeCount.setOnSucceeded(this::timeUpDisplay);
		answerText.setEditable(true);
		questionLabel.setText(question);

		timeCountdownProgress.progressProperty().bind(timeCount.progressProperty());
		new Thread(timeCount).start();
	}

	public void timeUpDisplay(WorkerStateEvent event) {
		if (timeCount.getTime() == 0) {
			resultLabel.setText("Time Up!!");
			resultLabel.setTextFill(Color.RED);
			client.sendScore(playerScore);
		}

	}

	public void onAnswerEnter(ActionEvent event) {
		String answerFromText = answerText.getText().trim();
		if (answerFromText.isEmpty()) {
			return;
		}
		timeCount.cancel();
		try {
			Double playerAnswer = Double.parseDouble(answerFromText);
			if (checkAnswer(answer, playerAnswer)) {
				resultLabel.setText("Correct!!");
				resultLabel.setTextFill(Color.GREEN);
				playerScore += timeCount.getTime();
			} else {
				resultLabel.setText(String.format("Wrong!! Answer: %.2f", answer));
				resultLabel.setTextFill(Color.RED);
			}
			player1Scorelabel.setText(playerScore + "");
			client.sendScore(playerScore);
			answerText.setEditable(false);
			answerText.clear();
		} catch (NumberFormatException e) {

		}
	}

	public boolean checkAnswer(double gameAnswer, double playerAnswer) {
		return Math.abs(playerAnswer - gameAnswer) < 1e-2;
	}

	public void backToHome() {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) questionLabel.getScene().getWindow(), player1NameLabel.getText());
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
