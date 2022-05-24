import acm.program.*; 
public class AMPMClockApp extends Program {
	public void run() {
		AMPMClock clock = new AMPMClock(16,28,58);
		clock.setHour(16);
		clock.setMin(28);
		clock.setSec(58);
		boolean bool = readBoolean("Enter true if you want to print the time with am-pm or false if not: ");
		for (int i = 0; i <= 180; i++){
			if( bool ){
				clock.setAMPM(bool);
			}
			println(clock.toString());
			clock.tick();
		}
	}
}