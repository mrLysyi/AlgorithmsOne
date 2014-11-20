package week5;

import java.util.Stack;

import com.algorithms.Point2D;
import com.algorithms.StdDraw;

public class KdTree {
	private Node first;
	private int count = 0;
	private Stack<Point2D> range = new Stack<Point2D>();

	private static class Node {
		private Point2D p; // the point
		private RectHV rect; // the axis-aligned rectangle corresponding to this
								// node
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

		Node(Point2D point) {
			this.p = point;
			this.rect = new RectHV(0.0, point.x(), 0.0, point.y());
			// this.lb = leftBottom;
			// this.rt = rightTop;
		}
	}

	public KdTree() {
		// first = new Node(null);
	} // construct an empty set of points

	public boolean isEmpty() {
		return first.p == null;
	} // is the set empty?

	public int size() {
		return count;
	} // number of points in the set

	public void insert(Point2D that) {
		Node current = first;
		Node previous = null;
		if (count == 0) {
			this.first.p = that;
		} else {
			while (true) {
				previous = current;
				if (that.compareTo(current.p) < 0) { // looking for last point
					current = current.lb;
				} else
					current = current.rt;
				if (current == null) { // insert new point && rect
					if (that.compareTo(previous.p) < 0) {
						previous.lb = new Node(that);
						return;
					} else {
						previous.rt = new Node(that);
						return;
					}
				}
			}

			// operation with current_previous

		}
		count++;
	} // add the point to the set (if it is not already in the set)

	public boolean contains(Point2D that) {
		Node current = first;
		Node previous = null;
		while (current != null) {
			previous = current;
			if (that.compareTo(current.p) < 0) { // looking for last point
				current = current.lb;
			} else if (that.compareTo(current.p) >= 0) {
				current = current.rt;
				if (current.p.compareTo(that) == 0)
					return true;
			}
		}
		return false;
	} // does the set contain point p?

	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
	} // draw all points to standard draw

	public Iterable<Point2D> range(RectHV rect) {
		Node test = first;
		while (test != null){
			test = rectRange(rectRange(test, rect), rect);
		}
		return this.range;
	} // all points that are inside the rectangle

	private static Node rectRange(Node node, RectHV rect) {
		if (!rect.intersects(node.rect))
			return null;
			if(node.lb.rect.intersects(rect) && node.rt.rect.intersects(rect)){	// left
				return rectRange(node.lb,rect);		// if !=null  ???
			}
			else {					// right
				return node.rt;		// if !=null  ???
			}				
//		return null;		
	}

	public Point2D nearest(Point2D p) {
		if (isEmpty())
			return null;
		return null;
	} // a nearest neighbor in the set to point p; null if the set is empty

	public static void main(String[] args) {
	} // unit testing of the methods (optional)
}
