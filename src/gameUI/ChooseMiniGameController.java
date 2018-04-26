package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseMiniGameController {

	@FXML
	Button calculadolaGameButton;
	@FXML
	Button questionIsGameButton;
	@FXML
	Button makeIt24GameButton;
	
	@FXML
	public void initialize() {
		calculadolaGameButton.setOnAction(this::onCalculadolaGameButtonClicked);
		questionIsGameButton.setOnAction(this::onQuestionIsGameButtonClicked);
		makeIt24GameButton.setOnAction(this::onmakeIt24GameButtonClicked);
	}

	public void onCalculadolaGameButtonClicked(ActionEvent event) {
		
		GameUISceneChange.CALCULADOLA.changeScene((Stage)calculadolaGameButton.getScene().getWindow());
		
	}
	
	public void onQuestionIsGameButtonClicked(ActionEvent event) {
	
		GameUISceneChange.QUESTIONIS.changeScene((Stage)questionIsGameButton.getScene().getWindow());
	}
	
	public void onmakeIt24GameButtonClicked(ActionEvent event) {
		
		GameUISceneChange.MAKEIT24.changeScene((Stage)makeIt24GameButton.getScene().getWindow());
	}
	
	

}
