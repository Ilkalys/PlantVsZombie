package Mob;

import java.io.File;

import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class PeasShooter extends Plant {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------	

	// Temps de rechargement pour tirer
	private Timer reload;
	// Prix du pire-pois
	private static final int PRICE = 100;
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
	public PeasShooter(double x, double y) {
		super(x, y);
		this.setLife(300);
		PeasShooter.setCooldown(new Timer(0));
		this.reload = new Timer(1500);
		this.setSpriteFilepath(new File("sprites/mob/peasShooter.png"));
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
		if(this.reload.hasFinished()) {
			GameWorld.addPeas(this.getX(), this.getY());
			this.reload = new Timer(1500);
		}
	}

	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		PeasShooter.setCooldown(new Timer(5000));
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le prix du tire-pois
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
