package com.company;

import java.util.ArrayList;

public class BookView {

    public void printMenu() {
        System.out.println("Welcome to the Book menu");
        System.out.println("1 - Get all the books");
        System.out.println("2 - Get book by their id");
        System.out.println("3 - Create new book");
        System.out.println("4 - Update book by their id");
        System.out.println("5 - Delete book by their id");
        System.out.println("6 - Create random books");
        System.out.println("0 - Exit");
    }

    public void printBookInfo(Book book) {
        if(book == null)
            System.out.println("Book does not exist");
        else {
            System.out.println("Book info : ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
            System.out.println("Id : " + book.id);
            System.out.println("Title : " + book.title);
            if(book.isAvailable) {
                System.out.println("The book is available");
            }
            else
                System.out.println("The book is not available");
            System.out.println("Amount : " + book.amount);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        }
    }

    public void printBooksInfo(ArrayList<Book> books) {
        if(books == null) {
            System.out.println("The Books table is empty");
        }
        else {
            for(int i = 0; i < books.size(); i++) {
                printBookInfo(books.get(i));
            }
        }
    }


}
