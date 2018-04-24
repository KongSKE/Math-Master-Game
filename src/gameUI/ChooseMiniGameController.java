package gameUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	}

	public void onCalculadolaGameButtonClicked(ActionEvent event) {
		
		GameUISceneChange.CALCULADOLA.changeScene((Stage)calculadolaGameButton.getScene().getWindow());
		
	}
	
	public void onQuestionIsGameButtonClicked(ActionEvent event) {
	
		GameUISceneChange.QUESTIONIS.changeScene((Stage)questionIsGameButton.getScene().getWindow());
	}
	
	public void onmakeIt24GameButtonClicked(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent myPane = null;
			myPane = FXMLLoader.load(getClass().getResource("--.fxml"));
			Scene scene = new Scene(myPane);
			stage.setScene(scene);

//			thisScene.close();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
