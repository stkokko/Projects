import java.util.Scanner;

/**
 * checking if a string is Watson-Crick palindrome
 * @author Stelios Kokkokyris
 */
public class DNAPalindrome { 

	/** type a string */
	public static String input(){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String s = in.nextLine();
		in.close();
		return s;
	}
	
	/**
	 * check if string is empty 
	 * @param str to check if the string is empty
	 * @return true if the string is empty
	 */
	public static boolean isEmpty(String str){
		if(str.trim().length() == 0) {
			System.out.print("Valid input!The string is empty.So it's a Watson-Crick complemented palindrome.");
			return true;
		}else return false;
	}
	
	/**
	 * check if the string is valid
	 * @param str to check if the string is valid
	 * @return true if string is valid
	 */
	public static boolean Valid(String str){
		boolean flag = true;
		if(isEmpty(str)) return flag;
		else{
			for(int i = 0; i < str.trim().length(); i++){
				if((str.charAt(i) != 'A') && (str.charAt(i) != 'C') && (str.charAt(i) != 'T') && (str.charAt(i) != 'G')) {
					System.out.print("\nThe string is not valid!So " + str + " is not a Watson-Crick complemented palindrome.");
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * add characters of string to DlinkedList
	 * @param str to add in the list
	 * @param DlinkedList handles the string
	 * @return reference to DlinkedList 
	 */
	public static CharDoubleEndedQueueImpl Add(String str, CharDoubleEndedQueueImpl DlinkedList){
		for(int i = 0; i < str.trim().length(); i++){
			DlinkedList.addLast(str.charAt(i));
		}
		return DlinkedList;
	}
	
	/**
	 * check if string is palindrome and print the pairs
	 * @param str to check if its a Watson-Crick complemented palindrome
	 * @param DlinkedList handles the string
	 */
	public static void CheckDNAPairs(String str, CharDoubleEndedQueueImpl DlinkedList){
		int pairs = 0;//count the complementary pairs of the string
		if(str.trim().length() % 2 != 0)//a character is left over 
			System.out.print("But it's not a Watson-Crick complemented palindrome.");
		else{
			for(Node f = DlinkedList.firstNode, l = DlinkedList.lastNode; f.next != l.previous; f = f.next, l = l.previous) {
		
				//found, print and count the complementary pairs 
				if((f.data == 'A' && l.data == 'T') || (f.data == 'T' && l.data == 'A') || (f.data == 'C' && l.data == 'G') || (f.data == 'G' && l.data == 'C')) {
					System.out.println(f.data + "-" + l.data);
					pairs++;
				}else {
					
					// pair is not complementary
					System.out.print("But " + str + " is not a Watson-Crick complemented palindrome.");
					break;
				}
					
				// the last pair is checked terminate the loop
				if(f.next == l)
					break;
			}
		}
		//check if the string is palindrome
		if(str.trim().length() / 2 == pairs) System.out.print("The string " + str + " is a Watson-Crick complemented palindrome.");
	}
	
	public static void main(String[] args) {
		
		//create a queue to handle the string 
		CharDoubleEndedQueueImpl DlinkedList = new CharDoubleEndedQueueImpl();
		
		//type the new string 
		String str = input();
		
		//check if the string str is valid 
		boolean flag = Valid(str);
		
		//if its valid and not empty 
		if(flag && str.trim().length() != 0) {
			System.out.println("\nThe string is valid.");
			DlinkedList = Add(str,DlinkedList);//add the string to list
			CheckDNAPairs(str,DlinkedList);//check if its palindrome and print the pairs
		}
		
	}
	
}