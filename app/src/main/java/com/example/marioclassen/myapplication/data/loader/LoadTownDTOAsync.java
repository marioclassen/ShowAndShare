package com.example.marioclassen.myapplication.data.loader;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.marioclassen.myapplication.MainActivity;
import com.example.marioclassen.myapplication.MainView;
import com.example.marioclassen.myapplication.R;
import com.example.marioclassen.myapplication.data.dto.TownDTO;
import com.example.marioclassen.myapplication.data.mapper.JsonToObjectMapperImpl;

import java.io.IOException;

/**
 * Created by marioclassen on 5/5/16.
 */
public class LoadTownDTOAsync extends AsyncTask<String, Void, TownDTO> {

    MainView mainView;

    public LoadTownDTOAsync (MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    protected TownDTO doInBackground(String... params) {

        String dataEndpoint = "https://raw.githubusercontent.com/AXA-GROUP-SOLUTIONS/mobilefactory-test/master/data.json";

        LoadStringDataFromURL loadStringDataFromURL = new LoadStringDataFromURL();
        JsonToObjectMapperImpl jsonToObjectMapper = new JsonToObjectMapperImpl();

        String dataFromUrlAsString = loadStringDataFromURL.loadDataFromUrlAsString(dataEndpoint);

        TownDTO townDTO = null;
        try {
            townDTO = jsonToObjectMapper.mapJsonToObject(dataFromUrlAsString, TownDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return townDTO;
    }

    protected void onPostExecute(TownDTO townDTO) {

        if (townDTO != null && townDTO.getPersonDTOs() != null) {

            mainView.updatePersonList(townDTO.getPersonDTOs());
        } else {

            System.out.println("Error");

        }

        System.out.println(townDTO);
    }
}
