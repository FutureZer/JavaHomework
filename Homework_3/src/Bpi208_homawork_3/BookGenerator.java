package Bpi208_homawork_3;

import java.util.Random;

public class BookGenerator {
    Random rand = new Random();

    String[] nameTemplate = {
            "Alex", "Noah", "Michael",
            "Liam", "Mason", "Jacob",
            "William", "Ethan", "Oscar",
            "Victoria", "Veronica", "Juliet",
            "Jessica", "Joan", "Agnes",
            "Katherine", "Caroline", "Zara"
    };

    String[] surnameTemplate = {
            "Smith", "Johnson", "Williams",
            "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez",
            "Martinez", "Hernandez", "Lopez",
            "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore"
    };

    String[] words = {
            "slippery", "chapter", "courtesy",
            "butterfly", "agony", "compartment",
            "deport", "confuse", "concept",
            "shadow", "village", "holiday",
            "leftovers", "fixture", "fraction",
            "biscuit", "density", "monk",
            "lovers", "destrucrion", "darkness",
            "void", "wax", "translation"
    };

    String[] descriptions = {
            "detective and drama in criminal town", "story about love and despair",
            "space sience fiction", "autobiography",
            "comedy about frienship", "romance with tragic end",
            "horro about family curse", "history of american presidents"
    };

    String[] preposotions = { "and", "or", "to", "of", "in", "at", "on" };

    public Book generateRandomBook() {
        String author = nameTemplate[rand.nextInt(nameTemplate.length)] + " " +
                surnameTemplate[rand.nextInt(surnameTemplate.length)];
        String bookName;
        if (rand.nextInt(2) == 0) {
            bookName = words[rand.nextInt(words.length)];
        } else {
            bookName = words[rand.nextInt(words.length)] + " " +
                    preposotions[rand.nextInt(preposotions.length)] + " " +
                    words[rand.nextInt(words.length)];
        }
        bookName = Character.toUpperCase(bookName.charAt(0)) + bookName.substring(1);
        String description = descriptions[rand.nextInt(descriptions.length)];
        int releaseDate = rand.nextInt(122) + 1900;
        return new Book(bookName, author, description, releaseDate);
    }
}
