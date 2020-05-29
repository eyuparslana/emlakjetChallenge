package com.emlakjet.emlakjetchallenge.service;

import com.emlakjet.emlakjetchallenge.exception.EstateAgencyAlreadyExistsException;
import com.emlakjet.emlakjetchallenge.exception.EstateAgencyNotFoundException;
import com.emlakjet.emlakjetchallenge.model.EstateAgency;
import com.emlakjet.emlakjetchallenge.repository.EstateAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstateAgencyServiceImpl implements EstateAgencyService {

    private EstateAgencyRepository estateAgencyRepository;

    @Autowired
    public EstateAgencyServiceImpl(EstateAgencyRepository estateAgencyRepository) {
        this.estateAgencyRepository = estateAgencyRepository;
    }

    @Override
    public List<EstateAgency> getAllAgencies() {
        return estateAgencyRepository.findAll();
    }

    @Override
    public EstateAgency getAgency(String agencyId) {
        Optional<EstateAgency> agencyOptional = estateAgencyRepository.findById(agencyId);
        if (!agencyOptional.isPresent()) {
            throw new EstateAgencyNotFoundException("Agency not found.");
        }

        return agencyOptional.get();
    }

    @Override
    public EstateAgency getAgencyByEmail(String email) {
        Optional<EstateAgency> agencyOptional = estateAgencyRepository.findByEmail(email);
        if (!agencyOptional.isPresent()) {
            throw new EstateAgencyNotFoundException("Agency not found.");
        }

        return agencyOptional.get();
    }

    @Override
    public EstateAgency createAgency(EstateAgency estateAgency) {
        String email = estateAgency.getEmail();
        Optional<EstateAgency> agencyOptional = estateAgencyRepository.findByEmail(email);
        if (agencyOptional.isPresent()) {
            throw new EstateAgencyAlreadyExistsException("Agency already exists.");
        }

        return estateAgencyRepository.save(estateAgency);
    }

    @Override
    public EstateAgency deleteAgency(String agencyId) {
        Optional<EstateAgency> agencyOptional = estateAgencyRepository.findById(agencyId);
        if (!agencyOptional.isPresent()) {
            throw new EstateAgencyNotFoundException("Agency not found.");
        }
        EstateAgency agency = agencyOptional.get();
        estateAgencyRepository.delete(agency);
        return agency;
    }

    @Override
    public EstateAgency updateAgency(String agencyId, EstateAgency newAgency) {
        Optional<EstateAgency> agencyOptional = estateAgencyRepository.findById(agencyId);
        if (!agencyOptional.isPresent()) {
            throw new EstateAgencyNotFoundException("Agency not found.");
        }
        EstateAgency agency = agencyOptional.get();
        agency.setEmail(newAgency.getEmail());
        agency.setFirstName(newAgency.getFirstName());
        agency.setLastName(newAgency.getLastName());
        return estateAgencyRepository.save(agency);
    }
}
