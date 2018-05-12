package player;

public class Player implements Comparable<Player> {

	private String name;
	private int score;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(Player o) {
		return Integer.compare(this.getScore(), o.getScore());
	}
}
