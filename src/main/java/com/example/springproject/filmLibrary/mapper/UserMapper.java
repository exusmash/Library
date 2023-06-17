package com.example.springproject.filmLibrary.mapper;

import com.example.springproject.filmLibrary.dto.UserDTO;
import com.example.springproject.filmLibrary.model.GenericModel;
import com.example.springproject.filmLibrary.model.User;
import com.example.springproject.filmLibrary.repository.RentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {

    private final RentRepository rentRepository;

    protected UserMapper(ModelMapper modelMapper,
                         RentRepository rentRepository) {
        super(User.class, UserDTO.class, modelMapper);
        this.rentRepository = rentRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper
                .createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setRents)).setPostConverter(toDTOConverter());
        modelMapper
                .createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setRents)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getRents())) {
            destination.setRents(rentRepository.findAllById(source.getRents()));
        } else {
            destination.setRents(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        destination.setRents(getIds(source));
    }

    @Override
    protected List<Long> getIds(User entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getRents())
                ? null
                : entity.getRents().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}

