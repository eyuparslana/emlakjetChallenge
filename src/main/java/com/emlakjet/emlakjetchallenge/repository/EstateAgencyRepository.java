package com.emlakjet.emlakjetchallenge.repository;

import com.emlakjet.emlakjetchallenge.model.EstateAgency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EstateAgencyRepository extends MongoRepository<EstateAgency, String> {
    Optional<EstateAgency> findByEmail(String email);
}
