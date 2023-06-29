package com.example.springproject.filmLibrary.constants;

import java.util.List;

public class SecurityConstants {

    private SecurityConstants() {

    }

    public static final List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**l",
            "/js/**",
            "/css/**",
            "/",
            "swagger-ui/**"
    );

    public static final List<String> FILMS_WHITE_LIST = List.of(
            "/films"
    );

    public static final List<String> FILMS_PERMISSIONS_LIST = List.of(
            "/films/add",
            "films/update",
            "films/delete"
    );

    public static final List<String> USER_WHITE_LIST = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password/"
    );
}


