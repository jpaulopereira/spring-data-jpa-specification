package com.example.advancedsearch.service;

import com.example.advancedsearch.dto.PersonFilterDTO;
import com.example.advancedsearch.enums.MaritalStatus;
import com.example.advancedsearch.model.Person;
import com.example.advancedsearch.repository.PersonRepository;
import com.example.advancedsearch.specification.PersonSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;
    private PersonSpecification personSpecification;

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Page<Person> findAll(PersonFilterDTO filter, Pageable pageable) {
        return this.personRepository.findAll(this.personSpecification.persons(filter), pageable);
    }

    public Page<Person> searchPersons(
            String name,
            String email,
            String maritalStatus,
            String district,
            String city,
            String state,
            LocalDate initialBirthday,
            LocalDate finalBirthday,
            Pageable pageable
    ) {
        MaritalStatus maritalStatusEnum = null;
        if (maritalStatus != null) {
            maritalStatusEnum = MaritalStatus.valueOf(maritalStatus.toUpperCase());
        }
        return personRepository.searchPersons(
                name, email, maritalStatusEnum, district, city, state, initialBirthday, finalBirthday, pageable
        );
    }
}

