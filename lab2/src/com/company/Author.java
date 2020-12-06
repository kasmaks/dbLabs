package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Author extends Model{

    public int id;
    public String fullname;
    public String country;
    public int yearOfBirth;
    public int book_id = -1;

    public int createAuthor(String fullname, String country, int yearOfBirth) {
        try {
            String query = "Insert into public.\"Authors\"(fullname, country, year_of_birth) Values(?, ?, ?) RETURNING id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, fullname);
            statement.setString(2, country);
            statement.setInt(3, yearOfBirth);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public ArrayList<Author> getAllAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            String query = "Select * from public.\"Authors\" ";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Author author = new Author();
                author.id = resultSet.getInt("Id");
                author.fullname = resultSet.getString("fullname");
                author.country = resultSet.getString("country");
                author.yearOfBirth = resultSet.getInt("year_of_birth");
                authors.add(author);
            }


        } catch (SQLException throwables) {
            authors = null;
        }

        if(authors != null && authors.size() == 0)
            authors = null;
        return authors;
    }

    public Author getAuthorById(int id) {
        Author author;
        try {
            String query = "Select * from public.\"Authors\" Where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            author = new Author();
            author.id = resultSet.getInt("Id");
            author.fullname = resultSet.getString("fullname");
            author.country = resultSet.getString("country");
            author.yearOfBirth = resultSet.getInt("year_of_birth");

        } catch (SQLException throwables) {
            author = null;
        }
        return author;
    }

    public int updateAuthor(int id, String newFullname, String newCountry, int newYearOfBirth) {
        try {
            String query = "Update public.\"Authors\" " +
                    "Set fullname = ?, " +
                    "country = ?, " +
                    "year_of_birth = ? " +
                    "Where id = ? Returning id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, newFullname);
            statement.setString(2, newCountry);
            statement.setInt(3, newYearOfBirth);
            statement.setInt(4, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            return id;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteAuthor(int id) {
        deleteAllBooksFromAuthor(id);
        try {
            String query = "Delete from public.\"Authors\" Where id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int addBookToAuthor(int bookId, int authorId) {
        try {
            String query = "Insert into public.\"Book_author_links\"(book_id, author_id) Values(?, ?) Returning book_id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, bookId);
            statement.setInt(2, authorId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteBookFromAuthor(int bookId, int authorId) {
        try {
            String query = "Delete from public.\"Book_author_links\" Where book_id = ? And author_id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, bookId);
            statement.setInt(2, authorId);
            statement.executeUpdate();
            return bookId;

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteAllBooksFromAuthor(int authorId) {
        try {
            String query = "Delete from public.\"Book_author_links\" Where author_id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, authorId);
            statement.executeUpdate();
            return authorId;

        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int createRandomAuthors(int number) {
        try {
            String query = "do $$\n" +
                    "            declare\n" +
                    "            counter integer := 0;\n" +
                    "            begin\n" +
                    "                while counter < " + number + "  loop\n" +
                    "                INSERT INTO public.\"Authors\" (fullname, country, year_of_birth)\n " +
                    "                SELECT\n" +
                    "                    chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int),\n" +
                    "                    chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int) || chr(trunc(65 + random() * 25)::int),\n" +
                    "                    trunc(random() * 2009 + 1)\n" +
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

    public ArrayList<Author> getFilteredAuthors(String fullnamePart, String countryPart, int yearOfBirthStart, int yearOfBirthEnd) {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            /*EXPLAIN ANALYZE*/
            String query = "Select * from (Select * from public.\"Authors\""
                    + " Where fullname Like ? And country Like ? And year_of_birth > ? And year_of_birth < ?) \n"
                    + "As author Join (Select * from public.\"Book_author_links\") As author_book On author.id = author_book.author_id";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1,"%" + fullnamePart + "%");
            statement.setString(2,"%" + countryPart + "%");
            statement.setInt(3,yearOfBirthStart);
            statement.setInt(4,yearOfBirthEnd);

            long before = System.currentTimeMillis();
            ResultSet resultSet = statement.executeQuery();
            long after = System.currentTimeMillis();
            System.out.println(after - before + " ms");
            while(resultSet.next()) {
                Author author = new Author();
                author.id = resultSet.getInt("Id");
                author.fullname = resultSet.getString("fullname");
                author.country = resultSet.getString("country");
                author.yearOfBirth = resultSet.getInt("year_of_birth");
                author.book_id = resultSet.getInt("book_id");
                authors.add(author);
            }


        } catch (SQLException throwables) {
            authors = null;
        }

        if(authors != null && authors.size() == 0)
            authors = null;
        return authors;
    }

}
