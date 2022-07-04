package com.vesmer.familytestbackend.service;

import com.vesmer.familytestbackend.dto.PersonDto;
import com.vesmer.familytestbackend.exception.FamilyException;
import com.vesmer.familytestbackend.mapper.PersonMapper;
import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.Relatives;
import com.vesmer.familytestbackend.repository.PersonRepository;
import com.vesmer.familytestbackend.repository.RelativesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final RelativesRepository relativesRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         RelativesRepository relativesRepository,
                         PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.relativesRepository = relativesRepository;
        this.personMapper = personMapper;
    }

    public Collection<PersonDto> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(personMapper::mapPersonToDto)
                .collect(Collectors.toList());
    }

    public PersonDto savePerson(PersonDto personDto){
        Person person = personMapper.mapDtoToPerson(personDto);
        Person savedPerson = personRepository.save(person);

        Relatives relatives = new Relatives(
                null,
                person,
                new HashSet<>(),
                new HashSet<>()
        );
        relativesRepository.save(relatives);

        return personMapper.mapPersonToDto(savedPerson);
    }

    @Transactional
    public PersonDto updatePerson(PersonDto personDto){
        personRepository.updatePerson(personDto.getName(),
                personDto.getGender(),
                personDto.getBirthday(),
                personDto.getId());
        Person updatedPerson = personRepository.findById(personDto.getId()).orElseThrow(
                () -> new FamilyException("Person not found by id - " + personDto.getId())
        );
        return personMapper.mapPersonToDto(updatedPerson);
    }

    @Transactional
    public void deletePerson(Long id){
        Person person = personRepository.findById(id).orElseThrow(
                () -> new FamilyException("Person not found by id " + id)
        );
        Relatives relatives = relativesRepository.findByPerson(person).orElseThrow(
                () -> new FamilyException("Relatives not found for" + person.getName() + " person")
        );

        relativesRepository.deleteChildrenFromPerson(person.getId());
        relativesRepository.deleteParentsFromPerson(person.getId());
        relativesRepository.delete(relatives);
        personRepository.delete(person);
    }

    public Collection<PersonDto> getPersonsFilteredByAge(Integer ageFrom,
                                                         Integer ageTo) {
        List<Person> persons = personRepository.findPersonsByAgeFilter(ageFrom, ageTo);
        return persons.stream()
                .map(personMapper::mapPersonToDto)
                .collect(Collectors.toList());
    }
}
