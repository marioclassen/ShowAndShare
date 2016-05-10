package com.example.marioclassen.myapplication.data.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by mclassen on 04/05/2016.
 */
public class TownDTO {

    public List<PersonDTO> getPersonDTOs() {
        return personDTOs;
    }

    @JsonProperty("Brastlewark")
    public void setPersonDTOs(List<PersonDTO> personDTOs) {
        this.personDTOs = personDTOs;
    }

    public List<PersonDTO> personDTOs;

}
