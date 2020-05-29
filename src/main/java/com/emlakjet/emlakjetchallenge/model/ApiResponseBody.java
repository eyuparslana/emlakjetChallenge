package com.emlakjet.emlakjetchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseBody implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private static final Instant now = Instant.now();

    private int status;
    private String description;
    private Object data;
    private Object errors;
    @Builder.Default
    private long timestamp = now.toEpochMilli();
    @Builder.Default
    private String date = now.toString();

}
