package calculadolaGame;

/**
 * Math Dividing style.
 * 
 * @author Dacharat Pankong
 *
 */
public class MathStyleDivide extends MathStyle{
	
	/**
	 * Initialize by add two number.
	 * 
	 * @param first
	 * @param second
	 */
	public MathStyleDivide(double first, double second) {
		super(first, second);
		super.mathStyle = "/";
	}

	/**
	 * Return Dividing style symbol that use for calculate these two number.
	 */
	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	/**
	 * Return number with dividing style.
	 */
	@Override
	public double getAnswer() {
		return super.firstNumber / super.secondNumber;
	}

}
