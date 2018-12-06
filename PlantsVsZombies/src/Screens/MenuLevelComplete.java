package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.ZombieSpawner;

public class MenuLevelComplete extends GameScreen {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	private File SpriteFilepath;
	
	public MenuLevelComplete() {
		super();
		SpriteFilepath = new File("sprites");
	}

	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x <= 0.68 && x >= 0.52 && y >= 0.57 && y <= 0.63) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(ZombieSpawner.getCurrentDifficulty()+1));
		}
		else if(x <= 0.48 && x >= 0.32 && y >= 0.57 && y <= 0.63) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}
		 
	}

	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(0.5, 0.5, 0.5);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.text(0.5, 0.9, "Congratulation");
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.8, "Level Complete!");
		StdDraw.picture(0.6, 0.6,(StdDraw.mouseX() <= 0.68 && StdDraw.mouseX() >= 0.52 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?SpriteFilepath.getAbsolutePath() + "/button/start!_p.png" : SpriteFilepath.getAbsolutePath() + "/button/start!.png", 0.16, 0.06);
		StdDraw.picture(0.4, 0.6,(StdDraw.mouseX() <= 0.48 && StdDraw.mouseX() >= 0.32 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?SpriteFilepath.getAbsolutePath() + "/button/quitter_p.png" : SpriteFilepath.getAbsolutePath() + "/button/quitter.png", 0.16, 0.06);


	}

}
