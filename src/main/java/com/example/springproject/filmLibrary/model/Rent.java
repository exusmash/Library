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
            foreignKey = @ForeignKey(name = "FK_ORDER_FILM_INFO_USERS"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDER_FILM_INFO_USER"))
    private Film film;

    @Column(name = "rent_date")
    private LocalDateTime rentDate;

    @Column(name = "rent_period")
    private Integer rentPeriod;

    @Column(name = "is_purchase")
    private Boolean isPurchase;
}
