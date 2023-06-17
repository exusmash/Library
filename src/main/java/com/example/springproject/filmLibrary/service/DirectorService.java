package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.DirectorDTO;
import com.example.springproject.filmLibrary.mapper.DirectorMapper;
import com.example.springproject.filmLibrary.model.Director;
import com.example.springproject.filmLibrary.model.Film;
import com.example.springproject.filmLibrary.repository.DirectorRepository;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class DirectorService extends GenericService<Director, DirectorDTO> {

    private final FilmRepository filmRepository;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper, FilmRepository filmRepository) {
        super(directorRepository, directorMapper);
        this.filmRepository = filmRepository;
    }

    public DirectorDTO addFilm(Long filmId,
                               Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("фильм не найден"));
        DirectorDTO director = getOne(directorId);
        director.getFilmsIds().add(film.getId());
        update(director);
        return director;
    }
}

