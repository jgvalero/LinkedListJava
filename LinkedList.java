//  Description: A linked list is a sequence of nodes with efficient
//  element insertion and removal.
//  This class contains a subset of the methods of the
//  standard java.util.LinkedList class.

import java.util.NoSuchElementException;

public class LinkedList {
	// nested class to represent a node
	private class Node {
		public Object data;
		public Node next;
	}

	// only instance variable that points to the first node.
	private Node first;

	// Constructs an empty linked list.
	public LinkedList() {
		first = null;
	}

	// Returns the first element in the linked list.
	public Object getFirst() {
		if (first == null) {
			NoSuchElementException ex = new NoSuchElementException();
			throw ex;
		} else
			return first.data;
	}

	// Removes the first element in the linked list.
	public Object removeFirst() {
		if (first == null) {
			NoSuchElementException ex = new NoSuchElementException();
			throw ex;
		} else {
			Object element = first.data;
			first = first.next; // change the reference since it's removed.
			return element;
		}
	}

	// Adds an element to the front of the linked list.
	public void addFirst(Object element) {
		// create a new node
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		// change the first reference to the new node.
		first = newNode;
	}

	// Returns an iterator for iterating through this list.
	public ListIterator listIterator() {
		return new LinkedListIterator();
	}

	// Returns all objects in a linked list as a string
	public String toString() {
		ListIterator iter = listIterator();
		String result = "{ ";
		while (iter.hasNext() == true) {
			Object obj = iter.next();
			result = result + obj.toString() + ", ";
		}
		result = result + "}\n";
		return result;
	}

	// Counts the amount of objects in a linked list
	public int size() {
		int counter = 0;
		ListIterator iter = listIterator();
		while (iter.hasNext()) {
			counter++;
			iter.next();
		}
		return counter;
	}

	// Adds an element in alphabetical order
	public void addElement(Object element) {
		// Create 2 iterators, 1st one is ahead by one (to compare)
		int compare = 0;
		ListIterator iter1 = listIterator();
		ListIterator iter2 = listIterator();
		while (iter1.hasNext()) {
			String str1 = (String) iter1.next();
			String str2 = (String) element;
			compare = str2.compareTo(str1);
			// Only keeps going to next element if new element returns negative when
			// compared
			if (compare >= 1) {
				iter2.next();
			}
		}
		iter2.add(element);
	}

	// Removes elements at each even index
	public void removeElementsAtEvenIndices() {
		int counter = 0;
		ListIterator iter = listIterator();

		while (iter.hasNext()) {
			iter.next();
			if (counter % 2 == 0) {
				iter.remove();
			}
			counter++;
		}
	}

	// Counts how many indexes appear before an element
	public int howManyAppearBefore(Object element) {
		int counter = 0;
		boolean found = false;
		ListIterator iter = listIterator();
		while (iter.hasNext() && !found) {
			// Add to counter until it reaches found index
			String str1 = (String) iter.next();
			String str2 = (String) element;
			if (str1.equals(str2)) {
				found = true;
			} else {
				counter++;
			}
		}
		// Return -1 if element was not found. Return counter otherwise
		if (iter.hasNext() == false && !found) {
			return -1;
		} else {
			return counter;
		}
	}

	// Finds the last index of a given element
	public int indexOfLast(Object element) {

		int counter = howManyAppearBefore(element);
		// If element is not found, return 1
		if (howManyAppearBefore(element) == -1) {
			return -1;
		} else {
			ListIterator iter = listIterator();
			// For loop to reach first instance of the element
			for (int i = 0; i <= howManyAppearBefore(element); i++) {
				iter.next();
			}
			// While loop only works if there is duplicate, adding to counter until no more
			// dupplicates
			while (iter.hasNext()) {
				String str1 = (String) iter.next();
				String str2 = (String) element;
				if (str1.equals(str2)) {
					counter++;
				}

			}
			return counter;
		}
	}

	// Duplicates each element once
	public void duplicateEach() {
		ListIterator iter = listIterator();
		while (iter.hasNext()) {
			String str = (String) iter.next();
			iter.add(str);

		}
	}

	// Removes an element at a given index
	public Object removeElementAt(int index) {
		String str = "";
		// Throws an error if the index is not in the range of the linked list
		if (size() < index) {
			IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
			throw ex;
		}
		// Goes to next element until it reaches the index given, then removes it
		ListIterator iter = listIterator();
		for (int i = 0; i <= index; i++) {
			str = (String) iter.next();
		}
		iter.remove();
		return str;
	}

	// nested class to define its iterator
	private class LinkedListIterator implements ListIterator {
		private Node position; // current position
		private Node previous; // it is used for remove() method

		// Constructs an iterator that points to the front
		// of the linked list.

		public LinkedListIterator() {
			position = null;
			previous = null;
		}

		// Tests if there is an element after the iterator position.
		public boolean hasNext() {
			if (position == null) // not traversed yet
			{
				if (first != null)
					return true;
				else
					return false;
			} else {
				if (position.next != null)
					return true;
				else
					return false;
			}
		}

		// Moves the iterator past the next element, and returns
		// the traversed element's data.
		public Object next() {
			if (!hasNext()) {
				NoSuchElementException ex = new NoSuchElementException();
				throw ex;
			} else {
				previous = position; // Remember for remove

				if (position == null)
					position = first;
				else
					position = position.next;

				return position.data;
			}
		}

		// Adds an element after the iterator position
		// and moves the iterator past the inserted element.
		public void add(Object element) {
			if (position == null) // never traversed yet
			{
				addFirst(element);
				position = first;
			} else {
				// making a new node to add
				Node newNode = new Node();
				newNode.data = element;
				newNode.next = position.next;
				// change the link to insert the new node
				position.next = newNode;
				// move the position forward to the new node
				position = newNode;
			}
			// this means that we cannot call remove() right after add()
			previous = position;
		}

		// Removes the last traversed element. This method may
		// only be called after a call to the next() method.
		public void remove() {
			if (previous == position) // not after next() is called
			{
				IllegalStateException ex = new IllegalStateException();
				throw ex;
			} else {
				if (position == first) {
					removeFirst();
				} else {
					previous.next = position.next; // removing
				}
				// stepping back
				// this also means that remove() cannot be called twice in a row.
				position = previous;
			}
		}

		// Sets the last traversed element to a different value.
		public void set(Object element) {
			if (position == null) {
				NoSuchElementException ex = new NoSuchElementException();
				throw ex;
			} else
				position.data = element;
		}
	} // end of LinkedListIterator class
} // end of LinkedList class
