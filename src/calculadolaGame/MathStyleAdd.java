package calculadolaGame;

public class MathStyleAdd extends MathStyle{

	public MathStyleAdd(double first, double second) {
		super(first, second);
		super.mathStyle = "+";
	}

	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	@Override
	public double getAnswer() {
		return super.firstNumber + super.secondNumber;
	}
	
	
}
