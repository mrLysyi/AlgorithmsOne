package weekTwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.algorithms.Stack;
import com.algorithms.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N = 5; // size
	private int count = 0;
	private int position = 0;
	private Item[] queue; // private !!!!!!!!!!
	
	public RandomizedQueue() {
		queue = (Item[]) new Object[N];
	} // construct an empty randomized queue

	public boolean isEmpty() {
		return (count == 0);
	} // is the queue empty?

	public int size() {
		return count;
	} // return the number of items on the queue

	public void enqueue(Item item) { // ++
		if (item==null)
			throw new NullPointerException("cant insert null element");
		if (position == N - 1)
			resize(N * 2);
		queue[position++] = item;
		count++;
	} // add the item

	public Item dequeue() { 		// - --
		if (position > 0 && position == queue.length / 4)
			resize(queue.length / 2);
		if (isEmpty())
			throw new NoSuchElementException();
		 int rand = StdRandom.uniform(position);
		Item t = queue[rand];
		queue[rand] = queue[position - 1]; // set last item into position "hole"
		queue[position - 1] = null; // deleted
		count--;
		position--;
		if (t==null)
			throw new NoSuchElementException();
		
		return t;
	} // delete and return a random item

	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = queue[StdRandom.uniform(position)];
		return item;
	} // return (but do not delete) a random item

	private void resize(int capasity) {
		Item[] copy = (Item[]) new Object[capasity]; // cap = 3*count
		for (int i = 0; i < N; i++)
			if (queue[i]!=null)
				copy[i] = queue[i];
		queue = copy;
		this.N = capasity;
	}

	public Iterator<Item> iterator() {
		return (new RQIterator());
	} // return an independent iterator over items in random order

	private class RQIterator implements Iterator<Item> {
		private int rand=StdRandom.uniform(N);;
		private int count;
		RQIterator(){			 
			{
			this.rand = StdRandom.uniform(N);
			this.count=0;
//			System.out.println("CONSTRUCTOR Iterator rand:" + rand);
			}
		}
		public boolean hasNext() {
			return (count < position);
		}

		public Item next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();			
			if (rand>=position)
				rand =0;
			count++;
			return queue[rand++];
		}

		public void remove() {
			throw new UnsupportedOperationException(
					"remove method is not working");
		}
	}

	public static void main(String[] args) {
		int N = 10;
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		Iterator<String> iterator = q.iterator();
		Iterator<String> iteratorTwo = q.iterator();
		q.enqueue("zero");
		q.enqueue("onr");
		q.enqueue("two");
		q.enqueue("three");
		q.enqueue("four");
		q.enqueue("five");
		q.enqueue("six");
		q.enqueue("seven");
		q.enqueue("eight");
		q.enqueue("nine");
		System.out.println("dequeu: " + q.dequeue());
		for (int i = 0; i < 10; i++) {
			System.out.println(i + ": " + q.sample());
		}
		System.out.println(++N + " iterator---------------");
		while (iterator.hasNext())
			System.out.println(iterator.next());
		System.out.println("Iterator TWO");
		while (iteratorTwo.hasNext())
			System.out.println(iteratorTwo.next());

	} // unit testing
}
