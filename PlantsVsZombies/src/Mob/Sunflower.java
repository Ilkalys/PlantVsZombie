package Mob;

import Resources.*;
import Soleil.*;

public class Sunflower extends Plant {

	// Timerpour l'apparition des soleils gerer par la plante
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
		Sunflower.setPrice(50);
		cooldown = new Timer(1);
		this.sunrise = new Timer(6500);
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
		if(sunrise.hasFinished()) {
			//GameWorld.addSun();
			this.sunrise = new Timer(6500);
		}
	}

	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public void dessine() {
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}
	
	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		cooldown = new Timer(5000);
	}

	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Retourne le timer gerant l'apparation des soleils
	 * 
	 * @return sunrise
	 */
	public Timer getSunrise() {
		return this.sunrise;
	}
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie sunrise
	 * 
	 * @param sunrise timer gerant l'apparation des soleils
	 */
	public void setSunrise(Timer sunrise) {
		this.sunrise = sunrise;
	}
	
}
