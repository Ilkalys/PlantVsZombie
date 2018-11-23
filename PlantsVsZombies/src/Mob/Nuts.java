package Mob;

import Resources.StdDraw;
import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Nuts extends Plant {

	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	
	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	public static Timer getCooldown() {
		return cooldown;
	}


	public static void setCooldown(Timer cooldown) {
		Nuts.cooldown = cooldown;
	}


	/**
	 * Constructeur
	 * 
	 * @param x coordonne X de la plante
	 * @param y coordonne Y de la plante
	 */
	public Nuts(double x, double y){
		super(x, y);
		this.setLife(1500);
		Nuts.setPrice(50);
		cooldown = new Timer(1);
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

	}
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public  void dessine() {
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}
	
	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		cooldown = new Timer(20000);
	}
	
}
