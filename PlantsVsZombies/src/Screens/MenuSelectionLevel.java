package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
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
		SpriteFilepath = new File("sprites/bg");
	}

	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {

		if(x >= 0.075 && x <=  0.225 && y >= 0.515 && y <= 0.685) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(1));
		}
		else if(x >= 0.25 && x <=  0.4 && y >= 0.490 && y <= 0.660) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(2));
		}
		else if(x >= 0.425 && x <=  0.575 && y >= 0.515 && y <= 0.685) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(3));
		}
		else if(x >= 0.600 && x <= 0.750  && y >= 0.490 && y <= 0.660) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(4));
		}
		else if(x >= 0.775 && x <=  0.925 && y >= 0.515 && y <= 0.685) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(5));
		}
		else if(x >= 0.075 && x <=  0.225 && y >= 0.265 && y <= 0.435) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(6));
		}
		else if(x >= 0.25 && x <=  0.4 && y >= 0.240 && y <= 0.410) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(7));
		}
		else if(x >= 0.425 && x <=  0.575 && y >= 0.265 && y <= 0.435) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(8));
		}
		else if(x >= 0.600 && x <= 0.750  && y >= 0.240 && y <= 0.345) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(9));
		}
		else if(x >= 0.775 && x <=  0.925 && y >= 0.265 && y <= 0.435) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(10));
		}

	}

	@Override
	public void step() {		
	}

	@Override
	public void dessine() {

		StdDraw.setFont();
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.picture(0.5, 0.5,SpriteFilepath.getAbsolutePath() + "/MenuLevel.png", 1, 1);
		StdDraw.picture(0.15, 0.6,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.15, 0.6, "1");

		StdDraw.picture(0.325, 0.575,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.325, 0.575, "2");

		StdDraw.picture(0.5, 0.6,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.5, 0.6, "3");

		StdDraw.picture(0.675, 0.575,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.675, 0.575, "4");

		StdDraw.picture(0.85, 0.6,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.85, 0.6, "5");

		StdDraw.picture(0.15, 0.35,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.15, 0.35, "6");

		StdDraw.picture(0.325, 0.325,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.325, 0.325, "7");

		StdDraw.picture(0.5, 0.35,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.5, 0.35, "8");

		StdDraw.picture(0.675, 0.325,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.675, 0.325, "9");

		StdDraw.picture(0.85, 0.35,SpriteFilepath.getAbsolutePath() + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.85, 0.35, "10");


	}


}
