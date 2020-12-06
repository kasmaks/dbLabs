package com.company;

import java.util.Scanner;

public class AuthorController {

    AuthorView authorView;
    Author author;

    AuthorController(AuthorView authorView, DBConnection connection) {
        this.authorView = authorView;
        author = new Author();
        author.setConnection(connection);
    }

    public void parseUserInput() {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("0")) {
            authorView.printMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1" : {
                    authorView.printAuthorsInfo(author.getAllAuthors());
                    break;
                }
                case "2" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    authorView.printAuthorInfo(author.getAuthorById(id));
                    break;
                }
                case "3" : {
                    System.out.println("Enter fullname");
                    String fullname = scanner.nextLine();
                    System.out.println("Enter country");
                    String country = scanner.nextLine();
                    System.out.println("Enter year of birth");
                    int yearOfBirth = getIntFromUser();

                    if(author.createAuthor(fullname, country, yearOfBirth) == -1)
                        System.out.println("Author wasn't created");
                    else
                        System.out.println("Author with id " + author.createAuthor(fullname, country, yearOfBirth) + " was created");
                    break;
                }
                case "4" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    System.out.println("Enter fullname");
                    String fullname = scanner.nextLine();
                    System.out.println("Enter country");
                    String country = scanner.nextLine();
                    System.out.println("Enter year of birth");
                    int yearOfBirth = getIntFromUser();
                    if(author.updateAuthor(id, fullname, country, yearOfBirth) == -1)
                        System.out.println("Author with id " + id + " wasn't updated");
                    else
                        System.out.println("Author with id " + id + " was updated");
                    break;
                }
                case "5" : {
                    System.out.println("Enter id");
                    int id = getIntFromUser();
                    if(author.deleteAuthor(id) == -1)
                        System.out.println("Author with id" + id + " wasn't deleted");
                    else
                        System.out.println("Author with id " + id + " was deleted");
                    break;
                }
                case "6" : {
                    System.out.println("Enter author's id ");
                    int authorId = getIntFromUser();
                    System.out.println("Enter id of the book");
                    int bookId = getIntFromUser();

                    if(author.addBookToAuthor(bookId, authorId) == -1)
                        System.out.println("Book wasn't added");
                    else
                        System.out.println("Book with id " + bookId + " was added");
                    break;
                }
                case "7" : {
                    System.out.println("Enter author's id ");
                    int authorId = getIntFromUser();
                    System.out.println("Enter id of the book");
                    int bookId = getIntFromUser();

                    if(author.deleteBookFromAuthor(bookId, authorId) == -1)
                        System.out.println("Book wasn't deleted");
                    else
                        System.out.println("Book with id " + bookId + " was deleted");
                    break;
                }
                case "8" : {
                    System.out.println("Enter author's id ");
                    int authorId = getIntFromUser();
                    if(author.deleteAllBooksFromAuthor(authorId) == -1)
                        System.out.println("Books weren't deleted");
                    else
                        System.out.println("Book were deleted");
                    break;
                }
                case "9" : {
                    System.out.println("Enter number ");
                    int number = getIntFromUser();
                    if(number < 0) number = 0;
                    if(author.createRandomAuthors(number) == 1)
                        System.out.println(number + " authors were created");
                    else
                        System.out.println("Authors were not created");
                    break;
                }
                case "10" : {
                    System.out.println("Enter fullname");
                    String fullname = scanner.nextLine();
                    System.out.println("Enter country");
                    String country = scanner.nextLine();
                    System.out.println("Enter interval for year of birth");
                    System.out.println("Start");
                    int yearOfBirthStart = getIntFromUser();
                    System.out.println("End");
                    int yearOfBirthEnd = getIntFromUser();
                    authorView.printAuthorsInfo(author.getFilteredAuthors(fullname, country, yearOfBirthStart, yearOfBirthEnd));
                    break;
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
