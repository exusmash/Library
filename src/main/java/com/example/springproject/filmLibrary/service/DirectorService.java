package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.DirectorDTO;
import com.example.springproject.filmLibrary.dto.FilmDTO;
import com.example.springproject.filmLibrary.mapper.DirectorMapper;
import com.example.springproject.filmLibrary.mapper.FilmMapper;
import com.example.springproject.filmLibrary.mapper.GenericMapper;
import com.example.springproject.filmLibrary.model.Director;
import com.example.springproject.filmLibrary.model.Film;
import com.example.springproject.filmLibrary.repository.DirectorRepository;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import com.example.springproject.filmLibrary.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DirectorService extends GenericService<Director, DirectorDTO> {

    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;
    private final DirectorMapper directorMapper;
    private final FilmMapper filmMapper;

    public DirectorService(GenericRepository<Director> repository, GenericMapper<Director, DirectorDTO> mapper, DirectorRepository directorRepository, FilmRepository filmRepository, DirectorMapper directorMapper, FilmMapper filmMapper) {
        super(repository, mapper);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.directorMapper = directorMapper;
        this.filmMapper = filmMapper;
    }

    public void addFilmToDirector(Long directorId, Long filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NoSuchElementException("не найден элемент"));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NoSuchElementException("не найден элемент"));
        director.getFilms().add(film);
        DirectorDTO dto = directorMapper.toDTO(director);
        update(dto);
    }

    public List<FilmDTO> getFilmsOfDirector(Long directorId) {
        List<Film> films = filmRepository.getFilmsByDirectorId(directorId);
        return filmMapper.toDTOs(films);
    }



}

