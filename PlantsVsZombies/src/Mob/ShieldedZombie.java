package Mob;

import Resources.StdDraw;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class ShieldedZombie extends Zombie {
	
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
	public ShieldedZombie(double x, double y) {
		super(x, y);
		this.setLife(560);
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
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}
		
}
