package com.company;

import java.util.ArrayList;

public class AuthorView {

    public void printMenu() {
        System.out.println("Welcome to the Author menu");
        System.out.println("1 - Get all the authors");
        System.out.println("2 - Get author by their id");
        System.out.println("3 - Create new author");
        System.out.println("4 - Update author by their id");
        System.out.println("5 - Delete author by their id");
        System.out.println("6 - Add new book to an author");
        System.out.println("7 - Delete author's book");
        System.out.println("8 - Delete all author's books");
        System.out.println("9 - Create random authors");
        System.out.println("10 - Get filtered authors");
        System.out.println("0 - Exit");
    }

    public void printAuthorInfo(Author author) {
        if(author == null)
            System.out.println("Author does not exist");
        else {
            System.out.println("Author info : ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
            System.out.println("Id : " + author.id);
            System.out.println("Fullname : " + author.fullname);
            System.out.println("Country : " + author.country);
            System.out.println("Year of birth : " + author.yearOfBirth);
            if(author.book_id != -1) {
                System.out.println("Id of the author's book : " + author.book_id);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        }
    }

    public void printAuthorsInfo(ArrayList<Author> authors) {
        if(authors == null) {
            System.out.println("The Authors table is empty");
        }
        else {
            for(int i = 0; i < authors.size(); i++) {
                printAuthorInfo(authors.get(i));
            }
        }
    }


}
