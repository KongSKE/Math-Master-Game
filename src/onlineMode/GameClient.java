package onlineMode;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import calculadolaGame.Calculadola;
import javafx.application.Application;
import makeIt24Game.MakeIt24;
import questionIsGame.QuestionIs;

public class GameClient {

	private static Client client;
	private CalculadolaOnlineGameUI gameUI;

	public GameClient() throws IOException {

		client = new Client();

		client.getKryo().register(Calculadola.class);
		client.getKryo().register(QuestionIs.class);
		client.getKryo().register(MakeIt24.class);

		client.getKryo().register(Packet.ScoreData.class);

		client.addListener(new Listener());

		new Thread() {
			public void run() {
				Application.launch(CalculadolaOnlineGameUI.class);
			};
		}.start();
		gameUI = CalculadolaOnlineGameUI.waitForLunch();
		gameUI.setClient(this);

		client.start();
		client.connect(5000, "127.0.0.1", 54333);
	}

	public void sendScore(int score) {
		client.sendTCP(score);
	}
	
	class GameClientListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			System.out.println("Connect!!");
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			System.out.println("Recieve");
			if (o instanceof Packet.ScoreData) {
				System.out.println(o);
				System.out.println("send data");
			}
		}
	}

	public static void main(String[] args) {
		try {
			new GameClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
