/*
* List.java
*
*/

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * Implement of a linked-list
 */
public class List
{
	/** @param firstNode is the first node of the linked-list */
	protected ListNode firstNode; 
	
	/** @param lastNode is the last node of the linked-list */
	protected ListNode lastNode; 
	
	/** @param listName is the name of the linked-list  */
	private String listName;
	
	/** @param sizeList is the size of the linked-list */
	private int sizeList;
	
	/** The comparison function */
	protected Comparator cmp;
	
	/** No-argument constructor */
	public List() {
		this("List", new DefaultComparator());
	}
	
	/**
	 * One-argument constructor
	 * Initializes firstNode and lastNode of linked-list
	 * the name and the size of linked-list
	 * @param listName is the name of the list
	 */
	public List(String listName, Comparator cmp) { 
		this.listName = listName;
		firstNode = lastNode = null;
		this.cmp = cmp;
		sizeList = 0;
	}
	
	/** @return true if the linked-list is empty */
	public boolean isEmpty() {
		return  firstNode == null;
	}
	
	/**
	 * method to insert an item at front of linked-list
	 */
	public void insertAtFront(BookInfo insertItem )
	{
		ListNode node = new ListNode( insertItem );
		if ( isEmpty() ) // firstNode and lastNode refer to same object
		firstNode = lastNode = node;
		else { // firstNode refers to new node
			node.nextNode = firstNode;
			firstNode = node;
		}
		sizeList++;
	} // end method insertAtFront
	
	/**
	 * method to insert an item at back of linked-list
	 */
	public void insertAtBack(BookInfo insertItem )
	{
		ListNode node = new ListNode( insertItem );
		if ( isEmpty() ) // firstNode and lastNode refer to same Object
		firstNode = lastNode = node;
		else { // lastNode's nextNode refers to new node
			lastNode.nextNode = node;
			lastNode = node;
		}
		sizeList++;
	} // end method insertAtBack
	
	/**
	 * method to remove a node 
	 * from the front of linked-list
	 * @return the removeItem
	 */
	public BookInfo removeFromFront() throws EmptyListException
	{
		if ( isEmpty() ) // throw exception if List is empty
			throw new EmptyListException( listName );

		BookInfo removedItem = firstNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if ( firstNode == lastNode )
		firstNode = lastNode = null;
		else
		firstNode = firstNode.nextNode;

		sizeList--;
		return removedItem; // return removed node data
	} // end method removeFromFront
	
	/**
	 * method to remove a node 
	 * from the back of linked-list
	 * @return the removedItem
	 */
	public BookInfo removeFromBack() throws EmptyListException
	{
		if ( isEmpty() ) // throw exception if List is empty
			throw new EmptyListException( listName );

		BookInfo removedItem = lastNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if ( firstNode == lastNode )
		firstNode = lastNode = null;
		else // locate new last node
		{
			ListNode current = firstNode;

			// loop while current node does not refer to lastNode
			while ( current.nextNode != lastNode )
			current = current.nextNode;

			lastNode = current; // current is new lastNode
			current.nextNode = null;
		} // end else

		sizeList--;
		return removedItem; // return removed node data
	} // end method removeFromBack
	
	/** method to sort the linked-list in ascending order */
	public void sort(){
		ListNode t,x,b = new ListNode(null,null);
		while(firstNode != null){
			t = firstNode;
			firstNode = firstNode.nextNode;
			for(x = b; x.nextNode != null; x = x.nextNode)
				if(cmp.compare(x.nextNode.data.ISBN,t.data.ISBN) > 0)
					break;
			t.nextNode = x.nextNode;
			x.nextNode = t;
			if(x.nextNode.nextNode == null)
				lastNode = x.nextNode;
		}
		firstNode = b.nextNode;
	}
	
	/** method to remove a node form anywhere inside the linked-list */
	public void remove(int isbn){
		ListNode tmp = firstNode;
		if(firstNode.data.ISBN == isbn) {
			firstNode = firstNode.nextNode;
			sizeList--;
			return;
		}
		ListNode x = firstNode.nextNode;
		for(; x != null; x = x.nextNode){
			if(x.data.ISBN == isbn) 
				break;
			firstNode = firstNode.nextNode;
		}
		firstNode.nextNode = x.nextNode;
		if(firstNode.nextNode == null) lastNode = firstNode;
		else lastNode = firstNode.nextNode;
		lastNode = tmp;
		sizeList--;
	}
	
	/** print the elements of the linked-list */
	public void printQueue(PrintStream stream) { 
		if(isEmpty()) {
			//throw new NoSuchElementException(listName + " is empty");
			System.out.println("The booklist is empty!\n");
		}
		else {
			for(ListNode i = firstNode; i != lastNode; i = i.nextNode)
				stream.printf("ISBN: %d , Copies: %d\n",i.data.ISBN,i.data.Copies);
			stream.println("ISBN: " + lastNode.data.ISBN + " , Copies: " + lastNode.data.Copies + "\n");
	    }
	}
	
	/**
	 * return the size of the linked-list, 0 if it is empty
	 * @return number of elements in the linked-list
	 */
	public int size() {
		return sizeList;
	}
}