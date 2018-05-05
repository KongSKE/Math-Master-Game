package onlineMode;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import gameUI.CalculadolaGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculadolaOnlineGameUI extends Application {

	private CalculadolaGameController controller;
	private GameClient client;
	private static CalculadolaOnlineGameUI ui;
	private final static CountDownLatch latch = new CountDownLatch(1);

	private FXMLLoader loader;
	private Parent root;
	private Scene scene;

	public void setCalculadolaOnlineGameUI(CalculadolaOnlineGameUI ui) {
		this.ui = ui;

		loader = new FXMLLoader(getClass().getResource("/gameUI/CalculadolaGameUI.fxml"));

		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);

		controller = loader.getController();
		latch.countDown();
	}

	public CalculadolaOnlineGameUI() {
		setCalculadolaOnlineGameUI(this);
	}

	public static CalculadolaOnlineGameUI waitForLunch() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ui;
	}

	public void setClient(GameClient client) {
		this.client = client;
		controller.setGameClient(client);
	}

	public void setPlayerScore(String name, int score) {
		controller.sentPlayerScore(name, score);
	}

	public void setQuestion(String question) {
		controller.receiveQuestion(question);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
