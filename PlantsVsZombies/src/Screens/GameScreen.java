package Screens;

public abstract class GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	public GameScreen() {

	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES PUBLIQUES
	 */
	//------------------------------------------------------------------------------
	public abstract void processUserInput(char key);
	public abstract void processMouseClick(double x, double y);
	public abstract void step();
	public abstract void dessine();
}
