package Mob;

import java.io.File;

import Resources.*;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Sunflower extends Plant {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Touche pour selectionner un tournesol
	private static char key = 't';
	// Icone du tournesol
	private static final File ICONE = new File("sprites/mob/sunflower/sunflower_0.png");
	// Point de vie de depart d'un tournesol
	private static final int HPMAX = 300;
	// Prix du tournesol
	private static final int PRICE = 50;
	// Temps (en ms) avant de pouvoir replanter un tournesol
	private static final int COOLDOWN_TIME = 5_000;
	// Timer du replantage d'un tournesol
	private static Timer Cooldown;

	// Chemin vers les sprites d'animation
	private static final File SpriteAnim = new File("sprites/mob/sunflower/sunflower_");
		
	// Temps (en ms) pour l'apparition des soleils, doit être supérieur à 2_000, influe également sur l'animation
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
		if(this.Sunrise.hasFinished() && Sun.somethingHere(GameWorld.getSuns(), this.getX(), this.getY()) == null) {
			GameWorld.addSun( this.getX(),this.getY());
			this.setSunrise(new Timer(SUNRISE_TIME));
		}
	}


	/**
	 * Dessine le mob, aux bonnes coordonnees
	 */
	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, SpriteAnim.getAbsolutePath() + this.Animate() + ".png", 0.15, 0.15);
	}

	/**
	 * Calcul la prochaine image de l'animation
	 */

	private String Animate(){
		double tmp = this.Sunrise.getActualTime();
		if(tmp >= 1.750 && tmp <= 2.000)
			return "2";
		else if(tmp >= 1.5 && tmp <= 1.750)
			return "1";
		else if(tmp >= 1.250 && tmp <= 1.5)
			return "2";
		else if(tmp >= 1 && tmp <= 1.25)
			return "1";
		else if(tmp >= 0.8 && tmp <= 1 )
			return "0";
		else if(tmp >= 0.6 && tmp <= 0.8)
			return "3";
		else if(tmp >= 0.4 && tmp <= 0.6)
			return "4";
		else if(tmp >= 0.2 && tmp <= 0.4)
			return "3";
		else if(tmp >= 0 && tmp <= 0.2)
			return "4";
		else return "0";
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne la touche pour selectionner un tournesol
	 * 
	 * @return KEY
	 */
	public static char getKey() {
		return key;
	}
	
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
	 * Modifie la touche de selection du tournesol
	 * 
	 * @param key la touche voulu
	 */
	public static void setKey(char key) {
		Sunflower.key = key;
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
