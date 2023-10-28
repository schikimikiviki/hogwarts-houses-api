package com.hogwartshouses.house.controller;

import com.hogwartshouses.house.exceptions.RoomNotFoundException;
import com.hogwartshouses.house.model.Person;
import com.hogwartshouses.house.model.Room;
import com.hogwartshouses.house.repository.RoomRepository;
import com.hogwartshouses.house.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController( RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
      return roomService.getAllRooms();
    }

    @PostMapping
    Room save(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PatchMapping("/{id}")
    Room savePerson(@PathVariable Long id, @RequestBody Person person) throws RoomNotFoundException {
        return roomService.addPersonToRoom(person, id);
    }


}
