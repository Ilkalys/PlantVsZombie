package Mob;

import Resources.StdDraw;
import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class PeasShooter extends Plant {
	
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
		PeasShooter.cooldown = cooldown;
	}


	/**
	 * Constructeur
	 * 
	 * @param x coordonne X de la plante
	 * @param y coordonne Y de la plante
	 */
	public PeasShooter(double x, double y) {
		super(x, y);
		this.setLife(300);
		PeasShooter.setPrice(100);
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
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}

	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		cooldown = new Timer(5000);
	}
	
}
