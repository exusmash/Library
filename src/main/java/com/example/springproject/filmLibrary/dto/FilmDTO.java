package com.example.springproject.filmLibrary.dto;


import com.example.springproject.filmLibrary.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends GenericDTO {
    private String filmTitle;
    private LocalDate publishDate;
    private String country;
    private Genre genre;
    private List<Long> directorIds;
}

