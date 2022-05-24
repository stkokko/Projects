import acm.program.*; 
public class AMPMClockApp extends Program {
	public void run() {
		int h = readInt("Give the hour: ");
		int m = readInt("Give the minutes: ");
		int s = readInt("Give the seconds: ");
		AMPMClock clock = new AMPMClock(h,m,s);
		clock.setHour(h);
		clock.setMin(m);
		clock.setSec(s);
		boolean bool = readBoolean("Give True if you want to print the time with am-pm or False to print the time without am-pm: ");
		for ( int i = 0; i <= 3; i++ ){
			println(clock.setAMPM(bool));
			clock.tick();
			clock.tickMin();
			clock.tickHour();
		}
	}
}