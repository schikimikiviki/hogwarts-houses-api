package com.hogwartshouses.house.service;

import com.hogwartshouses.house.service.exceptions.RoomNotFoundException;
import com.hogwartshouses.house.model.classes.Person;
import com.hogwartshouses.house.model.classes.Room;
import com.hogwartshouses.house.repository.PersonRepository;
import com.hogwartshouses.house.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;

    public RoomService(RoomRepository roomRepository, PersonRepository personRepository) {
        this.roomRepository = roomRepository;
        this.personRepository = personRepository;

    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room addPersonToRoom(Person person, Long roomId) throws RoomNotFoundException {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room foundRoom = roomOptional.get();

            person.setRoom(foundRoom);

            Person savedPerson = personRepository.save(person);

            List<Person> personList = foundRoom.getPersonList();
            if (personList == null) {
                personList = new ArrayList<>();
            }
            personList.add(savedPerson);
            foundRoom.setPersonList(personList);

            return roomRepository.save(foundRoom);
        } else {
            throw new RoomNotFoundException("Room.java with ID " + roomId + " not found");
        }
    }

    public Optional<Room> getSingleRoom(Long id) {

        Optional<Room> room = roomRepository.findRoomById(id);

        if (room.isPresent()) {
            return room;
        } else {
            throw new RoomNotFoundException("Room.java with ID " + id + " not found");
        }

    }

    public Map<String, Object> deleteRoom(Long id) {
        Optional<Room> roomOptional = roomRepository.findRoomById(id);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            roomRepository.deleteById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Room.java deleted: " + room);
            response.put("deletedRoomDetails", room);

            return response;
        } else {
            throw new RoomNotFoundException("Room.java with ID " + id + " not found");
        }
    }

    // this request is supposed only to take into account the fields OTHER THAN
    // the list of characters - for this, there is a separate request

    public Room updateRoom(Long id, Room room) throws RoomNotFoundException {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);

        // Update the existing room with the data
        existingRoom.setName(room.getName());
        existingRoom.setDescription(room.getDescription());
        existingRoom.setAffiliation(room.getAffiliation());
        existingRoom.setCapacity(room.getCapacity());

        // Save the updated room
        return roomRepository.save(existingRoom);

    }


}
