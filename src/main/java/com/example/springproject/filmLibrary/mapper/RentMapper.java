package com.example.springproject.filmLibrary.mapper;

import com.example.springproject.filmLibrary.dto.RentDTO;
import com.example.springproject.filmLibrary.model.Rent;
import com.example.springproject.filmLibrary.repository.FilmRepository;
import com.example.springproject.filmLibrary.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class RentMapper extends GenericMapper<Rent, RentDTO> {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    protected RentMapper(ModelMapper mapper, FilmRepository filmRepository, UserRepository userRepository) {
        super(Rent.class, RentDTO.class, mapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper
                .createTypeMap(Rent.class, RentDTO.class)
                .addMappings(m -> m.skip(RentDTO::setUserId))
                .addMappings(m -> m.skip(RentDTO::setFilmId))
                .setPostConverter(toDTOConverter());

        super.modelMapper
                .createTypeMap(RentDTO.class, Rent.class)
                .addMappings(m -> m.skip(Rent::setUser))
                .addMappings(m -> m.skip(Rent::setFilm))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(RentDTO source, Rent destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }

    @Override
    protected void mapSpecificFields(Rent source, RentDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
    }

    @Override
    protected List<Long> getIds(Rent entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
