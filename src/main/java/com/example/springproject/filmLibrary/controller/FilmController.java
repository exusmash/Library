package com.example.springproject.filmLibrary.controller;

import com.example.springproject.filmLibrary.model.Director;
import com.example.springproject.filmLibrary.model.Film;
import com.example.springproject.filmLibrary.repository.DirectorRepository;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import com.example.springproject.filmLibrary.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
public class FilmController extends GenericController<Film> {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public FilmController(GenericRepository<Film> genericRepository, FilmRepository filmRepository, DirectorRepository directorRepository) {
        super(genericRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    @Operation(description = "Добавить фильм к режиссеру")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> addFilm(@RequestParam(value = "filmId") Long filmId,
                                        @RequestParam(value = "directorId") Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("фильм не найден"));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("режиссер не найден"));
//        director.getFilms().add(director); - когда нет join table
        film.getDirectors().add(director);
        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(film));
    }
}

