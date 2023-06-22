package com.example.springproject.filmLibrary.service;

import com.example.springproject.filmLibrary.dto.FilmDTO;
import com.example.springproject.filmLibrary.dto.RentDTO;
import com.example.springproject.filmLibrary.mapper.RentMapper;
import com.example.springproject.filmLibrary.model.Rent;
import com.example.springproject.filmLibrary.repository.RentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RentService extends GenericService<Rent, RentDTO> {

    private final FilmService filmService;
    protected RentService(RentRepository rentRepository, RentMapper rentMapper, FilmService filmService) {
        super(rentRepository, rentMapper);
        this.filmService = filmService;
    }

//    public RentDTO rentBook(final RentDTO rentDTO) {
//        FilmDTO filmDTO = filmService.getOne(rentDTO.getFilmId());
//        long rentPeriod = rentDTO.getRentPeriod() != null ? rentDTO.getRentPeriod() : 14L;
//        rentDTO.setRentDate(LocalDateTime.now());
//        rentDTO.setIsPurchase(true);
//        rentDTO.setRentPeriod((int) rentPeriod);
//        rentDTO.setEndAccess(LocalDateTime.now().plusDays(rentPeriod));
//        return mapper.toDTO(repository.save(mapper.toEntity(rentDTO)));
//    }
//
//    public Page<RentDTO> listUserRentBooks(final Long id, final Pageable pageRequest) {
//        Page<Rent> objects = ((RentRepository) repository).getBookRentInfoByUserId(id, pageRequest);
//        List<BookRentInfoDTO> results = mapper.toDTOs(objects.getContent());
//        return new PageImpl<>(results, pageRequest, objects.getTotalElements());
//    }

}

