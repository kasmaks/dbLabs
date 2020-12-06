package com.company;

import java.util.Scanner;

public class ReaderController {

    ReaderView readerView;
    Reader reader;

    ReaderController(ReaderView readerView, DBConnection connection) {
        this.readerView = readerView;
        reader = new Reader();
        reader.setConnection(connection);
    }

    public void parseUserInput() {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("0")) {
            readerView.printMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1" : {
                    readerView.printReadersInfo(reader.getAllReaders());
                    break;
                }
                case "2" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    readerView.printReaderInfo(reader.getReaderById(id));
                    break;
                }
                case "3" : {
                    System.out.println("Enter fullname");
                    String fullname = scanner.nextLine();
                    System.out.println("Enter phone number");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter amount of books");
                    int amountOfBooks = getIntFromUser();

                    int id = reader.createReader(fullname, phoneNumber,amountOfBooks);
                    if(id == -1)
                        System.out.println("Reader wasn't created");
                    else
                        System.out.println("Reader with id " + id + " was created");
                    break;
                }
                case "4" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    System.out.println("Enter fullname");
                    String fullname = scanner.nextLine();
                    System.out.println("Enter phone number");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter amount of books");
                    int amountOfBooks = getIntFromUser();

                    if(reader.updateReader(id, fullname, phoneNumber, amountOfBooks) == -1)
                        System.out.println("Reader with id" + id + " wasn't deleted");
                    else
                        System.out.println("Reader with id " + id + " was updated");
                    break;
                }
                case "5" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    if(reader.deleteReader(id) == -1)
                        System.out.println("Reader with id" + id + " wasn't deleted");
                    else
                        System.out.println("Reader with id " + id + " was deleted");
                    break;
                }
                case "6" : {
                    System.out.println("Enter reader's id ");
                    int authorId = getIntFromUser();
                    System.out.println("Enter id of the book");
                    int bookId = getIntFromUser();

                    if(reader.addBookToReader(bookId, authorId) == -1)
                        System.out.println("Book wasn't added");
                    else
                        System.out.println("Book with id " + bookId + " was added");
                    break;
                }
                case "7" : {
                    System.out.println("Enter reader's id ");
                    int authorId = getIntFromUser();
                    System.out.println("Enter id of the book");
                    int bookId = getIntFromUser();

                    if(reader.deleteBookFromReader(bookId, authorId) == -1)
                        System.out.println("Book wasn't deleted");
                    else
                        System.out.println("Book with id " + bookId + " was deleted");
                    break;
                }
                case "8" : {
                    System.out.println("Enter reader's id ");
                    int readerId = getIntFromUser();
                    if(reader.deleteAllBooksFromReader(readerId) == -1)
                        System.out.println("Books weren't deleted");
                    else
                        System.out.println("Book were deleted");
                    break;
                }
                case "9" : {
                    System.out.println("Enter number ");
                    int number = getIntFromUser();
                    if(number < 0) number = 0;
                    if(reader.createRandomReaders(number) == 1)
                        System.out.println(number + " readers were created");
                    else
                        System.out.println("Readers were not created");
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
