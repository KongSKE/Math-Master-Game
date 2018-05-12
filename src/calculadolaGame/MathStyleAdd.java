package calculadolaGame;

/**
 * Math Addition style.
 * 
 * @author Dacharat Pankong
 *
 */
public class MathStyleAdd extends MathStyle{

	/**
	 * Initialize by add two number.
	 * 
	 * @param first
	 * @param second
	 */
	public MathStyleAdd(double first, double second) {
		super(first, second);
		super.mathStyle = "+";
	}

	/**
	 * Return Addition style symbol that use for calculate these two number.
	 */
	@Override
	public String getStyle() {
		return super.mathStyle;
	}

	/**
	 * Return number with addition style.
	 */
	@Override
	public double getAnswer() {
		return super.firstNumber + super.secondNumber;
	}
	
	
}
