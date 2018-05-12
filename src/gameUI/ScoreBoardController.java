package gameUI;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
	
	@FXML
	Button backButton;
	@FXML
	Button calculadolaScoreButton;
	@FXML
	Button questionIsScoreButton;
	@FXML
	Button makeIt24ScoreButton;
	

	Scoreboard board = new Scoreboard();
	Account account = Account.getInstance();
	Label[] temp = new Label[5];
	Label[] temp2 = new Label[5];
	String[] name = new String[5];
	Integer[] score = new Integer[5];
	
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
	}
	
	public void onCalculadolaButtonClicked(ActionEvent event) {
		
		board.calculadolaBoard();
		setHighScoreOutput();
		
	}
	
	public void onQuestionIsButtonClicked(ActionEvent event) {
		board.questionIsBoard();
		setHighScoreOutput();
	}
	
	public void onMakeIt24ButtonClicked(ActionEvent event) {
		board.makeit24Board();
		setHighScoreOutput();
	}
	
	public void setHighScoreOutput() {
		int box = 0;
		HashMap<String, Integer> top5 = account.getTopplayer();
		System.out.println("size " + top5.size());
		
		for (String s : top5.keySet()) {
			name[box] = s;
			score[box] = top5.get(s);
//			player.add(s);
//			highscore.add(top5.get(s));
			box++;
		}
		
		for (int i = 0 ; i < 5; i++) {
			temp[i].setText(name[i]);
			temp2[i].setText(score[i]+"");
		}
	}

	public void onBackButtonClicked(ActionEvent event) {
		GameUISceneChange.CHOOSEMINIGAME.changeScene((Stage) backButton.getScene().getWindow(), getName());
	}
}
