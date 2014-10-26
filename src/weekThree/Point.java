package weekThree;

/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

import com.algorithms.StdDraw;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator <Point> SLOPE_ORDER = new Comparator<Point>() {

		@Override
		public int compare(Point o1, Point o2) {
			double s1 = slopeTo(o1);
			double s2 = slopeTo(o2);
			return Double.compare(s1, s2);
		}
	}; // YOUR DEFINITION HERE

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point( int xx, int yy) {
		/* DO NOT MODIFY */
		this.x = xx;
		this.y = yy;
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		if (this.y == that.y && this.x == that.x)
		 {
			return	Double.NEGATIVE_INFINITY;	 //(between a point and itself) as negative infinity.			
		}
		if (this.y == that.y)
		 {
			return 0.0; //Treat the slope of a horizontal line segment as positive zero;
		}
		if (this.x == that.x)
		 {
			return Double.POSITIVE_INFINITY;//treat the slope of a vertical line segment as positive infinity
		}
		Double rezult = ((double)that.y - (double)this.y) / ((double)that.x - (double)this.x);
		return rezult;
		/* YOUR CODE HERE */
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {	// @return -1 if less; 0 -if equals; 1 if bigger
		/* YOUR CODE HERE */			// y0 < y1 or if y0 = y1 and x0 < x1.
		int rezult;
		if (this.y <that.y || (this.y == that.y) && this.x <that.x)
			rezult = -1;
		else if (this.y == that.y && this.x == that.x)
			rezult = 0;
		else 
			rezult = 1;
		return rezult;
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {
		/* YOUR CODE HERE */
	}
}
