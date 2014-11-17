package week5;

import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.algorithms.Point2D;
import com.algorithms.StdDraw;

public class PointSET {
	Set<Point2D> pSet;

	public PointSET() {
		pSet = new TreeSet<Point2D>();
	} // construct an empty set of points

	public boolean isEmpty() {
		return pSet.isEmpty();
	}// is the set empty?

	public int size() {
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
		while (iterator.hasNext()){
			Point2D temp = (Point2D)iterator.next();
			temp.draw();		
		}
	} // draw all points to standard draw
	//use StdDraw.setPenColor(StdDraw.RED) or StdDraw.setPenColor(StdDraw.BLUE) and StdDraw.setPenRadius() before drawing the splitting lines. 

	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> rezult = new Stack<Point2D>();
		Iterator<Point2D> iterator = pSet.iterator();
		while (iterator.hasNext()){
			Point2D temp = (Point2D)iterator.next();
			if(rect.contains(temp)){
				rezult.add(temp);
			}
		}
		return rezult;
	} // all points that are inside the rectangle

	public Point2D nearest(Point2D p) {
		if (pSet.isEmpty())
			return null;
//		pSet.
		return null;
		
	} // a nearest neighbor in the set to point p; null if the set is empty

	public static void main(String[] args) {

	} // unit testing of the methods (optional)

}
