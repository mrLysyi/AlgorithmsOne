package week5;

import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

import com.algorithms.Point2D;
import com.algorithms.SET;
import com.algorithms.StdDraw;

public class PointSET {
	private SET<Point2D> pSet;
	public PointSET() {
		pSet = new SET<Point2D>();
	} // construct an empty set of points

	public boolean isEmpty() {
		return pSet.isEmpty();
	}// is the set empty?

	public int size() {
//		pSet.ceiling(key)
		return pSet.size();
	} // number of points in the set

	public void insert(Point2D p) {
		pSet.add(p);
	} // add the point to the set (if it is not already in the set)

	public boolean contains(Point2D p) {
		return pSet.contains(p);
	} // does the set contain point p?

	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
		Iterator<Point2D> iterator = pSet.iterator();
		while (iterator.hasNext()) {
			Point2D temp = (Point2D) iterator.next();
			temp.draw();
		}
	} // draw all points to standard draw
		// use StdDraw.setPenColor(StdDraw.RED) or
		// StdDraw.setPenColor(StdDraw.BLUE) and StdDraw.setPenRadius() before
		// drawing the splitting lines.

	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> rezult = new Stack<Point2D>();
		Iterator<Point2D> iterator = pSet.iterator();
		while (iterator.hasNext()) {
			Point2D temp = (Point2D) iterator.next();
			if (rect.contains(temp)) {
				rezult.add(temp);
			}
		}
		return rezult;
	} // all points that are inside the rectangle

	public Point2D nearest(Point2D that) {
		if (pSet.isEmpty() )
			return null;
		Point2D rezult, larger, smaller;
		try {
			smaller = pSet.ceiling(that);	
		}catch (Exception e) {
//			smaller = that;
			return pSet.floor(that);
		}		
		try {
			larger = pSet.floor(that);			
		} catch (Exception e) {
//			larger = that;
			return pSet.ceiling(that);
		  }		
		if(that.distanceTo(larger) < that.distanceTo(smaller))
			rezult = larger;
		else rezult = smaller;
		return rezult;

	} // a nearest neighbor in the set to point p; null if the set is empty

	public static void main(String[] args) {

	} // unit testing of the methods (optional)

}
