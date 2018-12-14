package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;
import Resources.ZombieSpawner;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class MenuLevelComplete extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers les dossiers de sprites
	private static final File SPRITEFILEPATH = new File("sprites");

	// Durée de la musique de victoire
	private static final double DURATIONSONG = 22.857;
	// Timer determinant quand la musique se lance, 
	//puis une fois lancé devient un Timer de la durée de la musique pour gerer les animations
	private Timer launchSong = new Timer((int)(2_000));
	// indique où en est la musique
	private double actualTime;
	// L'effet sonore a-t'il ete joue ou non
	private boolean sePlayded = false;

	// Position Y initial des Boutons
	private double crossYPosition = 1.35;


	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Construit la scène de Victoire
	 */
	public MenuLevelComplete() {
		//Stop toute musique au démarrage
		SoundPlayer.StopBGM();
	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------

	/**  
	 * Gestion des interactions clavier avec l'utilisateur
	 *	
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		//Rien à faire
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		//Gere l'appuie sur le bouton pour passer au niveau suivant
		if(x <= 0.85 && x >= 0.56 && y >= crossYPosition - 0.4 && y <= crossYPosition - 0.28) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(ZombieSpawner.getCurrentDifficulty()+1));
		}
		//Gere l'appuie sur le bouton pour retourner au Menu Principal
		else if(x <= 0.44 && x >= 0.15 && y >= crossYPosition - 0.4 && y <= crossYPosition - 0.28) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}

	}

	/**
	 * Fait bouger/agir toutes les entites
	 */
	public void step() {	
		// lorsque le Timer est finis alors que la musique est arretée, la musique et le Timer démarrent
		//Le Timer prend comme durée le temps de la musique
		if(SoundPlayer.getBGMClip() == null) {
			if(launchSong.hasFinished()) {
				SoundPlayer.PlayBGM("Ragtime.wav");
				launchSong = new Timer((int)(DURATIONSONG * 1_000));
			}
		}
		// Lorsque la musique tourne, calcul de actualTime et lorsque la musique et le Timer se termine on les relance en précisant alreadyPlay
		else {
			actualTime = DURATIONSONG - launchSong.getActualTime();
			if(launchSong.hasFinished())
				launchSong = new Timer((int)(DURATIONSONG * 1_000));
		}	
		// Active le bruitage du spot lumineux si celui-ci n'a pas déja été fait lorsqu'il reste 0.5s au Timer
		if(!this.sePlayded && launchSong.getActualTime() <= 0.5) {
			SoundPlayer.PlaySE("spot.wav");
			this.sePlayded = true;
		}
	}

	/**
	 * Dessine les entites du jeu
	 */
	public void dessine() {
		StdDraw.setPenColor(StdDraw.BLACK);
		
		//Affichage du BackGround
		StdDraw.picture(0.5, 0.5, SPRITEFILEPATH.getAbsolutePath() + "/bg/MenuLevelComplete.png", 1, 1);
	
		//Affichage du Texte
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.text(0.5, 0.93, "Félicitations!");
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.88, "Niveau réussi !");

		if(SoundPlayer.getBGMClip() != null)
			StdDraw.picture(0.5, 0.2, SPRITEFILEPATH.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);

		//Affichage des Boutons
		StdDraw.picture(0.3, crossYPosition, SPRITEFILEPATH.getAbsolutePath() + "/set/CrossL.png",0.4,0.8);
		StdDraw.picture(0.7, crossYPosition, SPRITEFILEPATH.getAbsolutePath() + "/set/CrossR.png",0.4,0.8);

		//Affichage des Danseurs si la musique est démarrée
		if( SoundPlayer.getBGMClip() != null) {
			animation();
		}
		// Si la musique n'est pas démarrée, affichage des danseurs si il reste moins de 5s à launchSong
		else if(launchSong.getActualTime() <= 0.5 ) {
			StdDraw.picture(0.5, 0.2, SPRITEFILEPATH.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
			StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
			StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
		}
		// Si la musique n'est pas démarrée, affichage des ombres des danseurs si il reste plus de 5s à launchSong + descente des Boutons
		else {
			StdDraw.picture(0.5, 0.5, SPRITEFILEPATH.getAbsolutePath() + "/bg/Fondu.png",1,1);
			StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_shadow.png", 0.2, 0.2);
			StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_shadow.png", 0.2, 0.2);
			crossYPosition -= 0.02;
		}

	}

	//------------------------------------------------------------------------------
	/* 
	 **      GESTIONS DES ANIMATIONS (METHODES PRIVEES)
	 **      Toutes les Animations dépandent de actualTime.
	 */
	//------------------------------------------------------------------------------

	/**
	 * Gère l'animation des 2 Danseuses
	 */
	private void animation() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_10.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_10.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_9.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_9.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_7.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_8.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_8.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_7.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*16 && actualTime <= 1.142*19){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*12 && actualTime <= 1.142*15) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*8 && actualTime <= 1.142*11){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_4.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_3.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*4 && actualTime <= 1.142*7)  {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571 ) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.4, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SPRITEFILEPATH.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
		}
	}
}
