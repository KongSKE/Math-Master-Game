package onlineMode;

public class Packet {

	public static class ScoreData extends Packet {
		public int score;
		public String name;
	}
	
	public static class QuestionData extends Packet{
		public String queston;
	}

}
