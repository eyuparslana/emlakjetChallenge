package com.emlakjet.emlakjetchallenge.service;

import com.emlakjet.emlakjetchallenge.model.EstateAgency;

import java.util.List;

public interface EstateAgencyService {
    List<EstateAgency> getAllAgencies();
    EstateAgency getAgency(String agencyId);
    EstateAgency getAgencyByEmail(String email);
    EstateAgency createAgency(EstateAgency estateAgency);
    EstateAgency deleteAgency(String agencyId);
    EstateAgency updateAgency(String agencyId, EstateAgency newAgency);
}
