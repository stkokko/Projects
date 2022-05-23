import java.util.Scanner;
import java.io.PrintStream;

/**
 * class to represent a menu for the class ST
 */
class SystemMenu {
	
	private static ST st = new ST();
	private static PrintStream stream = new PrintStream(System.out);
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		int id,ISBN,copies;
		String name;
		boolean flag = true;
		while(flag){
			System.out.println("\n       Menu Options: \n");
			System.out.println("Enter 0 to exit from the program.\n" +
							   "Enter 1 to insert Warehouse.\n" + 
						       "Enter 2 to insert Book at Warehouse.\n" +
						       "Enter 3 to remove Warehouse.\n" +
						       "Enter 4 to remove a Book.\n" +
						       "Enter 5 to search a Warehouse.\n" +
						       "Enter 6 to search a Book in Warehouse.\n" +
						       "Enter 7 to search a Book.\n" +
						       "Enter 8 to print the tree.\n"); 
			System.out.println("Choose one of the above options to continue.");
			int input = in.nextInt();
			while(true){
				if(input > -1 && input < 9) break;
				System.out.println("Error!\nPlease enter a number from 0 to 8.");
				input = in.nextInt();
				System.out.print("\n");
			}
			if(input == 0) flag = false;
			else if(input == 1) {
				System.out.print("Please enter the id of the Warehouse you want to insert: ");
				id = in.nextInt();
				System.out.printf("Please enter the name of city you want to make the warehouse %d: ",id);
				name = in.next();
				st.insertWarehouse(id,name);
			}
			else if(input == 2){
				System.out.print("Please enter the id of the Warehouse you want to insert book: ");
				id = in.nextInt();
				System.out.printf("Please enter the ISBN of the book you want to insert in the booklist of Warehouse %d: ",id);
				ISBN = in.nextInt();
				System.out.printf("Please enter the number of copies you want to have in booklist of Warehouse %d for book with ISBN %d: ",id,ISBN);
				copies = in.nextInt();
				st.insertBookAtWarehouse(id,ISBN,copies);
			}
			else if(input == 3){
				System.out.print("Please enter the id of the Warehouse you want to remove: ");
				id = in.nextInt();
				st.removeWarehouse(id);
			}
			else if(input == 4){
				System.out.print("Please enter the id of the Warehouse you want to remove a book from her's booklist: ");
				id = in.nextInt();
				System.out.printf("Please enter the ISBN of the book you want to remove from the booklist of Warehouse %d: ",id);
				ISBN = in.nextInt();
				st.removeBook(id,ISBN);
			}
			else if(input == 5){
				System.out.print("Please enter the id of the Warehouse you want to search for: ");
				id = in.nextInt();
				st.searchByWarehouse(id);
			}
			else if(input == 6){
				System.out.print("Please enter the id of the Warehouse you want to search her's booklist for a book: ");
				id = in.nextInt();
				System.out.printf("Please enter the ISBN of the book you want to search for,in the booklist of the Warehouse %d: ",id);
				ISBN = in.nextInt();
				st.searchBookInWarehouse(id,ISBN);
			}
			else if(input == 7){
				System.out.print("Please enter the ISBN of the book you want to search for: ");
				ISBN = in.nextInt();
				st.searchBook(ISBN);
			}
			else
				st.printΤree(stream);
		}
	}
}