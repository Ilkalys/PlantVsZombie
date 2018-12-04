package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;

public class MenuStart extends GameScreen {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	private double ecart;
	private boolean start;
	private Timer fondu;
	private File SpriteFilepath;

	public MenuStart() {
		super();
		this.ecart = 0;
		SpriteFilepath = new File("sprites");
		SoundPlayer.PlayBGM("Blues.wav");
	}
	
	@Override
	public void processUserInput(char key) {		
	}

	public void processMouseClick(double x, double y) {
		if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68)
			start = true;
		else if(x <= 0.589 && x >= 0.429 && y >= 0.54 && y <= 0.59) {
			Game.setWorld(new MenuSelectionLevel());
		}
		else if(x <= 0.589 && x >= 0.429 && y >= 0.02 && y <= 0.07) {
			Game.setStopGame(true);
		}
	}

	public void step() {	
		if (!start) {
			ecart += 0.001;
			if (ecart >= 1.2)
				ecart = -0.2;
		}
		else {
			if(fondu == null)
				fondu = new Timer(2000);
			else if(fondu.hasFinished())
				Game.setWorld(new GameWorld(1));
		}
			
			
	}

	@Override
	public void dessine() {

		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(0.5, 0.5,(start)?SpriteFilepath.getAbsolutePath() + "\\bg\\Accueil2.png":SpriteFilepath.getAbsolutePath() + "\\bg\\Accueil1.png", 1, 1);
		StdDraw.picture(0+ecart, 0.8,SpriteFilepath.getAbsolutePath() + "\\set\\CloudDown.png", 0.51, 0.51);
		StdDraw.picture(1-ecart, 0.97,SpriteFilepath.getAbsolutePath() + "\\set\\CouldUp.png", 0.51, 0.51);
		StdDraw.picture(0.509, 0.57,SpriteFilepath.getAbsolutePath() + "\\set\\Affichage.png", 0.51, 0.51);
		StdDraw.setFont(new Font("sans serif",10,20));
		StdDraw.text(0.509, 0.65,"Nouvelle Partie");
		StdDraw.text(0.509, 0.57,"Choix Level");
		StdDraw.text(0.509, 0.05,"Quitter le Jeu");
		if(start)
			StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "\\bg\\Fondu.png",5,5);


	}

}
