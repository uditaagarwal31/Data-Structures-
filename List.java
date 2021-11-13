/***
 * Class to model the interface List<E> which is a generic interface
 * @author Udita Agarwal
 * @version 0.1
 * Date of creation: November 4th, 2021
 * Last Date Modified: November 11th, 2021
 */


import java.util.ListIterator; // to use the list iterator class 

public interface List<E>{

    // contains abstract method add , size, listIterator and listIterator(int index)
    public abstract boolean add(E item);
    public abstract int size();
    public abstract ListIterator<E> listIterator();
    public abstract ListIterator<E> listIterator(int index);
    


}