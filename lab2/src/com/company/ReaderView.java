package com.company;

import java.util.ArrayList;

public class ReaderView {

    public void printMenu() {
        System.out.println("Welcome to the Reader menu");
        System.out.println("1 - Get all the readers");
        System.out.println("2 - Get reader by their id");
        System.out.println("3 - Create new reader");
        System.out.println("4 - Update reader by their id");
        System.out.println("5 - Delete reader by their id");
        System.out.println("6 - Add new book to an reader");
        System.out.println("7 - Delete reader's book");
        System.out.println("8 - Delete all reader's books");
        System.out.println("9 - Create random readers");
        System.out.println("0 - Exit");
    }

    public void printReaderInfo(Reader reader) {
        if(reader == null)
            System.out.println("Reader does not exist");
        else {
            System.out.println("Reader info : ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
            System.out.println("Id : " + reader.id);
            System.out.println("Fullname : " + reader.fullname);
            System.out.println("Phone number : " + reader.phoneNumber);
            System.out.println("Amount of books : " + reader.amountOfBooks);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        }
    }

    public void printReadersInfo(ArrayList<Reader> readers) {
        if(readers == null)
            System.out.println("The Readers table is empty");
        else {
            for(int i = 0; i < readers.size(); i++) {
                printReaderInfo(readers.get(i));
            }
        }
    }


}
