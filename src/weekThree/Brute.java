package weekThree;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import com.algorithms.In;
import com.algorithms.LinkedQueue;
import com.algorithms.StdDraw;

public class Brute {
	private static String filename = "/home/hellow/workspaceJava/Algorithms_testFiles/collinear/input56.txt";
	
	public static void main(String[] args) throws IOException {
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		// read in the input
		// String filename = args[0];

		In in = new In(filename);
		int N = in.readInt();
		int countL = countLines(filename);
		Point[] parr = new Point[countL];
//		LinkedQueue<Point> arr = new LinkedQueue<Point>();
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			parr[i] = p;
			p.draw();
		}
		Arrays.sort(parr);
//		Iterator<Point> iterator = arr.iterator();
		int count;
		for (int i = 0;) {
			count = 0;
			Iterator<Point> iteratorIn = arr.iterator();
			Point p1 = iterator.next();
			while(iteratorIn.hasNext()){
				double slope = p1.slopeTo(iteratorIn.next());
				Iterator<Point> iteratorInIn = arr.iterator();
				count++;
				if(count == 4)
					break;
			}
			
		}
		// display to screen all at once
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
