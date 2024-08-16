package com.example.advancedsearch.service;

import com.example.advancedsearch.dto.PersonFilterDTO;
import com.example.advancedsearch.dto.PersonResponseDTO;
import com.example.advancedsearch.model.Person;
import com.example.advancedsearch.repository.PersonRepository;
import com.example.advancedsearch.specification.PersonSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Page<PersonResponseDTO> searchPersons(PersonFilterDTO filter, Pageable pageable) {
        Page<Person> personPage = personRepository.searchPersons(
                filter.getName(),
                filter.getEmail(),
                filter.getMaritalStatus(),
                filter.getDistrict(),
                filter.getCity(),
                filter.getState(),
                filter.getInitialBirthday(),
                filter.getFinalBirthday(),
                pageable
        );
        List<PersonResponseDTO> dtoList = personPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, personPage.getTotalElements());
    }

    private PersonResponseDTO convertToDTO(Person person) {
        PersonResponseDTO dto = new PersonResponseDTO();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setEmail(person.getEmail());
        dto.setMaritalStatus(person.getMaritalStatus());
        dto.setDistrict(person.getDistrict());
        dto.setCity(person.getCity());
        dto.setState(person.getState());
        dto.setBirthday(person.getBirthday());
        return dto;
    }
}

