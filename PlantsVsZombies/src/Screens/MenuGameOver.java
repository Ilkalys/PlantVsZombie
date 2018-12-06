package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.ZombieSpawner;

public class MenuGameOver  extends GameScreen {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	private File SpriteFilepath;
	
	public MenuGameOver() {
		SoundPlayer.PlayBGM("SadWestern.wav");
		SpriteFilepath = new File("sprites");
	}
	
	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68) {
		//if(x <= 0.68 && x >= 0.52 && y >= 0.57 && y <= 0.63) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(ZombieSpawner.getCurrentDifficulty()));
		}
		else if(x <= 0.589 && x >= 0.429 && y >= 0.54 && y <= 0.59) {
		//else if(x <= 0.48 && x >= 0.32 && y >= 0.57 && y <= 0.63) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}
		 
	}

	@Override
	public void step() {	
	}

	@Override
	public void dessine() {
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/night.png", 1, 1);
		StdDraw.picture(0.509, 0.57,SpriteFilepath.getAbsolutePath() + "/set/nightSign.png", 0.51, 0.51);
		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.9, "GAME OVER");
		StdDraw.setFont(new Font("sans serif",10,20));
		StdDraw.text(0.509, 0.65,"Reessayer");
		StdDraw.text(0.509, 0.57,"Menu Principal");
		/*
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(0.5,0.5,1);
		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.5, 0.9, "You Died...");
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.8, "Try Again?");
		StdDraw.picture(0.6, 0.6,(StdDraw.mouseX() <= 0.68 && StdDraw.mouseX() >= 0.52 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?SpriteFilepath.getAbsolutePath() + "/button/start!_p.png" : SpriteFilepath.getAbsolutePath() + "/button/start!.png", 0.16, 0.06);
		StdDraw.picture(0.4, 0.6,(StdDraw.mouseX() <= 0.48 && StdDraw.mouseX() >= 0.32 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?SpriteFilepath.getAbsolutePath() + "/button/quitter_p.png" : SpriteFilepath.getAbsolutePath() + "/button/quitter.png", 0.16, 0.06);
		*/
	}

}