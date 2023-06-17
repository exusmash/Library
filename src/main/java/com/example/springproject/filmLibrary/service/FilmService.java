package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.FilmDTO;
import com.example.springproject.filmLibrary.mapper.FilmMapper;
import com.example.springproject.filmLibrary.model.Director;
import com.example.springproject.filmLibrary.model.Film;
import com.example.springproject.filmLibrary.repository.DirectorRepository;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {
    private final DirectorRepository directorRepository;

    protected FilmService(FilmRepository filmRepository,
                          FilmMapper filmMapper,
                          DirectorRepository directorRepository) {
        super(filmRepository, filmMapper);
        this.directorRepository = directorRepository;
    }

    public FilmDTO addDirector(final Long filmId,
                               final Long directorId) {
        FilmDTO filmDTO = getOne(filmId);
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("режиссер не найден"));
        filmDTO.getDirectorIds().add(director.getId());
        update(filmDTO);
        return filmDTO;
    }
}

