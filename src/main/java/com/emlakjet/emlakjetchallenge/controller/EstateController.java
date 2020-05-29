package com.emlakjet.emlakjetchallenge.controller;

import com.emlakjet.emlakjetchallenge.helper.EstateUtils;
import com.emlakjet.emlakjetchallenge.model.ApiResponseBody;
import com.emlakjet.emlakjetchallenge.model.Estate;
import com.emlakjet.emlakjetchallenge.service.EstateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estate")
public class EstateController {

    private EstateServiceImpl estateService;

    @Autowired
    public EstateController(EstateServiceImpl estateService) {
        this.estateService = estateService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> getAllEstate() {
        List<Estate> estates = estateService.getAllEstate();
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(estates)
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<ApiResponseBody> getAllByAgency(@PathVariable String agencyId) {
        List<Estate> allEstateByAgency = estateService.getAllEstateByAgency(agencyId);
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(allEstateByAgency)
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseBody> getEstate(@RequestParam(required = false) String estateId,
                                                     @RequestParam(required = false) String minPrice,
                                                     @RequestParam(required = false) String maxPrice,
                                                     @RequestParam(required = false) String minSquareMeter,
                                                     @RequestParam(required = false) String maxSquareMeter,
                                                     @RequestParam(required = false) String title) {
        List<Estate> estates = new ArrayList<Estate>();

        if (estateId != null) {
            Estate estate = estateService.getEstate(estateId);
            ApiResponseBody body = ApiResponseBody.builder()
                    .data(estate)
                    .status(HttpStatus.OK.value())
                    .build();

            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        List<Estate> filteredEstates = estateService.getAllEstate();

        if (minPrice != null && EstateUtils.paramIsNumeric(minPrice)) {
            filteredEstates = filteredEstates.stream()
                    .filter((estate -> estate.getPrice() > Integer.parseInt(minPrice)))
                    .collect(Collectors.toList());
        }

        if (maxPrice != null && EstateUtils.paramIsNumeric(maxPrice)) {
            filteredEstates = filteredEstates.stream()
                    .filter((estate -> estate.getPrice() < Integer.parseInt(maxPrice)))
                    .collect(Collectors.toList());
        }

        if (minSquareMeter != null && EstateUtils.paramIsNumeric(minSquareMeter)) {
            filteredEstates = filteredEstates.stream()
                    .filter((estate -> estate.getSquareMeter() > Integer.parseInt(minSquareMeter)))
                    .collect(Collectors.toList());
        }

        if (maxSquareMeter != null && EstateUtils.paramIsNumeric(maxSquareMeter)) {
            filteredEstates = filteredEstates.stream()
                    .filter((estate -> estate.getSquareMeter() < Integer.parseInt(maxSquareMeter)))
                    .collect(Collectors.toList());
        }

        if (title != null) {
            filteredEstates = filteredEstates.stream()
                    .filter((estate -> estate.getTitle().contains(title)))
                    .collect(Collectors.toList());
        }

        ApiResponseBody body = ApiResponseBody.builder()
                .data(filteredEstates)
                .status(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> createEstate(@Valid @RequestBody Estate new_estate) {
        Estate estate = estateService.createEstate(new_estate);
        ApiResponseBody body = ApiResponseBody.builder()
                .data(estate)
                .description("Estate Created.")
                .status(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/{estateId}")
    public ResponseEntity<ApiResponseBody> deleteEstate(@PathVariable String estateId) {
        Estate deletedEstate = estateService.deleteEstate(estateId);
        ApiResponseBody body = ApiResponseBody.builder()
                .data(deletedEstate)
                .description("Estate Deleted.")
                .status(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{estateId}")
    public ResponseEntity<ApiResponseBody> updateEstate(@PathVariable String estateId, @Valid @RequestBody Estate estate) {
        Estate updatedEstate = estateService.updateEstate(estateId, estate);
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(updatedEstate)
                .description("Estate Updated.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
