package com.playzim.placarbt;

public abstract class ScoreManager {
	
	protected GameBT gameBT;
	protected boolean setHasFinished;
	public ScoreManager(GameBT gameBT){
		this.gameBT = gameBT;
		this.setHasFinished = false;
	}
	
	public abstract void score(int teamSide);

	public boolean hasSetFinished(){
		return setHasFinished;
	}

}
