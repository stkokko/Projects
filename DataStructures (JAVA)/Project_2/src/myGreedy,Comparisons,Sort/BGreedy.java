/*
 * Greedy.java
 */
 
import java.io.*;
import java.util.*;
 
 
 public class Greedy 
 {
	/**
	 * @param item is a list holding the data from the file
	 */
	 protected static List item;
	
	 public static void main(String[] args)
	{
		openFile("Greedy.txt");
		Sort.AlgorithmTwo(item);
	}
	
	/**
	 * method for open and read file
	 */
	public static void openFile(String Greedy)
	{
		File f = null;
		BufferedReader reader = null;
		String line;
		int number;

		try{
			f = new File(Greedy);
		}
		catch (NullPointerException e){
			System.err.println ("\nFile not found.");
			System.exit(1);
		}

		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e ){
			System.err.println("\nError opening file!");
			System.exit(1);
		}
		
		try {
			item = new List();
			line = reader.readLine(); 
			while(line != null)
			{
				number = Integer.parseUnsignedInt(line.trim());
				item.put(number);
				line = reader.readLine();
			}//end of while
		}
		catch (IOException e) 
		{
            System.err.println("\nError reading line!");
			System.exit(1);
		}
		catch (NumberFormatException e)
		{
			System.err.println("\nError reading integer value!");
			System.exit(1);
		}
		try 
		{
            reader.close();
        } 
		catch (IOException e)
		{
            System.err.println("\nError closing file.");
			System.exit(1);
        }
	}//end of method openFile
	
	
	public static int AlgorithmOne(List item)
	{	
		List tmp = item;
		int numOfProcessors = (int)tmp.get();
		MaxPQ processors = new MaxPQ(numOfProcessors);//create a MaxPQ
		int numOfProcesses = (int)tmp.get();
		
		for(int i = 0; i < numOfProcessors; i++) processors.insert(new Processor(i+1));//add to processors a new processor with unique id
		int l = numOfProcessors <= numOfProcesses ? processors.heap.length : numOfProcesses + 1;//if numOfProcessors <= numOfProcesses then put processors.heap.length-1 processes
																								//else put numOfProcesses processes
		for(int j = 1; j < l; j++) {
			processors.heap[j].processed_jobs.put(tmp.get());//add process
			processors.Swim(j);
		}
		int remProcesses = numOfProcesses - numOfProcessors;
		if(remProcesses > 0) {
			for(int j = remProcesses; j > 0; j--) {
				int min = (processors.heap.length-1)/2+1;//find parent of last node and move to next node
				int i = numOfProcessors <= 4 ? 2 : min;
				while(i < processors.heap.length){
					min = processors.heap[min].compareTo(processors.heap[i]) > 0 ? i : min;//find min active time
					i++;
				}
				processors.heap[min].processed_jobs.put(tmp.get());//add to min active time the remaining process
				processors.Swim(min);
			}
		}
		if(numOfProcesses < 100) 
		{
			Processor[] Pr = new Processor[processors.heap.length-1];//create an array holding processor items
			for(int i = 0; i < Pr.length; i++) Pr[i] = processors.getMax();//put processors object at Pr array in descending order
			for(int i = Pr.length - 1; i >= 0; i--) Pr[i].printDetails();
			System.out.println("Makespan = " + Pr[0].getActiveTime());
			return Pr[0].getActiveTime();	
			}
		
		else {
			processors.getMax().printMakespan();
			return processors.getMax().getActiveTime();
			
		}
		
	}	//end of method AlgorithmOne
		

 }//end of class Greedy