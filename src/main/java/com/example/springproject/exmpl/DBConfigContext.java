package com.example.springproject.exmpl;

import com.example.springproject.exmpl.constants.DBConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan
public class DBConfigContext {
    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://" + DBConstants.DB_HOST + ":" + DBConstants.PORT + "/" +
                DBConstants.DB, DBConstants.USER, DBConstants.PASSWORD);

    }
}
