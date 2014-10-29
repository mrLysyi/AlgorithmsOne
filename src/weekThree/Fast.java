package weekThree;

//https://class.coursera.org/algs4partI-006/forum/thread?thread_id=858
//https://class.coursera.org/algs4partI-006/forum/thread?thread_id=760
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.algorithms.In;
import com.algorithms.StdDraw;
import com.algorithms.StdOut;

public class Fast {

	private static boolean onLine(Point p1, Point p2, Point p3) {
		return p1.SLOPE_ORDER.compare(p2, p3) == 0;
	}

	private static boolean onLine(HashSet<Point> set, Point p1) {
		// Point p2;
		// Point p3;
		TreeSet<Point> pSet = new TreeSet<Point>(set);
		Point[] druck = (pSet.toArray(new Point[pSet.size()]));
		return p1.SLOPE_ORDER.compare(pSet.first(), pSet.last()) == 0;
		// return false;
	}

	private static String filename = "/home/hellow/workspaceJava/Algorithms_testFiles/collinear/input56.txt";

	public static void main(String[] args) {
		// read from the input
		// String filename = args[0];
		In in = new In(filename);

		StdDraw.setXscale(0, 32768); // rescale coordinates and turn on
		// animation mode
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		int N = in.readInt();

		Point[] parr = new Point[N];
		boolean[] pointOnLine = new boolean[N];
		Double[] slopeArr = new Double[N];

		for (int i = 0; i < N; i++) { // Create array of Points
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			parr[i] = p;
			p.draw();
			pointOnLine[i] = false;
		}

		int count = 0;
		int drawCount = 0;
		Point p1 = new Point(0, 0);
		Point pB, p2 = new Point(23452345, 234523452);
		Point pC, p3 = new Point(34560, 60);
		Point pD, p4 = new Point(457, 2456);
		Point q1, q2 = new Point(0, 0);

		HashMap<Double, HashSet<Point>> hashmap = new HashMap<Double, HashSet<Point>>();

		// HashSet<Point> temPoint = new HashSet<Point>();

		Arrays.sort(parr, 0, N);
		double slope = 0.011111111111111111111;
		for (int a = 0; a < N; a++) { 							// main upper loop
			
			Arrays.sort(parr, 0, N); // Sort
			p1 = parr[a];
//			Arrays.sort(parr, p1.SLOPE_ORDER);
			// pSet.clear();
			for (int b = 0; b < N-1; b += 2) { 					// FiLL slope Array
				if (b == N - 1) {
					q1 = parr[b];
					q2 = p1;
				} else {
					q1 = parr[b];
					q2 = parr[b + 1];
				}
				// q1 = parr[b];
				// q2 = parr[b + 1];
				slope = p2.slopeTo(q2);
//				
				if (p1.SLOPE_ORDER.compare(q1, q2) == 0  ) { // Search     && q1.SLOPE_ORDER.compare(p1, q2) == 0
					pointOnLine[a] = true;
					
//					double slope = p1.slopeTo(q2)+a; // slope.points
					// slopeArr[b] = slope;
					// System.out.println("№:" + b + " slopeArr[b]: "
					// + slopeArr[b]+ ", p1: " + p1);
					// pSet.add(parr[b]);

					// System.out.println(slope);
					if (hashmap.containsKey(slope)) { // update
						System.out.println("slope: " + slope +" a= "+ a +" b= "+b);
						HashSet<Point> temPoint = new HashSet<Point>();
						// System.out.print("update slope...");
						temPoint = hashmap.get(slope); // get
						// System.out.println("get kee"+hashmap.get(slope));
						if (onLine(temPoint, q1))
							temPoint.add(q1); // update
						if (onLine(temPoint, q2))
							temPoint.add(q2);
						if (onLine(temPoint, p1) )
							temPoint.add(p1); // update
						hashmap.put(slope, temPoint); // putIn
						// System.out.println("new walue is: " +
						// hashmap.toString());
						//
					} else { // put first time  								CREATE LINe	!!!
//						slope = p1.slopeTo(q2); 
						HashSet<Point> pointDraw = new HashSet<Point>();
						pointDraw.add(q1); // put point in set
						pointDraw.add(q2);
						count++;
						pointDraw.add(p1);
						// System.out.println("create slope "+ slope + " point "
						// + pointDraw);
						
						hashmap.put(slope, pointDraw);
					}
					// count++;
					// System.out.println("loop1 walue is: " +
				}
				// System.out.println("loop2 walue is: " + hashmap.toString());

				// System.out.println("loop3 walue is: " + hashmap.toString());
			}
			// System.out.println("loop4 walue is: " + hashmap.toString());
		}
		// }
		System.out.println(hashmap.size());
		System.out.println(hashmap.toString());
		System.out.println("--------------------");
		// draw and print
		HashSet<Point> draw = new HashSet<Point>();
		
		for (Map.Entry<Double, HashSet<Point>> entry : hashmap.entrySet()) {		// PRINT && DRAW
			draw = entry.getValue();
			TreeSet<Point> pSet = new TreeSet<Point>(entry.getValue());
//			System.out.println(draw);
			if (pSet.size() > 3) {
				pSet.first().drawTo(pSet.last());
				Point[] druck = new Point[pSet.size()];

				druck = (pSet.toArray(new Point[pSet.size()]));
				for (int i = 0; i < pSet.size(); i += 1) {
					if (i == pSet.size() - 1)
						StdOut.print(druck[i]+" "+entry.getKey());
					else
						StdOut.print(druck[i] + " -> ");
				}
				StdOut.println("");
				// StdOut.println(pSet.first() + " -> " + pSet.last() + " -> " +
				// pC + " -> " + pD);
			}
			// System.out.println(draw.toArray(a));

		}

		StdDraw.show(0);
		StdDraw.setPenRadius();

		// System.out.println("------------------------------------------");

		// count = 0;
		// for (Double s : slopeArr) {
		// if (s != null)
		// System.out.println(count + " : " + s);
		// else
		// System.out.println(count + " :");
		// count++;
		// }
		// TreeSet<Point> set = new TreeSet<Point>();
		// set.add(new Point(1, 2));
		// set.add(new Point(1, 1));
		// set.add(new Point(2, 1));
		// set.add(new Point(1, 1));
		// // set.
		// System.out.println(set.toString());
		// System.out.println(p3.slopeTo(p4));
		// System.out.println("drCount: " + drawCount);
		// HashMap<Double, HashSet<Point>> sss = new HashMap<Double,
		// HashSet<Point>>();
		// HashSet<Point> hp1= new HashSet<Point>();
		// HashSet<Point> hp2= new HashSet<Point>();
		// HashSet<Point> hp3= new HashSet<Point>();
		// hp1.add(p1);
		//
		//
		// hp2.add(p3);
		// hp2.add(p4);
		// hp2.add(p3);
		//
		// sss.put(1.0, hp2);
		// sss.put(1.0, hp1);
		//

		// System.out.println(sss.values());
		// I used a nested java collection. HashMap<Double, HashSet<Point>>.
		// Before any iteration, sort points by it's lexical order. In
		// iteration, HashMap could judge if a key in it by the complexity O(1).
		// HashSet could judge if a value in it by the complexity O(1). So every
		// time you find a line, you need to judge if the first or last point in
		// the line containted in the HashSet and the slope as the key
		// containted in HashMap. If both of this are satisfied, add the slope
		// as the key in HashMap and first and last to HashSet as the value. I
		// think this is not the most simple way to prevent printing
		// subsegments.

	}

}
