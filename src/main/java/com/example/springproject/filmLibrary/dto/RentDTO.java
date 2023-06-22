package com.example.springproject.filmLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO extends GenericDTO {
    private Long filmId;
    private Long userId;
    private LocalDateTime rentDate;
    private LocalDateTime endAccess;
    private Integer rentPeriod;
    private Boolean isPurchase;
}

