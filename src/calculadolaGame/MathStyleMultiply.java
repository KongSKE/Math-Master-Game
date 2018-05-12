package calculadolaGame;

/**
 * Math Multiply style.
 * 
 * @author Dacharat Pankong
 *
 */
public class MathStyleMultiply extends MathStyle{
	
	/**
	 * Initialize by add two number.
	 * 
	 * @param first
	 * @param second
	 */
	public MathStyleMultiply(double first, double second) {
		super(first, second);
		super.mathStyle = "x";
	}

	/**
	 * Return multiply style symbol that use for calculate these two number.
	 */
	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	/**
	 * Return number with multiply style.
	 */
	@Override
	public double getAnswer() {
		return super.firstNumber * super.secondNumber;
	}

}
