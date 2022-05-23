import java.io.PrintStream;
import java.util.Comparator;
import java.lang.Math;

/** 
 * class to represent a randomized binary search tree 
 */
class ST {

	/** class to represent node of tree */
	public class TreeNode {
		int id;// unique id of the node
		String city;//city where the node is located
		TreeNode parent;
		TreeNode l;// pointer to left subtree
		TreeNode r;// pointer to right subtree
		int N;//number of nodes in the subtree starting at this TreeNode
		List booklist;// sorted linked list of the books
					  // stored in this TreeNode
					
		/**
		 * Two-argument constructor
		 * Initialize node
		 */
		TreeNode(int id,String name){
			this.id = id;
			int N = 0;
			l = r = parent = null;
			booklist = new List();
			city = name;
		}
		
		/**
		 * method to print items of node
		 */
		public void visit(){
			if(id > 0){//valid id for warehouse only positive number
				System.out.printf("Warehouse %d in %s have: \n",id,city);
				booklist.printQueue(stream);
			}
		}
	
		/** method to unlink node */
		public void unlink(){
			parent = l = r = null;
			id = 0;
			N = 0;
			booklist = null;
		}
	
		public int getId(){
			return id;
		}
		
		public String getCity(){
			return city;
		}
	}
	
	PrintStream stream;
	
	/** @param head represent root of warehouse tree */
	private TreeNode head;

	/** The comparison function. */
    private Comparator cmp;
	
	/**
     * Default constructor.
     */
    public ST() {
        this(new DefaultComparator(), new PrintStream(System.out));
    }
    /**
     * Parametrized constructor that uses a given comparison function.
     * @param cmp The comparison function to use.
     */
    public ST(Comparator cmp,PrintStream stream) {
		head = null;
        this.cmp = cmp;
		this.stream = stream;
    }
	
	/**
	 * method to insert a node in tree
	 */
	void insertWarehouse(int nodeid, String name){
		head = insertWarehouse(head,nodeid,name,null);
		head.N = countR(head);
	}
	private TreeNode insertWarehouse(TreeNode h,int id, String name,TreeNode p){
		if(h == null){//reached leaf
			TreeNode n = new TreeNode(id,name);
			n.parent = p;
			++n.N;
			return n;
		}
		if(Math.random()*(h.N+1) < 1.0)
			return insertRoot(h,id,name,p);
		int result = cmp.compare(h.getId(),id);
		if(result == 0) {
			System.out.printf("\nWarehouse %d exists!\n", id);
			return h;
		}
		if (result > 0){
			h.l = insertWarehouse(h.l, id, name, h);//go to left subtree
			h.l.N = countR(h.l);
		}else{
			h.r = insertWarehouse(h.r, id, name, h);//go to right subtree
			h.r.N = countR(h.r);
		}
		h.N++;
		return h;
	}
	private TreeNode insertRoot(TreeNode h, int id, String name, TreeNode p){
		System.out.println("Inside insert root");
		if(h == null){//reached leaf
			TreeNode node = new TreeNode(id,name);
			node.parent = p;
			++node.N;
			return node;
		}
		int result = cmp.compare(h.getId(),id);
		if(result == 0){
			System.out.printf("Warehouse %d exists!\n", id);
			return h;
		}
		if(result > 0) {
			h.l = insertRoot(h.l, id, name, h);//go to left subtree
			h.l.N = countR(h.l);
			h = rotateRight(h);//rotate right to h
		}else{
			h.r = insertRoot(h.r, id, name, h);//go to right subtree
			h.r.N = countR(h.r);
			h = rotateLeft(h);//rotate left to h
		}
		h.N++;
		return h;
	}
	private TreeNode rotateLeft(TreeNode pivot){
		TreeNode parent = pivot.parent;
		TreeNode child = pivot.r;
		if(parent == null) head = child;
		else if(parent.l == pivot) parent.l = child;
		else parent.r = child;
		child.parent = pivot.parent;
		pivot.parent = child;
		pivot.r = child.l;
		if(child.r != null){
			child.r.parent = pivot;
		}
		child.l = pivot;
		child.N = countR(child);
		pivot.N = countR(pivot);
		return child;
	}
	private TreeNode rotateRight(TreeNode pivot){
		TreeNode parent = pivot.parent;
		TreeNode child = pivot.l;
		if(parent == null) head = child;
		else if(parent.l == pivot) parent.l = child;
		else parent.r = child;
		child.parent = pivot.parent;
		pivot.parent = child;
		pivot.l = child.r;
		if(child.r != null){
			child.r.parent = pivot;
		}
		child.r = pivot;
		child.N = countR(child);
		pivot.N = countR(pivot);
		return child;
	}
	
