package com.playzim.placarbt;

import static java.lang.Math.abs;

public class TieBrakeScore extends ScoreManager {

    private int[] serviceOrder = {0,1,1,0,0,1,1,0};

    public TieBrakeScore(GameBT gameBT){
        super(gameBT);
        if (gameBT.getCurrentServer() == TeamSide.TEAM_B){
            serviceOrder = new int[]{1,0,0,1,1,0,0,1};
        }
    }

    @Override
    public void score(int teamSide) {
        gameBT.getPoints()[teamSide]++;
        changeServer();
        if ((gameBT.getPoints()[teamSide] >= gameBT.getMaxTieBrake()) && ((abs(gameBT.getPoints()[0] - gameBT.getPoints()[1])) >= 2)) {
            if (gameBT.getMaxSets() != 1){ // apenas para não zerar placar
                gameBT.getPoints()[0] = 0;
                gameBT.getPoints()[1] = 0;
                gameBT.getGames()[0] = 0;
                gameBT.getGames()[1] = 0;
                gameBT.setInitialServer(gameBT.getNextFirstGameServer());
            }
            gameBT.getSets()[teamSide]++;
            setHasFinished = true;
        }

    }

    private void changeServer() {
        int somaPontos = gameBT.getPoints()[0] + gameBT.getPoints()[1];
        int server = somaPontos % 8;
        gameBT.setCurrentServer(serviceOrder[server]);
    }
}
