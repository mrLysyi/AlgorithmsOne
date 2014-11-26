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
			Node lb = null;
			Node rt = null;

			// this.rect = new RectHV(0.0, point.x(), 0.0, point.y());
			// this.lb = leftBottom;
			// this.rt = rightTop;
		}

		private int compareTo(Node that) {
			if (this.p.x() < that.p.x())
				return -1;
			if (this.p.x() > that.p.x())
				return +1;
			if (this.p.y() < that.p.y())
				return -1;
			if (this.p.y() > that.p.y())
				return +1;
			return 0;
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

	public void insert(Point2D thatP) {
		Node current = first;
		Node previous = null;
		Node next = null;
		Node thisNode = new Node(thatP);
		boolean left = false;
		int cmp;
		if (count == 0) {
			this.first.p = thatP;
			count++;
			System.out.println("insert frirst " + count);
			return;
		} else {
			while (true) { // looking for last point
				cmp = thisNode.compareTo(current);
				if (cmp < 0) { // left
					next = current.lb;
					if (next != null && thisNode.compareTo(next) > 0) { // collizia
						left = false;
						next = current.rt;
					} else
						left = true;
				} else if (cmp > 0) {// right

					next = current.rt;
					if (next != null && thisNode.compareTo(next) < 0) {
						left = true;
						next = current.lb;
					} else
						left = false;
				}
				if (next == null) {
					if (left)
						current.lb = new Node(thatP);
					else
						current.rt = new Node(thatP);
					count++;
					return;
				}
				current = next;
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
		// while (test != null){
		// test = rectRange(rectRange(test, rect), rect);
		// }
		return this.range;
	} // all points that are inside the rectangle

	// private static Node rectRange(Node node, RectHV rect) {
	// if (!rect.intersects(node.rect))
	// return null;
	// if(node.lb.rect.intersects(rect) && node.rt.rect.intersects(rect)){ //
	// left
	// return rectRange(node.lb,rect); // if !=null ???
	// }
	// else { // right
	// return node.rt; // if !=null ???
	// }
	// // return null;
	// }

	public Point2D nearest(Point2D p) {
		if (isEmpty())
			return null;
		return null;
	} // a nearest neighbor in the set to point p; null if the set is empty

	public static void main(String[] args) {
		KdTree test = new KdTree();
		Point2D point;
		System.out.println(test.isEmpty());
		// Point2D zeroNine = new Point2D(0.9,0.6);

		test.insert(new Point2D(0.7, 0.2));
		// System.out.println(zeroNine.compareTo(test.first.p));
		test.insert(new Point2D(0.9, 0.6)); // right
		test.insert(new Point2D(0.5, 0.4)); // left
		test.insert(new Point2D(0.2, 0.3)); // left
		test.insert(new Point2D(0.4, 0.7)); // left

		System.out.println(test.first.p + " -first");
		System.out.println(test.first.lb.p);
		System.out.println(test.first.rt.p); // null

		System.out.println((test.first.lb).lb.p);
		// System.out.println((test.first.lb).lb.rt.p); //wrong
		System.out.println((test.first.lb).rt.p);

	} // unit testing of the methods (optional)
}
