package calculadolaGame;

public abstract class MathStyle {

	public double firstNumber;
	public double secondNumber;
	public String mathStyle;

	protected MathStyle(double first, double second) {
		this.firstNumber = first;
		this.secondNumber = second;
	}

	public abstract String getStyle();

	public abstract double getAnswer();

	@Override
	public String toString() {
		return String.format("%.0f %s %.0f", firstNumber, mathStyle, secondNumber);
	}

}
