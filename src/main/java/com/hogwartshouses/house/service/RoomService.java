package com.hogwartshouses.house.service;

import com.hogwartshouses.house.exceptions.RoomNotFoundException;
import com.hogwartshouses.house.model.Person;
import com.hogwartshouses.house.model.Room;
import com.hogwartshouses.house.repository.PersonRepository;
import com.hogwartshouses.house.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

            person.setRoom(foundRoom); // Associate the person with the room

            Person savedPerson = personRepository.save(person); // Save the person

            // Add the saved person to the room's existing list of persons
            List<Person> personList = foundRoom.getPersonList();
            if (personList == null) {
                personList = new ArrayList<>();
            }
            personList.add(savedPerson);
            foundRoom.setPersonList(personList);

            return roomRepository.save(foundRoom); // Save the updated room
        } else {
            throw new RoomNotFoundException();
        }
    }

}
