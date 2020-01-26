package StraightGaitS;

import java.util.Timer;

public class Main {
	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		timer.schedule(new uptadeStatus(), 0, 3000); //send http request every 3 seconds
	}
}

