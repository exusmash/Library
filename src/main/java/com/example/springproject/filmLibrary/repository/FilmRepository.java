package com.example.springproject.filmLibrary.repository;

import com.example.springproject.filmLibrary.model.Film;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends GenericRepository<Film> {
}

