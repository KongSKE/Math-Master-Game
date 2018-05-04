package onlineMode;

import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import calculadolaGame.Calculadola;

public class GameServer {

	private Server server;
	// private Map<Integer, String> user;

	public GameServer() throws IOException {

		// user = new HashMap<Integer, String>();
		server = new Server();

		server.getKryo().register(Calculadola.class);
		// server.getKryo().register(Packet.Connect.class);
		server.getKryo().register(Packet.ScoreData.class);

		server.addListener(new GameServerListener());
		server.start();
		server.bind(54333);
		System.out.println("Server Start");
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
			System.out.println("Player disconnect");
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			// if (o instanceof Packet.Connect) {
			// server.sendToAllExceptTCP(connection.getID(), o);
			// user.put(connection.getID(), ((Packet.Connect) o).name);
			// } else
			if (o instanceof Packet.ScoreData) {
				System.out.println("Receive data");
				server.sendToAllTCP(o);
				// connection.sendTCP(((Packet.ScoreData)o).score);
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
