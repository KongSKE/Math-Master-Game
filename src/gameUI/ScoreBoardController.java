package gameUI;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import player.Scoreboard;
import users.Account;

/**
 * Scoreboard page show top 5 players for each games.
 * 
 * @author Dacharat Pankong
 *
 */
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
	@FXML
	HBox player1stBox;
	@FXML
	HBox player2ndBox;
	@FXML
	HBox player3rdBox;
	@FXML
	HBox player4thBox;
	@FXML
	HBox player5thBox;
	@FXML
	Button backButton;
	@FXML
	Button calculadolaScoreButton;
	@FXML
	Button questionIsScoreButton;
	@FXML
	Button makeIt24ScoreButton;
	@FXML
	ImageView gameImage;

	Scoreboard board = new Scoreboard();
	Account account = Account.getInstance();
	Label[] temp = new Label[5];
	Label[] temp2 = new Label[5];
	String[] name = new String[5];
	Integer[] score = new Integer[5];
	HBox[] boxes = new HBox[5];

	/**
	 * Initialize UI.
	 */
	public void initialize() {

		calculadolaScoreButton.setOnAction(this::onCalculadolaButtonClicked);
		questionIsScoreButton.setOnAction(this::onQuestionIsButtonClicked);
		makeIt24ScoreButton.setOnAction(this::onMakeIt24ButtonClicked);
		backButton.setOnAction(this::onBackButtonClicked);

		temp[0] = player1NameLabel;
		temp[1] = player2NameLabel;
		temp[2] = player3NameLabel;
		temp[3] = player4NameLabel;
		temp[4] = player5NameLabel;

		temp2[0] = player1ScoreLabel;
		temp2[1] = player2ScoreLabel;
		temp2[2] = player3ScoreLabel;
		temp2[3] = player4ScoreLabel;
		temp2[4] = player5ScoreLabel;

		boxes[0] = player1stBox;
		boxes[1] = player2ndBox;
		boxes[2] = player3rdBox;
		boxes[3] = player4thBox;
		boxes[4] = player5thBox;

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				board.calculadolaBoard();
				setHighScoreOutput();				
			}
		});
	}

	/**
	 * Show scoreborad of calculadola game when clicked this button.
	 * 
	 * @param event
	 */
	public void onCalculadolaButtonClicked(ActionEvent event) {
		gameImage.setImage(new Image("res/calculadola.gif"));
		board.calculadolaBoard();
		setHighScoreOutput();
	}

	/**
	 * Show scoreborad of questionIs game when clicked this button.
	 * 
	 * @param event
	 */
	public void onQuestionIsButtonClicked(ActionEvent event) {
		gameImage.setImage(new Image("res/questionIs.gif"));
		board.questionIsBoard();
		setHighScoreOutput();
	}

	/**
	 * Show scoreborad of makeIt24 game when clicked this button.
	 * 
	 * @param event
	 */
	public void onMakeIt24ButtonClicked(ActionEvent event) {
		gameImage.setImage(new Image("res/24Game.gif"));
		board.makeit24Board();
		setHighScoreOutput();
	}

	/**
	 * Set scoreboard.
	 */
	public void setHighScoreOutput() {
		HashMap<String, Integer> top5 = account.getTopplayer();

		for(int i = 0; i < 5; i++) {
			boxes[i].setStyle("-fx-border-color: transparent");
		}
		
		for (int i = 0; i < 5; i++) {
			name[i] = (String) top5.keySet().toArray()[i];
			score[i] = top5.get((String) top5.keySet().toArray()[i]);
		}

		for (int i = 0; i < 5; i++) {
			temp[i].setText(name[i]);
			temp2[i].setText(score[i] + "");
			if (temp[i].getText().equalsIgnoreCase(getName())) {
				System.out.println("Why");
				boxes[i].setStyle("-fx-border-color: red; -fx-border-width: 5; -fx-border-radius: 20");
			}
		}
	}

	/**
	 * Go to choose mini game page.
	 * 
	 * @param event
	 */
	public void onBackButtonClicked(ActionEvent event) {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) backButton.getScene().getWindow(), getName());
	}
}
