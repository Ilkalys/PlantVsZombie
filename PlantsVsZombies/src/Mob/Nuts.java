package Mob;

import java.io.File;

import Resources.StdDraw;
import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Nuts extends Plant {

	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	// Prix de la noix
	private static final int PRICE = 50;
	// Chelin vers le sprite de la noix
	private File SpriteFilepath;
	
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
	public Nuts(double x, double y){
		super(x, y);
		this.setLife(1500);
		cooldown = new Timer(0);
		SpriteFilepath = new File("sprites\\mob\\nuts.png");
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
		StdDraw.picture(this.getX(), this.getY(), SpriteFilepath.getAbsolutePath(), 0.2, 0.2);
	}
	
	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		cooldown = new Timer(20000);
	}
	

	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	public static Timer getCooldown() {
		return cooldown;
	}
	
	/**
	 * Retourne le prix de la noix
	 * 
	 * @return PRICE
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	public static void setCooldown(Timer newCooldown) {
		cooldown = newCooldown;
	}

}
