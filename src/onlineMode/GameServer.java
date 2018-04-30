package onlineMode;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class GameServer {

	private Server server;

	public GameServer() throws IOException {
		server = new Server();
		server.getKryo().register(ScoreData.class);
		server.addListener(new CalculadolaServerListener());
		server.start();
		server.bind(54333);
	}

	class CalculadolaServerListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
		}

		@Override
		public void disconnected(Connection connection) {
			super.disconnected(connection);
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			if (o instanceof ScoreData) {
				ScoreData sd = (ScoreData) o;
			}
		}
	}
}
