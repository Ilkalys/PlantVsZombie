package Mob;

import java.io.File;

import Resources.StdDraw;
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
	private static final File ICONE = new File("sprites/mob/peasShooter_0.png");
	// Point de vie de depart d'un tire-pois
	private static final int HPMAX = 300;
	// Prix du tire-pois
	private static final int PRICE = 100;
	// Temps (en ms) avant de pouvoir replanter un tire-pois
	private static final int COOLDOWN_TIME = 5_000;
	// Timer du replantage d'un tire-pois
	private static Timer Cooldown;
	
	// Chemin vers les sprites d'animation
	private static final File SpriteAnim = new File("sprites/mob/peasShooter/peasShooter_");
	// Status de l'animation
	private int actualAnim;

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
		this.actualAnim = 0;
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
		if(this.actualAnim == 75)
			this.actualAnim = 0;
		else
			this.actualAnim++;

		if( this.Reload.getActualTime()*1000 <= 500)
			return "0";
		else if(this.Reload.getActualTime()*1000 >= 500 && this.Reload.getActualTime()*1000 <= 600)
			return "3";
		else if(this.Reload.getActualTime()*1000 >= 600 && this.Reload.getActualTime()*1000 <= 700)
			return "4";
		else if(this.Reload.getActualTime()*1000 >= 700 && this.Reload.getActualTime()*1000 <= 800)
			return "3";
		else if(this.Reload.getActualTime()*1000 >= 800 && this.Reload.getActualTime()*1000 <= 1300)
			return "0";
		else if(this.Reload.getActualTime()*1000 >= 1300 && this.Reload.getActualTime()*1000 <= 1400)
			return "1";
		else if(this.Reload.getActualTime()*1000 >= 1400 && this.Reload.getActualTime()*1000 <= 1500)
			return "2";

		else return "0";
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
	 * Retourne le timer chargé de calculer le temps de rechargement pour planter un tire-pois
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
	 * Retourne le timer chargé de calculer le temps de rechargement pour tirer
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
	 * Modifie le timer chargé de calculer le temps de rechargement pour planter un tire-pois
	 * 
	 * @param timer nouveau timer
	 */
	public static void setCooldown(Timer timer) {
		Cooldown = timer;
	}

	/**
	 * Modifie le timer chargé de calculer le temps de rechargement pour tirer
	 * 
	 * @param timer nouveau timer
	 */
	public void setReload(Timer timer) {
		this.Reload = timer;
	}

}
