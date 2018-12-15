package Screens;

import java.awt.Font;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class MenuSelectionLevel extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le dossier de sprite de BackGround
	private static final String SPRITEFILEPATH = Game.getSpritefilepath().toString() + "/bg";

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Construit la sc�ne du Menu de Selections de Niveaux
	 */
	public MenuSelectionLevel() {
	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES PUBLIQUES
	 */
	//------------------------------------------------------------------------------

	/**  
	 * Gestion des interactions clavier avec l'utilisateur, ici inutile
	 *	
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		// Rien � faire via les touches
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		//D�marre le Niveau souhait�
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
		// Retour au Menu Principal
		else if(x >= 0.35 && x <= 0.65 && y >= 0.03 && y <=0.07) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}
	}

	/**
	 * Fait bouger/agir toutes les entites, ici inutile
	 */
	public void step() {	
		//Rien de bouge sur cette sc�ne
	}

	/**
	 * Dessine les entites du jeu
	 */
	public void dessine() {

		StdDraw.setFont(new Font("sans serif",10,40 * Game.getCanvasXY() /1000));
		//Affichage du Fond
		StdDraw.picture(0.5, 0.5,SPRITEFILEPATH + "/MenuLevel.png", 1, 1);
		
		//Affichage des diff�rentes Fiches amenant aux Niveaux
		StdDraw.picture(0.15, 0.6,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.15, 0.6, "1");
		StdDraw.picture(0.325, 0.575,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.325, 0.575, "2");
		StdDraw.picture(0.5, 0.6,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.5, 0.6, "3");
		StdDraw.picture(0.675, 0.575,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.675, 0.575, "4");
		StdDraw.picture(0.85, 0.6,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.85, 0.6, "5");
		StdDraw.picture(0.15, 0.35,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.15, 0.35, "6");
		StdDraw.picture(0.325, 0.325,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.325, 0.325, "7");
		StdDraw.picture(0.5, 0.35,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.5, 0.35, "8");
		StdDraw.picture(0.675, 0.325,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.675, 0.325, "9");
		StdDraw.picture(0.85, 0.35,SPRITEFILEPATH + "/FicheWanted.png", 0.2, 0.2);
		StdDraw.text(0.85, 0.35, "10");
		
		//Affichage du Texte pour retourner au Menu
		StdDraw.text(0.5, 0.05, "Retour au Menu");

	}
}