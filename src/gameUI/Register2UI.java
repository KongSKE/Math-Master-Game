package gameUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Register2UI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader calculadolaGameLoader = new FXMLLoader(getClass().getResource("Register2.fxml"));
			Parent root = calculadolaGameLoader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
