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

public class GameServer {

	private Server server;
	private Calculadola calculadola;
	private int numberPlayer = 2;
	 private Map<Connection, String> user;

	public GameServer() throws IOException {

		server = new Server();
		calculadola = new Calculadola();
		user = new HashMap<Connection, String>();

		server.getKryo().register(Calculadola.class);
		server.getKryo().register(Packet.ScoreData.class);

		server.addListener(new GameServerListener());
		server.start();
		server.bind(54333);
		System.out.println("Server Start");
	}
	
	class GameServerListener extends Listener {

		private int i = 1;
		
		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			System.out.println("New Client connect");
			user.put(connection, "Player" + i);
			System.out.println("Size = " + user.size());
			i++;
			if(user.size() == numberPlayer) {
				System.out.println("Game start!!");
			}
		}

		@Override
		public void disconnected(Connection connection) {
			super.disconnected(connection);
			System.out.println("Player disconnect");
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			if (o instanceof Packet.ScoreData) {
				System.out.println("Receive data");
				for(Connection c : user.keySet()) {
					c.sendTCP(o);
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
