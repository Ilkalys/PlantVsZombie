package Mob;

import java.io.File;

import Resources.*;
import Screens.GameWorld;
import Sun.*;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Sunflower extends Plant {

	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	// Timerpour l'apparition des Suns gerer par la plante
	private Timer sunrise;
	// Prix du tournesol
	private static final int PRICE = 50;
	// Chelin vers le sprite du tournesol
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
	public Sunflower(double x, double y) {
		super(x, y);
		this.setLife(300);
		cooldown = new Timer(0);
		this.sunrise = new Timer(6500);
		SpriteFilepath = new File("sprites\\mob\\sunflower.png");
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
			this.sunrise = new Timer(6500);

		else if(sunrise.hasFinished()) {
			GameWorld.addSun( this.getX(),this.getY());
			this.sunrise = new Timer(6500);
		}
	}

	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SpriteFilepath.getAbsolutePath(), 0.2, 0.2);
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
	
	public static Timer getCooldown() {
		return cooldown;
	}

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
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	public static void setCooldown(Timer newCooldown) {
		cooldown = newCooldown;
	}

	/**
	 * Modifie sunrise
	 * 
	 * @param sunrise timer gerant l'apparation des Suns
	 */
	public void setSunrise(Timer sunrise) {
		this.sunrise = sunrise;
	}
	
}
