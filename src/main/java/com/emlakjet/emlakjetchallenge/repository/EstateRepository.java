package com.emlakjet.emlakjetchallenge.repository;

import com.emlakjet.emlakjetchallenge.model.Estate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstateRepository extends MongoRepository<Estate, String> {
    List<Estate> findAllByAgencyId(String agencyId);
}
