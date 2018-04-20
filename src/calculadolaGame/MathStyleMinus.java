package calculadolaGame;

public class MathStyleMinus extends MathStyle{
	
	public MathStyleMinus(double first, double second) {
		super(first, second);
		super.mathStyle = "-";
	}

	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	@Override
	public double getAnswer() {
		return super.firstNumber - super.secondNumber;
	}

}
