package onlineMode;

import java.io.IOException;
import java.util.HashMap;
//import java.util.HashMap;
//import java.util.Map;
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

	public GameServer() throws IOException {

		server = new Server();
		calculadola = new Calculadola(new Player("Player 1"));
		user = new HashMap<Connection, String>();

		server.getKryo().register(Calculadola.class);
		server.getKryo().register(Packet.ScoreData.class);

		server.addListener(new GameServerListener());
		server.start();
		server.bind(54333);
		System.out.println("Server Start");
	}
	
	public void startGame() {
		String question = calculadola.getGameQuestion();
		Packet.QuestionData questionData = new Packet.QuestionData();
		questionData.queston = question;
		for(Connection c : user.keySet()) {
			c.sendTCP(questionData);
		}
	}
	
	public void sentPlayerScore(int score) {
		Packet.ScoreData scoreData = new Packet.ScoreData();
		String playerName = calculadola.getPlayerName();
		scoreData.name = playerName;
		scoreData.score = score;
		
		for(Connection c : user.keySet()) {
			c.sendTCP(scoreData);
		}
	}
	
	class GameServerListener extends Listener {

		private int i = 1;
		
		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			System.out.println("New Client connect");
			String playerName = "Player " + i;
			user.put(connection, playerName);
			i++;
			
			Packet.ScoreData scoreData = new Packet.ScoreData();
			scoreData.name = playerName;
			scoreData.score = 0;
			connection.sendTCP(scoreData);
			
			if(user.size() == numberPlayer) {
				startGame();
			}
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
				sentPlayerScore(score);
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
