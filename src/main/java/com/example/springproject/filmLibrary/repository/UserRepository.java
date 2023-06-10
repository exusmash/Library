package com.example.springproject.filmLibrary.repository;

import com.example.springproject.filmLibrary.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {
}

