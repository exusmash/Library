package com.example.springproject.exmpl.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private List<String> bookTitles;
}
