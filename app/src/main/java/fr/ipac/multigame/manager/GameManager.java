package fr.ipac.multigame.manager;

import fr.ipac.multigame.model.Game;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private Game game;
    public static GameManager getInstance() {return  instance;}
    public Game getGame() {return game;}
    public void setGame(Game game) { this.game = game;}
}
