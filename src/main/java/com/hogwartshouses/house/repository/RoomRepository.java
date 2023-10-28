package com.hogwartshouses.house.repository;

import com.hogwartshouses.house.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

   public Optional<Room> findRoomById(Long id);
}
