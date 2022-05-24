import acm.program.*;
public class ClockApp extends Program{
	public void run() {
		int h = readInt("Give the hour: ");
		int m = readInt("Give the minutes: ");
		int s = readInt("Give the seconds: ");
		Clock clock = new Clock(h,m,s);
		clock.setHour(h);
		clock.setMin(m);
		clock.setSec(s);
		for (int i = 0; i <= 3; i++) {
			println(clock.toString());
			clock.tick();
			clock.tickMin();
			clock.tickHour();
		}
	}
}
		
		