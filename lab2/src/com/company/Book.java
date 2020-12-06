package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Book extends Model{

    public int id;
    public String title;
    public boolean isAvailable;
    public int amount;

    public int createBook(String title, boolean isAvailable, int amount) {
        try {
            String query = "Insert into public.\"Books\"(title, is_available, amount) Values(?, ?, ?) RETURNING id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, title);
            statement.setBoolean(2, isAvailable);
            statement.setInt(3, amount);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String query = "Select * from public.\"Books\" ";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = new Book();
                book.id = resultSet.getInt("Id");
                book.title = resultSet.getString("title");
                book.isAvailable = resultSet.getBoolean("is_available");
                book.amount = resultSet.getInt("amount");
                books.add(book);
            }


        } catch (SQLException throwables) {
            books = null;
        }

        if(books != null && books.size() == 0)
            books = null;
        return books;
    }

    public Book getBookById(int id) {
        Book book;
        try {
            String query = "Select * from public.\"Books\" where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            book = new Book();
            book.id = resultSet.getInt("Id");
            book.title = resultSet.getString("title");
            book.isAvailable = resultSet.getBoolean("is_available");
            book.amount = resultSet.getInt("amount");

        } catch (SQLException throwables) {
            book = null;
        }
        return book;
    }

    public int updateBook(int id, String newTitle, boolean newIsAvailable, int newAmount) {
        try {
            String query = "Update public.\"Books\" " +
                    "Set title = ?, " +
                    "is_available = ?, " +
                    "amount = ? " +
                    "where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, newTitle);
            statement.setBoolean(2, newIsAvailable);
            statement.setInt(3, newAmount);
            statement.setInt(4, id);
            statement.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteBook(int id) {
        try {
            String query = "Delete from public.\"Books\" where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int createRandomBooks(int number) {
        try {
            String query = "do $$\n" +
                    "            declare\n" +
                    "            counter integer := 0;\n" +
                    "            begin\n" +
                    "                while counter < " + number + "  loop\n" +
                    "                INSERT INTO public.\"Books\" (title, is_available, amount)\n " +
                    "                SELECT\n" +
                    "                    chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int),\n" +
                    "                    true,\n" +
                    "                    trunc(random() * 100 + 1)\n" +
                    "                FROM generate_series(1, 1);\n" +
                    "                counter := counter + 1;\n" +
                    "            end loop;\n" +
                    "            end $$;";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.executeUpdate();
            return 1;

        } catch (SQLException throwables) {
            return -1;
        }
    }

}
