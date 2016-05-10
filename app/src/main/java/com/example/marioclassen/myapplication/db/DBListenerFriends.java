package com.example.marioclassen.myapplication.db;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.util.List;

/**
 * Created by marioclassen on 5/10/16.
 */
public interface DBListenerFriends {
    void addAllFriends (PersonDTO personDTO);
    void addFriend(int personId, String friendName);
    List<String> getAllFriends(int personId);
}
