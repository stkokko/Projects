import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Implement a queue FIFO that handles 
 * character data with a linked list
 * @author Stelios Kokkokyris
 */
public class CharQueueWithMinImpl implements CharQueueWithMin 
{	
	/** @param head is the first node of the queue */
	private Node head;
	
	/** @param tail is the last node of the queue */
	private Node tail;
	
	/** @param listName is the name of the list */
	private String listName;
	
	/** @param sizeQueue is the size of the list */
	private int sizeQueue;
	
	/** No-argument constructor */
	public CharQueueWithMinImpl() {
		this("List");
	}
	
	/**
	 * One-argument constructor
	 * Initializes head and tail of the queue
	 * the name of the list and the size of queue
	 * @param listname is the name of the list
	 */
	public CharQueueWithMinImpl(String listName) { 
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
			stream.print("The elements of queue are: ");
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
	
	/**
	 * find and return the min character
	 * @return the lesser character
	 */
	public char Min(){
	
		CharQueueImpl F = new CharQueueImpl();// create FIFO queue
		 
		CharDoubleEndedQueueImpl D = new CharDoubleEndedQueueImpl();// create double-linked queue 
		
		PrintStream ps = new PrintStream(System.out);
		
		// print size of FIFO 
		ps.println("The current size of FIFO is: " + F.size());
		
		// print size of double-ended queue
		ps.println("The current size of double-ended queue is: " + D.size() + "\n------------------------------");
	
		ps.print("Put new item in FIFO.\n"); F.put('d');
		F.printQueue(ps);//print the elements of FIFO
		ps.print("Add new item in front of double-linked list.\n");
		if(!(D.isEmpty())){
			if('d' > D.getFirst()){ //while(D.getLast() > D.getFirst()) D.removeLast(); 
				D.firstNode.next = D.lastNode.next;//remove the data from the last to min element of
				D.lastNode = D.firstNode;         //the list if the insertItem is bigger than first
			}
		} 
		D.addFirst('d');
		D.printQueue(ps);//print the elements of double-ended queue

	    ps.print("Put new item in FIFO.\n"); F.put('c');
		F.printQueue(ps);//print the elements of FIFO
		ps.print("Add new item in front of double-linked list.\n");
		if(!(D.isEmpty())){
			if('c' > D.getFirst()){ //while(D.getLast() > D.getFirst()) D.removeLast();
				D.firstNode.next = D.lastNode.next;//remove the data from the last to min element of
				D.lastNode = D.firstNode;         //the list if the insertItem is bigger than first
			}
		}
		D.addFirst('c');
		D.printQueue(ps);//print the elements of double-ended queue
	
	    ps.print("Put new item in FIFO.\n"); F.put('b');
	    F.printQueue(ps);//print the elements of FIFO
		ps.print("Add new item in front of double-linked list.\n");
	    if(!(D.isEmpty())){
			if('b' > D.getFirst()){ //while(D.getLast() > D.getFirst()) D.removeLast();
				D.firstNode.next = D.lastNode.next;//remove the data from the last to min element of
				D.lastNode = D.firstNode;         //the list if the insertItem is bigger than first
			}
		}
		D.addFirst('b');
		D.printQueue(ps);//print the elements of double-ended queue
		
		ps.print("Put new item in FIFO.\n"); F.put('a');
		F.printQueue(ps);//print the elements of FIFO
		ps.print("Add new item in front of double-linked list.\n");
		if(!(D.isEmpty())){
			if('a' > D.getFirst()){ //while(D.getLast() > D.getFirst()) D.removeLast(); 
				D.firstNode.next = D.lastNode.next;//remove the data from the last to min element of
				D.lastNode = D.firstNode;         //the list if the insertItem is bigger than first
			}
		} 
		D.addFirst('a');
		D.printQueue(ps);//print the elements of double-ended queue
		
		ps.println("------------------------------");
		
		ps.print("The current size of FIFO is: " + F.size());//print size of FIFO
		
		ps.println("\nThe current size of double-ended queue is: " + D.size());// print size of double-ended queue
	
		/* remove objects from list print after each removal */
		try
		{
			ps.print("------------------------------");
		
			char removedItem = F.get();
			ps.printf("\nRemove item from the head , %s removed.\n", removedItem);
			F.printQueue(ps);
			if(removedItem == D.getLast()){
				ps.printf("Remove the last item of double-ended queue , %s removed.\n", removedItem);
				D.removeLast();
			}
					
			removedItem = F.get();
			ps.printf("Remove item from the head , %s removed.\n", removedItem);
			F.printQueue(ps);
			if(removedItem == D.getLast()){
				ps.printf("Remove the last item of double-ended queue , %s removed.\n", removedItem);
				D.removeLast();
			}
		}
		catch ( NoSuchElementException noSuchElementException) 
        {
			noSuchElementException.printStackTrace();
        }
		return D.getFirst() < D.getLast() ? D.getFirst() : D.getLast();
	}
	
	public static void main(String[] args) {
	
	    PrintStream ps = new PrintStream(System.out);
	
		CharQueueWithMinImpl M = new CharQueueWithMinImpl();

		//print the min of the queue
		ps.print("Min char of queue is: " + M.Min() + "\n------------------------------\n");
	}
}