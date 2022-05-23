/*
 * ListNode.java
 *
 */

/**
 * class to represent one node in a list
 */
class ListNode
{
	/** @param data */
	BookInfo data;
	
	/** @param nextNode */
	ListNode nextNode;

	/**
	 * One-argument constructor creates a Node that refers to obj
	 */
	ListNode( BookInfo obj )
	{
		this( obj, null );
	} 

	/** 
	 * Two-argument constructor creates Node that refers to
	 * obj and to nextNode 
	 */
	ListNode( BookInfo obj, ListNode node )
	{
		data = obj;
		nextNode = node;
	} 

	/**
     * @return reference to data in node
	 */
	BookInfo getData()
	{
		return data; 
	} 

	/**
     * @return reference to next node in list
	 */
	ListNode getNext()
	{
		return nextNode; 
	} 
	
} 