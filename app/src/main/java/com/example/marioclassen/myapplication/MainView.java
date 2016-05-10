package com.example.marioclassen.myapplication;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.util.List;

/**
 * Created by marioclassen on 5/5/16.
 */
public interface MainView {

    void updatePersonList (List<PersonDTO> personDTOs);

    void showPersonDetails (int id) throws Exception;

}
