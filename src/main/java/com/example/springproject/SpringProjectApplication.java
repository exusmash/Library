package com.example.springproject;

import com.example.springproject.exmpl.dao.BookDAOBean;
import com.example.springproject.exmpl.dao.UserDAOBean;
import com.example.springproject.exmpl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Swagger path: http://localhost:8080/swagger-ui/index.html");
    }
}
