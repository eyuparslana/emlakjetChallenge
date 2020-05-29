package com.emlakjet.emlakjetchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "estate")
public class Estate {

    @Id
    private String id;

    private String agencyId;

    @NotNull(message = "title should be entered.")
    @NotEmpty(message = "title should not be empty.")
    private String title;

    @Min(value = 1, message = "price should not be less than 1 or null")
    private int price;

    @Min(value = 1, message = "squareMeter should not be less than 1 or null")
    private int squareMeter;
}
