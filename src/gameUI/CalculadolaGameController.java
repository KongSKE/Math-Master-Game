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

	public void initialize() {
		answerText.setOnAction(this::onAnswerEnter);
		answerText.setEditable(false);

		calculadora = new Calculadola();
		changeQuestion();
		playerScore = 0;
		questionNumber = 0;
		player1Scorelabel.setText("Score: " + playerScore);
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
			questionLabel.setText(calculadora.getQuestion());
			questionNumber++;
			
			timeCountdownProgress.progressProperty().bind(timeCount.progressProperty());
			new Thread(timeCount).start();
		} else
			gameEnd();
	}
	
	public void timeUpDisplay(WorkerStateEvent event) {
		if(timeCount.getTime() == 0) {
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
			if (calculadora.checkAnswer(answer)) {
				resultLabel.setText("Correct!!");
				resultLabel.setTextFill(Color.GREEN);
				playerScore += timeCount.getTime();
				player1Scorelabel.setText("Score: " + playerScore);
			} else {
				resultLabel.setText(String.format("Wrong!! Answer: %.2f", calculadora.getAnswer()));
				resultLabel.setTextFill(Color.RED);
			}
			answerText.setEditable(false);
			answerText.clear();
			changeQuestion();
		} catch (NumberFormatException e) {

		}
	}

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
