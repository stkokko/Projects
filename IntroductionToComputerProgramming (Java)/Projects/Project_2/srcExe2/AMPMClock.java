public class AMPMClock extends Clock {
	private int t = 1;
	private String str;
	
	public AMPMClock(int h,int m,int s){
		super(h,m,s);
	}
		
	public String setAMPM(boolean bool){
		if (bool){
			return toString();
		}else{
			return super.toString();
		}
	}			
	
	public String toString(){
		h = h + (t - 1) * 12;
		if (h >= 13){
			if (t < 2){
				t++;
			}
			for(int i = 0; i == 0; i++) {
				h -=12;
			}
			str = super.toString() + "pm";
			if (h == 12){
				h = 0;
				t = 1;
				str = super.toString() + "am";
			}
		}else if (h == 12){
			str = super.toString() + "pm";
		}else if (h == 11 && m == 59 && s == 59){
			str = super.toString() + "am";
		}else{
			str = super.toString() + "am";
		}
		return str;
	}
}