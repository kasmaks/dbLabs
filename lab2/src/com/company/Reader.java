package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Reader extends Model{

    public int id;
    public String fullname;
    public String phoneNumber;
    public int amountOfBooks;

    public int createReader(String fullname, String phoneNumber, int amountOfBooks) {
        try {
            String query = "Insert into public.\"Readers\"(fullname, phone_number, amount_of_books) Values(?, ?, ?) RETURNING id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, fullname);
            statement.setString(2, phoneNumber);
            statement.setInt(3, amountOfBooks);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public ArrayList<Reader> getAllReaders() {
        ArrayList<Reader> readers = new ArrayList<>();
        try {
            String query = "Select * from public.\"Readers\" ";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Reader reader = new Reader();
                reader.id = resultSet.getInt("Id");
                reader.fullname = resultSet.getString("fullname");
                reader.phoneNumber = resultSet.getString("phone_number");
                reader.amountOfBooks = resultSet.getInt("amount_of_books");
                readers.add(reader);
            }


        } catch (SQLException throwables) {
            readers = null;
        }

        if(readers != null && readers.size() == 0)
            readers = null;
        return readers;
    }

    public Reader getReaderById(int id) {
        Reader reader;
        try {
            String query = "Select * from public.\"Readers\" where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            reader = new Reader();
            reader.id = resultSet.getInt("Id");
            reader.fullname = resultSet.getString("fullname");
            reader.phoneNumber = resultSet.getString("phone_number");
            reader.amountOfBooks = resultSet.getInt("amount_of_books");

        } catch (SQLException throwables) {
            reader = null;
        }
        return reader;
    }

    public int updateReader(int id, String newFullname, String newPhoneNumber, int newAmountOfBooks) {
        try {
            String query = "Update public.\"Readers\" " +
                    "Set fullname = ?, " +
                    "phone_number = ?, " +
                    "library_card_amount_of_books = ? " +
                    "where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, newFullname);
            statement.setString(2, newPhoneNumber);
            statement.setInt(3, newAmountOfBooks);
            statement.setInt(4, id);
            statement.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteReader(int id) {
        deleteAllBooksFromReader(id);
        try {
            String query = "Delete from public.\"Readers\" where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int addBookToReader(int bookId, int readerId) {
        try {
            String query = "Insert into public.\"Book_reader_links\"(book_id, reader_id) Values(?, ?) Returning book_id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, bookId);
            statement.setInt(2, readerId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteBookFromReader(int bookId, int readerId) {
        try {
            String query = "Delete from public.\"Book_reader_links\" Where book_id = ? And reader_id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, bookId);
            statement.setInt(2, readerId);
            statement.executeUpdate();
            return bookId;

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteAllBooksFromReader(int readerId) {
        try {
            String query = "Delete from public.\"Book_reader_links\" Where reader_id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, readerId);
            statement.executeUpdate();
            return readerId;

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int createRandomReaders(int number) {
        try {
            String query = "do $$\n" +
                    "            declare\n" +
                    "            counter integer := 0;\n" +
                    "            begin\n" +
                    "                while counter < " + number + "  loop\n" +
                    "                INSERT INTO public.\"Readers\" (fullname, phone_number, amount_of_books)\n " +
                    "                SELECT\n" +
                    "                    chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int),\n" +
                    "                    chr(43) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int) || chr(trunc(48 + random() * 10)::int),\n" +
                    "                    trunc(random() * 10)\n" +
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
