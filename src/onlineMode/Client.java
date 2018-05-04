package onlineMode;

import java.util.ArrayList;
import java.util.List;

import com.lloseng.ocsf.client.AbstractClient;

import gameUI.CalculadolaGameController;

public class Client extends AbstractClient {

	private List<CalculadolaGameController> calculadoraGame = new ArrayList<CalculadolaGameController>();

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {

		String[] message = ((String) msg).split(" ");
		String playerName = message[0];
		String score = message[1];
		for (CalculadolaGameController game : calculadoraGame) {
			game.displayScore(playerName + " " + score);
		}
	}

}
