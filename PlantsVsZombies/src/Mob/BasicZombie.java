package Mob;

import Resources.*;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class BasicZombie extends Zombie {
	
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
	public BasicZombie(double x, double y) {
		super(x, y);
		this.setLife(200);
		this.setDamage(30);
		this.setSpeed(0.0025);
	}
		
	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public  void dessine() {
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}
		
}
