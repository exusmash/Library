package com.example.springproject.exmpl.dao;

import com.example.springproject.exmpl.model.Book;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAOBean {
    private final Connection connection;
    private final String BOOK_SELECT_BY_ID_QUERY = "select * from books where id = ?";
    private final String BOOK_SELECT_BY_TITLE_QUERY = "select * from books where title = ?";

    public BookDAOBean(Connection connection) {
        this.connection = connection;
    }

    public List<Book> findBooksByTitles(List<String> titles) throws SQLException {
        List<Book> result = new ArrayList<>();
        PreparedStatement selectQuery = connection.prepareStatement(BOOK_SELECT_BY_TITLE_QUERY);
        for (String title : titles){
            selectQuery.setString(1, title);
            ResultSet resultSet = selectQuery.executeQuery();
            Book book = new Book();
            while (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setDateAdded(resultSet.getDate("date_added"));
                result.add(book);
            }
        }
        System.out.println(result);
        return result;
    }

    public Book findBookById(Integer bookId) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(BOOK_SELECT_BY_ID_QUERY);
        selectQuery.setInt(1, bookId);
        ResultSet resultSet = selectQuery.executeQuery();
        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
            book.setDateAdded(resultSet.getDate("date_added"));
            System.out.println(book);
        }
        return book;
    }
}
