import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library implements Iterable<Book>{
    private List<Book> books = new ArrayList<>();

    //добавление книги в список
    public void addBook(Book b) {
        books.add(b);
    }

    //получение списка книг
    public List<Book> getBooks() {
        return books;
    }

    //сортировка книг
    public void sortBooks(Comparator<Book> comparator) {
        Collections.sort(books, comparator);
    }

    //удаление книги из списка
    public void removeBook(Book b) {
        books.remove(b);
    }

    public List<Book> filterBooks(Predicate<Book> predicate) {
        return books.stream().filter(predicate).collect(Collectors.toList());
    }

    //итератор для перебора книг в списке
    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }
}
