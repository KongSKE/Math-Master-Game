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
import player.Player;

public class GameClient {

	private static Client client;
	CalculadolaGameController controller;
	private Player player;

	private String name;
	private int score;
	private String question;
	private double answer;

	public GameClient(String name, Stage stage) throws IOException {
		client = new Client();
		player = new Player(name);

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
			System.out.println("controller "+controller);
			controller.setPlayerName(name);
			controller.setGameClient(this);

			stage.setScene(chooseGameScene);
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.start();
		client.connect(5000, "127.0.0.1", 54333);
	}

	public Player getPlayer() {
		return player;
	}

	public void sendScore(int score) {
		client.sendTCP(score);
	}

	class GameClientListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			client.sendTCP(name);
			System.out.println("Connect!!");
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			System.out.println("Recieve");
			if (o instanceof Packet.ScoreData) {
				Packet.ScoreData scoreData = (Packet.ScoreData) o;
				score = scoreData.score;
				name = scoreData.name;
				System.out.println(name + "__" + score);
				controller.sentPlayerScore(name, score);
			} else if (o instanceof Packet.QuestionData) {
				Packet.QuestionData questionData = (Packet.QuestionData) o;
				question = questionData.queston;
				answer = questionData.answer;
				System.out.println(question);
				controller.receiveQuestion(question, answer);
			}
		}
	}

}
