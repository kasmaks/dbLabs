package com.company;

import java.util.Scanner;

public class Controller {

    DBConnection connection;
    View view;
    AuthorController authorController;
    BookController bookController;
    ReaderController readerController;

    Controller(View view) {
        this.view = view;
        connection = new DBConnection();
        connection.connect();

        authorController = new AuthorController(view.authorView, connection);
        bookController = new BookController(view.bookView, connection);
        readerController = new ReaderController(view.readerView, connection);
    }

    public void parseUserInput() {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("0")) {
            view.printMainMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1" : {
                    authorController.parseUserInput();
                    break;
                }
                case "2" : {
                    bookController.parseUserInput();
                    break;
                }
                case "3" : {
                    readerController.parseUserInput();
                    break;
                }
                default : {
                    break;
                }
            }
        }
    }
}
