package com.emlakjet.emlakjetchallenge.service;

import com.emlakjet.emlakjetchallenge.model.Estate;

import java.util.List;

public interface EstateService {
    List<Estate> getAllEstate();
    List<Estate> getAllEstateByAgency(String agencyId);
    Estate getEstate(String estateId);
    Estate deleteEstate(String estateId);
    Estate createEstate(Estate estate);
    Estate updateEstate(String estateId, Estate newEstate);
}
