package Screens;

import java.awt.Font;
import java.io.File;

import Mob.Dynamite;
import Mob.Nuts;
import Mob.PeasShooter;
import Mob.Sunflower;
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
	private boolean menuSetButton = false;
	private boolean menuInfos = false;
	private char changeButton;


	public MenuStart() {
		if(SoundPlayer.getBGMPlayed() != "Blues.wav")
			SoundPlayer.PlayBGM("Blues.wav");
		this.ecart = 0;
		SpriteFilepath = new File("sprites");
		changeButton = ' ';
	}

	@Override
	public void processUserInput(char key) {	
		if(changeButton == Sunflower.getKey()) {
			if(key != PeasShooter.getKey() && key != Nuts.getKey() && key != Dynamite.getKey() && key != 'i')
				Sunflower.setKey(key);
			changeButton = ' ';
		} else if(changeButton == PeasShooter.getKey()) {
			if(key != Sunflower.getKey() && key != Nuts.getKey() && key != Dynamite.getKey() && key != 'i')
				PeasShooter.setKey(key);
			changeButton = ' ';
		} else if(changeButton == Nuts.getKey()) {
			if(key != PeasShooter.getKey() && key != Sunflower.getKey() && key != Dynamite.getKey() && key != 'i')
				Nuts.setKey(key);
			changeButton = ' ';
		} else if(changeButton == Dynamite.getKey()) {
			if(key != PeasShooter.getKey() && key != Nuts.getKey() && key != Sunflower.getKey() && key != 'i')
				Dynamite.setKey(key);
			changeButton = ' ';
		}
	}

	public void processMouseClick(double x, double y) {
		if(menuSetButton) {
			if(x <= 0.6  && x >= 0.3 && y >= 0.13 && y <= 0.18) {
				SoundPlayer.PlaySE("fire.wav");
				changeButton = ' ';
				menuSetButton = false;
			}
			else if(x <= 0.6  && x >= 0.4 && y >= 0.58 && y <= 0.62) {
				SoundPlayer.PlaySE("fire.wav");
				changeButton = Sunflower.getKey();
			}
			else if(x <= 0.6  && x >= 0.4 && y >= 0.48 && y <= 0.52) {
				SoundPlayer.PlaySE("fire.wav");
				changeButton = PeasShooter.getKey();
			}
			else if(x <= 0.6  && x >= 0.4 && y >= 0.38 && y <= 0.42) {
				SoundPlayer.PlaySE("fire.wav");
				changeButton = Nuts.getKey();
			}
			else if(x <= 0.6  && x >= 0.4 && y >= 0.28 && y <= 0.32) {
				SoundPlayer.PlaySE("fire.wav");
				changeButton = Dynamite.getKey();
			}
		}
		else if(menuInfos) {
			if(x <= 0.6  && x >= 0.3 && y >= 0.10 && y <= 0.15) {
				SoundPlayer.PlaySE("fire.wav");
				menuInfos = false;
			}
		}
		else {
			if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68) {
				SoundPlayer.PlaySE("fire.wav");
				start = true;
			}
			else if(x <= 0.589 && x >= 0.429 && y >= 0.54 && y <= 0.59) {
				SoundPlayer.PlaySE("fire.wav");
				Game.setWorld(new MenuSelectionLevel());
			}
			else if(x <= 0.589 && x >= 0.429 && y >= 0.02 && y <= 0.07) {
				SoundPlayer.PlaySE("fire.wav");
				Game.setStopGame(true);
			}
			else if(x <= 0.16 && x >= 0.04 && y >= 0.015 && y <= 0.065) {
				SoundPlayer.PlaySE("fire.wav");
				menuSetButton = true;
			}
			else if(x <= 0.96 && x >= 0.84 && y >= 0.015 && y <= 0.065) {
				SoundPlayer.PlaySE("fire.wav");
				menuInfos = true;
			}
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
		StdDraw.setFont();
		StdDraw.picture(0.5, 0.5,(start)?SpriteFilepath.getAbsolutePath() + "/bg/Accueil2.png":SpriteFilepath.getAbsolutePath() + "/bg/Accueil1.png", 1, 1);
		StdDraw.picture(0+ecart, 0.8,SpriteFilepath.getAbsolutePath() + "/set/CloudDown.png", 0.51, 0.51);
		StdDraw.picture(1-ecart, 0.97,SpriteFilepath.getAbsolutePath() + "/set/CouldUp.png", 0.51, 0.51);
		StdDraw.picture(0.509, 0.57,SpriteFilepath.getAbsolutePath() + "/set/Affichage.png", 0.51, 0.51);
		StdDraw.picture(0.5, 0.9, SpriteFilepath.getAbsolutePath() + "/set/title.png");
		StdDraw.setFont(new Font("sans serif",10,20));
		StdDraw.text(0.509, 0.65,"Nouvelle Partie");
		StdDraw.text(0.509, 0.57,"Choix Level");
		StdDraw.text(0.509, 0.05,"Quitter le Jeu");

		StdDraw.picture(0.1, 0.05,SpriteFilepath.getAbsolutePath() + "/button/keyboard.png",0.06,0.06);
		StdDraw.picture(0.1, 0.05, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.06,0.04);
		StdDraw.picture(0.9, 0.05,SpriteFilepath.getAbsolutePath() + "/button/Interrogation.png",0.03,0.03);
		StdDraw.picture(0.9, 0.05, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.06,0.04);

		if(menuSetButton)
			menuSetButton();
		else if(menuInfos)
			menuInfos();
		else if(start)
			StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",5,5);


	}

	private void menuSetButton() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",15,40));

		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.text(0.5, 0.85,"Changement de touches :");

		StdDraw.setPenColor((changeButton == Sunflower.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.6,"Spawn Sherif : " + Sunflower.getKey());

		StdDraw.setPenColor((changeButton == PeasShooter.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.5,"Spawn CowBoy :" + PeasShooter.getKey());

		StdDraw.setPenColor((changeButton == Nuts.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.4,"Spawn Tonneau :" + Nuts.getKey());

		StdDraw.setPenColor((changeButton == Dynamite.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.3,"Spawn Dynamite :" + Dynamite.getKey());

		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.15,"Retour");
		StdDraw.setFont(new Font("sans serif",15,20));
		StdDraw.text(0.5, 0.7,"Touche i = Infos");

		StdDraw.setPenColor(StdDraw.BLACK);
	}

	private void menuInfos() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",15,40));

		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);

		StdDraw.text(0.5, 0.85,"Informations");
		StdDraw.setFont(new Font("sans serif",15,20));
		StdDraw.text(0.5, 0.78,"Etoiles : Monnaie du jeu, apparaît aléatoirement");
		StdDraw.text(0.5, 0.7,"Sherif : Fournit des étoiles");
		StdDraw.text(0.5, 0.665,"CowBoy : Inflige des dégats en tirant en ligne droite");
		StdDraw.text(0.5, 0.63,"Tonneau : Possède une grande quantité de vie pour bloquer les ennemis");
		StdDraw.text(0.5, 0.595,"Dynamite : Posez la et cliquez dessus pour faire une explosion en croix");
		StdDraw.text(0.5, 0.5,"Cactus : Ennemi de base");
		StdDraw.text(0.5, 0.465,"Cactus blindé : Ennemi avec plus de vie");
		StdDraw.text(0.5, 0.43,"Cactus explosif : Ennemi plus rapide, qui n'attaque pas mais explose en croix à la mort");
		StdDraw.text(0.5, 0.35, "Si un cactus atteint la gauche de l'écran, vous avez perdu.");
		StdDraw.text(0.5, 0.32, "Survivez à la vague de cactus pour reussir le niveau.");
		StdDraw.text(0.5, 0.25, "Réalisé par.");
		StdDraw.text(0.5, 0.22, "Matthieu Gauget-Berlioz et Julien Cochet");
		StdDraw.picture(0.3, 0.22, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png",0.1,0.1);
		StdDraw.picture(0.7, 0.22, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png",0.1,0.1);


		StdDraw.setFont(new Font("sans serif",15,30));

		StdDraw.text(0.5, 0.12,"Retour");
	}
}
