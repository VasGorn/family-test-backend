package com.vesmer.familytestbackend.service;

import com.vesmer.familytestbackend.dto.RelativesDto;
import com.vesmer.familytestbackend.exception.FamilyException;
import com.vesmer.familytestbackend.mapper.RelativesMapper;
import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.Relatives;
import com.vesmer.familytestbackend.repository.PersonRepository;
import com.vesmer.familytestbackend.repository.RelativesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelativesService {
    private final RelativesRepository relativesRepository;
    private final RelativesMapper relativesMapper;
    private final PersonRepository personRepository;

    @Autowired
    public RelativesService(RelativesRepository relativesRepository,
                            RelativesMapper relativesMapper,
                            PersonRepository personRepository) {
        this.relativesRepository = relativesRepository;
        this.relativesMapper = relativesMapper;
        this.personRepository = personRepository;
    }

    public RelativesDto getRelativesByPerson(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow(
                () -> new FamilyException("Person not found by id " + personId)
        );
        Relatives relatives = relativesRepository.findByPerson(person).orElseThrow(
                () -> new FamilyException("Relatives not found for" + person.getName() + " person")
        );
        return relativesMapper.mapPersonToDto(relatives);
    }

    public RelativesDto addPersonAsParent(Long personId, Long parentId) {
        Person person = personRepository.findById(personId).orElseThrow(
                () -> new FamilyException("Person not found by id " + personId)
        );
        Person parent = personRepository.findById(parentId).orElseThrow(
                () -> new FamilyException("Person not found by id " + personId)
        );

        Relatives relatives = relativesRepository.findByPerson(person).orElseThrow(
                () -> new FamilyException("Relatives not found for" + person.getName() + " person")
        );

        relatives.getParents().add(parent);
        relativesRepository.save(relatives);

        addPersonAsChild(parentId, personId);

        return relativesMapper.mapPersonToDto(relatives);
    }

    public RelativesDto addPersonAsChild(Long personId, Long childId) {
        Person person = personRepository.findById(personId).orElseThrow(
                () -> new FamilyException("Person not found by id " + personId)
        );
        Person child = personRepository.findById(childId).orElseThrow(
                () -> new FamilyException("Person not found by id " + personId)
        );

        Relatives relatives = relativesRepository.findByPerson(person).orElseThrow(
                () -> new FamilyException("Relatives not found for" + person.getName() + " person")
        );

        relatives.getChildren().add(child);
        relativesRepository.save(relatives);
        return relativesMapper.mapPersonToDto(relatives);
    }
}
