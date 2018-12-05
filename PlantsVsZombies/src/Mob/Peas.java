package Mob;

import java.io.File;

import Resources.*;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Peas extends Entite {

	// Nombre de degat qu'inflige un pois
	private static final int DAMAGE = 20;
	// Vitesse d'un pois
	private static final double SPEED = 0.5;
	// Sprite d'un zombie basique
	private static final File SPRITE_DEFAULT = new File("sprites/mob/peas.png");

	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Constructeur
	 * 
	 * @param x coordonne X du pois
	 * @param y coordonne Y du pois
	 */
	public Peas(double x, double y) {
		super(x, y);
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------

	/**
	 * Met a jour l'entite : deplacement, effectuer une action
	 */
	public  void step() {
		Zombie obstacle = Zombie.somethingHere(GameWorld.getEntites(), this.getX() - 0.02 + (SPEED / 100), this.getY());
		if(obstacle == null)
			this.position.setX(this.position.getX() + (SPEED / 100));
		else {
			obstacle.takeDamage(DAMAGE);
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
		}
		if(this.getX() >= 1.2)
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
	}
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public void dessine() {
		StdDraw.picture(this.position.getX() + 0.04, this.position.getY() + 0.003, SPRITE_DEFAULT.getAbsolutePath(), 0.02);
	}
	

	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le nombre de damage du pois
	 * 
	 * @return DAMAGE
	 */
	public static int getDamage() {
		return DAMAGE;
	}

	/**
	 * Retourne la vitesse du pois
	 * 
	 * @return SPEED
	 */
	public static double getSpeed() {
		return SPEED;
	}

	/**
	 * Retourne le sprite d'un pois
	 * 
	 * @return SPRITE
	 */
	public static File getSpriteDefault() {
		return SPRITE_DEFAULT;
	}
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	
}
