package com.emlakjet.emlakjetchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "estate_agency")
public class EstateAgency {

    @Id
    private String id;

    @NotNull(message = "firstName should be entered.")
    @NotEmpty(message = "firstName should not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid character in firstName")
    private String firstName;

    @NotNull(message = "lastName should be entered.")
    @NotEmpty(message = "lastName should not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid character in lastName")
    private String lastName;

    @NotNull(message = "email should be entered.")
    @NotEmpty(message = "email should not be empty.")
    @Email(message = "Invalid email format")
    private String email;
}
