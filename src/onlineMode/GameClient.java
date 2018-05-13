package onlineMode;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import calculadolaGame.Calculadola;
import gameUI.CalculadolaGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Calculadola game client.
 * 
 * @author Dacharat Pankong
 *
 */
public class GameClient {

	private static Client client;
	CalculadolaGameController controller;

	private String name;
	private int score;
	private String question;
	private double answer;

	/**
	 * Initialize client game .
	 * 
	 * @param name
	 * @param stage
	 * @throws IOException when connot connect to server.
	 */
	public GameClient(String name, Stage stage) throws IOException {
		client = new Client();

		this.name = name;

		client.getKryo().register(Calculadola.class);

		client.getKryo().register(Packet.QuestionData.class);
		client.getKryo().register(Packet.ScoreData.class);

		client.addListener(new GameClientListener());

		try {
			FXMLLoader chooseGameLoader = new FXMLLoader(getClass().getResource("../gameUI/CalculadolaGameUI.fxml"));
			Parent chooseGameRoot = chooseGameLoader.load();
			Scene chooseGameScene = new Scene(chooseGameRoot);

			controller = chooseGameLoader.getController();
			controller.setPlayerName(name);
			controller.setGameClient(this);

			stage.setScene(chooseGameScene);
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.start();
		client.connect(5000, "127.0.0.1", 54333);
	}

	/**
	 * Send score to server.
	 * 
	 * @param score
	 */
	public void sendScore(int score) {
		client.sendTCP(score);
	}

	public void disconnected() {
		client.sendTCP(true);
	}
	
	/**
	 * Kryonet listener for client action.
	 * 
	 * @author Dacharat Pankong
	 *
	 */
	class GameClientListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			client.sendTCP(name);
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			if (o instanceof Packet.ScoreData) {
				Packet.ScoreData scoreData = (Packet.ScoreData) o;
				score = scoreData.score;
				name = scoreData.name;
				controller.sentPlayerScore(name, score);
			} else if (o instanceof Packet.QuestionData) {
				Packet.QuestionData questionData = (Packet.QuestionData) o;
				question = questionData.queston;
				answer = questionData.answer;
				controller.receiveQuestion(question, answer);
			}
		}
	}

}
