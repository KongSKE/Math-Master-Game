package calculadolaGame;

/**
 * Math Minus style.
 * 
 * @author Dacharat Pankong
 *
 */
public class MathStyleMinus extends MathStyle{
	
	/**
	 * Initialize by add two number.
	 * 
	 * @param first
	 * @param second
	 */
	public MathStyleMinus(double first, double second) {
		super(first, second);
		super.mathStyle = "-";
	}

	/**
	 * Return Minus style symbol that use for calculate these two number.
	 */
	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	/**
	 * Return number with minus style.
	 */
	@Override
	public double getAnswer() {
		return super.firstNumber - super.secondNumber;
	}

}
