package com.example.springproject.filmLibrary.repository;

import com.example.springproject.filmLibrary.model.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends GenericRepository<Rent> {
    Page<Rent> getBookRentInfoByUserId(Long id, Pageable pageRequest);
}

