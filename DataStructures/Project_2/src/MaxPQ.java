/*
 * MaxPQ.java
 */

import java.util.Comparator;
 
public class MaxPQ 
{
	/**
     * Array based heap representation
     */
    protected Processor[] heap;
    /**
     * The number of objects in the heap
     */
    private int size;

    /**
     * Creates heap with a given capacity and comparator.
     * @param capacity The capacity of the heap being created.
     */
    public MaxPQ(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.heap = new Processor[capacity+1];
        this.size = 0;
    }
    /**
     * Inserts an object in this heap.
     * throws IllegalStateException if heap capacity is exceeded.
     * @param object The object to insert.
     */
    public void insert(Processor processor) {
        if(processor == null) throw new IllegalArgumentException();    // Ensure object is not null
        if(size == heap.length - 1) throw new IllegalStateException();// Check available space
		heap[++size] = processor;  									// Place object at the next available position
		swim(size);                                                   // Let the newly added object swim
    }
    /**
     * Removes the object at the root of this heap.
     * throws IllegalStateException if heap is empty.
     * return The object removed.
     */
    public Processor getMax() {
        if(size == 0) throw new IllegalStateException();// Ensure not empty
        Processor p = heap[1];                // Keep a reference to the root object
        if(size > 1) heap[1] = heap[size];              // Replace root object with the one at rightmost leaf
        heap[size--] = null;                           // Dispose the rightmost leaf
        sink(1);                                       // Sink the new root element
        return p;                                     // Return the object removed
    }
    /**
     * Shift up.
     */
    private void swim(int i) {
        while(i > 1){                                 // if i root (i==1) return
			int p = i/2;                              // find parent
			int result = heap[i].compareTo(heap[p]);  // compare parent with child i
			if(result <= 0) return;                   // if child <= parent return
			swap(i,p);                                // else swap and i=p
			i = p;
		}
	}
	public void Swim(int i)
	{
		swim(i);
	}
	
    /**
     * Shift down.
     */
    private void sink(int i){
        int left = 2*i;                                                     // determine left, right child
        int right = 2*i+1;
		int max = left;                                                     // if 2*i > size, node i is a leaf   return
		while(left <= size){	                                            // while haven't reached the leafs
			if(right <= size){ 
				max = heap[left].compareTo(heap[right]) < 0 ? right : left; // Determine the largest child of node i
			}                                                                               
			if(heap[i].compareTo(heap[max]) >= 0) return;                   // If the heap condition holds, stop. Else swap and go on.
			swap(i,max);
			i = max; left = 2*i; right = left + 1; max = left;
		}
	}
	public void Sink(int i)
	{
		sink(i);
	}
	
    /**
     * Interchanges two array elements.
     */
    private void swap(int i, int j) {
        Processor tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
	public void Swap(int i, int j) {
		swap(i,j);
	}
	
    /**
     * Prints the objects of the heap.
     */
    public void print() {
        for (int i=1; i<=size; i++){
            System.out.print(heap[i].getActiveTime() + " ");
        }
        System.out.println("\n");
    }
    /**
     * Check if heap is empty.
     */
    boolean empty(){
        return size == 0;
    }
}//end of class MaxPQ