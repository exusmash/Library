package com.example.springproject.filmLibrary.controller;

import com.example.springproject.filmLibrary.dto.UserDTO;
import com.example.springproject.filmLibrary.model.User;
import com.example.springproject.filmLibrary.repository.UserRepository;
import com.example.springproject.filmLibrary.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями фильмотеки")
public class UserController extends GenericController<User, UserDTO> {
    public UserController(UserService userService) {
        super(userService);
    }
}



