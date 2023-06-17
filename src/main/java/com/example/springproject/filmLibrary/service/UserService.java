package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.RoleDTO;
import com.example.springproject.filmLibrary.dto.UserDTO;
import com.example.springproject.filmLibrary.mapper.GenericMapper;
import com.example.springproject.filmLibrary.model.User;
import com.example.springproject.filmLibrary.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
        extends GenericService<User, UserDTO> {
    public UserService(GenericRepository<User> repository,
                       GenericMapper<User, UserDTO> mapper) {
        super(repository, mapper);
    }

    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }
}

