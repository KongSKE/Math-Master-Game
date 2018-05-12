package gameUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Enum that collect all scene fxml file.
 * 
 * @author Dacharat Pankong
 *
 */
public enum GameUISceneChange {
	CHOOSEMINIGAME("ChooseMiniGameUI.fxml"), CALCULADOLA("CalculadolaGameUI.fxml"), QUESTIONIS(
			"QuestionIsGameUI.fxml"), MAKEIT24("MakeIt24GameUI.fxml"), LOGIN("MainPageUI.fxml"), REGISTER(
					"Register.fxml"), REGISTER2("Register2.fxml"), FORGET1(
							"forget1.fxml"), FORGET2("forget2.fxml"), SCOREBOARD("ScoreBoardUI.fxml");

	private String fxmlName;

	/**
	 * Set fxml name to selected scene.
	 * 
	 * @param fxmlName
	 */
	private GameUISceneChange(String fxmlName) {
		this.fxmlName = fxmlName;
	}

	/**
	 * Change scene to selecte scene.
	 * 
	 * @param stage
	 * @param name
	 */
	public void changeScene(Stage stage, String name) {
		try {

			FXMLLoader chooseGameLoader = new FXMLLoader(getClass().getResource(fxmlName));
			Parent chooseGameRoot = chooseGameLoader.load();
			Scene chooseGameScene = new Scene(chooseGameRoot);
			chooseGameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Contoller controller = chooseGameLoader.getController();
			System.out.println("Name: " + name);
			controller.setName(name);

			stage.setScene(chooseGameScene);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
