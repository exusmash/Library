package com.example.springproject.filmLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO extends GenericDTO {
    private Long userId;
    private Long filmId;
    private Date date;
    private LocalDateTime rentPeriod;
    private boolean isPurchased;

}

