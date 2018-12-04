package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.StdDraw;
import Resources.ZombieSpawner;

public class MenuSelectionLevel extends GameScreen {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	private File SpriteFilepath;
	
	public MenuSelectionLevel() {
		super();
		SpriteFilepath = new File("sprites");
	}

	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x >= 0.15 && x <=  0.25 && y >= 0.55 && y <= 0.65) {
			Game.setWorld(new GameWorld(1));
		}
		else if(x >= 0.35 && x <=  0.45 && y >= 0.55 && y <= 0.65) {
			Game.setWorld(new GameWorld(2));
		}
		else if(x >= 0.55 && x <=  0.65 && y >= 0.55 && y <= 0.65) {
			Game.setWorld(new GameWorld(3));
		}
		else if(x >= 0.75 && x <=  0.85 && y >= 0.55 && y <= 0.65) {
			Game.setWorld(new GameWorld(4));
		}
		else if(x >= 0.45 && x <=  0.55 && y >= 0.35 && y <= 0.45) {
			Game.setWorld(new GameWorld(5));
		}
		else if(x >= 0.41 && x <=  0.59 && y >= 0.07 && y <= 0.13) {
			Game.setWorld(new MenuStart());
		}
		 
	}

	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledSquare(0.2, 0.6, 0.05);	
		StdDraw.filledSquare(0.4, 0.6, 0.05);	
		StdDraw.filledSquare(0.6, 0.6, 0.05);	
		StdDraw.filledSquare(0.8, 0.6, 0.05);	
		StdDraw.filledSquare(0.5, 0.4, 0.05);	

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(0.5, 0.1, 0.09, 0.03);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.2, 0.6, "1");
		StdDraw.text(0.4, 0.6, "2");
		StdDraw.text(0.6, 0.6, "3");
		StdDraw.text(0.8, 0.6, "4");
		StdDraw.text(0.5, 0.4, "5");
		StdDraw.text(0.5, 0.1, "Retour");
		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.text(0.5, 0.9, "Choose Your Level");



	}

}
