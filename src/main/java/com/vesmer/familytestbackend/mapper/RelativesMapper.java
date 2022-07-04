package com.vesmer.familytestbackend.mapper;

import com.vesmer.familytestbackend.dto.PersonDto;
import com.vesmer.familytestbackend.dto.RelativesDto;
import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.Relatives;

public interface RelativesMapper {
    RelativesDto mapPersonToDto(Relatives relatives);
    Relatives mapDtoToPerson(RelativesDto relativesDto);
}
