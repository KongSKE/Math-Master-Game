package gameUI;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class TimeCounter extends Task<Integer> {

	private int time;
	private int totalcount;
	private ProgressBar bar;

	public TimeCounter(int totalcount, ProgressBar bar) {
		this.time = totalcount * 1000;
		this.totalcount = totalcount * 1000;
		this.bar = bar;
	}

	public int getTime() {
		return time;
	}

	@Override
	protected Integer call() throws Exception {
		int increment = Math.max(totalcount / 100, 2);
		double percent;
		while (time > 0) {
			time -= 1;
			percent = (time * 100) / totalcount;
			if (time % increment == 0) {
				updateProgress(time, totalcount);
				if(percent >= 80)
					bar.setStyle("-fx-accent: #11e011");
				else if(percent >= 60)
					bar.setStyle("-fx-accent: #c2f248");
				else if (percent >= 40)
					bar.setStyle("-fx-accent: #f4ee30");
				else if(percent >= 20)
					bar.setStyle("-fx-accent: #fcae28");
				else
					bar.setStyle("-fx-accent: #ff0000");
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				break;
			}
		}
		return time;
	}

}
