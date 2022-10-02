import java.util.List;

public class Catalogue {
	private ReadItemCommand readItemCommand;
	private WriteItemCommand writeItemCommand;

	public Catalogue(ReadItemCommand readItemCommand, WriteItemCommand writeItemCommand) {
		super();
		this.readItemCommand = readItemCommand;
		this.writeItemCommand = writeItemCommand;
	}

	public List<Book> getAllBook() {
		
		return readItemCommand.readAll();
	}

	public void addBook(Book book) {
		writeItemCommand.insertItem(book);
		
	}

	public void addBooks(List<Book> books) {
		for (Book book : books) {
			writeItemCommand.insertItem(book);
		}
	}

	public Book getBook(String isbn) {
		// TODO Auto-generated method stub
		return readItemCommand.getItem(isbn);
	}
}
