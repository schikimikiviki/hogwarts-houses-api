package com.hogwartshouses.house.service;

import com.hogwartshouses.house.service.exceptions.RoomNotFoundException;
import com.hogwartshouses.house.model.Person;
import com.hogwartshouses.house.model.Room;
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
            throw new RoomNotFoundException("Room with ID " + roomId + " not found");
        }
    }

    public Optional<Room> getSingleRoom(Long id) {

        Optional<Room> room = roomRepository.findRoomById(id);

        if (room.isPresent()) {
            return room;
        } else {
            throw new RoomNotFoundException("Room with ID " + id + " not found");
        }

    }
    public Map<String, Object> deleteRoom(Long id) {
        Optional<Room> roomOptional = roomRepository.findRoomById(id);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get(); // Get the room details before deletion
            roomRepository.deleteById(id);

            // Construct a response object with "room deleted" message and the deleted room's details
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Room deleted: " + room);
            response.put("deletedRoomDetails", room);

            return response; // Return the constructed response
        } else {
            throw new RoomNotFoundException("Room with ID " + id + " not found");
        }
    }


}
