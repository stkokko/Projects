import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Implement a queue that handles 
 * character data with a double-linked list
 * @author Stelios Kokkokyris
 */
public class CharDoubleEndedQueueImpl implements CharDoubleEndedQueue 
{	
	
	/** @param listName is the name of the list */
	private String listName;
	 
	/** @param firstNode is the first node of the list */
	protected Node firstNode;
	 
	/** @param lastNode is the last node of the list */
	protected Node lastNode;
	
	/** @param sizeQueue is the size of the list */
	private int sizeQueue;

	/** No-argument constructor */
	public CharDoubleEndedQueueImpl() {
		this("List");
	}
	
	/**
	 * One-argument constructor
	 * Initializes first and last node of the queue
	 * the name of the list and the size of the queue
	 * @param listName is the name of the list
	 */
	public CharDoubleEndedQueueImpl(String listName) {
		this.listName = listName;
		firstNode = lastNode = null;
		sizeQueue = 0;
	}
	
	/**
	 * insert a character at the front of the queue
	 * @param insertItem is the data of the new node
	 */
	public void addFirst(char insertItem) {
		Node newNode = new Node(insertItem);
		if (isEmpty())
			firstNode = lastNode = newNode;
		else {
			newNode.previous = firstNode.previous;
			newNode.next = firstNode;
			firstNode.previous = newNode;
			firstNode = newNode;
		}
		sizeQueue++;
	}
	
	/**
	 * remove and return a character from the front of the queue
	 * @return character from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public char removeFirst() throws NoSuchElementException {
		if (isEmpty()) 
			throw new NoSuchElementException(listName + " is empty");
		char removedItem = firstNode.data;
		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else { 
			firstNode.next.previous = firstNode.previous;
			firstNode = firstNode.next;
		}
		sizeQueue--;
		return removedItem;
	}
	
	/**
	 * insert a character at the end of the queue
	 * @param insertItem is the element of the new node
	 */
	public void addLast(char insertItem) {
		Node newNode = new Node(insertItem);
		if (isEmpty())
			firstNode = lastNode = newNode;
		else {
			newNode.next = lastNode.next;
			newNode.previous = lastNode;
			lastNode.next = newNode;
			lastNode = newNode;
		}
		sizeQueue++;
	}
	
	/**
	 * remove and return a character from the end of the queue
	 * @return character from the end of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public char removeLast() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		char removedItem = lastNode.data;
		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else {
			lastNode.previous.next = lastNode.next;
			lastNode = lastNode.previous;
		}
		sizeQueue--;
		return removedItem;
	}
	
	/**
	 * return without removing the first item in the queue
	 * @return character from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public char getFirst() {
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		return firstNode.data;
	}
	
	/**
	 * return without removing the last item in the queue
	 * @return character from the end of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public char getLast() {
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		return lastNode.data;
	}
	
	/** print the elements of the queue */
	public void printQueue(PrintStream stream){
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		else {
			stream.print("The elements of double-ended queue are: ");
			for(Node i = firstNode; i != lastNode; i = i.next)
				stream.print(i.data + " ");
			stream.println(lastNode.data);
		}
	}
	
	/**
	 * return the size of the queue, 0 if empty
	 * @return number of elements in the queue
	 */
	public int size() {	
		return sizeQueue;
	}		
	
	/** @return true if the queue is empty */
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public static void main(String[] args) {
		// create list
		CharDoubleEndedQueueImpl Queue = new CharDoubleEndedQueueImpl(); 
		PrintStream ps = new PrintStream(System.out);
		
		// print size of Queue
		ps.println("The current size of Queue is: " + Queue.size());
		
		// insert characters in the list
		ps.print("Add at first.\n");
		Queue.addFirst('c');
		Queue.printQueue(ps);
		ps.print("Add at first.\n");
		Queue.addFirst('b');
		Queue.printQueue(ps);
		ps.print("Add at last.\n");
		Queue.addLast('d');
		Queue.printQueue(ps);
		ps.print("Add at last.\n");
		Queue.addLast('e');
		Queue.printQueue(ps);
		ps.print("Add at last.\n");
		Queue.addLast('f');
		Queue.printQueue(ps);
		
		// print size of Queue
		ps.println("The current size of Queue is: " + Queue.size());
		
		// remove objects from list print after each removal
		try
		{	
			char removedItem = Queue.removeFirst();
			ps.printf("Remove the first item , %s removed.\n", removedItem);
			Queue.printQueue(ps);
			
			removedItem = Queue.removeFirst();
			ps.printf("Remove the first item , %s removed.\n", removedItem);
			Queue.printQueue(ps);
			
			removedItem = Queue.removeLast();
			ps.printf("Remove the last item , %s removed.\n", removedItem);
			Queue.printQueue(ps);
			
			removedItem = Queue.removeFirst();
			ps.printf("Remove the first item , %s removed.\n", removedItem);
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