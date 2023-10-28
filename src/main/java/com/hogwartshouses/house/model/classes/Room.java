package com.hogwartshouses.house.model.classes;

import com.hogwartshouses.house.model.enums.Affiliation;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    private Affiliation affiliation;


    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private List<Person> personList;

    private int capacity;


    private int placesLeft;

    public Room() {

    }

    //logic

    public void updatePlacesLeft() {
        int currentPersons = personList != null ? personList.size() : 0;
        placesLeft = Math.max(0, capacity - currentPersons);
    }

    public boolean hasAvailableCapacity() {
        return placesLeft > 0;
    }

    public boolean addPerson(Person newPerson) {
        if (hasAvailableCapacity()) {
            personList.add(newPerson);
            updatePlacesLeft();
            return true;
        } else {
            return false;
        }
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        updatePlacesLeft();
    }

    public List<Person> getPersonList() {
        updatePlacesLeft();
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        updatePlacesLeft();
    }

    public int getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(int placesLeft) {
        this.placesLeft = placesLeft;
    }

}