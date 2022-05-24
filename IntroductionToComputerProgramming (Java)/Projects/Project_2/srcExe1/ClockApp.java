import acm.program.*;
public class ClockApp extends Program{
	public void run(){
		Clock clock = new Clock(16,28,58);
		clock.setHour(16);
		clock.setMin(28);
		clock.setSec(58);
		for (int i = 0; i <= 180; i++) {
			println(clock.toString());
			clock.tick();
		}
	}
}
		
		