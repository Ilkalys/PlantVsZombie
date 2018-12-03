package Mob;

import java.io.File;

import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Nuts extends Plant {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Prix de la noix
	private static final int PRICE = 50;
	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	
	
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
		Nuts.setCooldown(new Timer(0));
		this.setSpriteFilepath(new File("sprites\\mob\\nuts.png"));
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
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		Nuts.setCooldown(new Timer(20000));
	}
	

	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le prix de la noix
	 * 
	 * @return PRICE
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	/**
	 * Retourne le timer chargé de calculer le temps de rechargement de la plante
	 * 
	 * @return cooldown
	 */
	public static Timer getCooldown() {
		return cooldown;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie le timer chargé de calculer le temps de rechargement de la plante
	 * 
	 * @param timer
	 */
	public static void setCooldown(Timer timer) {
		cooldown = timer;
	}
	
	
}
