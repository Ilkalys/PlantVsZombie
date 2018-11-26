package Mob;

import Resources.GameWorld;
import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Zombie extends Mob {

	private static Timer attack;
	// Nombre de degat qu'inflige un zombie
	private int damage;
	// Vitesse du zombie
	private double speed;
	
	
	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur
	 * 
	 * @param x coordonne X de la plante
	 * @param y coordonne Y de la plante
	 */
	public Zombie(double x, double y) {
		super(x, y);
		this.attack = new Timer(1);
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
		Plant obstacle = Plant.somethingHere(GameWorld.getEntites(), this.getX() - 0.01 - this.getSpeed(), this.getY());
		if(obstacle == null)
			this.position.setX(this.position.getX() - this.getSpeed());
		else if(this.attack.hasFinished()) {
			obstacle.takeDamage(this.damage);
			this.attack = new Timer(1000);
		}
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Retourne le nombre de damage du zombie
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Retourne la vitesse du zombie
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
	 * @param damage nombre de degat qu'inflige un zombie
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
