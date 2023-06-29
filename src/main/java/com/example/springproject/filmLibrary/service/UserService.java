package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.FilmDTO;
import com.example.springproject.filmLibrary.dto.RoleDTO;
import com.example.springproject.filmLibrary.dto.UserDTO;
import com.example.springproject.filmLibrary.mapper.GenericMapper;
import com.example.springproject.filmLibrary.model.Film;
import com.example.springproject.filmLibrary.model.Rent;
import com.example.springproject.filmLibrary.model.User;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import com.example.springproject.filmLibrary.repository.GenericRepository;
import com.example.springproject.filmLibrary.repository.RentRepository;
import com.example.springproject.filmLibrary.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService extends GenericService<User, UserDTO> {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(GenericRepository<User> repository, GenericMapper<User, UserDTO> mapper, RentRepository rentRepository, FilmRepository filmRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(repository, mapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Film> getPurchasedFilms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        List<Rent> rents = user.getRents();
        List<Film> films = new ArrayList<>();

        for (Rent rent: rents) {
            if (rent.getIsPurchased()) {
                films.add(rent.getFilm());
            }
        }

        return films;
    }

    @Override
    public UserDTO create(UserDTO newObject) {
        newObject.setRoleId(1L);
        newObject.setPassword(bCryptPasswordEncoder.encode(newObject.getPassword()));
        newObject.setCreatedBy("REGISTRATION FORM");
        newObject.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }


    public void rentFilm(Long userId, Long filmId) {
        UserDTO dto = getOne(userId);
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Film film = filmRepository.findById(filmId).orElseThrow(NoSuchElementException::new);
        Rent rent = new Rent(user, film, LocalDateTime.now(), LocalDateTime.now(), true);
        rent = rentRepository.save(rent);
        dto.getRents().add(rent.getId());

        update(dto);
    }

    public UserDTO getUserByLogin(final String login) {
        return mapper.toDTO(userRepository.findUserByLogin(login));
    }
    public UserDTO getUserByEmail(final String email) {
        return mapper.toDTO(userRepository.findUserByEmail(email));
    }
}


