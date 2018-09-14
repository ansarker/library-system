
package main.dashboard;

import main.DatabaseConnection;

public class Book {
    private int BookID;
    private String Title;
    private String Author;
    private String Category;
    private String Publisher;
    private String ISBN;
    private String Edition;
    private String ImagePath;
    private String Language;

    DatabaseConnection dbc = new DatabaseConnection();
    
    public Book(int BookID, String Title, String Author, String Category, String Publisher, String ISBN, String Edition, String ImagePath, String Language) {
        this.BookID = BookID;
        this.Title = Title;
        this.Author = Author;
        this.Category = Category;
        this.Publisher = Publisher;
        this.ISBN = ISBN;
        this.Edition = Edition;
        this.ImagePath = ImagePath;
        this.Language = Language;
    }

    public int getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCategory() {
        return Category;
    }

    public String getPublisher() {
        return Publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getEdition() {
        return Edition;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public String getLanguage() {
        return Language;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setEdition(String Edition) {
        this.Edition = Edition;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }
}
