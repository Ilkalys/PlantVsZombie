package Screens;

import java.awt.Font;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class MenuVictory extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------


	// Chemin vers le dossier de sprite de BackGround
	private static final String SPRITEFILEPATH = Game.getSpritefilepath().toString();

	// Dur�e de la musique de victoire
	private static double DURATIONSONG = 22.857;
	// Timer determinant quand la musique se lance, 
	//puis une fois lanc� devient un Timer de la dur�e de la musique pour gerer les animations
	private Timer launchSong;
	// indique o� en est la musique
	private double actualTime;

	// Position Y initial du backGround
	private double bgYPosition = 0.25;
	// Position initial des personnages qui dansent
	private double persoYPosition;

	// L'effet sonore a-t'il �t� jou� ou non
	private boolean sePlayded = false;
	// La musique a-t'elle �t� jou� ou non
	private boolean alreadyPlay = false;

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Construit la sc�ne de Victoire
	 */
	public MenuVictory() {
		// cr�er un d�lai avant le d�marrage de la chanson
		launchSong = new Timer((int)(4_000));
		// arr�te la chanson qui �tait jou� pr�c�dement (s'il y en avait une)
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
		// il n'y a rien � faire avec des touches.
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		// si la musique a �t� jou� une fois, le joueur peut retourner au menu principal en cliquant n'importe o�.
		if(alreadyPlay ) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}
	}

	/**
	 * Fait bouger/agir toutes les entites
	 */
	public void step() {	
		// initialise la position des personnages sur la sc�ne
		persoYPosition = bgYPosition + 0.13;
		// lorsque le Timer est finis alors que la musique est arret�e, la musique et le Timer d�marrent
		//Le Timer prend comme dur�e le temps de la musique
		if(SoundPlayer.getBGMClip() == null) {
			if(launchSong.hasFinished()) {
				SoundPlayer.PlayBGM("Win.wav");
				launchSong = new Timer((int)(DURATIONSONG * 1_000));
			}
		}
		// Lorsque la musique tourne, calcul de actualTime et lorsque la musique et le Timer se termine on les relance en pr�cisant alreadyPlay
		else {
			actualTime = DURATIONSONG - launchSong.getActualTime();
			if(launchSong.hasFinished()) {
				alreadyPlay = true;
				launchSong = new Timer((int)(DURATIONSONG * 1_000));
			}
		}
		// Active le bruitage du spot lumineux si celui-ci n'a pas d�ja �t� fait lorsqu'il reste 0.5s au Timer
		if(!this.sePlayded && launchSong.getActualTime() <= 0.5) {
			SoundPlayer.PlaySE("spot.wav");
			this.sePlayded = true;
		}
	}

	/**
	 * Dessine les entites du jeu
	 */
	public void dessine() {
		//Affichage du BackGround, en fonction de bgYPosition
		StdDraw.picture(0.5, bgYPosition, SPRITEFILEPATH + "/bg/MenuVictory.png", 1, 1.5);

		//affichage du texte et le cercle o� sont les danseurs lorsque la musique d�marre 
		StdDraw.setPenColor(StdDraw.WHITE);
		if(SoundPlayer.getBGMClip() != null){
			StdDraw.setFont(new Font("sans serif",10,50));
			StdDraw.text(0.5, 0.97, "F�licitations!");
			StdDraw.setFont(new Font("sans serif",10,40));
			StdDraw.text(0.5, 0.9, "Vous avez termin� le Jeu !");
			StdDraw.picture(0.5, persoYPosition - 0.18, SPRITEFILEPATH + "/bg/dancefloor.png", 0.5, 0.5);
		}

		// Animation de tous les Instruments sauf le Piano lorsque la musique � d�j� �t� jou�e une fois
		//Apparation du Texte pr�cisant que l'on peut quitter la sc�ne
		if(alreadyPlay) {
			animationDrum();
			animationDoubleBass();
			animationViolin();
			animationJewsharp();
			StdDraw.setFont(new Font("sans serif",15,30));
			StdDraw.text(0.5, 0.05,"Cliquez pour Quitter");
		}
		// Gestion du lancement des Animations de tous les Instruments sauf le Piano en fonction d'o� en est la musique
		else {
			if(actualTime >= 1.142*4)
				animationDoubleBass();
			else
				StdDraw.picture(0.3, persoYPosition  -0.25, SPRITEFILEPATH + "/mob/musical/doublebass_shadow.png", 0.2, 0.2);
			if(actualTime >= 1.142*8)
				animationViolin();
			else
				StdDraw.picture(0.7, persoYPosition - 0.25, SPRITEFILEPATH + "/mob/musical/violin_shadow.png", 0.2, 0.2);
			if(actualTime >= 1.142*12)
				animationJewsharp();
			else
				StdDraw.picture(0.2, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_shadow.png", 0.2, 0.2);

			StdDraw.picture(0.8, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/drum_shadow.png", 0.2, 0.2);
		}

		//Gestion de l'animation des Danseurs et du Piano � partir du moment o� il y a de la musique
		if( SoundPlayer.getBGMClip() != null) {
			animationDanse();
			animationPiano();
		}
		else {
			// Affichage du dancefloor et les personnages 0.5s avant le d�marrage
			if(launchSong.getActualTime() <= 0.5 ) {
				StdDraw.picture(0.5, persoYPosition - 0.18, SPRITEFILEPATH + "/bg/dancefloor.png", 0.5, 0.5);
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else {
				// Gestion de l'impression descente de la cam�ra
				if(3-launchSong.getActualTime() >= 1) {
					bgYPosition +=0.0085;
				}
				// AFfichage de l'ombre des danseurs et d'un effet assombrissant
				StdDraw.picture(0.5, 0.5, SPRITEFILEPATH + "/bg/Fondu.png",1,1);
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_shadow.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_shadow.png", 0.2, 0.2);
			}
			// Affichage de l'ombre du Piano tant que la musique n'a pas demarr�
			StdDraw.picture(0.5, persoYPosition  -0.2, SPRITEFILEPATH + "/mob/musical/Piano_shadow.png", 0.2, 0.2);
		}
	}

	//------------------------------------------------------------------------------
	/* 
	 **      GESTIONS DES ANIMATIONS (METHODES PRIVEES)
	 **      Toutes les Animations d�pandent de actualTime.
	 */
	//------------------------------------------------------------------------------

	/**
	 * G�re l'animation des 2 Danseuses
	 */
	private void animationDanse() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_10.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_10.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_9.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_9.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_7.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_8.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_8.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_7.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*16 && actualTime <= 1.142*19){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*12 && actualTime <= 1.142*15) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*8 && actualTime <= 1.142*11){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_4.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_3.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*4 && actualTime <= 1.142*7)  {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571 ) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.4, persoYPosition, SPRITEFILEPATH + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SPRITEFILEPATH + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * G�re l'animation du Piano
	 */
	private void animationPiano() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SPRITEFILEPATH + "/mob/musical/Piano_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SPRITEFILEPATH + "/mob/musical/Piano_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SPRITEFILEPATH + "/mob/musical/Piano_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SPRITEFILEPATH + "/mob/musical/Piano_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SPRITEFILEPATH + "/mob/musical/Piano_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SPRITEFILEPATH + "/mob/musical/Piano_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SPRITEFILEPATH + "/mob/musical/Piano_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SPRITEFILEPATH + "/mob/musical/Piano_3.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * G�re l'animation de la contre-basse
	 */
	private void animationDoubleBass() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SPRITEFILEPATH + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SPRITEFILEPATH + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SPRITEFILEPATH + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SPRITEFILEPATH + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.3, persoYPosition - 0.25, SPRITEFILEPATH + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.3, persoYPosition - 0.25, SPRITEFILEPATH + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * G�re l'animation du violon
	 */
	private void animationViolin() {
		if(actualTime % 1.142 >= 0.8565) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SPRITEFILEPATH + "/mob/musical/violin_2.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 >= 0.571) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SPRITEFILEPATH + "/mob/musical/violin_1.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 >= 0.2855) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SPRITEFILEPATH + "/mob/musical/violin_2.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 <= 0.2855) {
			StdDraw.picture(0.7, persoYPosition  -0.25, SPRITEFILEPATH + "/mob/musical/violin_1.png", 0.2, 0.2);
		}
	}

	/**
	 * G�re l'animation de la guimbarde
	 */
	private void animationJewsharp() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
		}
	}

	/**
	 * G�re l'animation de la batterie
	 */
	private void animationDrum() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SPRITEFILEPATH + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SPRITEFILEPATH + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SPRITEFILEPATH + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SPRITEFILEPATH + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
		}
	}
}
