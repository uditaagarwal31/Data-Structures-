import java.io.File;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.Scanner;


public class Test {
    
    public static void main (String[] args) throws FileNotFoundException{
        // creates instances of the arraylist, linkedlist, and doublylinkedlist classes 
        ArrayList<String> list1 = new ArrayList<>();
        LinkedList<String> list2 = new LinkedList<>();
        DoublyLinkedList<String> list3 = new DoublyLinkedList<>();

        // calls method readFromFile and adds country names to the 3 lists  
        readFromFile(list1, "countries.txt");
        readFromFile(list2, "countries.txt");
        readFromFile(list3, "countries.txt"); 

        // prints the lists forward 
        System.out.println("Array List (Forward):");
        printListForward(list1); 
        System.out.println();
        System.out.println("Linked List (Forward):");
        printListForward(list2); 
        System.out.println();
        System.out.println("Doubly Linked List (Forward):");
        printListForward(list3); 
        System.out.println();

        // prints the lists backward 
        System.out.println("Array List (Backward):");
        printListBackward(list1);
        System.out.println();
        System.out.println("Linked List (Backward):");
        printListBackward(list2); 
        System.out.println();
        System.out.println("Doubly Linked List (Backward):");
        printListBackward(list3); 
        System.out.println();
    }
    

    /***
	 	* Method to read the file and populate the content in the list  
	 	* @param list which contains the list where the information from the file needs to be added 
        * @param fileName which contains the name of the file which needs to be read 
	 	* no return value 
	 	*/
    public static void readFromFile(List<String> list, String fileName) throws FileNotFoundException{
        
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        try{
            Scanner readFile = new Scanner(file);
            // reads information from the file and stores it in the relevant data members
            while(readFile.hasNext()){
                String line = readFile.nextLine();
                list.add(line);
            }
            readFile.close();
        }
        // catches the file not found exception
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
    }

    /***
	 	* Method to print the elements of the list forward using the bidirectional iterator 
	 	* @param list which contains the list which needs to be printed 
	 	* no return value 
	 	*/
    public static <E> void printListForward(List<E> list){ // O(n)
        ListIterator<E> it = list.listIterator(); // O(1)
        System.out.print("[");
        while(it.hasNext()){ // O(n)
            E temp = it.next();
            System.out.print(temp + " "); 
        }
        System.out.print("]");
        System.out.println();
    }

    /***
	 	* Method to print the elements of the list forward using the bidirectional iterator  
	 	* @param list which contains the list which needs to be printed 
	 	* no return value 
	 	*/
    public static <E> void printListBackward(List<E> list){ // O(n)
        ListIterator<E> it = list.listIterator(list.size());
        System.out.print("[");
        while(it.hasPrevious()){
            System.out.print(it.previous() + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
