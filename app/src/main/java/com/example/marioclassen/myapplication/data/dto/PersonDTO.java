package com.example.marioclassen.myapplication.data.dto;

import java.util.List;
import java.util.Scanner;

/**
 * Created by mclassen on 04/05/2016.
 */

public class PersonDTO {

    private int id;
    private String name;
    private String thumbnail;
    private int age;
    private double weight;
    private double height;
    private String hair_color;
    List<String> professions;

    public List<Scanner> getFriends() {
        return friends;
    }

    public void setFriends(List<Scanner> friends) {
        this.friends = friends;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    List<Scanner> friends;

}
