package Screens;

import Resources.SoundPlayer;

public abstract class GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	public GameScreen() {
		SoundPlayer.stopAll();
	}
	
	public abstract void processUserInput(char key);
	public abstract void processMouseClick(double x, double y);
	public abstract void step();
	public abstract void dessine();
}
