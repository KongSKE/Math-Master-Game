package onlineMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import calculadolaGame.Calculadola;
import player.Player;

public class GameServer {

	private Server server;
	private Calculadola calculadola;
	private int numberPlayer = 2;
	private Map<Connection, String> user;
	private int questionNumber = 0;

	public GameServer() throws IOException {

		server = new Server();
		calculadola = new Calculadola(new Player("Player 1"));
		user = new HashMap<Connection, String>();

		server.getKryo().register(Calculadola.class);

		server.getKryo().register(Packet.QuestionData.class);
		server.getKryo().register(Packet.ScoreData.class);

		server.addListener(new GameServerListener());
		server.start();
		server.bind(54333);
		System.out.println("Server Start");
	}

	public void changeGameQuestion() {
		String question = calculadola.getGameQuestion();
		Packet.QuestionData questionData = new Packet.QuestionData();
		questionData.queston = question;
		questionData.answer = calculadola.getAnswer();
		for (Connection c : user.keySet()) {
			c.sendTCP(questionData);
		}
	}

	public void sendPlayerScore(Connection connection, int score) {
		Packet.ScoreData scoreData = new Packet.ScoreData();
		String playerName = user.get(user.keySet().toArray()[user.size() - 2]);
		scoreData.name = playerName;
		scoreData.score = score;

		for (Connection c : user.keySet()) {
			if (!c.equals(connection))
				c.sendTCP(scoreData);
		}
	}

	public void sendPlayerName(Connection connection) {
		Packet.ScoreData scoreData1 = new Packet.ScoreData();
		scoreData1.name = user.get(connection);
		scoreData1.score = 0;
		Packet.ScoreData scoreData2 = new Packet.ScoreData();
		scoreData2.name = user.get(user.keySet().toArray()[user.size() - 2]);
		System.out.println(scoreData2.name);
		scoreData2.score = 0;
		for (Connection c : user.keySet()) {
			if (c.equals(connection)) {
				c.sendTCP(scoreData2);
			} else {
				c.sendTCP(scoreData1);
			}
		}
	}

	class GameServerListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			System.out.println("New Client connect");

		}

		@Override
		public void disconnected(Connection connection) {
			super.disconnected(connection);
			user.remove(connection);
			System.out.println("Player disconnect");
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			if (o instanceof Integer) {
				System.out.println("Receive data");
				Integer score = (Integer) o;
				sendPlayerScore(connection, score);
				if (questionNumber < 3) {
					changeGameQuestion();
					questionNumber++;
				}
				System.out.println("====> " + questionNumber);
			} else if (o instanceof String) {
				String name = (String) o;
				user.put(connection, name);

				System.out.println(user.size());
				if (user.size() == numberPlayer) {
					System.out.println("Game start");
					sendPlayerName(connection);
					changeGameQuestion();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			new GameServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
