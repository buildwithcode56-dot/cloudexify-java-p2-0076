package com.mycompany.librarymanagementsystem;
import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String BOOK_FILE = "books.dat";
    private static final String MEMBER_FILE = "members.dat";
    public static void saveBooks(ArrayList<Book> books) 
    {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(BOOK_FILE)))
        {
            out.writeObject(books);
        } 
        catch(IOException e) 
        {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
    
    public static ArrayList<Book> loadBooks() 
    {
        ArrayList<Book> books = new ArrayList<>();
        try(ObjectInputStream in =new ObjectInputStream(new FileInputStream(BOOK_FILE)))
        {
            books = (ArrayList<Book>) in.readObject();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        } 
        catch(IOException | ClassNotFoundException e) 
        {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    public static void saveMembers(ArrayList<Member> members) 
    {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(MEMBER_FILE)))
        {
            out.writeObject(members);
        } 
        catch(IOException e) 
        {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public static ArrayList<Member> loadMembers()
    {
        ArrayList<Member> members = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream( new FileInputStream(MEMBER_FILE)))
        {
            members = (ArrayList<Member>) in.readObject();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException | ClassNotFoundException e) {


            System.out.println("Error loading members: " + e.getMessage());

        }
        return members;
    }
}
