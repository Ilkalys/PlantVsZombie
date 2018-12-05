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

	// Icone du tournesol
	private static final File ICONE = new File("sprites/mob/sunflower.png");
	// Point de vie de depart d'un tournesol
	private static final int HPMAX = 300;
	// Prix du tournesol
	private static final int PRICE = 50;
	// Temps (en ms) avant de pouvoir replanter un tournesol
	private static final int COOLDOWN_TIME = 5_000;
	// Timer du replantage d'un tournesol
	private static Timer Cooldown;

	// Temps (en ms) pour l'apparition des soleils
	private static final int SUNRISE_TIME = 24_000;
	// Timer pour l'apparition des soleils gerer par la plante
	private Timer Sunrise;
	
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
		super(x, y, ICONE.getAbsolutePath(), HPMAX);
		setCooldown(new Timer(COOLDOWN_TIME));

		this.Sunrise = new Timer(SUNRISE_TIME);
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
		this.AddSun();
	}
	
	private void AddSun() {
		if(Sun.somethingHere(GameWorld.getSuns(), this.getX(), this.getY()) != null)
			this.setSunrise(new Timer(SUNRISE_TIME));
		else if(this.Sunrise.hasFinished()) {
			GameWorld.addSun( this.getX(),this.getY());
			this.setSunrise(new Timer(SUNRISE_TIME));
		}
	}

	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne l'icone du tournesol
	 * 
	 * @return ICONE
	 */
	public static File getIcone() {
		return ICONE;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un tournesol
	 * 
	 * @return HPMAX
	 */
	public static int getHPMax() {
		return HPMAX;
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
	 * Retourne le temps (en ms) avant de pouvoir replanter un tournesol
	 * 
	 * @return COOLDOWN_TIME
	 */
	public static int getCooldownTime() {
		return COOLDOWN_TIME;
	}
	
	/**
	 * Retourne le timer chargé de calculer le temps de rechargement pour planter un tournesol
	 * 
	 * @return Cooldown
	 */
	public static Timer getCooldown() {
		return Cooldown;
	}
	
	/**
	 * Retourne le temps (en ms) pour l'apparition des soleils
	 * 
	 * @return SUNRISE_TIME
	 */
	public static int getSunriseTime() {
		return SUNRISE_TIME;
	}
	
	/**
	 * Retourne le timer pour l'apparition des soleils gerer par la plante
	 * 
	 * @return Sunrise
	 */
	public Timer getSunrise() {
		return this.Sunrise;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie le timer chargé de calculer le temps de rechargement pour planter un tournesol
	 * 
	 * @param timer nouveau timer
	 */
	public static void setCooldown(Timer timer) {
		Cooldown = timer;
	}
	
	/**
	 * Modifie le timer pour l'apparition des soleils gerer par la plante
	 * 
	 * @param timer nouveau timer
	 */
	public void setSunrise(Timer timer) {
		this.Sunrise = timer;
	}
	
}
