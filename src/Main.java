import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //создание библиотеки
        Library library = new Library();
        initializeLibrary(library);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println('\n');
            System.out.println("Выберите действие:");
            System.out.println("1. Показать все книги");
            System.out.println("2. Фильтровать книги");
            System.out.println("3. Выход");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        showAllBooks(library); //показать все книги
                        break;
                    case 2:
                        filterBooks(library, scanner); //фильтровать книги
                        break;
                    case 3:
                        System.out.println("Выход из программы.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Такой опции нет");
                }
            } else {
                System.out.println("Ошибка ввода. Введите корректное целочисленное значение");
                scanner.next(); // очистка буфера после некорректного ввода
            }
        }
    }
        private static void initializeLibrary (Library library){
            //добавляем книги
            library.addBook(new Book("Убийство Роджера Экройда", "Агата Кристи", 1926));
            library.addBook(new Book("Путешествие к центру Земли", "Жюль Верн", 1864));
            library.addBook(new Book("Происхождение", "Дэн Браун", 2017));
            library.addBook(new Book("Цель", "Элияху Голдратт", 1986));
        }

        //для вывода списка книг
        private static void showAllBooks (Library library){
            System.out.println("Все книги в библиотеке: ");
            for (Book b : library) {
                System.out.println(b);
            }
        }

        private static void filterBooks (Library library, Scanner scanner){
            System.out.println('\n');
            System.out.println("Выберите критерий фильтрации:");
            System.out.println("1. По автору");
            System.out.println("2. По году издания");

            int filterChoice;

            try {
                filterChoice = scanner.nextInt();
                scanner.nextLine();  // Очистка буфера после ввода числа
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите корректное целочисленное значение");
                scanner.nextLine();  // Очистка буфера после ошибочного ввода
                return;  // Прерывание выполнения метода
            }

            switch (filterChoice) {
                case 1:
                    System.out.println("Введите автора для фильтрации и сортировки: ");
                    String authorFilter = scanner.nextLine();
                    filterAndSortByAuthor(library, authorFilter);
                    break;
                case 2:
                    System.out.println("Введите год для фильтрации и сортировки: ");
                    int yearFilter;

                    try {
                        yearFilter = scanner.nextInt();
                        scanner.nextLine();  // Очистка буфера после ввода числа
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка ввода. Введите корректное целочисленное значение");
                        scanner.nextLine();  // Очистка буфера после ошибочного ввода
                        return;  // Прерывание выполнения метода
                    }
                    filterAndSortByYear(library, yearFilter);
                    break;

                default:
                    System.out.println("Такой опции нет");
            }
        }

        private static void filterAndSortByAuthor (Library library, String authorFilter){
            //Проверка содержится ли введенная строка в имени автора(регистронезависимо)
            List<Book> filteredByAuthor = library.filterBooks(book -> book.getAuthor().toLowerCase().contains(authorFilter.toLowerCase()));

            if (filteredByAuthor.isEmpty()) {
                System.out.println("Книги по автору '" + authorFilter + "' не найдены.");
            } else {
                filteredByAuthor.sort(AuthorComparator.asc());//сортировка от А до Я
                showFilteredBooks(filteredByAuthor);//вывод списка отфильтрованных книг
            }
        }

        private static void filterAndSortByYear (Library library,int yearFilter){
            //проверка равен ли год издания заданному
            List<Book> filteredByYear = library.filterBooks(book -> book.getPublishedYear() == yearFilter);

            if (filteredByYear.isEmpty()) {
                System.out.println("Книги по году издания '" + yearFilter + "' не найдены.");
            } else {
                filteredByYear.sort(YearComparator.asc());//сортировка по возрастанию
                showFilteredBooks(filteredByYear);//вывод списка отфильтрованных книг
            }
        }

        private static void showFilteredBooks (List < Book > filteredBooks) {
            System.out.println("Отфильтрованные и отсортированные книги:");
            for (Book book : filteredBooks) {
                System.out.println(book);
            }
        }

/*
        library.sortBooks(Book::compareTo);
        System.out.println("Сортировка по названию: ");
        for (Book b: library) {
            System.out.println(b);
        }

        library.sortBooks(AuthorComparator.asc());
        System.out.println("Сортировка по автору(от А до Я): ");
        for (Book b: library) {
            System.out.println(b);
        }

        library.sortBooks(AuthorComparator.desc());
        System.out.println("Сортировка по автору(от Я до А): ");
        for (Book b: library) {
            System.out.println(b);
        }

        library.sortBooks(YearComparator.asc());
        System.out.println("Сортировка по году(по возрастанию): ");
        for (Book b: library) {
            System.out.println(b);
        }

        library.sortBooks(YearComparator.desc());
        System.out.println("Сортировка по году(по убыванию): ");
        for (Book b: library) {
            System.out.println(b);
        }
*/
    }
