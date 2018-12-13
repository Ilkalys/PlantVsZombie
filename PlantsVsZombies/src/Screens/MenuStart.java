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

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class MenuStart extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers les dossiers de sprites
	private File SpriteFilepath  = new File("sprites");

	// Verifie si le bouton pour commencer à été pressé
	private boolean launchGame;
	// Timer Avant le lancement du jeu une fois le bouton pressé
	private Timer launchTimer;

	// Veut-on afficher le menu de configuration de touche?
	private boolean menuSetButton = false;
	// Récupère la touche que l'on veut changer
	private char changeButton = ' ';
	// Veut-on afficher le menu d'Infos?
	private boolean menuInfos = false;

	// Gère la position en X des Nuages
	private double posXCloud = 0;

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Construit la scène du Menu Principal
	 */
	public MenuStart() {
		//Démarrage de la musique si celle-ci n'a pas déjà demarrée Via une autre scène
		if(SoundPlayer.getBGMPlayed() != "Blues.wav")
			SoundPlayer.PlayBGM("Blues.wav");
	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES PUBLIQUES
	 */
	//------------------------------------------------------------------------------

	/**  
	 * Gestion des interactions clavier avec l'utilisateur, ici Uniquement lors du changement des touches
	 *	
	 * @param key Touche pressee par l'utilisateur
	 */
	@Override
	public void processUserInput(char key) {	
		//Verifie si la touche appuyée n'est pas déjà prise.
		if(key != Sunflower.getKey() && key != PeasShooter.getKey() && key != Nuts.getKey() && key != Dynamite.getKey() && key != 'i') {
			//Vérifie quelle touche l'utilisateur veut changer
			if(changeButton == Sunflower.getKey()) {
				Sunflower.setKey(key);
			} else if(changeButton == PeasShooter.getKey()) {
				PeasShooter.setKey(key);
			} else if(changeButton == Nuts.getKey()) {
				Nuts.setKey(key);
			} else if(changeButton == Dynamite.getKey()) {
				Dynamite.setKey(key);
			}
		}
		//Remet changeButton de façon à ne selectionner aucune touche
		changeButton = ' ';
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		//Gestion des interactions souris lorsqu'on est dans le Menu de Configuration de Touches : Gère le bouton Retour et quel touche veut être changer celon où l'on clique
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
		//Gestion des interactions souris lorsqu'on est dans le Menu d'Informations : gère uniquement le bouton retour
		else if(menuInfos) {
			if(x <= 0.6  && x >= 0.3 && y >= 0.10 && y <= 0.15) {
				SoundPlayer.PlaySE("fire.wav");
				menuInfos = false;
			}
		}
		// Gestion des interactions souris sur la Scène Initale
		else {
			// Lance le jeu après un delai
			if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68) {
				SoundPlayer.PlaySE("fire.wav");
				launchGame = true;
			}
			// Ouvre le menu de selection de niveau
			else if(x <= 0.589 && x >= 0.429 && y >= 0.54 && y <= 0.59) {
				SoundPlayer.PlaySE("fire.wav");
				Game.setWorld(new MenuSelectionLevel());
			}
			// Ferme le Jeu
			else if(x <= 0.589 && x >= 0.429 && y >= 0.02 && y <= 0.07) {
				SoundPlayer.PlaySE("fire.wav");
				Game.setStopGame(true);
			}
			// Ouvre le Menu de Configuration de Touche
			else if(x <= 0.16 && x >= 0.04 && y >= 0.015 && y <= 0.065) {
				SoundPlayer.PlaySE("fire.wav");
				menuSetButton = true;
			}
			// Ouvre le Menu d'Informations
			else if(x <= 0.96 && x >= 0.84 && y >= 0.015 && y <= 0.065) {
				SoundPlayer.PlaySE("fire.wav");
				menuInfos = true;
			}
		}

	}

	/**
	 * Fait bouger/agir toutes les entites
	 */
	public void step() {	
		//Gestion du déplacement des Nuages tant que l'on a pas cliqué sur le bouton demarré
		if (!launchGame) {
			posXCloud += 0.001;
			if (posXCloud >= 1.2)
				posXCloud = -0.2;
		}
		//Gestion de LaunchTimer si l'utilisateur veut demarré le jeu
		else {
			if(launchTimer == null)
				launchTimer = new Timer(2000);
			else if(launchTimer.hasFinished())
				Game.setWorld(new GameWorld(1));
		}


	}

	/**
	 * Dessine les entites du jeu
	 */
	public void dessine() {

		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.BLACK);
		
		//Gère le Fond, le Titre et les Nuages
		StdDraw.picture(0.5, 0.5,(launchGame)?SpriteFilepath.getAbsolutePath() + "/bg/Accueil2.png":SpriteFilepath.getAbsolutePath() + "/bg/Accueil1.png", 1, 1);
		StdDraw.picture(0+posXCloud, 0.8,SpriteFilepath.getAbsolutePath() + "/set/CloudDown.png", 0.51, 0.51);
		StdDraw.picture(1-posXCloud, 0.97,SpriteFilepath.getAbsolutePath() + "/set/CouldUp.png", 0.51, 0.51);
		StdDraw.picture(0.509, 0.57,SpriteFilepath.getAbsolutePath() + "/set/Affichage.png", 0.51, 0.51);
		StdDraw.picture(0.5, 0.9, SpriteFilepath.getAbsolutePath() + "/set/title.png");
		
		//Affichage du Texte
		StdDraw.setFont(new Font("sans serif",10,20));
		StdDraw.text(0.509, 0.65,"Nouvelle Partie");
		StdDraw.text(0.509, 0.57,"Choix Niveau");
		StdDraw.text(0.509, 0.05,"Quitter le Jeu");
		
		//Affichage des Boutons du Menu de Configuration de Touches et du Menu d'Informations
		StdDraw.picture(0.1, 0.05,SpriteFilepath.getAbsolutePath() + "/button/keyboard.png",0.06,0.06);
		StdDraw.picture(0.1, 0.05, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.06,0.04);
		StdDraw.picture(0.9, 0.05,SpriteFilepath.getAbsolutePath() + "/button/Interrogation.png",0.03,0.03);
		StdDraw.picture(0.9, 0.05, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.06,0.04);

		//Affichage du Menu de Configuration de Touches si demandé
		if(menuSetButton)
			menuSetButton();
		//Affichage du Menu d'Informations si demandé
		else if(menuInfos)
			menuInfos();
		//Effet Assombrissant lors du démarrage du jeu
		else if(launchGame)
			StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",5,5);
	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES PRIVEES
	 */
	//------------------------------------------------------------------------------
	/**
	 * Gère l'affichage du Menu de Configuration de Touches
	 */
	private void menuSetButton() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",15,40));

		// Affichage du Fond
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);

		//Affichage du Texte, les couleurs variants en fonction de quel bouton va être changé
		StdDraw.text(0.5, 0.85,"Changement de touches :");

		StdDraw.setPenColor((changeButton == Sunflower.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.6,"Sherif : " + Sunflower.getKey());

		StdDraw.setPenColor((changeButton == PeasShooter.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.5,"Cowboy : " + PeasShooter.getKey());

		StdDraw.setPenColor((changeButton == Nuts.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.4,"Tonneau : " + Nuts.getKey());

		StdDraw.setPenColor((changeButton == Dynamite.getKey())?StdDraw.RED : StdDraw.WHITE);
		StdDraw.text(0.5, 0.3,"Dynamite : " + Dynamite.getKey());

		// Affichage du Bouton de Retour
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.15,"Retour");

		//Affichage d'un Texte dans une autre police
		StdDraw.setFont(new Font("sans serif",15,20));
		StdDraw.text(0.5, 0.7,"Touche i = Infos");
	}

	/**
	 * Gère l'affichage du Menu d'Informations
	 */
	private void menuInfos() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",15,40));

		// Affichage du Fond
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",0.8,0.8);

		// Affichage du Texte
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
		//Affichage du Bouton Retour
		StdDraw.text(0.5, 0.12,"Retour");
	}
}
