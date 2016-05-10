package com.example.marioclassen.myapplication.data.loader;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.marioclassen.myapplication.MainView;
import com.example.marioclassen.myapplication.PersonDetailView;
import com.example.marioclassen.myapplication.R;
import com.example.marioclassen.myapplication.data.dto.PersonDTO;
import com.example.marioclassen.myapplication.data.dto.TownDTO;
import com.example.marioclassen.myapplication.data.mapper.JsonToObjectMapperImpl;

import java.io.IOException;

/**
 * Created by marioclassen on 5/6/16.
 */
public class LoadPersonDTOAsync extends AsyncTask<Integer, Void, PersonDTO> {

    PersonDetailView personDetailView;

    public LoadPersonDTOAsync (PersonDetailView personDetailView) {
        this.personDetailView = personDetailView;
    }

    @Override
    protected PersonDTO doInBackground(Integer... params) {

        String dataEndpoint = "https://raw.githubusercontent.com/AXA-GROUP-SOLUTIONS/mobilefactory-test/master/data.json";

        LoadStringDataFromURL loadStringDataFromURL = new LoadStringDataFromURL();
        JsonToObjectMapperImpl jsonToObjectMapper = new JsonToObjectMapperImpl();

        String dataFromUrlAsString = loadStringDataFromURL.loadDataFromUrlAsString(dataEndpoint);

        PersonDTO personDTO = null;
        try {
            TownDTO townDTO = jsonToObjectMapper.mapJsonToObject(dataFromUrlAsString, TownDTO.class);
            personDTO = townDTO.getPersonDTOs().get(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personDTO;
    }

    protected void onPostExecute(PersonDTO personDTO) {
        personDetailView.updatePersonDetails(personDTO);
        System.out.println(personDTO);
    }


}
