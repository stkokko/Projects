/**
 * class to represent node of the list
 * @author Stelios Kokkokyris
 */
class Node
{
	/** @param data */
	char data;
	
	/** @param next */
	Node next;
	
	/** @param previous */
	Node previous;

	/**
	 * One-argument constructor creates a Node that refers to data
	 */
	Node( char data ) {
		this( data, null, null );
	} 

	/** 
	 * Three-argument constructor creates Node that refers to
	 * data,to next Node and to previous Node
	 */
	Node( char data, Node next, Node previous ) {
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

	/**
     * @return reference to data in node
	 */
	char getChar() {
		return data; 
	} 

	/**
     * @return reference to next node in list
	 */
	Node getNext() {
		return next;
	}
	
	/**
	 * @return reference to previous node in list
	 */
	Node getPrevious() {
		return previous;
	}
} 