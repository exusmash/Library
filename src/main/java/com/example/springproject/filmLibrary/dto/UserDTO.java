package com.example.springproject.filmLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends GenericDTO {
    private String login;
    private String password;
    private String email;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String address;
    private Long roleId;
    private List<Long> rents;
}
