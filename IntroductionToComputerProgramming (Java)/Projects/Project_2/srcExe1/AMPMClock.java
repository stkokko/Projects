public class AMPMClock extends Clock {
	private int n = 0;
	
	public AMPMClock(int h,int m,int s){
		super(h,m,s);
	}
	
	public void setAMPM(boolean bool){
		h = h + 12 * n;
		if(bool && h > 12){
			h -= 12;
			n = 1;
		}else{
			h += 12;
		}
	}
	
	public String toString(){
		if( h > 12 ){
			return super.toString();
		}else{
			return super.toString() + " pm";
		}
	}
}