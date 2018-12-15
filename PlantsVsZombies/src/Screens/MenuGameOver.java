package Screens;

import java.awt.Font;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.ZombieSpawner;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class MenuGameOver  extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le dossier de sprite de BackGround
	private static final String SPRITEFILEPATH = Game.getSpritefilepath().toString();
	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Construit la sc�ne du Menu de Selections de Niveaux
	 */
	public MenuGameOver() {
		//Demarrage de la musique de d�faite
		SoundPlayer.PlayBGM("SadWestern.wav");
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
		//Redemarre le niveau rat�
		if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(ZombieSpawner.getCurrentDifficulty()));
		}
		// Retour au Menu Principal
		else if(x <= 0.589 && x >= 0.429 && y >= 0.54 && y <= 0.59) {
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
		//Affichage du Fond
		StdDraw.picture(0.5, 0.5, SPRITEFILEPATH + "/bg/night.png", 1, 1);
		StdDraw.picture(0.509, 0.57,SPRITEFILEPATH + "/set/nightSign.png", 0.51, 0.51);
		
		//Affichage du Texte des Boutons
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.9, "GAME OVER");
		StdDraw.setFont(new Font("sans serif",10,20));
		StdDraw.text(0.509, 0.65,"Reessayer");
		StdDraw.text(0.509, 0.57,"Menu Principal");
	}

}