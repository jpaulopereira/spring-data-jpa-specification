package com.example.advancedsearch.dto;

import com.example.advancedsearch.enums.MaritalStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class PersonResponseDTO {

    private String id;
    private String name;
    private String email;
    private MaritalStatus maritalStatus;
    private String district;
    private String city;
    private String state;
    private LocalDate birthday;
}
