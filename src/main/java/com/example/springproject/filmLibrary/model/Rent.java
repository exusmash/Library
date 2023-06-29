package com.example.springproject.filmLibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "rents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "films_sequence", allocationSize = 1)
public class Rent extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USERS_ID"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_FILM_ID"))
    private Film film;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "rent_period", nullable = false)
    private LocalDateTime rentPeriod;

    @Column(name = "purchase")
    private Boolean isPurchased;

}
