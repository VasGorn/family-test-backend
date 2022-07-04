package com.vesmer.familytestbackend.dto;

import com.vesmer.familytestbackend.model.enumeration.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate birthday;
}
