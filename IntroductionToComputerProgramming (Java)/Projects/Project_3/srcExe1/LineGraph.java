import acm.program.*;
import acm.graphics.*;
import java.awt.*;
public class LineGraph extends GraphicsProgram {
	public void run() {
		int n = readInt("Enter the nubmer of points: ");
		double[] Points = new double[n];
		for(int i = 0; i < n; i++) {
			int k = i + 1;
			double value = readDouble("Enter the value of " + k + " point: ");
			Points[i] = value;
		}
		drawLineGraph(Points,n);
	}
	private void drawLineGraph(double[] Points,int n) {
		int dx = (getWidth() - 2) / n - 1;
		int x1 = 10;
		int x2 = x1 + dx;
		GOval oval;
		GLine line;
		for(int i = 0; i < n ; i++) {
			oval = new GOval(x1 - 3,Points[i] - 2,size,size);
			oval.setFilled(true);
			add(oval);
			if(i < (n - 1)) {
				line = new GLine(x1,Points[i],x2,Points[i + 1]);
				line.setColor(Color.DARK_GRAY);
				add(line);
			}
			x1 = x2;
			x2 += dx;
		}
    }
	private static final int size = 5;
}
		
			
		