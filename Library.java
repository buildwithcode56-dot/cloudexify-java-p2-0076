package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;
public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() 
    {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() 
    {
        return books;
    }

    public ArrayList<Member> getMembers() 
    {
        return members;
    }
    public void addBook(Book book) 
    {
        books.add(book);
    }
    public void removeBook(int id)
    {
        Book book = searchBook(id);
        if(book != null) 
        {
            books.remove(book);
        }
    }
    public boolean removeMember(int id) 
    {
    Member member = searchMember(id);
    if(member != null) 
    {
        if(member.getBorrowedBooks().isEmpty())
        {
            members.remove(member);
            return true;
        }
    }
    return false;
}
    public Book searchBook(int id)
    {
        for(Book b : books)
        {
            if(b.getBookId() == id)
            {
                return b;
            }
        }
        return null;
    }

    public ArrayList<Book> searchBook(String text) 
    {
        ArrayList<Book> result = new ArrayList<>();
        for(Book b : books)
        {
            if(b.getTitle() .toLowerCase().contains(text.toLowerCase())) 
            {
                result.add(b);
            }
        }
        return result;
    }
    public void addMember(Member member)
    {
        members.add(member);
    }

    public Member searchMember(int id)
    {
        for(Member m : members)
        {
            if(m.getMemberId() == id)
            {
                return m;
            }
        }
        return null;
    }
    
    public boolean issueBook(int bookId, int memberId) 
    {
        Book book = searchBook(bookId);
        Member member = searchMember(memberId);

        if(book != null && member != null)
        {
            if(book.issueBook()) 
            {
                member.borrowBook(bookId);
                return true;
            }
        }
        return false;
    }
    public boolean returnBook(int bookId, int memberId)
    {
        Book book = searchBook(bookId);
        Member member = searchMember(memberId);

        if(book != null && member != null) 
        {
            if(member.getBorrowedBooks().contains(bookId))
            {
        
              member.returnBook(bookId);
              book.returnBook();
              return true;
            }
        }
        return false;
    }
}
