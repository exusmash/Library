package com.example.springproject.exmpl.dao;

import com.example.springproject.exmpl.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDAOBean {
    private final String USER_SELECT_BY_EMAIL_QUERY = "select book_titles from users where email = ?";
    private final String INSERT_INTO_USERS = "insert into users (id, first_name, last_name, date_of_birth, phone_number, email, book_titles) values (?, ?, ?, ?, ?, ?, ?)";
    private final Connection connection;

    public UserDAOBean(Connection connection) {
        this.connection = connection;
    }

    public List<String> getListOfUsersBooksByEmail(String email) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(USER_SELECT_BY_EMAIL_QUERY);
        selectQuery.setString(1, email);
        ResultSet resultSet = selectQuery.executeQuery();
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            res = Arrays.asList(resultSet.getString("book_titles").split(", "));
        }
        return res;
    }

    public void addUserToDatabase(User user) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(INSERT_INTO_USERS);
        selectQuery.setInt(1, user.getId());
        selectQuery.setString(2, user.getName());
        selectQuery.setString(3, user.getSurname());
        selectQuery.setDate(4, new Date(user.getDateOfBirth().getTime()));
        selectQuery.setString(5, user.getPhoneNumber());
        selectQuery.setString(6, user.getEmail());
        selectQuery.setString(7, user.getBookTitles().toString());
        selectQuery.executeUpdate();
        System.out.println("ГОТОВО!");
    }

}
