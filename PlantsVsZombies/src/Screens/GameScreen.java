package Screens;

public abstract class GameScreen {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	public abstract void processUserInput(char key);
	public abstract void processMouseClick(double x, double y);
	public abstract void step();
	public abstract void dessine();
}
