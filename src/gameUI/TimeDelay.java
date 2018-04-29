package gameUI;

import javafx.concurrent.Task;

public class TimeDelay extends Task<Void>{

	@Override
	protected Void call() throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return null;
	}

}
