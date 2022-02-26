package com.playzim.placarbt;

import static java.lang.Math.abs;

public class SuperTieScore extends ScoreManager {

    private int[] serviceOrder = {0,1,1,0,0,1,1,0};

    public SuperTieScore(GameBT gameBT){
        super(gameBT);
        if (gameBT.getCurrentServer() == TeamSide.TEAM_B){
            serviceOrder = new int[]{1,0,0,1,1,0,0,1};
        }
    }

    @Override
    public void score(int teamSide) {
        gameBT.getPoints()[teamSide]++;
        changeServer();
        if ((gameBT.getPoints()[teamSide] >= gameBT.getMaxSuperTie()) && ((abs(gameBT.getPoints()[0] - gameBT.getPoints()[1])) >= 2)) {
                gameBT.getSets()[teamSide]++;
                gameBT.getGames()[0] = 0;
                gameBT.getGames()[1] = 0;
                setHasFinished = true;
            }

    }

    private void changeServer() {
        int somaPontos = gameBT.getPoints()[0] + gameBT.getPoints()[1];
        int server = somaPontos % 8;
        gameBT.setCurrentServer(serviceOrder[server]);
    }
}
