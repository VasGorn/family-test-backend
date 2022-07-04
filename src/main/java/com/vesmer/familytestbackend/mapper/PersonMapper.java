package com.vesmer.familytestbackend.mapper;

import com.vesmer.familytestbackend.dto.PersonDto;
import com.vesmer.familytestbackend.model.Person;

import java.util.List;
import java.util.Set;

public interface PersonMapper {
    PersonDto mapPersonToDto(Person person);
    Person mapDtoToPerson(PersonDto personDto);
}
