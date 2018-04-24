package gameUI;

import java.util.Optional;

import calculadolaGame.Calculadola;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
	Label timeCountdown;

	private Calculadola calculadora;
	private int questionNumber = 0;

	public void initialize() {
		answerText.setOnAction(this::onAnswerEnter);
		answerText.setEditable(false);

		calculadora = new Calculadola();
		changeQuestion();
	}

	public void changeQuestion() {
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				if (questionNumber < 2) {
					answerText.setEditable(true);
					questionLabel.setText(calculadora.getQuestion());
					questionNumber++;
					timeCountdown.setText(questionNumber + ": ");
				} else
					gameEnd();
			}
		});
		new Thread(sleeper).start();

	}

	public void onAnswerEnter(ActionEvent event) {
		String answerFromText = answerText.getText().trim();
		if (answerFromText.isEmpty()) {
			return;
		}
		try {
			Double answer = Double.parseDouble(answerFromText);
			if (calculadora.checkAnswer(answer)) {
				resultLabel.setText("Correct!!");
				resultLabel.setTextFill(Color.GREEN);
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
			// ... user chose "One"
		} else if (result.get() == buttonTypeTwo) {
			backToHome();
		}
	}

}
