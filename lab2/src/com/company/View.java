package com.company;

public class View {

    public AuthorView authorView;
    public BookView bookView;
    public ReaderView readerView;

    View(AuthorView authorView, BookView bookView, ReaderView readerView) {
        this.authorView = authorView;
        this.bookView = bookView;
        this.readerView = readerView;
    }

    public void printMainMenu() {
        System.out.println("Welcome to the main menu");
        System.out.println("1 - Author menu");
        System.out.println("2 - Book menu");
        System.out.println("3 - Reader menu");
        System.out.println("0 - Exit");
    }
}