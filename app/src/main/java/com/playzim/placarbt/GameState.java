package com.playzim.placarbt;

import java.io.Serializable;

public class GameState implements Serializable {

    private int[] sets;
    private int[] games;
    private int[] points;
    private TeamSide currentServer;
    private TeamSide nextFirstGameServer;
    private GameMode gameMode;

    public GameState(int[] sets, int[] games, int[] points, TeamSide currentServer, TeamSide nextFirstGameServer, GameMode gameMode) {
        this.sets = sets;
        this.games = games;
        this.points = points;
        this.currentServer = currentServer;
        this.setNextFirstGameServer(nextFirstGameServer);
        this.setGameMode(gameMode);
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

    public int[] getGames() {
        return games;
    }

    public void setGames(int[] games) {
        this.games = games;
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public TeamSide getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(TeamSide currentServer) {
        this.currentServer = currentServer;
    }

	public TeamSide getNextFirstGameServer() {
		return nextFirstGameServer;
	}

	public void setNextFirstGameServer(TeamSide nextFirstGameServer) {
		this.nextFirstGameServer = nextFirstGameServer;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
}
