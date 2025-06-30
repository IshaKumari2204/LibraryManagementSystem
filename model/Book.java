package model;

public class Book {
    //attributes representing a book
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(String id, String title, String author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + isAvailable;
    }

    //It creates a Book object from a line in the file.
    public static Book fromString(String line) {
        String[] parts = line.split(","); 
        return new Book(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
    }
}
