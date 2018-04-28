package gameUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public enum GameUISceneChange {
	CHOOSEMINIGAME("ChooseMiniGameUI.fxml"), CALCULADOLA("CalculadolaGameUI.fxml"), QUESTIONIS(
			"QuestionIsGameUI.fxml"), MAKEIT24(
					"MakeIt24GameUI.fxml"), LOGIN("MainPageUI.fxml"), REGISTER("Register.fxml"), REGISTER2("Register2.fxml");

	private String fxmlName;

	private GameUISceneChange(String fxmlName) {
		this.fxmlName = fxmlName;
	}

	public void changeScene(Stage stage) {
		try {

			FXMLLoader chooseGameLoader = new FXMLLoader(getClass().getResource(fxmlName));
			Parent chooseGameRoot = chooseGameLoader.load();
			Scene chooseGameScene = new Scene(chooseGameRoot);
			chooseGameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(chooseGameScene);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
