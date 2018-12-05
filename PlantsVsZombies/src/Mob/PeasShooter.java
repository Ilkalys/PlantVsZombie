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

	// Icone du tire-pois
	private static final File ICONE = new File("sprites/mob/peasShooter.png");
	// Point de vie de depart d'un tire-pois
	private static final int HPMAX = 300;
	// Prix du tire-pois
	private static final int PRICE = 100;
	// Temps (en ms) avant de pouvoir replanter un tire-pois
	private static final int COOLDOWN_TIME = 5_000;
	// Timer du replantage d'un tire-pois
	private static Timer Cooldown;

	// Temps (en ms) pour recharger
	private static final int RELOAD_TIME = 1_500;
	// Timer du rechargement pour tirer
	private Timer Reload;

	
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
		super(x, y, ICONE.getAbsolutePath(), HPMAX);
		setCooldown(new Timer(COOLDOWN_TIME));
		
		this.Reload = new Timer(RELOAD_TIME);
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
		this.Tirer();
	}
	
	/**
	 * Tire un pois devant le tire-pois
	 */
	private void Tirer() {
		if(this.Reload.hasFinished()) {
			GameWorld.addPeas(this.getX(), this.getY());
			this.Reload = new Timer(RELOAD_TIME);
		}
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne l'icone du tire-pois
	 * 
	 * @return ICONE
	 */
	public static File getIcone() {
		return ICONE;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un tire-pois
	 * 
	 * @return HPMAX
	 */
	public static int getHPMax() {
		return HPMAX;
	}
	
	/**
	 * Retourne le prix du tire-pois
	 * 
	 * @return PRICE
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	/**
	 * Retourne le temps (en ms) avant de pouvoir replanter un tire-pois
	 * 
	 * @return COOLDOWN_TIME
	 */
	public static int getCooldownTime() {
		return COOLDOWN_TIME;
	}
	
	/**
	 * Retourne le timer charg� de calculer le temps de rechargement pour planter un tire-pois
	 * 
	 * @return Cooldown
	 */
	public static Timer getCooldown() {
		return Cooldown;
	}
	
	/**
	 * Retourne le temps (en ms) pour recharger
	 * 
	 * @return RELOAD_TIME
	 */
	public static int getReloadTime() {
		return RELOAD_TIME;
	}
	
	/**
	 * Retourne le timer charg� de calculer le temps de rechargement pour tirer
	 * 
	 * @return Reload
	 */
	public Timer getReload() {
		return this.Reload;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie le timer charg� de calculer le temps de rechargement pour planter un tire-pois
	 * 
	 * @param timer nouveau timer
	 */
	public static void setCooldown(Timer timer) {
		Cooldown = timer;
	}
	
	/**
	 * Modifie le timer charg� de calculer le temps de rechargement pour tirer
	 * 
	 * @param timer nouveau timer
	 */
	public void setReload(Timer timer) {
		this.Reload = timer;
	}
	
}
