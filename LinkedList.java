/***
 * Class to model the class LinkedList which is a generic class and implements the interface List<E>
 * @author Udita Agarwal
 * @version 0.1
 * Date of creation: November 4th, 2021
 * Last Date Modified: November 11th, 2021
 */

 
import java.util.Iterator; // to use the iterator class
import java.util.ListIterator; // to use the list iterator class
import java.util.NoSuchElementException; // to use no such element exception class

public class LinkedList<E> implements List<E>{
	// Data members
    private Node head, tail;
	int size;

	//Inner class node
	private class Node {
		// Data members
		E value;
		Node next;
        
		/***
	 	* Default constructor for inner Node class 
	 	* @param initialValue for initial value of the node
	 	* Initialises data members value to the initialValue and next to null
	 	*/
		Node(E initialValue) {
			value = initialValue;
			next = null;
		}
	}

	/***
	 * Default constructor for LinkedList class
	 * Initialises data members head and tail to null
	 * Initialises data member size to 0
	 */
	public LinkedList() {
		head = tail = null;
		size = 0;
	}

	/***
	 * Method to add an item right in the front of the linkedlist
	 * @param item which contains the item that needs to be added 
	 * @return true if the item is added
	 */
	public boolean addFirst(E item) {// O(1)
		Node newNode = new Node(item);
		if (head == null) { 
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
		return true;
	}

	/***
	 * Method to add an item right in the end of the linkedlist
	 * @param item which contains the item that needs to be added 
	 * @return true if the item is added
	 */
	public boolean addLast(E item) {// O(1)
		Node newNode = new Node(item);
		if (head == null) {
			head = tail = newNode;
		} else {
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
		while (current.next != null){ 
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
	 * @return LinkedListIterator
	 */
	public Iterator<E> iterator() {
		return new LinkedListIterator(); 
	}

	/***
	 * Method to generate a list iterator for the list
	 * no parameters
	 * @return LinkedListListIterator
	 */
	public ListIterator<E> listIterator(){
		return new LinkedListListIterator(); 
	}
 
	/***
	 * Method to generate a list iterator for the list starting at the index passed
	 * @param index according to which the linkedlist list iterator needs to be created
	 * @return LinkedListListIterator 
	 */
	public ListIterator<E> listIterator(int index){ 
		if(index < 0 || index > size){
			throw new ArrayIndexOutOfBoundsException();
		} else {
			 return new LinkedListListIterator(size);
		}
	}

	// Inner linked list iterator class which implements Iterator of Generic type
	class LinkedListIterator implements Iterator<E> {
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

 
	// Inner linkedlist list iterator class which implements ListIterator of Generic type
	class LinkedListListIterator implements ListIterator<E>{
		// data member
		private Node current;
 
		/***
	 	* Default constructor for the linkedlistlistiterator class 
	 	* no parameters
	 	* Initialises data member current to head
	 	*/
		private LinkedListListIterator(){
			current = head; 
		}
	
		/***
	 	*  constructor for the linkedlistlistiterator class 
	 	* @param index which contains the value till which the list has to be iterated
	 	* Initialises data member current to head 
		* Iterates through the list
	 	*/
		private LinkedListListIterator(int index){
			current = head; 
			int i = 0;
			while(i < index - 1){
				current = current.next;
				i++;
			}
		}
 
		/***
	 	* Method to check if list has another next element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
		public boolean hasNext() { 
			return (current != null);
		}
  
		/***
	 	* Method to return next element in the list  
	 	* no parameters
	 	* @return value of the next element
	 	*/
		public E next() { 
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
		public boolean hasPrevious() { 
			return (current != null);
		}
  
		/***
	 	* Method to return the previous element in the list  
	 	* no parameters
	 	* @return value of the previous element
	 	*/
		public E previous() { // O(1) 
			if (current == null){
                throw new NoSuchElementException();
            }	
			Node temp = head;
			Node p = null;
			while(current != temp){
				p = temp;
				temp = temp.next;
			}
			E value = current.value;
			current = p;
			return value;
		}
  
		/***
	 	* Method to return the next index 
	 	* no parameters
	 	* throws unsupported operation exception 
	 	*/
		public int nextIndex(){
			throw new UnsupportedOperationException();
		}
  
		/***
	 	* Method to return the previous index 
	 	* no parameters
	 	* throws unsupported operation exception 
	 	*/
		public int previousIndex(){
			throw new UnsupportedOperationException();
		}
  
		/***
	 	* Method to set an element
	 	* @param e which contains the element that needs to be set
	 	* throws unsupported operation exception 
	 	*/
		public void set(E e){
			throw new UnsupportedOperationException();
		}
  
		/***
	 	* Method to add an element
	 	* @param e which contains the element that needs to be added
	 	* throws unsupported operation exception 
	 	*/
		public void add(E e){
			throw new UnsupportedOperationException();
		}
  
		/***
	 	* Method to remove an element
	 	* no parameters
	 	* throws unsupported operation exception 
	 	*/
		public void remove(){
			throw new UnsupportedOperationException();
		}
 
 
	}
}

 
	

	




