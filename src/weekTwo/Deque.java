package weekTwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.algorithms.StdRandom;

public class Deque<Item> implements Iterable<Item> {
	private int count = 0;
	private int first;
	private int last;
	private int N = 2;
	private Item[] deque; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	public Deque() { // construct an empty deque
		this.deque = (Item[]) new Object[N];
		this.first = N / 2 - 1;
		this.last = N / 2;
	}

	public boolean isEmpty() { // is the deque empty?
		return (count == 0);
	}

	public int size() { // return the number of items on the deque
		return count;
	}

	public void addFirst(Item item) {
		if (item==null)
			throw new NullPointerException();
		if (first == 0)
			leftResize(2 * deque.length);
		deque[first] = item;
		--first;
		count++;
	} // insert the item at the front

	public void addLast(Item item) {
		if (item==null)
			throw new NullPointerException();
		if (last == N - 1)
			rightResize(2 * deque.length);
		deque[last] = item;
		++last;
		count++;
	} // insert the item at the end

	public Item removeFirst() { // LEFT
		if (isEmpty())
			throw new java.util.NoSuchElementException("cant remove from void set");
		Item t = deque[++first];
		deque[first] = null;
		count--;
		return t;
	} // delete and return the item at the front

	public Item removeLast() { // Right
		if (isEmpty())
			throw new java.util.NoSuchElementException("cant remove from void set");
		Item t = deque[--last];
		deque[last] = null;
		count--;
		return t;
	}// delete and return the item at the end

	@Override
	public Iterator<Item> iterator() {
		return (new DequeIterator());
	}// return an iterator over items in order from front to end

	private class DequeIterator implements Iterator<Item> {
		private int k = first+1;
		@Override
		public boolean hasNext() {
			return k <= last-1;
		}
		@Override
		public Item next() {
			if (!hasNext()) 
				throw new java.util.NoSuchElementException();
			return deque[k++];
		}
		@Override
		public void remove() { // unused
			throw new UnsupportedOperationException("removemethod is not working");
		}
	}

	private void leftResize(int capasity) {
		Item[] copy = (Item[]) new Object[capasity]; // cap = 3*count
		for (int i = 0; i < N; i++)
			copy[N + i] = deque[i];
		deque = copy;
		this.first = first + N;
		this.last = last + N;
		this.N = capasity;
	}

	private void rightResize(int capasity) {
		Item[] copy = (Item[]) new Object[capasity]; // cap = 3*count
		for (int i = 0; i < N; i++)
			copy[i] = deque[i];
		deque = copy;
		this.N = capasity;
	}

	public static void main(String[] args) {
		int i = 1;
		System.out.println(i--);
		Deque d = new Deque<String>();
		d.addFirst("firstLeft");
		d.addLast("firstRight");
		d.addFirst("secondLeft");
		d.addLast("secondRight");
		d.addLast("ThirdRight");
		d.addLast("fourthRight");
		d.addLast("fifthRight");
		d.addLast("sixthRight");
		for (Object ii : d.deque) {
			System.out.println(i++ + " :" + ii);
		}
		i = 0;
		System.out.println(d.removeFirst()); // remove left
		System.out.println(d.removeFirst());
		System.out.println(d.removeFirst());

		// System.out.println(d.removeLast()); // remove right
		// System.out.println(d.removeLast());
		// System.out.println(d.removeLast());
		// System.out.println(d.removeLast());
		// System.out.println(d.removeLast());

		for (Object ii : d.deque) {
			System.out.println(i++ + " :" + ii);
		}
		i = 0;
		
		Iterator it = d.iterator();
		System.out.println("length" + d.deque.length);
		System.out.println("");
		System.out.println("ITERATOR");
		while (it.hasNext()) {
			System.out.print(i++ + ":");
			System.out.println(it.next());
		}
	}// unit testing
}