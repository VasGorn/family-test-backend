package com.vesmer.familytestbackend.controller;

import com.vesmer.familytestbackend.dto.PersonDto;
import com.vesmer.familytestbackend.dto.RelativesDto;
import com.vesmer.familytestbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Collection<PersonDto>> getAllPersons() {
        return ResponseEntity.status(OK).body(personService.getAllPersons());
    }

    @GetMapping("/filter/from/{ageFrom}/to/{ageTo}")
    public ResponseEntity<Collection<PersonDto>> getPersonsFilteredByAge(
            @PathVariable Integer ageFrom, @PathVariable Integer ageTo) {
        return ResponseEntity.status(OK).body(
                personService.getPersonsFilteredByAge(ageFrom, ageTo)
        );
    }

    @PostMapping
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        return ResponseEntity.status(OK).body(personService.savePerson(personDto));
    }

    @PutMapping
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto personDto) {
        return ResponseEntity.status(OK).body(personService.updatePerson(personDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(OK);
    }
}
