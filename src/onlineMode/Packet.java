package onlineMode;

public class Packet {

	public static class Connect extends Packet {
		public String name;
	}

	public static class ScoreData extends Packet {
		public int score;
		public String name;
	}

}
