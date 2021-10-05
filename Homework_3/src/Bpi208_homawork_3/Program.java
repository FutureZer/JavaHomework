package Bpi208_homawork_3;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Program {

    static HashMap<Book, Integer> takenBooks = new HashMap<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        RandomLibrary lib = new RandomLibrary();
        System.out.println("Enter \"/help\" to see command list");
        String command;
        for(;;) {
            command = input.next();
            switch (command) {
                case "/get":
                    String name = input.nextLine().substring(1);
                    try {
                        addToTaken(lib.getBook(name));
                        System.out.print(MessageFormat.format(
                                "You have sucessfully taken \"{0}\"\n", name));
                    } catch (IllegalArgumentException ex) {
                        System.out.print(ex.getMessage() + "\n");
                    }
                    break;
                case "/list":
                    System.out.print(getBooksList());
                    break;
                case "/put":
                    String bookToReturn = input.nextLine().substring(1);
                    try {
                        lib.putBook(findByName(bookToReturn));
                        removeFromTaken(findByName(bookToReturn));
                        System.out.print(MessageFormat.format(
                                "You have sucessfully returned \"{0}\"\n", bookToReturn));
                    } catch (IllegalArgumentException ex) {
                        System.out.print(ex.getMessage() + "\n");
                    }
                    break;
                case "/all":
                    System.out.print(lib.getBooksList());
                    break;
                case "/info":
                    String book = input.nextLine().substring(1);
                    System.out.println(findByName(book));
                    break;
                case "/help":
                    System.out.print(getHelp());
                    break;
                case "/exit":
                    return;
                default:
                    System.out.print("Incorrect command\n");
                    break;
            }
        }
    }

    public static Book findByName(String name) {
        for (Book book : takenBooks.keySet()) {
            if ((book.getName()).equalsIgnoreCase(name)) {
                return book;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format(
                "You haven't taken book \"{0}\" from library", name));
    }

    public static String getBooksList() {
        StringBuilder report = new StringBuilder();
        if (takenBooks.size() == 0) {
            return "You haven't taken any book yet\n";
        }
        for (Book key : takenBooks.keySet()) {
            report.append(MessageFormat.format("{0} - {1} books\n",
                    key.getName(), takenBooks.get(key)));
        }
        return report.toString();
    }

    public static void addToTaken(Book bookToAdd) {
        if (takenBooks.containsKey(bookToAdd)) {
            takenBooks.replace(bookToAdd, takenBooks.get(bookToAdd), takenBooks.get(bookToAdd) + 1);
        } else {
            takenBooks.put(bookToAdd, 1);
        }
    }

    public static void removeFromTaken(Book bookToRemove) {
        if (takenBooks.get(bookToRemove) == 1) {
            takenBooks.remove(bookToRemove);
        } else {
            takenBooks.replace(bookToRemove, takenBooks.get(bookToRemove), takenBooks.get(bookToRemove) - 1);
        }
    }

    public static String getHelp() {
        return "/get <book_name> - takes books from library\n" +
                "/put <book_name> - return book to library\n" +
                "/list - display all taken books\n" +
                "/all - display all books from library\n" +
                "/info <book_name> - display all information about book, that you have taken\n" +
                "/exit - leave program\n";
    }
}
