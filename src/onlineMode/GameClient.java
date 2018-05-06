package onlineMode;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import calculadolaGame.Calculadola;
import javafx.application.Application;
import player.Player;

public class GameClient {

	private static Client client;
	private CalculadolaOnlineGameUI gameUI;
	private Player player;
	
	private String name;
	private int score;
	private String question;
	private double answer;
	
	public GameClient(String name) throws IOException {
		client = new Client();
		player = new Player(name);
		
		this.name = name;

		client.getKryo().register(Calculadola.class);

		client.getKryo().register(Packet.QuestionData.class);
		client.getKryo().register(Packet.ScoreData.class);

		client.addListener(new GameClientListener());

		new Thread() {
			public void run() {
				Application.launch(CalculadolaOnlineGameUI.class);
			};
		}.start();
		gameUI = CalculadolaOnlineGameUI.waitForLunch();
		gameUI.setClient(this);
		gameUI.setPlayerName(name);

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
				gameUI.setPlayerScore(name, score);
			} else if (o instanceof Packet.QuestionData) {
				Packet.QuestionData questionData = (Packet.QuestionData) o;
				question = questionData.queston;
				answer = questionData.answer;
				System.out.println(question);
				gameUI.setQuestion(question, answer);
			}
		}
	}

	public static void main(String[] args) {
		try {
			new GameClient("asb");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
