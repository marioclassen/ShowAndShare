package com.example.marioclassen.myapplication.db;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.util.List;

/**
 * Created by marioclassen on 5/10/16.
 */
public interface TownListener {
    void addPerson (PersonDTO personDTO);
    int getPersonCount();
    List<PersonDTO> getAllPersons();
    PersonDTO getPersonWithId (int id);

}
