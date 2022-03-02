package com.playzim.placarbt;

import static java.lang.Math.abs;

import java.io.Serializable;

public class NormalScore extends ScoreManager {

    public NormalScore(GameBT gameBT) {
        super(gameBT);
    }

    @Override
    public void score(int teamSide) {
        switch (gameBT.getPoints()[teamSide]) {
            case 0:
                gameBT.getPoints()[teamSide] = 15;
                break;
            case 15:
                gameBT.getPoints()[teamSide] = 30;
                break;
            case 30:
                gameBT.getPoints()[teamSide] = 40;
                break;
            case 40:
                gameBT.getPoints()[0] = 0;
                gameBT.getPoints()[1] = 0;
                gameBT.getGames()[teamSide]++;
                gameBT.setInitialServer(gameBT.getNextFirstGameServer());
//                gameBT.changeServer();
                break;
        }
        if ((gameBT.getGames()[teamSide] >= gameBT.getMaxGames()) && ((abs(gameBT.getGames()[0] - gameBT.getGames()[1])) >= 2)) {
            gameBT.getSets()[teamSide]++;
            gameBT.getGames()[0] = 0;
            gameBT.getGames()[1] = 0;
            setHasFinished = true;
        } else if ( (gameBT.getMaxGames() == 4) && (gameBT.getGames()[0] == 3) && (gameBT.getGames()[1] == 3)) { // Caso particular para Sets de 3 Games
            gameBT.setScoreManager(new TieBrakeScore(gameBT));
        }
        else if ((gameBT.getGames()[0] == gameBT.getMaxGames()) && (gameBT.getGames()[1] == gameBT.getMaxGames())) {
            gameBT.setScoreManager(new TieBrakeScore(gameBT));
        }
    }

}



