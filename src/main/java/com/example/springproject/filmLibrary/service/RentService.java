package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.RentDTO;
import com.example.springproject.filmLibrary.mapper.RentMapper;
import com.example.springproject.filmLibrary.model.Rent;
import com.example.springproject.filmLibrary.repository.RentRepository;
import org.springframework.stereotype.Service;

@Service
public class RentService extends GenericService<Rent, RentDTO> {
    protected RentService(RentRepository rentRepository,
                          RentMapper rentMapper) {
        super(rentRepository, rentMapper);
    }
}

