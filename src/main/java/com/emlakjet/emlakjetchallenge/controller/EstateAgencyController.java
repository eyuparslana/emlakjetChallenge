package com.emlakjet.emlakjetchallenge.controller;

import com.emlakjet.emlakjetchallenge.model.ApiResponseBody;
import com.emlakjet.emlakjetchallenge.model.EstateAgency;
import com.emlakjet.emlakjetchallenge.service.EstateAgencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agency")
public class EstateAgencyController {

    private EstateAgencyServiceImpl estateAgencyService;

    @Autowired
    public EstateAgencyController(EstateAgencyServiceImpl estateAgencyService) {
        this.estateAgencyService = estateAgencyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> getAllAgencies() {
        List<EstateAgency> allAgencies = estateAgencyService.getAllAgencies();
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(allAgencies)
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<ApiResponseBody> getAgencyById(@PathVariable String agencyId) {
        EstateAgency agency = estateAgencyService.getAgency(agencyId);
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(agency)
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseBody> getAgencyByEmail(@RequestParam(required = false) String email,
                                                            @RequestParam(required = false) String firstName,
                                                            @RequestParam(required = false) String lastName) {
        if (email != null) {
            EstateAgency agency = estateAgencyService.getAgencyByEmail(email);
            ApiResponseBody body = ApiResponseBody.builder()
                    .status(HttpStatus.OK.value())
                    .data(agency)
                    .build();

            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        List<EstateAgency> agencies = estateAgencyService.getAllAgencies();
        if (firstName != null) {
            agencies = agencies.stream()
                    .filter(agency -> agency.getFirstName().contains(firstName))
                    .collect(Collectors.toList());
        }

        if (lastName != null) {
            agencies = agencies.stream()
                    .filter(agency -> agency.getFirstName().contains(lastName))
                    .collect(Collectors.toList());
        }

        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(agencies)
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> createAgency(@Valid @RequestBody EstateAgency agency) {
        EstateAgency newAgency = estateAgencyService.createAgency(agency);
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.CREATED.value())
                .data(newAgency)
                .description("Agency created.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/{agencyId}")
    public ResponseEntity<ApiResponseBody> deleteAgency(@PathVariable String agencyId) {
        EstateAgency agency = estateAgencyService.deleteAgency(agencyId);
        ApiResponseBody body = ApiResponseBody.builder()
                .description("Agency deleted.")
                .data(agency)
                .status(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{agencyId}")
    public ResponseEntity<ApiResponseBody> updateAgency(@PathVariable String agencyId, @Valid @RequestBody EstateAgency agency) {
        EstateAgency estateAgency = estateAgencyService.updateAgency(agencyId, agency);
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(estateAgency)
                .description("Agency updated.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
