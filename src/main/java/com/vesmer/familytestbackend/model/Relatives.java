package com.vesmer.familytestbackend.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "relatives")
public class Relatives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "person_id_fk"),
            name = "person_id",
            referencedColumnName = "id"
    )
    private Person person;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CHILD_PARENT",
            joinColumns = {@JoinColumn(
                    foreignKey = @ForeignKey(name = "child_id_fk"),
                    name = "child_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(
                    foreignKey = @ForeignKey(name = "parent_id_fk"),
                    name = "parent_id",
                    referencedColumnName = "id")})
    private Set<Person> parents = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = {@JoinColumn(
                    foreignKey = @ForeignKey(name = "parent_id_fk"),
                    name = "parent_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(
                    foreignKey = @ForeignKey(name = "child_id_fk"),
                    name = "child_id",
                    referencedColumnName = "id")})
    private Set<Person> children = new HashSet<>();
}
