package weekThree;

import com.algorithms.StdDraw;

public class Fast {
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		Point p = new Point(1, 1);
		Point p2 = new Point(2, 2);
		p.draw();
		p.drawTo(p2);
	}
}
