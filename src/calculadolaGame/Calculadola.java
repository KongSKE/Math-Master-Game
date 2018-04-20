package calculadolaGame;

import java.util.Random;

public class Calculadola {
	
	private Random random = new Random();
	private MathStyle math;
	private int first;
	private int second;
	
	public String getQuestion() {
		this.first = random.nextInt(100) + 1;
		this.second = random.nextInt(100) + 1;
		int style = random.nextInt(4) + 1;
		switch (style) {
		case 1:
			math = new MathStyleAdd(first, second);
			break;
		case 2:
			math = new MathStyleMinus(first, second);
			break;
		case 3:
			math = new MathStyleMultiply(first, (second % 10) + 2);
			break;
		case 4:
			math = new MathStyleDivide(first, (second % 9) + 2);
		default:
			break;
		}
		return math.toString();
	}
	
	public boolean checkAnswer(double answer) {
		if(answer == math.getAnswer())
			return true;
		else {
			return String.valueOf(answer).equals(String.format("%.2f", getAnswer()));
		}
	}

	public double getAnswer() {
		return math.getAnswer();
	}

}
