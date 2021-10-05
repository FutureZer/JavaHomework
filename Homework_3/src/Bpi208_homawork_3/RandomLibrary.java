package Bpi208_homawork_3;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Random;

public class RandomLibrary {

    Random rand = new Random();
    BookGenerator generator = new BookGenerator();
    HashMap<Book, Integer> books = new HashMap<Book, Integer>();

    public RandomLibrary() {
        int capacity = rand.nextInt(11) + 10;
        for (int i = 0; i < capacity; ++i) {
            books.put(generator.generateRandomBook(), rand.nextInt(20) + 1);
        }
    }

    public Book findByName(String name) {
        for (Book book : books.keySet()) {
            if ((book.getName()).equalsIgnoreCase(name)) {
                return book;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format(
                "There is no such book \"{0}\" in library", name));
    }

    public String getBooksList() {
        StringBuilder report = new StringBuilder();
        for (Book key : books.keySet()) {
            report.append(MessageFormat.format("{0} - {1} books\n",
                    key.getName(), books.get(key)));
        }
        return report.toString();
    }

    public Book getBook(String name) {
        try {
            Book request = findByName(name);
            if (books.get(request) == 0) {
                throw new IllegalArgumentException(MessageFormat.format(
                        "All the books \"{0}\" were taken apart", name));
            } else {
                books.replace(request, books.get(request), books.get(request) - 1);
                return request;
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public void putBook(Book bookToReturn) {
        books.replace(bookToReturn, books.get(bookToReturn), books.get(bookToReturn) + 1);
    }
}
