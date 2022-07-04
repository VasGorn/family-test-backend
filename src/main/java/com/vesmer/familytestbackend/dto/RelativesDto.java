package com.vesmer.familytestbackend.dto;

import com.vesmer.familytestbackend.model.Person;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RelativesDto {
    private Long id;
    private Person person;
    private List<PersonDto> parents;
    private List<PersonDto> children;
}
