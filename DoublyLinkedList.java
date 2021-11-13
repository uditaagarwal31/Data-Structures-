/***
 * Class to model the class DoublyLinkedList which is a generic class and implements the interface List<E>
 * @author Udita Agarwal
 * @version 0.1
 * Date of creation: November 4th, 2021
 * Last Date Modified: November 11th, 2021
 */


import java.util.Iterator; // to use the iterator class
import java.util.ListIterator; // to use the list iterator class
import java.util.NoSuchElementException; // to use the no such element exception class


public class DoublyLinkedList<E> implements List<E>{

	// Data members
    private Node head, tail;
	int size;

	private class Node {
       
		E value; 
		Node next;
        Node previous;

		/***
	 	* Default constructor for inner Node class 
	 	* @param initialValue for initial value of the node
	 	* Initialises data members next and previous to null
	 	*/
		Node(E initialValue) { // O(1)
			value = initialValue;
			next = null;
            previous = null;
		}
	}

	/***
	 * Default constructor for DoublyLinkedList class
	 * Initialises data members head and tail to null
	 * Initialises data member size to 0
	 */
	public DoublyLinkedList() { // O(1)
		head = tail = null;
		size = 0;
	}

	/***
	 * Method to add an item right in the front of the doublylinkedlist
	 * @param item which contains the item that needs to be added 
	 * @return true if the item is added
	 */
	public boolean addFirst(E item) { // O(1)
		Node newNode = new Node(item);
		if (head == null) { // adding the first node
			head = tail = newNode;
		} else {
			newNode.next = head;
            head.previous = newNode;
			head = newNode;
		}
		size++;
		return true;
	}

	/***
	 * Method to add an item right in the end of the doublylinkedlist
	 * @param item which contains the item that needs to be added 
	 * @return true if the item is added
	 */
	public boolean addLast(E item) { // O(1)
		Node newNode = new Node(item);
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.previous = tail;
            tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}

	/***
	 * Method to add an item 
	 * @param item which contains the item that needs to be added 
	 * @return calls method addLast(item)
	 */
	public boolean add(E item) { // O(1)
		return addLast(item);
	}

	/***
	 * Method to retrieve the first element in the list (head)
	 * no parameters
	 * @return value of head
	 */
    public E getFirst() { // O(1)
		if (head == null){
            throw new NoSuchElementException();
        }
		return head.value;
	}

	/***
	 * Method to retrieve the last element in the list (tail)
	 * no parameters
	 * @return value of tail
	 */
	public E getLast() { // O(1)
		if (head == null){
            throw new NoSuchElementException();
        }	
		return tail.value;
	}

	/***
	 * Method to remove the first element in the list (head)
	 * no parameters
	 * @return true 
	 */
    public boolean removeFirst() { // O(1)
		if (head == null){
            throw new NoSuchElementException();
        }	
		head = head.next;
		head.previous = null;
		if (head == null){
            tail = null;
        }
		size--;
		return true;
	}

