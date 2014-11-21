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
//			this.rect = new RectHV(0.0, point.x(), 0.0, point.y());
			// this.lb = leftBottom;
			// this.rt = rightTop;
		}
	}

	public KdTree() {
		 first = new Node(null);
	} // construct an empty set of points

	public boolean isEmpty() {
		return count == 0;
	} // is the set empty?

	public int size() {
		return count;
	} // number of points in the set

	public void insert(Point2D that) {
		Node current = first;
//		Node previous = null;
		if (count == 0) {
			this.first.p = that;
			count++;
			return;		
		} else {
			while (true) {
//				previous = current;
				if (that.compareTo(current.p) > 0) { // looking for last point
					
					if (current.lb == null){
						current.lb = new Node(that);
						System.out.println("insert left " + count);
						count++;
						return;
					} else
					current = current.lb;
				} else if (that.compareTo(current.p) < 0){
					
					if (current.rt == null){
						current.rt = new Node(that);
						System.out.println("insert right " + count);
						count++;
						return;
					}
					current = current.rt;
				}
//				if (current == null) { // insert new point && rect
//					if (that.compareTo(previous.p) > 0) {
//						previous.lb = new Node(that);
//						count++;
//						return;
//					} else {
//						previous.rt = new Node(that);
//						count++;
//						return;
//					}
//				}
			}
			// operation with current_previous
		}		
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
//		while (test != null){
//			test = rectRange(rectRange(test, rect), rect);
//		}
		return this.range;
	} // all points that are inside the rectangle

//	private static Node rectRange(Node node, RectHV rect) {
//		if (!rect.intersects(node.rect))
//			return null;
//			if(node.lb.rect.intersects(rect) && node.rt.rect.intersects(rect)){	// left
//				return rectRange(node.lb,rect);		// if !=null  ???
//			}
//			else {					// right
//				return node.rt;		// if !=null  ???
//			}				
////		return null;		
//	}

	public Point2D nearest(Point2D p) {
		if (isEmpty())
			return null;
		return null;
	} // a nearest neighbor in the set to point p; null if the set is empty

	public static void main(String[] args) {
		KdTree test = new KdTree();
		Point2D point;
		System.out.println(test.isEmpty());
		test.insert(new Point2D(0.7,0.2));
		test.insert(new Point2D(0.5,0.4));	//left
		test.insert(new Point2D(0.2,0.3));	//left
		test.insert(new Point2D(0.4,0.7));	//left
		test.insert(new Point2D(0.9,0.6));	//right
		System.out.println(test.first.p + " -first");
		System.out.println(test.first.lb.p);
//		System.out.println(test.first.rt.p);
//		System.out.println((test.first.lb).lb.p);
//		System.out.println(test.first.lb.rt.p);
	} // unit testing of the methods (optional)
}
