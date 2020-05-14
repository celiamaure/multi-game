package fr.ipac.multigame.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    String name;
    String firstName;
    String age;
    String picture;
    String localisation;
    ArrayList<Integer> scores;

    public Player(String name, String firstName, String age, String picture, String localisation, ArrayList scores) {
        setName(name);
        setFirstName(firstName);
        setAge(age);
        setPicture(picture);
        setLocalisation(localisation);
        setScores(scores);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }
}
