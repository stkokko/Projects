/**
 * class to represent a book
 */
class BookInfo{

	/** isbn code of book */
	protected int ISBN;
	
	/** number of copies */
	protected int Copies;

	/** Three-argument constructor */
	public BookInfo(int isbn,int copies){
		ISBN = isbn;
		Copies = copies;
	}
	
	/** method to get isbn code of book */
	public int getISBN(){
		return ISBN;
	}

	/** method to get copies of book */
	public int getCopies(){
		return Copies;
	}

}