	/**
	 * method to insert a book in the booklist of node 
	 */
	void insertBookAtWarehouse(int nodeid, int isbn, int copies){
		head = insertBookAtWarehouse(head,nodeid,isbn,copies);
	}
	private TreeNode insertBookAtWarehouse(TreeNode head, int id, int isbn, int copies){
		TreeNode h = head;
		if(h == null){//reached leaf
			System.out.printf("\nWarehouse %d does not exist.\n",id);
			return null;
		}
		int result = cmp.compare(id,h.getId());
		if(result == 0){
			findBook(h,isbn,copies);
			return h;
		} 
		if(result < 0){
			h.l = insertBookAtWarehouse(h.l,id,isbn,copies);//go to left subtree
		}else{
			h.r = insertBookAtWarehouse(h.r,id,isbn,copies);//go to right subtree
		}
		return h;	
	}
	private void findBook(TreeNode n, int isbn, int copies){
		ListNode i = n.booklist.firstNode;
		boolean found = false;
		for(; i != null; i = i.nextNode){
			if(i.data.ISBN == isbn){
				i.data.Copies += copies;
				found = true;
				break;
			}
		}
		if(!found){
			BookInfo newBook = new BookInfo(isbn,copies);
			n.booklist.insertAtBack(newBook);
			if(n.booklist.size() > 1) n.booklist.sort();
		}
	}
			
	/**
	 * method to remove a node from the tree
	 */
	void removeWarehouse(int nodeid){
		boolean remove = removeWarehouse(head,nodeid);
		if(remove) System.out.printf("\nWarehouse %d removed.\n",nodeid);
		else System.out.printf("\nWarehouse %d does not exist.\n",nodeid);
	}
	private boolean removeWarehouse(TreeNode h,int id){
		TreeNode tn = findWarehouse(id);
		if(tn == null) return false;
		removeWarehouse(tn);
		return true;
	}
	private void removeWarehouse(TreeNode w){
		if(w == head){
			if(w.l == null && w.r != null) head.N = w.r.N;
			if(w.l != null && w.r == null) head.N = w.l.N;
			head = joinLR(w.l,w.r);
			w.unlink();
			return;
		}
		if(w.l == null && w.r == null){
			if(w.parent.id > w.id) w.parent.l = null;
			if(w.parent.id < w.id) w.parent.r = null;
			w.unlink();
		}
		else {
			if(w.parent.id > w.id){
				w.parent.l = joinLR(w.l,w.r);
			}
			else{ 
				w.parent.r = joinLR(w.l,w.r);
			}
			w.unlink();
		}
		w.N--;
		head.N--;
	}
	private TreeNode joinLR(TreeNode a, TreeNode b){
		if(a == null) return b;//return left subtree
		if(b == null) return a;//return right subtree
		int N = a.N + b.N;
		if(Math.random()*N < 1.0*a.N){
			a.r = joinLR(a.r,b);
			a.N += b.N;
			return a;
		}
		else {
			b.l = joinLR(a,b.l);
			b.N += a.N;
			return b;
		}
	}
	private TreeNode findWarehouse(int id){
		TreeNode tn = head;
        while (tn != null) {
            // Compare id with the id in the current subtree
            int result = cmp.compare(id, tn.getId());
            if (result == 0) {
                break;
            }
            // Go left or right based on comparison result
            tn = result < 0 ? tn.l : tn.r;
        }
        return tn;
	}
	
