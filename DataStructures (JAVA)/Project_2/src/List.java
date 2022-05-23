/*
* List.java
*
*/

import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Implement a queue FIFO that handles 
 * Object data with a linked list
 * @author Stelios Kokkokyris
 */
public class List
{
	/** @param head is the first node of the queue */
	protected ListNode head;
	
	/** @param tail is the last node of the queue */
	protected ListNode tail;
	
	/** @param listName is the name of the list  */
	private String listName;
	
	/** @param sizeQueue is the size of the list */
	private int sizeQueue;
	
	/** @param act_time is the total processing time */
	private int act_time;
	
	/** No-argument constructor */
	public List() {
		this("List");
	}
	
	/**
	 * One-argument constructor
	 * Initializes head and tail of the queue
	 * the name of the list and the size of queue
	 * @param listName is the name of the list
	 */
	public List(String listName) { 
		this.listName = listName;
		head = tail = null;
		sizeQueue = 0;
		act_time = 0;
	}
	
	/** @return true if the queue is empty */
	public boolean isEmpty() {
		return  (size() == 0);//(head == null);
	}
	
	/**
	 * insert a single character to the queue
	 * @param insertItem is the data of the new node
	 */
	public void put(Object insertItem) {
		ListNode newNode = tail;
		tail = new ListNode(insertItem);
		if(isEmpty())
			head = tail;
		else
			newNode.nextNode = tail;
		sizeQueue++;
		act_time += (int)insertItem;
	}
	
	/**
 	 * remove and return the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public Object get() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		Object removedItem = head.data;
		if(head == tail)
			head = tail = null;
		else {
			head = head.nextNode;
		}
		sizeQueue--;
		return removedItem;
	}
	
	/**
	 * return without removing the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public Object peek() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException(listName + " is empty");
		else
			return head.data;
	}
	
	/** print the elements of the queue */
	public void printQueue(PrintStream stream) { 
		if(isEmpty()) {
			//throw new NoSuchElementException(listName + " is empty");
			System.out.println(0);
		}
		else {
			for(ListNode i = head; i != tail; i = i.nextNode)
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
	 * return the total processing time
	 * @return total processing time 
	 */
	public int ActiveTime()
	{
		return act_time;
	}
	
}