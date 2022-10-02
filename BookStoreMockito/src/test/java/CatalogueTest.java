import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CatalogueTest {
	Catalogue catalogue;

	@Mock
	private ReadItemCommand mockReadItem;

	@Mock
	private WriteItemCommand mockWriteItem;

	@BeforeEach
	void setUp() throws Exception {
		catalogue = new Catalogue(mockReadItem, mockWriteItem);

	}

	@Test
	public void getAllBooks_ReturnsEmptyBookList_IfNoBooksAreInTheCatalogue() {

		// Arrange - create Catalogue object
		List<Book> books = catalogue.getAllBook();

		// Act - call method

		// Assert - the size of book list should be equal to zero
		assertEquals(0, books.size());

	}

	@Test
	public void getAllBooks_CallsReadAllMethodOfReadItemCommand_WhenCalled() {

		catalogue.getAllBook();
		verify(mockReadItem, times(1)).readAll();

	}

	@Test
	public void getAllBooks_ReturnsListOfBooksItReceivesFromReadAllMethodOfReadItemCommand_WhenCalled() {

		List<Book> books = new ArrayList<Book>();
		books.add(new Book());
		books.add(new Book());

		when(mockReadItem.readAll()).thenReturn(books);

		List<Book> foundBooks = catalogue.getAllBook();

		assertEquals(books, foundBooks);

	}

	@Test
	public void addBook_CallsInsertItemMethodOfWriteItemCommand_WhenCalled() {

		Book book = new Book();
		catalogue.addBook(book);
		verify(mockWriteItem, times(1)).insertItem(book);

	}

	@Test
	public void addBook_CanTakeMultipleBooks_InsertItemShouldBeCAlled_WhenCalled() {
		Book book = new Book();
		List<Book> books = new ArrayList<>();
		books.add(book);
		books.add(book);
		books.add(book);
		
		catalogue.addBooks(books);
		verify(mockWriteItem,times(3)).insertItem(book);
	}
	@Test
	public void getBook_ReturnsBooks_ItRecievesFrom_GetItem() {
		Book book = new Book();
		String isbn = "1234";
		
		when(mockReadItem.getItem(isbn)).thenReturn(book);
		
		Book foundBook = catalogue.getBook(isbn);
		verify(mockReadItem, times(1)).getItem(isbn);
		assertEquals(book, foundBook);
		
	}

}
