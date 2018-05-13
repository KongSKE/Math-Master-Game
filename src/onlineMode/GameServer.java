package onlineMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import calculadolaGame.Calculadola;
import player.Player;

/**
 * Calculadola game server.
 * 
 * @author Dacharat Pankong
 *
 */
public class GameServer {

	private Server server;
	private Calculadola calculadola;
	private int numberPlayer = 2;
	private Map<Connection, String> user;
	private int questionNumber = 0;
	private int numberOfPlayerAnswer = 0;

	private static GameServer gameServer = null;

	/**
	 * Initialize game server.
	 * 
	 * @throws IOException
	 *             when port cannot bind
	 */
	private GameServer() throws IOException {

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

	public static GameServer getInstance() {
		if (gameServer == null)
			try {
				gameServer = new GameServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return gameServer;
	}

	/**
	 * Change game question and sent to client.
	 */
	public void changeGameQuestion() {
		String question = calculadola.getGameQuestion();
		Packet.QuestionData questionData = new Packet.QuestionData();
		questionData.queston = question;
		questionData.answer = calculadola.getAnswer();
		for (Connection c : user.keySet()) {
			c.sendTCP(questionData);
		}
	}

	/**
	 * Send player score to client.
	 * 
	 * @param connection
	 * @param score
	 */
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

	/**
	 * Send player name to other client.
	 * 
	 * @param connection
	 */
	public void sendPlayerName(Connection connection) {

		System.out.println(user);

		Packet.ScoreData scoreData1 = new Packet.ScoreData();
		scoreData1.name = user.get(connection);
		System.out.println(scoreData1.name + "----------------------");
		scoreData1.score = 0;
		Packet.ScoreData scoreData2 = new Packet.ScoreData();
		scoreData2.name = user.get(user.keySet().toArray()[user.size() - 2]);
		System.out.println(scoreData2.name + "----------------------");
		scoreData2.score = 0;

		for (Connection c : user.keySet()) {
			if (!c.equals(connection)) {
				c.sendTCP(scoreData1);
			} else {
				c.sendTCP(scoreData2);
			}
		}
	}

	/**
	 * Kryonet listener for server action.
	 * 
	 * @author Dacharat Pankong
	 *
	 */
	class GameServerListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);

		}

		@Override
		public void disconnected(Connection connection) {
			super.disconnected(connection);
			user.remove(connection);
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			if (o instanceof Integer) {
				Integer score = (Integer) o;
				sendPlayerScore(connection, score);
				numberOfPlayerAnswer++;
				if (questionNumber < 3 && numberOfPlayerAnswer == 2) {
					numberOfPlayerAnswer = 0;
					changeGameQuestion();
					questionNumber++;
				} else if (questionNumber == 3 && numberOfPlayerAnswer == 2) {
					questionNumber = 0;
					Packet.QuestionData questionData = new Packet.QuestionData();
					questionData.queston = "End";
					questionData.answer = 0;
					for (Connection c : user.keySet()) {
						c.sendTCP(questionData);
					}
				}
			} else if (o instanceof String) {
				String name = (String) o;
				user.put(connection, name);

				if (user.size() == numberPlayer) {
					sendPlayerName(connection);
					changeGameQuestion();
				}
			} else if (o instanceof Boolean) {
				disconnected(connection);
			}
		}
	}

	/**
	 * Start server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new GameServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
