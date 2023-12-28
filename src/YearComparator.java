import java.util.Comparator;

@FunctionalInterface
public interface YearComparator extends Comparator<Book> {

    //абстрактный метод для сравнения
    @Override
    int compare(Book b1, Book b2);

    //сортировка по возрастанию
    static Comparator<Book> asc() {
        return Comparator.comparingInt(Book::getPublishedYear);
    }

    //сортировка по убыванию
    static Comparator<Book> desc() {
        return (b1, b2) -> Integer.compare(b2.getPublishedYear(), b1.getPublishedYear());
    }
}
