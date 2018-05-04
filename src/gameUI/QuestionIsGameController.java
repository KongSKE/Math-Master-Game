package gameUI;

import java.util.Optional;
import java.util.Random;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import questionIsGame.QuestionIs;

public class QuestionIsGameController {

	@FXML
	TextField number1Text;
	@FXML
	TextField number2Text;
	@FXML
	TextField number3Text;
	@FXML
	Label operation1Label;
	@FXML
	Label operation2Label;
	@FXML
	Label answerLabel;
	@FXML
	Label resultLabel;
	@FXML
	Label player1NameLabel;
	@FXML
	Label player1ScoreLabel;
	@FXML
	Label player2NameLabel;
	@FXML
	Label player2ScoreLabel;
	@FXML
	ProgressBar timeCountdownProgress;

	private QuestionIs question;
	private int questionNumber;
	private String num1Text;
	private String num2Text;
	private String num3Text;
	private Random random = new Random();
	private int playerScore;
	private TimeCounter timeCounter;
	private TimeDelay delay;

	public void initialize() {
		number1Text.setOnAction(this::onAnyTextFieldPressEnter);
		number2Text.setOnAction(this::onAnyTextFieldPressEnter);
		number3Text.setOnAction(this::onAnyTextFieldPressEnter);
		resultLabel.setText("");

		question = new QuestionIs();
		questionNumber = 0;
		playerScore = 0;
		player1ScoreLabel.setText("Score: " + playerScore);
		getNewQuestion();
	}

	public void getNewQuestion() {
		delay = new TimeDelay();
		delay.setOnSucceeded(this::setNewQuestion);
		new Thread(delay).start();
	}

	public void setNewQuestion(WorkerStateEvent event) {
		if (questionNumber < 2) {
			timeCounter = new TimeCounter(10);
			timeCounter.setOnSucceeded(this::timeUpDisPlay);
			question.getQuestion();
			operation1Label.setText(question.getFirstOperation());
			operation2Label.setText(question.getSecondOperation());
			answerLabel.setText(question.getAnswer() + "");
			number1Text.requestFocus();
			randomShowNumber();
			questionNumber++;

			timeCountdownProgress.progressProperty().bind(timeCounter.progressProperty());
			new Thread(timeCounter).start();
		} else
			gameEnd();
	}

	public void timeUpDisPlay(WorkerStateEvent event) {
		if (timeCounter.getTime() == 0) {
			resultLabel.setText("Time Up!!");
			getNewQuestion();
		}
	}

	public void randomShowNumber() {
		int num = random.nextInt(3) + 1;
		if (num == 1) {
			number1Text.setText(question.getFirstNumber() + "");
			number1Text.setEditable(false);
		} else if (num == 2) {
			number2Text.setText(question.getSecondNumber() + "");
			number2Text.setEditable(false);
		} else if (num == 3) {
			number3Text.setText(question.getThirdNumber() + "");
			number3Text.setEditable(false);
		}
	}

	public boolean checkEmptyTextField() {
		if (number1Text.getText().trim().isEmpty()) {
			number1Text.requestFocus();
			number1Text.setStyle("-fx-focus-color: red");
			return false;
		} else if (number2Text.getText().trim().isEmpty()) {
			number2Text.requestFocus();
			number2Text.setStyle("-fx-focus-color: red");
			return false;
		} else if (number3Text.getText().trim().isEmpty()) {
			number3Text.requestFocus();
			number3Text.setStyle("-fx-focus-color: red");
			return false;
		}
		return true;
	}

	public void resetTextField() {

		number1Text.clear();
		number2Text.clear();
		number3Text.clear();
		number1Text.setEditable(true);
		number2Text.setEditable(true);
		number3Text.setEditable(true);
	}

	public void onAnyTextFieldPressEnter(ActionEvent event) {
		if (checkEmptyTextField()) {
			num1Text = number1Text.getText().trim();
			num2Text = number2Text.getText().trim();
			num3Text = number3Text.getText().trim();
			try {
				int num1 = Integer.parseInt(num1Text);
				int num2 = Integer.parseInt(num2Text);
				int num3 = Integer.parseInt(num3Text);

				timeCounter.cancel();

				String ans = num1 + question.getFirstOperation() + num2 + question.getSecondOperation() + num3;
				System.out.println(ans);

				if (question.checkAnswer(ans)) {
					resultLabel.setText("Correct!!");
					resultLabel.setTextFill(Color.GREEN);
					playerScore += timeCounter.getTime();
					player1ScoreLabel.setText("Score: " + playerScore);
				} else {
					resultLabel.setText("Wrong!: " + question.getQuestionIsSolution());
					resultLabel.setTextFill(Color.RED);
				}
				resetTextField();
				getNewQuestion();
			} catch (NumberFormatException e) {

			}
		}
	}

	public void backToHome() {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) answerLabel.getScene().getWindow());
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
