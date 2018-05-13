package gameUI;

import javafx.concurrent.Task;

/**
 * Delay for change question.
 * 
 * @author Dacharat Pankong
 *
 */
public class TimeDelay extends Task<Void>{

	/**
	 * When task start.
	 */
	@Override
	protected Void call() throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return null;
	}

}
