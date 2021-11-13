/***
 * Class to model the class ArrayList which is a generic class and implements the interface List<E>
 * @author Udita Agarwal
 * @version 0.1
 * Date of creation: November 4th, 2021
 * Last Date Modified: November 11th, 2021
 */


import java.util.Iterator; // to use the iterator 
import java.util.ListIterator; // to use the list iterator 

public class ArrayList<E> implements List<E>{
    // data members 
    private E[] elements;
    private int size;

     /***
	 * Default constructor
	 * No parameters
	 * Initializes elements to a default size of 10
	 */
    public ArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    /***
	 * Constructor for ArrayList class
	 * @param capacity which contains the capacity the ArrayList should have
	 * Gives data members element that specific capacity 
	 * Initialises data member size to 0
	 */
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /***
	 * Method to add an item 
	 * @param item which contains the item that needs to be added 
	 * @return call to method add which takes paramaters size and item
	 */
    public boolean add(E item) {
        return add(size, item);
    }

    /***
	 * Method to add an item at the specific index
	 * @param item which contains the item that needs to be added 
	 * @param index where item has to be added
	 * @return true if added 
	 */
    public boolean add(int index, E item){
        if(index > size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        ensureCapacity(); // grows the list size if needed 
		// adds the element to the correct index 
        for(int i = size - 1; i > index; i--){
            elements[i + 1] = elements[i];
        }
        elements[index] = item;
        size++;
        return true;
    }

    /***
	 * Method to get an item at the specific index
	 * @param index from where item has to be retrieved
	 * @return element at that particular index 
	 */
    public E get(int index) {
        checkIndex(index); // checks validity of the index 
        return elements[index];
    }

    /***
	 * Method to set an item at the specific index
	 * @param index from where item has to be set
	 * @param item which contains the item that has to be set at the specific index
	 * @return element that used to be at that particular index 
	 */
    public E set(int index, E item) {
        checkIndex(index); // checks validity of the index 
        E oldItem = elements[index];
        elements[index] = item;
        return oldItem;
    }

    /***
	 * Method to return list size
	 * no parameters
	 * @return list size 
	 */
    public int size() { 
        return size; 
    }

    /***
	 * Method to clear list 
	 * no parameters
	 * no return value
	 */
    public void clear() { 
        size = 0; 
    }

    /***
	 * Method to check if list is empty
	 * no parameters
	 * @return true if its empty, false if its not emoty 
	 */
    public boolean isEmpty() { 
        return (size == 0);
    }

    /***
	 * Method to remove the item from the list
	 * @param o the item that has to be removed
	 * @return true if it has been removed
	 */
	public boolean remove(Object o) { // O(n)
		E item = (E) o;
		for (int i = 0; i < size; i++) { // O(n)
			if (elements[i].equals(item)) {
				remove(i); // O(n)
				return true;
			}
		}
		return false;
	}
	 
	/***
	 * Method to remove the item at index from the list
	 * @param index from where the item has to be removed
	 * @return item which has been removed
	 */
	public E remove(int index) { // O(n)
        checkIndex(index);
        E item = elements[index];
        for(int i = index; i < size - 1; i++) { // O(n)
            elements[i] = elements[i+1];
        }
        size--;
        return item;
   }


   /***
	 * Method to trim the list to the accurate size
	 * no parameters
	 * no return value
	 */
   public void trimToSize() { // O(n)
       if (size != elements.length) {
           E[] newElements = (E[]) new Object[size];
           for (int i = 0; i < size; i++) {
               newElements[i] = elements[i];
           }
           elements = newElements;
       }
   }


   /***
	 * Method to grow the list to fit new size
	 * no parameters
	 * no return value
	 */
   private int ensureCapacity() { // O(n)
       int iterations=0;
       if (size >= elements.length) {
           int newCap = (int) (elements.length * 1.5);
           E[] newElements = (E[]) new Object[newCap];
           for (int i = 0; i < size; i++) {
               newElements[i] = elements[i];
               iterations++;
           }
           elements = newElements;
       }
       return iterations;
   }
   
    /***
	 * Method to check validity of index
	 * @param index which needs to be checked
	 * no return value
	 */
   private void checkIndex(int index) { // O(1)
       if (index < 0 || index >= size)
           throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1));
   }


   /***
	 * Method to return values of the list
	 * no parameters
	 * @return string containing all the values 
	 */
   public String toString() {// O(n)
       String output = "[";
       for (int i = 0; i < size - 1; i++) {
           output += elements[i] + ", ";
       }
       output += elements[size - 1] + "]";
       return output;
   }

  
   /***
	 * Method to generate an iterator for the list
	 * no parameters
	 * @return ArrayIterator
	 */
   public Iterator<E> iterator(){
        return new ArrayIterator(); 
   } 

   // Inner class that implements Iterator<E>
	private class ArrayIterator implements Iterator<E>{
		// data member
		private int current = -1;

		/***
	 	* Method to check if list has another next element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
		public boolean hasNext() { 
			return current < size-1; 
		}

		/***
	 	* Method to return next element in the list  
	 	* no parameters
	 	* @return value of the next element
	 	*/
		public E next() { 
			return elements[++current]; 
		}
	}

	/***
	* Method to check if that item is there in the list  
	* @param item which needs to be checked if it exists in the list
	* @return value of the iteration of where the item is found
	*/
	public int contains(E item) { // O(1)
		Iterator<E> iter = iterator();
		int iterations = 0;
		while(iter.hasNext()) {
			iterations++;
			if(item.equals(iter.next())) {
				return iterations;
			}
		}
		return iterations;
	}

    /***
	 * Method to generate an iterator for the list
	 * no parameters
	 * @return ArrayListIterator
	 */
   public ListIterator<E> listIterator(){ 
       return new ArrayListIterator(); 
   }

   /***
	 * Method to generate a list iterator for the list starting at the index passed
	 * @param index according to which the arraylist list iterator needs to be created
	 * @return ArrayListIterator 
	 */
   public ListIterator<E> listIterator(int index){ 
       if(index < 0 || index > size){
           throw new ArrayIndexOutOfBoundsException();
       } else{ 
            return new ArrayListIterator(size);
    }
}

   // Inner array list iterator class which implements ListIterator of Generic type
   class ArrayListIterator implements ListIterator<E>{
     // data member   
    private int current;

        /***
	 	* Default constructor for the arraylist iterator class 
	 	* no parameters
	 	* Initialises data member current to -1
	 	*/
        private ArrayListIterator(){
            current = -1; 
        }
        
        /***
	 	*  constructor for the arraylistiterator class 
	 	* @param index which contains the value till which the list has to be iterated
	 	* Initialises data member current index 
	 	*/
        private ArrayListIterator(int index){
            current = index; 
        }

        /***
	 	* Method to check if list has another next element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
        public boolean hasNext() { 
            return current < size-1; 
        }
 
        /***
	 	* Method to return next element in the list  
	 	* no parameters
	 	* @return value of the next element
	 	*/
        public E next() { 
            return elements[++current]; 
        }
 
        /***
	 	* Method to check if list has a previous element 
	 	* no parameters
	 	* @return true if it does, false if it doesn't
	 	*/
        public boolean hasPrevious() { 
             return current > 0; 
         }
 
         /***
	 	* Method to return the previous element in the list  
	 	* no parameters
	 	* @return value of the previous element
	 	*/
         public E previous() { 
             return elements[--current]; 
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
