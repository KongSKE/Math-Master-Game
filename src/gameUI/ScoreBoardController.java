package gameUI;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import player.Scoreboard;
import users.Account;

public class ScoreBoardController extends Contoller {

	@FXML
	Label player1NameLabel;
	@FXML
	Label player2NameLabel;
	@FXML
	Label player3NameLabel;
	@FXML
	Label player4NameLabel;
	@FXML
	Label player5NameLabel;
	@FXML
	Label player1ScoreLabel;
	@FXML
	Label player2ScoreLabel;
	@FXML
	Label player3ScoreLabel;
	@FXML
	Label player4ScoreLabel;
	@FXML
	Label player5ScoreLabel;

	Scoreboard board = new Scoreboard();
	List<Label> playername = new ArrayList<>();
	List<Label> score = new ArrayList<>();
	Account account = Account.getInstance();

	public void initialize() {
		playername.add(player1NameLabel);
		playername.add(player2NameLabel);
		playername.add(player3NameLabel);
		playername.add(player4NameLabel);
		playername.add(player5NameLabel);
		score.add(player1ScoreLabel);
		score.add(player2ScoreLabel);
		score.add(player3ScoreLabel);
		score.add(player4ScoreLabel);
		score.add(player5ScoreLabel);

		board.calculadolaBoard();
		// for (Label label : account.getTopplayer().) {
		//
		// }
	}
}
