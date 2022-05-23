/*
 * Processor.java
 */
 
 import java.io.PrintStream;
 
 /**
  * class to represent a processor
  * @author Stelis Kokkokyris
  */
public class Processor implements Comparable<Processor>
{
	/** @param id unique for each processor */ 
	private int id;
	
	/** @para activeTime holds the total processing time of processor */
	private int activeTime;
	
	/** @param processed_jobs is a list holding processes */
	protected List processed_jobs;

	
	/**
	 * One-argument constructor
	 */
	public Processor(int id)
	{
		this(id,new List(),0);
	}

	/** 
	 * Three-argument constructor 
	 * Initialize id, processed_jobs and activeTime
	 */
	public Processor(int id,List newList,int activeTime)
	{
		this.activeTime = activeTime;
		processed_jobs = newList;
		this.id = id;
	}
	
	/**
	 * @return total processing time of processor
	 */
	public int getActiveTime() 
	{	
		if(processed_jobs == null)	return 0;
		return activeTime = processed_jobs.ActiveTime();
	}
	
	/**
	 * method for comparing total processing times 
	 * of two processors and return the min
	 * @return min of two processing times
	 */
	private int compareToP(Processor pr)
	{
		//if(getActiveTime() == pr.getActiveTime()) return 0;
		//if(getActiveTime() > pr.getActiveTime()) return 1;
		//return -1;
		//return Integer.compare(this.getActiveTime(), pr.getActiveTime());
		return this.getActiveTime() > pr.getActiveTime() ? 1 : this.getActiveTime() < pr.getActiveTime() ? -1 : 0;
	}
	public int compareTo(Processor pr)
	{
		return compareToP(pr);
	}
		
	/**
	 * method for printing details of processor such
	 * as id, total processing time and each process job
	 */
	public void printDetails()
	{
		System.out.printf("\n id %d, load = %d : ", id, getActiveTime());
		PrintStream ps = new PrintStream(System.out);
		processed_jobs.printQueue(ps);
	}
	
	/**
	 * method for printing makespan of MaxPQ
	 */
	public void printMakespan()
	{
		System.out.println("\nMakespan = " + getActiveTime());
	}
	
}//end of class Processor