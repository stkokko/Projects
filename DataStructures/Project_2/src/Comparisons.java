import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Scanner;
import java.util.*;

public class Comparisons {
	
	private static Formatter x;
	
	public static void writeFile() {
		try {
			x = new Formatter("Greedy.txt"); //create Greedy.txt file
		}
		 catch(Exception e) {
			 System.out.println("you have an error");
		 }
	}
	
	/**
	 * This method is writing the Math.random()
	 * inputs in the file line by line.
	 */
	public static void addRecords(int proc) {
		x.format("%d%n",(int)Math.sqrt(proc));
		x.format("%d%n",proc);
		for(int j=0; j<=proc; j++) {
			x.format("%d%n",(int) Math.floor(Math.random() * 101));
		}
	}
	
	public static void closeFile() {
		x.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int alg1=0;
		int alg2=0;
		
		System.out.println("Give the number of processes. Suggested numbers are 100,500,1000");
		Scanner input = new Scanner(System.in);
		
		for(int r=1; r<4; r++) {
		System.out.println("\n give number of processes ");	
		int N = input.nextInt();
		
		for(int i=0; i<5; i++) {
		writeFile(); //creates file
		addRecords(N); //passes to addRecords method the number of processes.
		closeFile(); 
		
		Greedy.openFile("Greedy.txt");
		
		Sort s = new Sort(Greedy.newItem);
		
		 alg1 = alg1+Greedy.AlgorithmOne(Greedy.item)/5; //puts to alg1 the average makespan of algorithm 1
		 alg2 = alg2+s.AlgorithmTwo()/5;   // puts to alg2 the average makespan of algorithm 2
		 
		}
		if(alg2>alg1) {System.out.printf("\n for number of processes =  %d, algorithm 1 is faster ", N);
		
		} else if(alg1>alg2) {System.out.printf("\n for number of processes =  %d, algorithm 2 is faster ", N);
			} else {	System.out.println("The two algorithms have the same efficiensy");}

		}
	}
}