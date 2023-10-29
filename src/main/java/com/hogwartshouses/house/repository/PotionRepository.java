package com.hogwartshouses.house.repository;

import com.hogwartshouses.house.model.classes.Potion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotionRepository  extends JpaRepository<Potion, Long> {
}
