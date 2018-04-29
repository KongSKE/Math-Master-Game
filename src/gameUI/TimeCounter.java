package gameUI;

import javafx.concurrent.Task;

public class TimeCounter extends Task<Integer> {

	private int time;
	private int totalcount;

	public TimeCounter(int totalcount) {
		this.time = totalcount * 1000;
		this.totalcount = totalcount * 1000;
	}
	
	public int getTime() {
		return time;
	}

	@Override
	protected Integer call() throws Exception {
		updateMessage("Starting");
		int increment = Math.max(totalcount / 100, 2);
		while (time > 0) {
			time -= 1;
			updateValue(time);
			updateMessage(Integer.toString(time));
			if (time % increment == 0)
				updateProgress(time, totalcount);
			try {
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				break;
			}
		}
		updateMessage("Done!");
		return time;
	}

}
