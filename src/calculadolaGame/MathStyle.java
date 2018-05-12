package calculadolaGame;

/**
 * Math calculation style that other style will calculate other way.
 * 
 * @author Dacharat Pankong
 *
 */
public abstract class MathStyle {

	public double firstNumber;
	public double secondNumber;
	public String mathStyle;

	/**
	 * Initialize by add two number.
	 * 
	 * @param first
	 * @param second
	 */
	protected MathStyle(double first, double second) {
		this.firstNumber = first;
		this.secondNumber = second;
	}

	/**
	 * Return style symbol that use for calculate these two number.
	 * 
	 * @return symbol
	 */
	public abstract String getStyle();

	/**
	 * Return answer from calculate these two number.
	 * 
	 * @return answer.
	 */
	public abstract double getAnswer();

	/**
	 * Return number with calculation style.
	 */
	@Override
	public String toString() {
		return String.format("%.0f %s %.0f", firstNumber, mathStyle, secondNumber);
	}

}
