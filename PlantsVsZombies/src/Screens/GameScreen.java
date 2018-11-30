package Screens;

public abstract class GameScreen {
	public abstract void processUserInput(char key);
	public abstract void processMouseClick(double x, double y);
	public abstract void step();
	public abstract void dessine();
}
