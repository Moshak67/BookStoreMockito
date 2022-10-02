import java.util.List;

public interface ReadItemCommand {

	List<Book> readAll();

	Book getItem(Object isbn);

}
