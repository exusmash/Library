package com.example.springproject.filmLibrary.controller;

import com.example.springproject.filmLibrary.model.Rent;
import com.example.springproject.filmLibrary.repository.RentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Прокат фильма", description = "Контроллер для работы с прокатом фильма пользателям")
public class RentController extends GenericController<Rent> {

    public RentController(RentRepository repository) {
        super(repository);
    }
}

