package com.example.advancedsearch.repository;

import com.example.advancedsearch.enums.MaritalStatus;
import com.example.advancedsearch.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface PersonRepository extends JpaRepository<Person, String>, JpaSpecificationExecutor<Person> {

    @Query("SELECT p FROM Person p " +
            "WHERE (:name IS NULL OR UPPER(p.name) LIKE CONCAT(UPPER(:name), '%')) " +
            "AND (:email IS NULL OR LOWER(p.email) LIKE CONCAT(LOWER(:email), '%')) " +
            "AND (:maritalStatus IS NULL OR p.maritalStatus = :maritalStatus) " +
            "AND (:district IS NULL OR UPPER(p.district) LIKE CONCAT(UPPER(:district), '%')) " +
            "AND (:city IS NULL OR p.city = :city) " +
            "AND (:state IS NULL OR p.state = :state) " +
            "AND (:initialBirthday IS NULL OR p.birthday >= :initialBirthday) " +
            "AND (:finalBirthday IS NULL OR p.birthday <= :finalBirthday)")
    Page<Person> searchPersons(
            @Param("name") String name,
            @Param("email") String email,
            @Param("maritalStatus") MaritalStatus maritalStatus,
            @Param("district") String district,
            @Param("city") String city,
            @Param("state") String state,
            @Param("initialBirthday") LocalDate initialBirthday,
            @Param("finalBirthday") LocalDate finalBirthday,
            Pageable pageable
    );
}