	/***
	 * Method to remove the last element in the list (tail)
	 * no parameters
	 * @return true 
	 */
	public boolean removeLast() { // O(n)
		if (head == null){
            throw new NoSuchElementException();
        }
		if (size == 1){
            return removeFirst();
        }
		Node current = head;
		Node previous = null;
		while (current.next != null) {
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;
		size--;
		return true;
	}
	

	/***
	 * Method to return values of the linked list
	 * no parameters
	 * @return string containing all the values 
	 */
	public String toString() { // O(n)
		String output = "[";
		Node node = head;
		while (node != null) {
			output += node.value + " ";
			node = node.next;
		}
		output += "]";
		return output;
	}

	/***
	 * Method to clear the linked list
	 * no parameters
	 * no return value 
	 */
	public void clear() { // O(1)
		head = tail = null;
		size = 0;
	}

	/***
	 * Method to check if list is empty
	 * no parameters
	 * no return value 
	 */
	public boolean isEmpty() { // O(1)
		return (size == 0);
	}

	/***
	 * Method to return list size
	 * no parameters
	 * @return list size 
	 */
	public int size() { // O(1)
		return size;
	}

	/***
	 * Method to generate an iterator for the list
	 * no parameters
	 * @return DoublyLinkedListIterator
	 */
	public Iterator<E> iterator() { // O(1)
		return new DoublyLinkedListIterator();
	}

	/***
	 * Method to generate an iterator for the list
	 * no parameters
	 * @return DoublyLinkedListListIterator
	 */
	public ListIterator<E> listIterator() { // O(1)
        return new DoublyLinkedListListIterator();
    }

	/***
	 * Method to generate a list iterator for the list starting at the index passed
	 * @param index according to which the Doublylinkedlist list iterator needs to be created
	 * @return LinkedListListIterator 
	 */
	public ListIterator<E> listIterator(int index){ // O(1)
		if(index < 0 || index > size){
			throw new ArrayIndexOutOfBoundsException();
		} else{
			 return new DoublyLinkedListListIterator(index);
		}
	}

	// Inner doublylinked list list iterator class which implements Iterator of Generic type
	private class DoublyLinkedListListIterator implements ListIterator<E> {
		// data member
		private Node current = head;
		private Node previous = null;

		/***
	 	* Default constructor for the doublylinkedlistlistiterator class 
	 	* no parameters
	 	* Initialises data member current to head
	 	*/
		private DoublyLinkedListListIterator(){ // O(1)
			current = head; 
		}
		
		/***
	 	* Default constructor for the doublylinkedlistlistiterator class 
	 	* @param index which contains the value till which the list has to be iterated
	 	* Initialises data member current to head 
		* Iterates through the list
	 	*/
		private DoublyLinkedListListIterator(int index){ // O(n)
			current = head; 
			int i = 0;
			while(i < index - 1){
				previous = current;
				current = current.next;
				i++;
			}
		}

		/***
	 	* Method to check if list has another next element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
		public boolean hasNext() { // O(1)
			return (current != null);
		}

		/***
	 	* Method to return next element in the list  
	 	* no parameters
	 	* @return value of the next element
	 	*/
		public E next() { // O(1)
			if (current == null){
                throw new NoSuchElementException();
            }	
			E value = current.value; 
			current = current.next;
			return value; 
		}

		/***
	 	* Method to check if list has a previous element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
        public boolean hasPrevious() { // O(1)
			return (current != null);
		}

		/***
	 	* Method to return the previous element in the list  
	 	* no parameters
	 	* @return value of the next element
	 	*/
        public E previous() { // O(1)
			if (current == null){
                throw new NoSuchElementException();
            }	
			
            E value = current.value;
			current = current.previous;
			return value;
		}

		/***
	 	* Method to return the next index 
	 	* no parameters
	 	* throws unsupported operation exception 
	 	*/
        public int nextIndex(){ // O(1)
            throw new UnsupportedOperationException();
        }

		/***
	 	* Method to return the previous index 
	 	* no parameters
	 	* throws unsupported operation exception 
	 	*/
        public int previousIndex(){ // O(1)
            throw new UnsupportedOperationException();
        }

		/***
	 	* Method to set an element
	 	* @param e which contains the element that needs to be set
	 	* throws unsupported operation exception 
	 	*/
        public void set(E e){ // O(1)
            throw new UnsupportedOperationException();
        }

		/***
	 	* Method to add an element
	 	* @param e which contains the element that needs to be added
	 	* throws unsupported operation exception 
	 	*/
        public void add(E e){ // O(1)
            throw new UnsupportedOperationException();
        }

		/***
	 	* Method to remove an element
	 	* no parameters
	 	* throws unsupported operation exception 
	 	*/
        public void remove(){ // O(1)
            throw new UnsupportedOperationException();
        }

	}

	// Inner linked list class which implements Iterator of Generic type
	class DoublyLinkedListIterator implements Iterator<E> {
		// data member
		private Node current = head;

		/***
	 	* Method to check if list has another next element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
		public boolean hasNext() { // O(1)
			return (current != null);
		}

		/***
	 	* Method to return next element in the list  
	 	* no parameters
	 	* @return value of the next element
	 	*/
		public E next() { // O(1)
			if (current == null){
				throw new NoSuchElementException();
			}
			E value = current.value;
			current = current.next;
			return value;
		}
	}
    
}
