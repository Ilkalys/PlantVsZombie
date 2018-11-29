package Mob;

import Resources.*;
import Screens.GameWorld;

public class Peas extends Entite {

	// Nombre de degat qu'inflige un pois
	private int damage;
	// Vitesse du pois
	private double speed;
	
	
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
		this.setDamage(20);
		this.setSpeed(0.005);
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
		Zombie obstacle = Zombie.somethingHere(GameWorld.getEntites(), this.getX() - 0.02 + this.getSpeed(), this.getY());
		if(obstacle == null)
			this.position.setX(this.position.getX() + this.getSpeed());
		else {
			obstacle.takeDamage(this.damage);
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
		}
		if(this.getX() >= 1.2)
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
	}
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public void dessine() {
		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.02);
	}
	

	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Retourne le nombre de damage du pois
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Retourne la vitesse du pois
	 * 
	 * @return speed
	 */
	public double getSpeed() {
		return speed;
	}

	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Modifie damage
	 * 
	 * @param damage nombre de degat qu'inflige un pois
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Modifie speed
	 * 
	 * @param speed vitesse du zombie
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
