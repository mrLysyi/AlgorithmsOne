package weekTwo;

public class Deque<Item> implements Iterable<Item> {
	private int count = 0;

	public Deque() { // construct an empty deque

	}

	public boolean isEmpty() { // is the deque empty?
		return (count == 0);
	}

	public int size() { // return the number of items on the deque
		return count;
	}

	public void addFirst(Item item) {
	} // insert the item at the front

	public void addLast(Item item) {
	} // insert the item at the end

	public Item removeFirst() {
	} // delete and return the item at the front

	public Item removeLast() {
	}// delete and return the item at the end

	public Iterator<Item> iterator() {
	}// return an iterator over items in order from front to end
	
	private class Node{
		Node first = null;
		Node previous;
		Node current;
	}

	public static void main(String[] args) {
	}// unit testing
}