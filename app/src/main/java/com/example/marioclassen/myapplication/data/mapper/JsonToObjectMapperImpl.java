package com.example.marioclassen.myapplication.data.mapper;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by mclassen on 04/05/2016.
 */
public class JsonToObjectMapperImpl {

    public <T> T mapJsonToObject (String json, Class<T> classType) throws IOException {
        return new ObjectMapper().readValue(json, classType);
    }

}
