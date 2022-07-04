package com.vesmer.familytestbackend.model;

import com.vesmer.familytestbackend.model.enumeration.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "persons",
        uniqueConstraints = {
                @UniqueConstraint(name = "name_unique", columnNames = "name"),
        }
)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30, message = "Name is too short or too long")
    @NotBlank(message = "Username is required")
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
}
