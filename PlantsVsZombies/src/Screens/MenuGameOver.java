package Screens;

import java.awt.Font;

import Resources.Game;
import Resources.StdDraw;

public class MenuGameOver  extends GameScreen {
	
	
	public MenuGameOver() {
	}
	
	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x <= 0.68 && x >= 0.52 && y >= 0.57 && y <= 0.63) {
			Game.setWorld(new GameWorld());
		}
		else if(x <= 0.48 && x >= 0.32 && y >= 0.57 && y <= 0.63) {
			Game.setWorld(new MenuStart());
		}
		 
	}

	@Override
	public void step() {	
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(0.5,0.5,1);

		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.5, 0.9, "You Died...");
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.8, "Try Again?");
		StdDraw.picture(0.6, 0.6,(StdDraw.mouseX() <= 0.68 && StdDraw.mouseX() >= 0.52 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?"/Pictures/start!_p.png" : "/Pictures/start!.png", 0.16, 0.06);
		StdDraw.picture(0.4, 0.6,(StdDraw.mouseX() <= 0.48 && StdDraw.mouseX() >= 0.32 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?"/Pictures/quitter_p.png" : "/Pictures/quitter.png", 0.16, 0.06);


	}

}