import acm.graphics.*;
import acm.program.*;
import java.awt.*;
public class Checkerboard extends GraphicsProgram {
	public void run() {
		double m = Power();
		double sqSize = (double) getHeight() / m;
		for (int i=0; i<N_ROWS; i++) {
			for (int j=0; j<N_COLUMNS; j++) {
				double x = j * sqSize + (double)getWidth();
				double y = i * sqSize;/* + (double)getWidth() / 3*/
				GRect sq = new GRect(x,y,sqSize,sqSize);
				if((i+j)%2!=0) {
				sq.setFilled(true);
				sq.setFillColor(Color.gray);
				}
				add(sq);
			}
			for (int j=0; j<N_COLUMNS; j++) {
				double x = j * sqSize + (double)getWidth();
				double y = i * sqSize;/* + (double)getWidth() / 3*/
				if((i+j)%2!=0) {
				 if(i<3) {
					  GOval ov = new GOval(x,y,sqSize,sqSize);
					  ov.setFilled(true);
					  ov.setFillColor(Color.red);
					  add(ov);
				  }if(i>=5) {
					  GOval ov = new GOval(x,y,sqSize,sqSize);
					  ov.setFilled(true);
					  ov.setFillColor(Color.black);
					  add(ov);
				  }
				}
			}
		}
	}
	private static final int N_ROWS = 8;
	private static final int N_COLUMNS = 8;
	private double Power() {
		int p = readInt("Give a number greater than or equal to 3: ");
		while(p<3) {
			p = readInt("Illegal number!Try again: ");
		}
		double m = 2;
		while(p>0) {
			p--;
			m*=2;
		}
		return m;
	}
}