	/**
	 * method to remove a book from the booklist of node
	 */
	void removeBook(int nodeid, int isbn){
		head = removeBook(head,nodeid,isbn);
	}
	private TreeNode removeBook(TreeNode head,int id, int isbn){
		TreeNode h = head;
		if(h == null){//reached leaf
			System.out.printf("\nWarehouse %d does not exist.",id);
			return null;
		}
		int result = cmp.compare(id,h.getId());
		if(result == 0){
			removeBook(h,isbn);
			return h;
		}
		if(result < 0){
			h.l = removeBook(h.l,id,isbn);//go to left subtree
		}else{
			h.r = removeBook(h.r,id,isbn);//go to right subtree
		}
		return h;
	}
	private void removeBook(TreeNode n,int isbn){
		ListNode i = n.booklist.firstNode;
		for(; i != null; i = i.nextNode){
			if(i.data.ISBN == isbn){
				i.data.Copies--;
				break;
			}
		}
		if(i == null){//booklist is empty
			System.out.printf("There is not book with ISBN : %d \n", isbn);
			return;
		}
		if(i.data.Copies == 0){
			System.out.printf("The last copie of book with ISBN : %d ,just deleted.\n",isbn);
			n.booklist.remove(isbn);
		}  
	}
	
	/**
	 * method to search for a node
	 */
	void searchByWarehouse(int nodeid){
		searchByWarehouse(head,nodeid);
	}
	private void searchByWarehouse(TreeNode h,int id){
		if(h == null) {//reached leaf
			System.out.printf("\nWarehouse %d does not exist.",id);
			return;
		}
		int result = cmp.compare(id,h.getId());
		if(result == 0){
			System.out.printf("Warehouse %d located in %s.\n",h.getId(),h.getCity());
			h.booklist.printQueue(stream);
			return;
		}
		if(result < 0) searchByWarehouse(h.l,id);
		else searchByWarehouse(h.r,id);
	}
	
	/**
	 * method to search for a book in booklist of a node
	 */
	void searchBookInWarehouse(int nodeid, int isbn){
		searchBookInWarehouse(head,nodeid,isbn);
	}
	private void searchBookInWarehouse(TreeNode h, int id, int isbn){
		if(h == null){//reached leaf
			System.out.printf("\nWarehouse %d does not exist.",id);
			return;
		}
		int result = cmp.compare(id,h.getId());
		if(result == 0){
			System.out.printf("Warehouse %d located in %s.\n",h.getId(),h.getCity());
			searchBookInWarehouse(h,isbn);
			return;
		}
		if(result < 0) searchBookInWarehouse(h.l,id,isbn) ;
		else searchBookInWarehouse(h.r,id,isbn);
	}
	private void searchBookInWarehouse(TreeNode n, int isbn){
		ListNode i = n.booklist.firstNode;
		boolean found = false;
		for(; i != null; i = i.nextNode){
			if(i.data.ISBN == isbn){
				found = true;
				break;
			}
		}
		if(found) System.out.printf("\nWarehouse %d has this book.There are %d copies of this book.\n",n.getId(),i.data.Copies);
		else System.out.printf("\nWarehouse %d does not have this book!\n",n.getId());
	}
	
	/**
	 * method to search for a book at the booklists of tree
	 */
	void searchBook(int isbn){
		System.out.println("\nThe book is available at: ");
		searchBook(head,isbn);
	}
	private void searchBook(TreeNode h,int isbn){
		if(h == null) return;//reached leaf
		searchBook(h.l,isbn);//search in left subtree
		searchBooklist(h,isbn);//chech if current node is the node we are looking for
		searchBook(h.r,isbn);//search in right subtree
	}
	private void searchBooklist(TreeNode n, int isbn){
		ListNode i = n.booklist.firstNode;
		boolean found = false;
		for(; i != null; i = i.nextNode){
			if(i.data.ISBN == isbn){
				found = true;
				break;
			}
		}
		if(found) System.out.printf("\nWarehouse %d located in %s, copies: %d\n",n.getId(),n.getCity(),i.data.Copies);
	}
	
	/**
	 * method to print all the node of the tree in ascending order
	 */
	void printΤree(PrintStream stream){
		if(isEmpty()){
			stream.print("There is not warehouse.Tree is empty!");
		}else{
			stream.println("\n------------------------\nThe warehouses of tree are below\n");
			inorder(head);
		}
	}
	
	/**
	 * method to traverse the nodes of tree in ascending order
	 */
    private void inorder(TreeNode h) {
		if(h == null) return;
		inorder(h.l);
		h.visit();
		inorder(h.r);
	}
	
	/**
	 * method to count the nodes of the subtree with root h
	 */
	int countR(TreeNode h) {
		if (h == null) return 0;
		return 1 + countR(h.l) + countR(h.r); 
	}              
	
	/**
	 * method to check if tree is empty
	 */
	boolean isEmpty(){
		return head.N == 0;
	}
	
}