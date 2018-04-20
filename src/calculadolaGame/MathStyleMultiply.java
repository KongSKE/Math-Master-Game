package calculadolaGame;

public class MathStyleMultiply extends MathStyle{
	
	public MathStyleMultiply(double first, double second) {
		super(first, second);
		super.mathStyle = "x";
	}

	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	@Override
	public double getAnswer() {
		return super.firstNumber * super.secondNumber;
	}

}
