package com.company;

import java.util.Scanner;

public class BookController {

    BookView bookView;
    Book book;

    BookController(BookView bookView, DBConnection connection) {
        this.bookView = bookView;
        book = new Book();
        book.setConnection(connection);
    }

    public void parseUserInput() {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("0")) {
            bookView.printMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1" : {
                    bookView.printBooksInfo(book.getAllBooks());
                    break;
                }
                case "2" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    bookView.printBookInfo(book.getBookById(id));
                    break;
                }
                case "3" : {
                    System.out.println("Enter title");
                    String title = scanner.nextLine();
                    System.out.println("Enter amount");
                    int amount = getIntFromUser();

                    int id = book.createBook(title, amount > 0, amount);
                    if(id == -1)
                        System.out.println("Book wasn't created");
                    else
                        System.out.println("Book with id " + id + " was created");
                    break;
                }
                case "4" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    System.out.println("Enter title");
                    String title = scanner.nextLine();
                    System.out.println("Enter amount");
                    int amount = getIntFromUser();
                    if(book.updateBook(id, title, amount > 0, amount) == -1)
                        System.out.println("Book with id" + id + " wasn't deleted");
                    else
                        System.out.println("Book with id " + id + " was updated");
                    break;
                }
                case "5" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    if(book.deleteBook(id) == -1)
                        System.out.println("Book with id" + id + " wasn't deleted");
                    else
                        System.out.println("Book with id " + id + " was deleted");
                    break;
                }
                case "6" : {
                    System.out.println("Enter number ");
                    int number = getIntFromUser();
                    if(number < 0) number = 0;
                    if(book.createRandomBooks(number) == 1)
                        System.out.println(number + " books were created");
                    else
                        System.out.println("Books were not created");
                }
                default:
                    break;
            }
        }
    }

    public int getIntFromUser() {
        Scanner scanner = new Scanner(System.in);

        while(!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("That's not an integer, please enter again:");
        }
        int number = scanner.nextInt();

        return number;
    }
}
