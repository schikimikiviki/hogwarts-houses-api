package com.hogwartshouses.house.service;

import com.hogwartshouses.house.service.exceptions.RoomCapacityFullException;
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
        if (room.getPlacesLeft() > 0) {
            return roomRepository.save(room);
        } else {
            throw new RoomCapacityFullException();
        }
    }


    public Room addPersonToRoom(Person person, Long roomId) throws RoomNotFoundException, RoomCapacityFullException {
        Room foundRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));

        if (foundRoom.getPlacesLeft() > 0 && !foundRoom.getPersonList().contains(person)) {
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
            throw new RoomCapacityFullException("Room capacity reached or person already exists in the room. Cannot add more people.");
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

    public Room updateRoom(Long id, Room newRoom) throws RoomNotFoundException {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);

        // Update the existing newRoom with the data
        existingRoom.setName(newRoom.getName());
        existingRoom.setDescription(newRoom.getDescription());
        existingRoom.setAffiliation(newRoom.getAffiliation());
        existingRoom.setCapacity(newRoom.getCapacity());


        // Update the placesLeft field based on the modified capacity and personList
        existingRoom.setPersonList(newRoom.getPersonList());
        existingRoom.updatePlacesLeft();

        // Save the updated newRoom
        return roomRepository.save(existingRoom);
    }

    public List<Room> getRoomsAvailable() {
        List<Room> allRooms = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : allRooms) {
            if (room.getPlacesLeft() > 0) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }


    public Optional<Person> getPerson(Long id){
        return personRepository.findById(id);
    }

    public Optional<Person> updatePersonRoom(Long personId, Long newRoomId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        Optional<Room> newRoomOptional = roomRepository.findRoomById(newRoomId);

        if (personOptional.isPresent() && newRoomOptional.isPresent()) {
            Person person = personOptional.get();
            Room newRoom = newRoomOptional.get();

            person.setRoom(newRoom);
            personRepository.save(person);

            return Optional.of(person);
        } else {
            return Optional.empty();
        }
    }


}
