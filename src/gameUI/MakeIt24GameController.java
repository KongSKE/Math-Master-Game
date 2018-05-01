package gameUI;

import java.util.Optional;

import org.matheclipse.core.eval.ExprEvaluator;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import makeIt24Game.MakeIt24;

public class MakeIt24GameController {

	@FXML
	Label resultLabel;
	@FXML
	Label correctLabel;
	@FXML
	Button openBracketButton;
	@FXML
	Button number1Button;
	@FXML
	Button number2Button;
	@FXML
	Button number3Button;
	@FXML
	Button number4Button;
	@FXML
	Button closingBracketButton;
	@FXML
	Button addOperationButton;
	@FXML
	Button minusOperationButton;
	@FXML
	Button multiplyOperationButton;
	@FXML
	Button divideOperationButton;
	@FXML
	Button deleteButton;
	@FXML
	Button clearButton;

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

	private MakeIt24 make24;
	private int bracket;
	private int question;
	private ExprEvaluator e;
	private String oldInput;
	private String newInput;
	private String output;
	private int playerScore;
	private TimeCounter timeCounter;
	private TimeDelay delay;

	public void initialize() {
		number1Button.setOnAction(this::onNumberButtonClicked);
		number2Button.setOnAction(this::onNumberButtonClicked);
		number3Button.setOnAction(this::onNumberButtonClicked);
		number4Button.setOnAction(this::onNumberButtonClicked);

		addOperationButton.setOnAction(this::onOperationButtonClick);
		minusOperationButton.setOnAction(this::onOperationButtonClick);
		multiplyOperationButton.setOnAction(this::onOperationButtonClick);
		divideOperationButton.setOnAction(this::onOperationButtonClick);

		openBracketButton.setOnAction(this::onOperationButtonClick);
		closingBracketButton.setOnAction(this::onOperationButtonClick);

		deleteButton.setOnAction(this::onDeleteButtonClicked);
		clearButton.setOnAction(this::onClearButtonClicked);

		make24 = new MakeIt24();
		e = new ExprEvaluator();
		question = 0;
		playerScore = 0;
		player1ScoreLabel.setText("Score: " + playerScore);
		getAllNumber();
	}

	public void getAllNumber() {

		delay = new TimeDelay();
		delay.setOnSucceeded(this::setAllNumber);
		new Thread(delay).start();

	}

	public void setAllNumber(WorkerStateEvent event) {
		bracket = 1;
		if (question < 2) {
			timeCounter = new TimeCounter(20);
			timeCounter.setOnSucceeded(this::timeUpDisplay);
			resultLabel.setText("(");
			make24.getQuestion();
			number1Button.setText(make24.getNumber1() + "");
			number2Button.setText(make24.getNumber2() + "");
			number3Button.setText(make24.getNumber3() + "");
			number4Button.setText(make24.getNumber4() + "");

			number1Button.setVisible(true);
			number2Button.setVisible(true);
			number3Button.setVisible(true);
			number4Button.setVisible(true);

			question++;

			timeCountdownProgress.progressProperty().bind(timeCounter.progressProperty());
			new Thread(timeCounter).start();
		} else
			gameEnd();
	}

	public void timeUpDisplay(WorkerStateEvent event) {
		if (timeCounter.getTime() == 0) {
			resultLabel.setText("Time Up!!");
			getAllNumber();
		}
	}

	public void onOperationButtonClick(ActionEvent event) {
		Button b = (Button) event.getSource();
		oldInput = resultLabel.getText();
		newInput = b.getText();
		output = oldInput + newInput;
		if (newInput.equals("("))
			bracket++;
		else if (newInput.equals(")")) {
			if (bracket > 0) {
				String calculate = oldInput.substring(oldInput.lastIndexOf("(") + 1);
				output = oldInput.substring(0, oldInput.lastIndexOf("(")) + e.evaluate(calculate);
				bracket--;
			} else
				newInput = "";
		}
		resultLabel.setText(output);
	}

	public void onNumberButtonClicked(ActionEvent event) {
		Button b = (Button) event.getSource();
		b.setVisible(false);
		oldInput = resultLabel.getText();
		newInput = b.getText();
		output = oldInput + newInput;
		if (!number1Button.isVisible() && !number2Button.isVisible() && !number3Button.isVisible()
				&& !number4Button.isVisible()) {
			if (bracket > 0) {
				for (int i = 0; i < bracket; i++) {
					output += ")";
				}
			}
			resultLabel.setText(output = e.evaluate(output) + "");
			timeCounter.cancel();
			if (output.equals("24")) {
				correctLabel.setText("Corect!!");
				playerScore += timeCounter.getTime();
				player1ScoreLabel.setText("Score: " + playerScore);
			} else
				correctLabel.setText("Wrong!!");
			getAllNumber();
		} else {
			resultLabel.setText(output);
		}
	}

	public void onClearButtonClicked(ActionEvent event) {
		correctLabel.setText(make24.getSolution());
		resultLabel.setText("");
		bracket = 0;
		number1Button.setVisible(true);
		number2Button.setVisible(true);
		number3Button.setVisible(true);
		number4Button.setVisible(true);

	}

	public void onDeleteButtonClicked(ActionEvent event) {
		output = resultLabel.getText();
		char lastIndex = output.charAt(output.length() - 1);
		output = output.substring(0, output.length() - 1);
		if (lastIndex == '(')
			bracket--;
		else if (lastIndex == ')')
			bracket++;
		else if (lastIndex == number1Button.getText().charAt(0))
			number1Button.setVisible(true);
		else if (lastIndex == number2Button.getText().charAt(0))
			number2Button.setVisible(true);
		else if (lastIndex == number3Button.getText().charAt(0))
			number3Button.setVisible(true);
		else if (lastIndex == number4Button.getText().charAt(0))
			number4Button.setVisible(true);
		resultLabel.setText(output);
	}

	public void backToHome() {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) number1Button.getScene().getWindow());
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
// ÷
