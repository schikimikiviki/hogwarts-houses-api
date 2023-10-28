package com.hogwartshouses.house.repository;

import com.hogwartshouses.house.model.classes.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
