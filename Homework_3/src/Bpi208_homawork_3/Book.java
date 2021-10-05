package Bpi208_homawork_3;

import java.text.MessageFormat;

public class Book {

    String name;
    String author;
    String description;
    int releaseDate;

    public String getName() {
        return name;
    }

    public Book(String name, String author, String description, int release) {
        this.name = name;
        this.author = author;
        this.description = description;
        releaseDate = release;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Book {0} written by {1} and released in {2}\nIt is a {3}",
                name, author, releaseDate, description);
    }
}
