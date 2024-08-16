package com.example.advancedsearch.resource;

import com.example.advancedsearch.dto.PersonFilterDTO;
import com.example.advancedsearch.enums.MaritalStatus;
import com.example.advancedsearch.model.Person;
import com.example.advancedsearch.repository.PersonRepository;
import com.example.advancedsearch.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonResource {

    private PersonService personService;

    private PersonRepository personRepository;

    @GetMapping
    public List<Person> findAll() {
        return this.personService.findAll();
    }

    @GetMapping("/search-with-specification")
    public Page<Person> findAll(PersonFilterDTO filter, Pageable pageable) {
        return this.personService.findAll(filter, pageable);
    }

    @GetMapping("/search-with-jpql")
    public ResponseEntity<Page<Person>> searchPersons(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String maritalStatus,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) LocalDate initialBirthday,
            @RequestParam(required = false) LocalDate finalBirthday,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<Person> persons = personService.searchPersons(
                name, email, maritalStatus, district, city, state, initialBirthday, finalBirthday, pageable
        );
        return ResponseEntity.ok(persons);
    }
}
