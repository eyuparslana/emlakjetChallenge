package com.emlakjet.emlakjetchallenge.service;

import com.emlakjet.emlakjetchallenge.exception.EstateAgencyNotFoundException;
import com.emlakjet.emlakjetchallenge.exception.EstateAlreadyExistsException;
import com.emlakjet.emlakjetchallenge.exception.EstateNotFoundException;
import com.emlakjet.emlakjetchallenge.model.Estate;
import com.emlakjet.emlakjetchallenge.model.EstateAgency;
import com.emlakjet.emlakjetchallenge.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstateServiceImpl implements EstateService {

    private EstateRepository estateRepository;
    private EstateAgencyServiceImpl estateAgencyService;

    @Autowired
    public EstateServiceImpl(EstateRepository estateRepository, EstateAgencyServiceImpl estateAgencyService) {
        this.estateRepository = estateRepository;
        this.estateAgencyService = estateAgencyService;
    }

    @Override
    public List<Estate> getAllEstate() {
        return estateRepository.findAll();
    }

    @Override
    public List<Estate> getAllEstateByAgency(String agencyId) {
        EstateAgency agency = estateAgencyService.getAgency(agencyId);
        if (agency == null) {
            throw new EstateAgencyNotFoundException("Agency not found.");
        }
        return estateRepository.findAllByAgencyId(agencyId);
    }

    @Override
    public Estate getEstate(String estateId) {
        Optional<Estate> optionalEstate = estateRepository.findById(estateId);
        if (!optionalEstate.isPresent()) {
            throw new EstateNotFoundException("Estate not found.");
        }

        return optionalEstate.get();
    }

    @Override
    public Estate deleteEstate(String estateId) {
        Optional<Estate> optionalEstate = estateRepository.findById(estateId);
        if (!optionalEstate.isPresent()) {
            throw new EstateNotFoundException("Estate not found.");
        }

        Estate estate = optionalEstate.get();
        estateRepository.delete(estate);
        return estate;
    }

    @Override
    public Estate createEstate(Estate estate) {
        return estateRepository.save(estate);
    }

    @Override
    public Estate updateEstate(String estateId, Estate newEstate) {
        Optional<Estate> optionalEstate = estateRepository.findById(estateId);
        if (!optionalEstate.isPresent()) {
            throw new EstateNotFoundException("Estate not found.");
        }

        Estate estate = optionalEstate.get();
        estate.setPrice(newEstate.getPrice());
        estate.setSquareMeter(newEstate.getSquareMeter());
        estate.setTitle(newEstate.getTitle());
        return estateRepository.save(estate);
    }
}
