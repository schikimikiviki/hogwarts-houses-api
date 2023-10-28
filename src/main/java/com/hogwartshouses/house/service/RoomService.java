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

    public Room addPersonToRoom(Person person, Long id) throws RoomNotFoundException {
        Optional<Room> roomById = roomRepository.findRoomById(id);


        if (roomById.isPresent()){

            Room foundRoom = roomById.get();
            Person savedPerson = personRepository.save(person);

            List<Person> personList = new ArrayList<>();
            personList.add(savedPerson);
            foundRoom.setPersonList(personList);

            return roomRepository.save(foundRoom);
        } else {
            //error
            throw new RoomNotFoundException();
        }

    }
}
