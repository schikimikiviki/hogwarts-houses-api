package com.hogwartshouses.house.controller;

import com.hogwartshouses.house.service.exceptions.RoomNotFoundException;
import com.hogwartshouses.house.model.Person;
import com.hogwartshouses.house.model.Room;
import com.hogwartshouses.house.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    Optional<Room> getSingleRoom(@PathVariable Long id) {
        return roomService.getSingleRoom(id);
    }


}
