package Mob;

import java.io.File;

import Resources.*;
import Screens.GameWorld;
import Sun.*;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Sunflower extends Plant {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	// Prix
	private static final int PRICE = 50;
	// Chemin vers le sprite
	private static final String SPRITE_FILEPATH = "sprites/mob/sunflower.png";
	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	// Timer pour l'apparition des soleils gerer par la plante
	private Timer sunrise;
	
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
	public Sunflower(double x, double y) {
		super(x, y);
		this.setLife(300);
		Sunflower.setCooldown(new Timer(0));
		this.sunrise = new Timer(24000);
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Met a jour l'entite : deplacement, effectuer une action
	 */
	public void step() {
		if(Sun.somethingHere(GameWorld.getSuns(), this.getX(), this.getY()) != null)
			this.sunrise = new Timer(24000);

		else if(sunrise.hasFinished()) {
			GameWorld.addSun( this.getX(),this.getY());
			this.sunrise = new Timer(24000);
		}
	}
	
	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		Sunflower.setCooldown(new Timer(5000));
	}

	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le timer gerant l'apparation des Suns
	 * 
	 * @return sunrise
	 */
	public Timer getSunrise() {
		return this.sunrise;
	}
	
	/**
	 * Retourne le prix du tournesol
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
	 * Modifie sunrise
	 * 
	 * @param sunrise timer gerant l'apparation des Suns
	 */
	public void setSunrise(Timer sunrise) {
		this.sunrise = sunrise;
	}
	
	/**
	 * Modifie le timer chargé de calculer le temps de rechargement de la plante
	 * 
	 * @param timer
	 */
	public static void setCooldown(Timer timer) {
		cooldown = timer;
	}
	
	
}
