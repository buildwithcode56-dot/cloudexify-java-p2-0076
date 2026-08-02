package com.mycompany.librarymanagementsystem;
import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private static int idCounter = 1001;

    private int bookId;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private int quantity;


    public Book(String title, String author, String category, String isbn, int quantity) {

        this.bookId = idCounter++;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.quantity = quantity;
    }
    public int getBookId() 
    {
        return bookId;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor() 
    {
        return author;
    }
    public String getCategory()
    {
        return category;
    }
    public String getIsbn() 
    {
        return isbn;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }
    public void setIsbn(String isbn) 
    {
        this.isbn = isbn;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public boolean issueBook()
    {
        if(quantity > 0) 
        {
            quantity--;
            return true;
        }
        return false;
    }
    public void returnBook()
    {
        quantity++;
    }

    public String toString() 
    {
        return bookId + " | " + title + " | " + author + " | "+ category + " | " + quantity;
    }
}
