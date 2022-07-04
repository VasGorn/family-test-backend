package com.vesmer.familytestbackend.repository;

import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.Relatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RelativesRepository extends JpaRepository<Relatives, Long> {
    Optional<Relatives> findByPerson(Person person);

    @Modifying
    @Query(value = "DELETE FROM child_parent WHERE child_id = ?1 OR parent_id = ?1",
            nativeQuery = true)
    void deleteParentsFromPerson(Long id);

    @Modifying
    @Query(value = "DELETE FROM parent_child WHERE parent_id = ?1 OR child_id = ?1",
            nativeQuery = true)
    void deleteChildrenFromPerson(Long id);
}
