package weekThree;
//https://class.coursera.org/algs4partI-006/forum/thread?thread_id=858
//https://class.coursera.org/algs4partI-006/forum/thread?thread_id=760
import java.util.Arrays;
import java.util.TreeSet;

import com.algorithms.In;
import com.algorithms.StdDraw;

public class Fast {
	private static String filename = "/home/kokoko/workspace/Algorithms_sendfiles/Colinear_ex/input56.txt";
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

		// int countL = N;

		Point[] parr = new Point[N];
		Double[] slopeArr = new Double[N];
		TreeSet<Point> pSet = new TreeSet<Point>();

		for (int i = 0; i < N; i++) { // Create array of Points
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			parr[i] = p;
			 p.draw();
		}

		int count = 0;

		Point p1 = new Point(0, 0);
		// Point pB, p2 = new Point(0, 0);
		// Point pC, p3 = new Point(34560, 60);
		// Point pD, p4 = new Point(457, 2456);
		Point q1, q2 = new Point(0, 0);

		// boolean print = false;
		count = 0;
		int drawCount = 0;

		double previous, next;
		for (int a = 0; a < N; a++) { // main upper loop		
					System.out.println("____________________NEW Iteration____________________________");
			count = 0; // Initialize for inner loop
			previous = 0.0;

			Arrays.sort(parr, 0, N); // Sort
			p1 = parr[a];
			Arrays.sort(parr, p1.SLOPE_ORDER);
//			pSet.clear();
			for (int b = 0; b < N; b++) { // FiLL slope Array
				if (b == N - 1) {
					q1 = parr[b];
					q2 = p1;
				} else {
					q1 = parr[b];
					q2 = parr[b + 1];
				}

				if (p1.SLOPE_ORDER.compare(q1, q2) == 0) { // Search
															// slope.points
					slopeArr[b] = p1.slopeTo(parr[b]);
					// System.out.println("№:" + b + " slopeArr[b]: "
					// + slopeArr[b]+ ", p1: " + p1);
					pSet.add(parr[b]);
					count++;

				} 
				
				else {
					if (count >= 2) {
						p1.drawTo(pSet.last()); // Draw
						System.out.println(p1);
//						p1.draw();
						drawCount++;
						
					}

					pSet.clear();
					count = 0;
				}
				
				
				// slopeArr[b] = null;
			}
			
		

			// for (int d = 0; d < N - 1; d++) { // check
			//
			// if (slopeArr[d] != null) {
			// next = slopeArr[d];
			// if (previous == next ) {
			// pSet.add(parr[d]);
			// count++;
			// } else {
			// if (count > 1) {
			// p1.drawTo(pSet.last());
			// }
			// pSet.clear();
			// count = 0;
			// }
			// previous = next;
			// }
			// }
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
		System.out.println("drCount: " + drawCount);

	}


}
