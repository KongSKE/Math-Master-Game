package gameUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader calculadolaGameLoader = new FXMLLoader(getClass().getResource("MainPageUI.fxml"));
			Parent root = calculadolaGameLoader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
