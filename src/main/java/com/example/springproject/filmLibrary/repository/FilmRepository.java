package com.example.springproject.filmLibrary.repository;

import com.example.springproject.filmLibrary.model.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends GenericRepository<Film> {
    @Query(value = "select films.* from films inner join films_directors fd on films.id = fd.film_id\n" +
            "    where fd.director_id = :director_id", nativeQuery = true)
    public List<Film> getFilmsByDirectorId(@Param("director_id") Long directorId);


}

