import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Implement a queue FIFO that handles 
 * character data with a linked list
 * @author Stelios Kokkokyris
 */
public class CharQueueImpl implements CharQueue 
{
	
	/** @param head is the first node of the queue */
	private Node head;
	
	/** @param tail is the last node of the queue */
	private Node tail;
	
	/** @param listName is the name of the list  */
	private String listName;
	
	/** @param sizeQueue is the size of the list */
	private int sizeQueue;
	
	/** No-argument constructor */
	public CharQueueImpl() {
		this("List");
	}
	
	/**
	 * One-argument constructor
	 * Initializes head and tail of the queue
	 * the name of the list and the size of queue
	 * @param listName is the name of the list
	 */
	public CharQueueImpl(String listName) { 
		this.listName = listName;
		head = tail = null;
		sizeQueue = 0;
	}
	
	/** @return true if the queue is empty */
	public boolean isEmpty() {
		return  (size() == 0);//(head == null);
	}
	
	/**
	 * insert a single character to the queue
	 * @param insertItem is the data of the new node
	 */
	public void put(char insertItem) {
		Node newNode = tail;
		tail = new Node(insertItem);
		if(isEmpty())
			head = tail;
		else
			newNode.next = tail;
		sizeQueue++;
	}
	
	/**
 	 * remove and return the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public char get() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		char removedItem = head.data;
		if(head == tail)
			head = tail = null;
		else {
			head = head.next;
		}
		sizeQueue--;
		return removedItem;
	}
	
	/**
	 * return without removing the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public char peek() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		else
			return head.data;
	}
	
	/** print the elements of the queue */
	public void printQueue(PrintStream stream) { 
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		else {
			stream.print("The elements of FIFO queue are: ");
			for(Node i = head; i != tail; i = i.next)
				stream.print(i.data + " ");
			stream.println(tail.data);
	    }
	}
	
	/**
	 * return the size of the queue, 0 if it is empty
	 * @return number of elements in the queue
	 */
	public int size() {
		return sizeQueue;
	}
	
	public static void main(String[] args) {
		// create list
		CharQueueImpl Queue = new CharQueueImpl();
		
		PrintStream ps = new PrintStream(System.out);
		
		// print size of Queue
		ps.println("The current size of Queue is: " + Queue.size());
	
		// insert characters in the list
		ps.print("Put new item.\n");
		Queue.put('q');
		Queue.printQueue(ps);
		ps.print("Put new item.\n");
		Queue.put('u');
		Queue.printQueue(ps);
		ps.print("Put an item.\n");
		Queue.put('e');
		Queue.printQueue(ps);
		ps.print("Put an item.\n");
		Queue.put('u');
		Queue.printQueue(ps);
		ps.print("Put an item.\n");
		Queue.put('e');
		Queue.printQueue(ps);
	
		// print size of Queue
		ps.println("The current size of Queue is: " + Queue.size());
	
		// remove objects from list print after each removal 
		try
		{
			char removedItem = Queue.get();
			ps.printf("Remove item from the head , %s removed.\n", removedItem);
			Queue.printQueue(ps);
			
			removedItem = Queue.get();
			ps.printf("Remove item from the head , %s removed.\n", removedItem);
			Queue.printQueue(ps);
			
			removedItem = Queue.get();
			ps.printf("Remove item from the head , %s removed\n", removedItem);
			Queue.printQueue(ps);
		}
		catch ( NoSuchElementException noSuchElementException) 
        {
			noSuchElementException.printStackTrace();
        }
		
		// print size of Queue 
		ps.println("The current size of Queue is: " + Queue.size());
	}
}