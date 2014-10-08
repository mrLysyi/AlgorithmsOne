package weekTwo;


import com.algorithms.StdIn;
import com.algorithms.StdOut;

public class Subset {
	   public static void main(String[] args){
		   RandomizedQueue rq = new RandomizedQueue<String>();
		   int k=0;
		   k = Integer.parseInt(args[0]);
//		   for(int i = 0; i< k; i++){
//			   String c = StdIn.readString();
//			   rq.enqueue(c);		  
//		   }
		   java.util.Iterator<String> iterator = rq.iterator();
		   
		   while (!StdIn.isEmpty()) {
//			   k = StdIn.readInt();
			   String str = StdIn.readString(); 
			   rq.enqueue(str);

			   }
		  
		   for(int i = 0; i<k; i++){
			   StdOut.print(iterator.next());
		   }		   
	   }
	}