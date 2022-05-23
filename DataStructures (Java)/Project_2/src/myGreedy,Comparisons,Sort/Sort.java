public class Sort  {

	private static List processedItem;
	
	/**
	 * AlgoritmhTwo puts the processes list in an array,
	 * then sorts the array and returns the processes sorted 
	 * in a list.
	 */
	public static int AlgorithmTwo(List item) {
		processedItem = item;
		System.out.println(processedItem.size());
		ListNode p = processedItem.head.nextNode; //p equals the first process
		int numOfProcesses = (int)p.data;
		p = p.nextNode;
		
		int [] a = new int[numOfProcesses]; //creates an array long as the number of processes. 
		for(int i = 0; i < a.length; i++)  {
			a[i] = (int) p.data;
			p = p.nextNode;
		}
		
		quicksort(a, 0, a.length-1);
		
		ListNode b = processedItem.head.nextNode.nextNode;
		int i=0;
		while(numOfProcesses > 0) {
			b.data = a[i];	// now the list of processes is sorted
			b = b.nextNode;
			i++;
			numOfProcesses--;
		}
		
		
		return Greedy.AlgorithmOne(processedItem);
	}
	
	
	/**
	 * Using the quicksort method for array.
	 */
	static void quicksort(int[] a, int l, int r) 
	{ 
		if (r <= l) return;
		int i = partition(a, l, r);
		quicksort(a, l, i-1);
		quicksort(a, i+1, r);
	}
	
	
	
	static int partition(int a[], int l, int r) {
		int i = l-1, j = r; int v = a[r]; 
		for (;;) { 
			while (less(a[++i], v));
			while (less(v, a[--j])) 
				if (j == l) break; 
			 if (i >= j) break;
			 exch(a, i, j);
		}
		exch(a, i, r);
		return i; }

	static void exch(int[] a, int i, int j) {
		int t=a[i];  a[i]=a[j]; a[j]=t;
	}

	static boolean less(int v, int w) {
		return v>w;
	}
	
}