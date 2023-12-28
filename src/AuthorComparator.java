import java.util.Comparator;

@FunctionalInterface
public interface AuthorComparator extends Comparator<Book> {

    //абстрактный метод для сравнения
    @Override
    int compare(Book b1, Book b2);

    //сортировка в алфавитном порядке
    static Comparator<Book> asc() {
        return Comparator.comparing(Book::getAuthor);
    }

    //сортировка в обратном алфавитном порядке
     static Comparator<Book> desc() {
        return (b1, b2) -> b2.getAuthor().compareTo(b1.getAuthor());
     }
}
