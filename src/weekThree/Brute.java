package weekThree;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.algorithms.In;
import com.algorithms.StdDraw;
import com.algorithms.StdOut;

public class Brute {
	private static String filename = "/home/kokoko/workspace/Algorithms_sendfiles/input56.txt";

	public static void main(String[] args) throws IOException {
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		// read from the input
		// String filename = args[0];

		In in = new In(filename);
		int N = in.readInt();
		int countL = countLines(filename);
		Point[] parr = new Point[countL];

		for (int i = 0; i < N; i++) { // Create array of Points
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			parr[i] = p;
			p.draw();
		}
		// Point zeroPoint = new Point(0, 0);
		// Arrays.
		Arrays.sort(parr);
		// Iterator<Point> iterator = arr.iterator();
		Point to = new Point(0, 0);
		int count;
		double outerSloope, innerSloope;
		for (int i = 0; i < countL; i++) { // OUTER LOOP
			count = 0;
			Point p1 = parr[i];
			// outerSloope = .slopeTo(p1);
			// sloopeArr[i] = outerSloope;
			for (int j = i + 1; j < countL; j++) { // OUTER_INNER LOOP
			// count = 0;
				Point p2 = parr[j];
				outerSloope = p1.slopeTo(p2);
				if (count >= 5)
					break;
				for (int k = j; k < countL; k++) { // INNER_InnerLoop
					if (count >= 5)
						break;
					Point p3 = parr[k];
					innerSloope = p1.slopeTo(p3);
					if (innerSloope == outerSloope && count <= 5) {
						// System.out.println("5");

						to = p3;
						if (count == 0)
							StdOut.print(to);
						else
							StdOut.print(" -> " + to);
						count++;

					}

				}

			}
			if (count >= 4) {
				p1.drawTo(to);
				StdOut.println();
				// break;
				// System.out.println("6");
			}

		}
		// MY TEST

		// for (Point s : parr){
		// System.out.println(s.toString());
		// }

		StdDraw.show(0);

		// reset the pen radius
		StdDraw.setPenRadius();
	}

	private static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

}
