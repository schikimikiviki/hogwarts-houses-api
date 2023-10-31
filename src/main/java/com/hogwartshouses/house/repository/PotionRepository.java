package com.hogwartshouses.house.repository;

import com.hogwartshouses.house.model.classes.Potion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotionRepository  extends JpaRepository<Potion, Long> {

    @Query("SELECT p FROM Potion p WHERE p.person.id = :personId")
    List<Potion> findByPersonId(Long personId);
}
