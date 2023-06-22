package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.FilmDTO;
import com.example.springproject.filmLibrary.dto.RoleDTO;
import com.example.springproject.filmLibrary.dto.UserDTO;
import com.example.springproject.filmLibrary.mapper.GenericMapper;
import com.example.springproject.filmLibrary.model.Film;
import com.example.springproject.filmLibrary.model.User;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import com.example.springproject.filmLibrary.repository.GenericRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService extends GenericService<User, UserDTO> {
    private final FilmRepository filmRepository;

    public UserService(GenericRepository<User> repository, GenericMapper<User, UserDTO> mapper, FilmRepository filmRepository) {
        super(repository, mapper);
        this.filmRepository = filmRepository;
    }

    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

//    public List<FilmDTO> getUserFilms(Long userId) {
//        List<FilmDTO> filmDTOS = Collections.emptyList();
//        UserDTO userDTO = getOne(userId);
//        filmDTOS.addAll(userDTO.getRents())
//        return filmDTOS;
//    }
}

