package com.example.marioclassen.myapplication.db;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.util.List;

/**
 * Created by marioclassen on 5/10/16.
 */
public interface DBListenerProfs {
    void addAllProfs(PersonDTO personDTO);
    void addProf(int personId, String profName);
    List<String> getAllProfs(int personId);
}
