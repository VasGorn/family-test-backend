package com.vesmer.familytestbackend.repository;

import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.enumeration.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Modifying
    @Query("UPDATE Person p SET p.name = :name, p.gender = :gender, p.birthday = " +
            ":birthday WHERE p.id = :id")
    void updatePerson(@Param(value = "name") String name,
                      @Param(value = "gender") Gender gender,
                      @Param(value = "birthday") LocalDate birthday,
                      @Param(value = "id") Long id);

    @Query("FROM Person p " +
            "WHERE  EXTRACT(YEAR FROM AGE (LOCALTIMESTAMP, p.birthday)) > " +
            ":ageFrom "  +
            "AND  EXTRACT(YEAR FROM AGE (LOCALTIMESTAMP, p.birthday)) < " +
            ":ageTo")
    List<Person> findPersonsByAgeFilter(@Param(value = "ageFrom") Integer ageFrom,
                                        @Param(value = "ageTo") Integer ageTo);
}
