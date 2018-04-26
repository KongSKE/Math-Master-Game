package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import makeIt24Game.MakeIt24;

public class MakeIt24GameController {

	@FXML
	Label resultLabel;
	@FXML
	Button number1Button;
	@FXML
	Button number2Button;
	@FXML
	Button number3Button;
	@FXML
	Button number4Button;
	@FXML
	Button addOperationButton;
	@FXML
	Button minusOperationButton;
	@FXML
	Button multiplyOperationButton;
	@FXML
	Button divideOperationButton;
	@FXML
	Button clearButton;

	private MakeIt24 make24;

	public void initialize() {
		number1Button.setOnAction(this::onNumberButtonClicked);
		number2Button.setOnAction(this::onNumberButtonClicked);
		number3Button.setOnAction(this::onNumberButtonClicked);
		number4Button.setOnAction(this::onNumberButtonClicked);
		addOperationButton.setOnAction(this::onOperationButtonClick);
		minusOperationButton.setOnAction(this::onOperationButtonClick);
		multiplyOperationButton.setOnAction(this::onOperationButtonClick);
		divideOperationButton.setOnAction(this::onOperationButtonClick);

		make24 = new MakeIt24();
		resultLabel.setText("");
		getAllNumber();
	}

	public void getAllNumber() {
		make24.getQuestion();
		number1Button.setText(make24.getNumber1() + "");
		number2Button.setText(make24.getNumber2() + "");
		number3Button.setText(make24.getNumber3() + "");
		number4Button.setText(make24.getNumber4() + "");
		
		number1Button.setVisible(true);
		number1Button.setVisible(true);
		number1Button.setVisible(true);
		number1Button.setVisible(true);
	}

	public void onOperationButtonClick(ActionEvent event) {
		Button b = (Button) event.getSource();
		resultLabel.setText(resultLabel.getText() + b.getText());
		System.out.println(b.getText());
	}
	
	public void onNumberButtonClicked(ActionEvent event) {
		Button b = (Button) event.getSource();
		b.setVisible(false);
		resultLabel.setText(resultLabel.getText() + b.getText());
		System.out.println(b.getText());
	}
}
