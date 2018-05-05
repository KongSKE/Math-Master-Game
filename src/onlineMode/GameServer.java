package onlineMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.HashMap;
//import java.util.Map;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import calculadolaGame.Calculadola;
import player.Player;

public class GameServer {

	private Server server;
	private Calculadola calculadola;
	private int numberPlayer = 2;
	// private Map<Connection, Player> user;
	private List<Connection> connections;
	private Connection thisConnection;
	private int questionNumber = 0;

	public GameServer() throws IOException {

		server = new Server();
		calculadola = new Calculadola(new Player("Player 1"));
		// user = new HashMap<Connection, Player>();
		connections = new ArrayList<Connection>();

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
		for (Connection c : connections) {
			c.sendTCP(questionData);
		}
	}

	public void sentPlayerScore(int score) {
		Packet.ScoreData scoreData = new Packet.ScoreData();
		String playerName = calculadola.getPlayerName();
		scoreData.name = playerName;
		scoreData.score = score;

		for (Connection c : connections) {
			c.sendTCP(scoreData);
		}
	}

	class GameServerListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			System.out.println("New Client connect");
			connections.add(connection);

			if (connections.size() == numberPlayer) {
				changeGameQuestion();
				System.out.println("Game start");
			}
		}

		@Override
		public void disconnected(Connection connection) {
			super.disconnected(connection);
			connections.remove(connection);
			System.out.println("Player disconnect");
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			thisConnection = connection;
			if (o instanceof Integer) {
				System.out.println("Receive data");
				Integer score = (Integer) o;
				sentPlayerScore(score);
				if (questionNumber < 3) {
					changeGameQuestion();
					questionNumber++;
				}
				System.out.println("====> " + questionNumber);
			} else if (o instanceof String) {
				String name = (String) o;
				Packet.ScoreData scoreData = new Packet.ScoreData();
				scoreData.name = name;
				scoreData.score = 0;
				for (Connection c : connections) {
					if (!c.equals(thisConnection))
						c.sendTCP(scoreData);
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
