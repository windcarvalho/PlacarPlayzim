package com.playzim.placarbt;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

public class GameBT {

    private String teamA;
    private String teamB;
    private int[] sets = {0, 0};
    private int[] games = {0, 0};
    private int[] points = {0, 0};
    private boolean gameHasFinished;

    private int maxSets = 5;
    private int maxGames = 6;
    private int maxTieBrake = 7;
    private boolean hasSuperTie = true;
    private int maxSuperTie = 10;
    private TeamSide currentServer = TeamSide.TEAM_A;
    private TeamSide nextFirstGameServer = TeamSide.TEAM_B;

    public TeamSide getNextFirstGameServer() {
		return nextFirstGameServer;
	}

	public GameBT(String teamA, String teamB, int maxSets, int maxGames, int maxTieBrake, boolean hasSuperTie, int maxSuperTie, TeamSide firstServer) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.maxSets = maxSets;
        this.maxGames = maxGames;
        this.maxTieBrake = maxTieBrake;
        this.hasSuperTie = hasSuperTie;
        this.maxSuperTie = maxSuperTie;
        this.setInitialServer(firstServer);
        this.scoreManager = new NormalScore(this);
    }

    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    private ScoreManager scoreManager;


    public static int TeamToInt(TeamSide teamSide){
        int ret = 0;
        if (teamSide == TeamSide.TEAM_B) {
            ret = 1;
        }
        return ret;
    }

    private List<GameState> gameState = new ArrayList<GameState>();

    public void score(TeamSide teamSide) {
        int team = TeamToInt(teamSide);
        if (!gameHasFinished) {
            saveGameState();
            scoreManager.score(team);
            if (scoreManager.hasSetFinished()) {
                gameHasFinished = checkGameHasFinished(team);
            }
        }
    }

    private void saveGameState(){
        GameState gs = createGameState();
        this.gameState.add(gs);
    }
    
    public void undo() {
    	if (this.gameState.size() != 0) {
    		GameState gs = gameState.get(gameState.size() - 1);    		
    		updateGameState(gs);
    		gameHasFinished = false;
    		gameState.remove(gameState.size() - 1);
    	}
    	
    }

    private void updateGameState(GameState gs) {
        sets[0] = gs.getSets()[0];
        sets[1] = gs.getSets()[1];
        games[0] = gs.getGames()[0];
        games[1] = gs.getGames()[1];
        points[0] = gs.getPoints()[0];
        points[1] = gs.getPoints()[1]; 
        this.currentServer = gs.getCurrentServer();
        this.nextFirstGameServer = gs.getNextFirstGameServer();	
        if (gs.getGameMode() == GameMode.NORMAL) this.scoreManager = new NormalScore(this);
        if (gs.getGameMode() == GameMode.TIEBRAKE) this.scoreManager = new TieBrakeScore(this);
        if (gs.getGameMode() == GameMode.SUPERTIE) this.scoreManager = new SuperTieScore(this);
	}

	private GameState createGameState(){
		GameMode gm = GameMode.NORMAL;
		if (this.scoreManager instanceof TieBrakeScore) gm = GameMode.TIEBRAKE;
		if (this.scoreManager instanceof SuperTieScore) gm = GameMode.SUPERTIE;
        GameState gs = new GameState(this.sets.clone(), this.games.clone(), this.points.clone(), this.currentServer, this.nextFirstGameServer, gm);
        return gs;
    }


    private boolean checkGameHasFinished(int team) {
        boolean hasFinished = false;
        int setsToWin = 1;
        if (this.maxSets != 1) {
            setsToWin = Math.abs(this.maxSets/2) + 1;
        }
        if (this.sets[team] == setsToWin )
            hasFinished = true;
        else if ((sets[0] == (setsToWin - 1)) && (sets[1] == (setsToWin - 1 ))){
        	this.scoreManager = new NormalScore(this);
            if (this.hasSuperTie) {
                this.scoreManager = new SuperTieScore(this);
            }
     //       setInitialServer(this.nextFirstGameServer);
        } else {
        	this.scoreManager = new NormalScore(this);
        }
        return hasFinished;
    }

    public String getTeamA (){
        return teamA;
    }
    public String getTeamB (){
        return teamB;
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

    public boolean isGameHasFinished() {
        return gameHasFinished;
    }

    public void setGameHasFinished(boolean gameHasFinished) {
        this.gameHasFinished = gameHasFinished;
    }

    public int getMaxSets() {
        return maxSets;
    }

    public void setMaxSets(int maxSets) {
        this.maxSets = maxSets;
    }

    public int getMaxGames() {
        return maxGames;
    }

    public void setMaxGames(int maxGames) {
        this.maxGames = maxGames;
    }

    public int getMaxTieBrake() {
        return maxTieBrake;
    }

    public void setMaxTieBrake(int maxTieBrake) {
        this.maxTieBrake = maxTieBrake;
    }

    public boolean isHasSuperTie() {
        return hasSuperTie;
    }

    public void setHasSuperTie(boolean hasSuperTie) {
        this.hasSuperTie = hasSuperTie;
    }

    public int getMaxSuperTie() {
        return maxSuperTie;
    }

    public void setMaxSuperTie(int maxSuperTie) {
        this.maxSuperTie = maxSuperTie;
    }

    public TeamSide getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(int i) {
        this.currentServer = TeamSide.TEAM_A;
        if (i == 1){
            this.currentServer = TeamSide.TEAM_B;
        }
    }

    public void setInitialServer(TeamSide server) {
        this.currentServer = TeamSide.TEAM_A;
        this.nextFirstGameServer = TeamSide.TEAM_B;
        if (server == TeamSide.TEAM_B){
            this.currentServer = TeamSide.TEAM_B;
            this.nextFirstGameServer = TeamSide.TEAM_A;
        }
    }

    
    
    public void changeServer() {
        if (currentServer == TeamSide.TEAM_A){
            currentServer = TeamSide.TEAM_B;
        } else {
            currentServer = TeamSide.TEAM_A;
        }
    }
}
