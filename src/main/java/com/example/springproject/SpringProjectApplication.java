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
    private UserDAOBean userDAOBean;
    private BookDAOBean bookDAOBean;

    public SpringProjectApplication(UserDAOBean userDAOBean) {
        this.userDAOBean = userDAOBean;
    }

    @Autowired
    public void setBean(BookDAOBean bookDAOBean) {
        this.bookDAOBean = bookDAOBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookDAOBean.findBooksByTitles(userDAOBean.getListOfUsersBooksByEmail("rsmitherham10@cnet.com"));
        userDAOBean.addUserToDatabase(new User(41, "Вася", "Иванов", new Date(1 / 12 / 2000), "62 (503) 460-6001", "vasek@mail.ru", Arrays.asList(new String[]{"Сказка", "Стих"})));
    }
}
