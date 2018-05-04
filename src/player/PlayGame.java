package player;

import java.util.Scanner;

import calculadolaGame.Calculadola;

public class PlayGame {

	private Player p1;
	private Player p2;
	private Game game;

	public PlayGame(Player p1, Player p2, Game game) {
		this.p1 = p1;
		this.p2 = p2;
		this.game = game;
	}

	public void getGameQuestion() {
		game.getQuestion();
	}
	
	public boolean checkGameAnswer(String ans) {
		return game.checkAnswer(ans);
	}

	public int getPlayer1Score() {
		return p1.getScore();
	}

	public int getPlayer2Score() {
		return p2.getScore();
	}

	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calculadola c = new Calculadola();
		PlayGame game = new PlayGame(new Player("p1"), new Player("p2"), c);
		for (int i = 0; i < 3; i++) {
			game.getGameQuestion();
			System.out.println(c.getGameQuestion());
			System.out.print("Ans: ");
			double ans = sc.nextDouble();
			if(game.checkGameAnswer(ans+"")) {
				System.out.println("Collect");
			}else {
				System.out.println("Wrong");
			}
		}
	}
}
