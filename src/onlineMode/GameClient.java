package onlineMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import calculadolaGame.Calculadola;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import makeIt24Game.MakeIt24;
import questionIsGame.QuestionIs;

public class GameClient extends Application {

	private static Client client;
//	private static Map<String, Integer> player;

	public GameClient() throws IOException {

		client = new Client();
//		player = new HashMap<String, Integer>();

		client.getKryo().register(Calculadola.class);
		client.getKryo().register(QuestionIs.class);
		client.getKryo().register(MakeIt24.class);

		client.getKryo().register(Packet.ScoreData.class);
//		client.getKryo().register(Packet.Connect.class);

		client.addListener(new Listener());
		client.start();
		client.connect(5000, "127.0.0.1", 54333);
	}

	// public static void connect(String name, String ipAddress) throws IOException
	// {
	// client.getKryo().register(Calculadola.class);
	// client.getKryo().register(QuestionIs.class);
	// client.getKryo().register(MakeIt24.class);
	//
	// client.getKryo().register(Packet.ScoreData.class);
	// client.getKryo().register(Packet.Connect.class);
	//
	// client.addListener(new Listener());
	// client.start();
	// client.connect(5000, " 1270.0.0.1", 54333);

	// }

	class GameClientListener extends Listener {

		@Override
		public void connected(Connection connection) {
			super.connected(connection);
			System.out.println("Connect!!");
//			if (connection.getID() == 0) {
//				player.put("Player1", 0);
//				System.out.println("Player 1 ");
//			} else {
//				player.put("Player2", 0);
//				System.out.println("Player 2 ");
//			}
		}

		@Override
		public void received(Connection connection, Object o) {
			super.received(connection, o);
			System.out.println("Recieve");
			if (o instanceof Packet.ScoreData) {
				System.out.println(o);
//				player.put(((Packet.ScoreData) o).name, ((Packet.ScoreData) o).score);
				System.out.println("send data");
			}
		}
	}

	public static void sendScore(String name, int score) {
		Packet.ScoreData data = new Packet.ScoreData();
		data.name = name;
		data.score = score;
		client.sendTCP(data);
	}

	// public static Map<String, Integer> getPlayer() {
	// return player;
	// }

	@Override
	public void start(Stage stage) throws Exception {
		try {
			FXMLLoader calculadolaGameLoader = new FXMLLoader(getClass().getResource("/gameUI/CalculadolaGameUI.fxml"));
			Parent root = calculadolaGameLoader.load();
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new GameClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
		launch(args);
	}
}
