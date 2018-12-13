package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;

public class MenuVictory extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers les dossiers de sprites
	private File SpriteFilepath = new File("sprites");

	// Durée de la musique de victoire
	private double durationSong = 22.857;
	// Timer determinant quand la musique se lance, 
	//puis une fois lancé devient un Timer de la durée de la musique pour gerer les animations
	private Timer launchSong;
	// indique où en est la musique
	private double actualTime;

	// Position Y initial du backGround
	private double bgYPosition = 0.25;
	// Position initial des personnages qui dansent
	private double persoYPosition;

	// L'effet sonore a-t'il été joué ou non
	private boolean sePlayded = false;
	// La musique a-t'elle été joué ou non
	private boolean alreadyPlay = false;

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Construit la scène de Victoire
	 */
	public MenuVictory() {
		// créer un délai avant le démarrage de la chanson
		launchSong = new Timer((int)(4_000));
		// arrête la chanson qui était joué précédement (s'il y en avait une)
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
	@Override
	public void processUserInput(char key) {
		// il n'y a rien à faire avec des touches.
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	@Override
	public void processMouseClick(double x, double y) {
		// si la musique a été joué une fois, le joueur peut retourner au menu principal en cliquant n'importe où.
		if(alreadyPlay ) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}
	}

	/**
	 * Fait bouger/agir toutes les entites
	 */
	@Override
	public void step() {	
		// initialise la position des personnages sur la scène
		persoYPosition = bgYPosition + 0.13;
		// lorsque le Timer est finis alors que la musique est arretée, la musique et le Timer démarrent
		//Le Timer prend comme durée le temps de la musique
		if(SoundPlayer.getBGMClip() == null) {
			if(launchSong.hasFinished()) {
				SoundPlayer.PlayBGM("Win.wav");
				launchSong = new Timer((int)(durationSong * 1_000));
			}
		}
		// Lorsque la musique tourne, calcul de actualTime et lorsque la musique et le Timer se termine on les relance en précisant alreadyPlay
		else {
			actualTime = durationSong - launchSong.getActualTime();
			if(launchSong.hasFinished()) {
				alreadyPlay = true;
				launchSong = new Timer((int)(durationSong * 1_000));
			}
		}
		// Active le bruitage du spot lumineux si celui-ci n'a pas déja été fait lorsqu'il reste 0.5s au Timer
		if(launchSong.getActualTime() <= 0.5 && !this.sePlayded) {
			SoundPlayer.PlaySE("spot.wav");
			this.sePlayded = true;
		}
	}

	/**
	 * Dessine les entites du jeu
	 */
	@Override
	public void dessine() {
		//Affichage du BackGround, en fonction de bgYPosition
		StdDraw.picture(0.5, bgYPosition, SpriteFilepath.getAbsolutePath() + "/bg/MenuVictory.png", 1, 1.5);

		//affichage du texte et le cercle où sont les danseurs lorsque la musique démarre 
		StdDraw.setPenColor(StdDraw.WHITE);
		if(SoundPlayer.getBGMClip() != null){
			StdDraw.setFont(new Font("sans serif",10,50));
			StdDraw.text(0.5, 0.97, "Félicitations!");
			StdDraw.setFont(new Font("sans serif",10,40));
			StdDraw.text(0.5, 0.9, "Vous avez terminé le Jeu !");
			StdDraw.picture(0.5, persoYPosition - 0.18, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
		}

		// Animation de tous les Instruments sauf le Piano lorsque la musique à déjà été jouée une fois
		//Apparation du Texte précisant que l'on peut quitter la scène
		if(alreadyPlay) {
			animationDrum();
			animationDoubleBass();
			animationViolin();
			animationJewsharp();
			StdDraw.setFont(new Font("sans serif",15,30));
			StdDraw.text(0.5, 0.05,"Cliquez pour Quitter");
		}
		// Gestion du lancement des Animations de tous les Instruments sauf le Piano en fonction d'où en est la musique
		else {
			if(actualTime >= 1.142*4)
				animationDoubleBass();
			else
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_shadow.png", 0.2, 0.2);
			if(actualTime >= 1.142*8)
				animationViolin();
			else
				StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_shadow.png", 0.2, 0.2);
			if(actualTime >= 1.142*12)
				animationJewsharp();
			else
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_shadow.png", 0.2, 0.2);

			StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_shadow.png", 0.2, 0.2);
		}

		//Gestion de l'animation des Danseurs et du Piano à partir du moment où il y a de la musique
		if( SoundPlayer.getBGMClip() != null) {
			animationDanse();
			animationPiano();
		}
		else {
			// Affichage du dancefloor et les personnages 0.5s avant le démarrage
			if(launchSong.getActualTime() <= 0.5 ) {
				StdDraw.picture(0.5, persoYPosition - 0.18, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else {
				// Gestion de l'impression descente de la caméra
				if(3-launchSong.getActualTime() >= 1) {
					bgYPosition +=0.0085;
				}
				// AFfichage de l'ombre des danseurs et d'un effet assombrissant
				StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",1,1);
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_shadow.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_shadow.png", 0.2, 0.2);
			}
			// Affichage de l'ombre du Piano tant que la musique n'a pas demarré
			StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_shadow.png", 0.2, 0.2);
		}
	}

	//------------------------------------------------------------------------------
	//------------------------------------------------------------------------------
	/* 
	**      GESTIONS DES ANIMATIONS (METHODES PRIVEES)
	**      Toutes les Animations dépandent de actualTime.
	 */
	//------------------------------------------------------------------------------

	/**
	 * Gère l'animation des 2 Danseuses
	 */
	private void animationDanse() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_10.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_10.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_9.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_9.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_7.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_8.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_8.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_7.png", 0.2, 0.2);
			}

		}
		else if(actualTime >= 1.142*16 && actualTime <= 1.142*19){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*12 && actualTime <= 1.142*15) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*8 && actualTime <= 1.142*11){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_4.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_3.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*4 && actualTime <= 1.142*7)  {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571 ) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * Gère l'animation du Piano
	 */
	private void animationPiano() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_2.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_3.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * Gère l'animation de la contre-basse
	 */
	private void animationDoubleBass() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.3, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.3, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * Gère l'animation du violon
	 */
	private void animationViolin() {
		if(actualTime % 1.142 >= 0.8565) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_2.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 >= 0.571) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_1.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 >= 0.2855) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_2.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 <= 0.2855) {
			StdDraw.picture(0.7, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_1.png", 0.2, 0.2);
		}
	}

	/**
	 * Gère l'animation de la guimbarde
	 */
	private void animationJewsharp() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * Gère l'animation de la batterie
	 */
	private void animationDrum() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
		}
	}
}
