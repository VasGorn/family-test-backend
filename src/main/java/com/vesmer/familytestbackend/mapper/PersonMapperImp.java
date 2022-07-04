package com.vesmer.familytestbackend.mapper;

import com.vesmer.familytestbackend.dto.PersonDto;
import com.vesmer.familytestbackend.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperImp implements PersonMapper{
    @Override
    public PersonDto mapPersonToDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .gender(person.getGender())
                .birthday(person.getBirthday())
                .build();
    }

    @Override
    public Person mapDtoToPerson(PersonDto personDto) {
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .gender(personDto.getGender())
                .birthday(personDto.getBirthday())
                .build();
    }
}
