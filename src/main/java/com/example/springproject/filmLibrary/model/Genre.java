package com.example.springproject.filmLibrary.model;

public enum Genre {
    ROMANTIC_COMEDY("Романтическая комедия"),
    SCIENCE_FICTION("Научная фантастика"),
    HORROR("Хоррор"),
    DOCUMENTARY("Документальный фильм"),
    ANIMATED_FILM("Анимационный фильм"),
    ACTION("Боевик"),
    THRILLER("Триллер"),
    DRAMA("Драмма");
    private final String genreTextDisplay;

    Genre(String text) {
        this.genreTextDisplay = text;
    }

    public String getGenreTextDisplay() {
        return this.genreTextDisplay;
    }

}
