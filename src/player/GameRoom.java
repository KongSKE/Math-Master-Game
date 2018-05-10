package player;

public class GameRoom {

	private Player p1;
	private Player p2;
	private Game game;

	public GameRoom(Player p1, Player p2, Game game) {
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

}
