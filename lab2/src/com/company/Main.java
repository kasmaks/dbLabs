package com.company;

public class Main {

    // TODO: 22.11.2020 all sout`s in Views, not in Controllers

    public static void main(String[] args) {

        AuthorView authorView = new AuthorView();
        BookView bookView = new BookView();
        ReaderView readerView = new ReaderView();

        View view = new View(authorView, bookView, readerView);
        Controller controller = new Controller(view);
        controller.parseUserInput();


    }
}
