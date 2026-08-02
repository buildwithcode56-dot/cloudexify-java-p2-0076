package com.mycompany.librarymanagementsystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable {


    private static final long serialVersionUID = 1L;
    private static int memberCounter = 1;
    private int memberId;
    private String name;
    private String phone;

    private ArrayList<Integer> borrowedBooks;
    public Member(String name, String phone)
    {

        this.memberId = memberCounter++;
        this.name = name;
        this.phone = phone;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() 
    {
        return memberId;
    }
    public String getName() 
    {
        return name;
    }
    public String getPhone() 
    {
        return phone;
    }

    public ArrayList<Integer> getBorrowedBooks() 
    {
        return borrowedBooks;
    }
    public void borrowBook(int bookId) {

        borrowedBooks.add(bookId);
    }

    public void returnBook(int bookId) 
    {
        borrowedBooks.remove(Integer.valueOf(bookId));
    }
    
    public String toString() 
    {
        return memberId + " | " + name + " | "+ phone;
    }
}
