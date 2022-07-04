package com.vesmer.familytestbackend.mapper;

import com.vesmer.familytestbackend.dto.PersonDto;
import com.vesmer.familytestbackend.dto.RelativesDto;
import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.Relatives;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RelativesMapperImp implements RelativesMapper {
    private final PersonMapper personMapper;

    public RelativesMapperImp(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public RelativesDto mapPersonToDto(Relatives relatives) {
        List<PersonDto> parentsDto = relatives.getParents().stream()
                .map(personMapper::mapPersonToDto)
                .collect(Collectors.toList());
        List<PersonDto> childrenDto = relatives.getChildren().stream()
                .map(personMapper::mapPersonToDto)
                .collect(Collectors.toList());

        return RelativesDto.builder()
                .id(relatives.getId())
                .person(relatives.getPerson())
                .parents(parentsDto)
                .children(childrenDto)
                .build();
    }

    @Override
    public Relatives mapDtoToPerson(RelativesDto relativesDto) {
        Set<Person> parents = relativesDto.getParents().stream()
                .map(personMapper::mapDtoToPerson)
                .collect(Collectors.toSet());

        Set<Person> children = relativesDto.getChildren().stream()
                .map(personMapper::mapDtoToPerson)
                .collect(Collectors.toSet());

        return Relatives.builder()
                .id(relativesDto.getId())
                .person(relativesDto.getPerson())
                .parents(parents)
                .children(children)
                .build();
    }
}
