public class Clock{

	public int h;
	public int m;
	public int s;

	public Clock(int h,int m,int s){
		this.h = h;
		this.m = m;
		this.s = s;
	}

	public int setHour(int h){
		return h;
	}

	public int setMin(int m){
		return m;
	}

	public int setSec(int s){
		return s;
	}
	
	public void tick(){
		s++;
		if (s == 60){
			s = 0;
			m++;
		}
	}
	
	public void tickMin(){
		if (m == 60){
			m = 0;
			h++;
		}
	}
	
	public void tickHour(){
		if (h == 24){
			h = 0;
		}
	}
			
	public String toString(){
		return "The time is" + " " + this.h + ":" + this.m + ":" + this.s;
	}
}