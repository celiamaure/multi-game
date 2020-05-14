package fr.ipac.multigame.model;

public class Game {
    String name;
    Integer highestScore;

    public Game(String name, Integer highestScore) {
        setName(name);
        setHighestScore(highestScore);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(Integer highestScore) {
        this.highestScore = highestScore;
    }
}
