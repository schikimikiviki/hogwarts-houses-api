package com.hogwartshouses.house.service;

import com.hogwartshouses.house.model.classes.Person;
import com.hogwartshouses.house.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(Long id){
        return personRepository.findById(id);
    }
}
