package com.example.advancedsearch.resource;

import com.example.advancedsearch.dto.PersonFilterDTO;
import com.example.advancedsearch.dto.PersonResponseDTO;
import com.example.advancedsearch.model.Person;
import com.example.advancedsearch.repository.PersonRepository;
import com.example.advancedsearch.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonResource {

    private PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return this.personService.findAll();
    }

    @GetMapping("/search-with-specification")
    public Page<Person> findAll(PersonFilterDTO filter, Pageable pageable) {
        return this.personService.findAll(filter, pageable);
    }

    @GetMapping("/search-with-jpql")
    public ResponseEntity<Page<PersonResponseDTO>> searchPersons(
            @ModelAttribute PersonFilterDTO filter,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<PersonResponseDTO> persons = personService.searchPersons(filter, pageable);
        return ResponseEntity.ok(persons);
    }
}
