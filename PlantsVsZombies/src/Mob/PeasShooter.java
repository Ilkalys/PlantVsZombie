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

	// Touche pour selectionner un tire-pois
	private static char key = 'p';
	// Icone du tire-pois
	private static final File ICONE = new File("sprites/mob/peasShooter/peasShooter_0.png");
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

	// Temps (en s) pour recharger, doit �tre superieur � 1_000
	private static final double RELOAD_TIME = 1.500;
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
		this.Reload = new Timer((int)(RELOAD_TIME * 1_000));
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
		double tmp = RELOAD_TIME - this.Reload.getActualTime();
		if( tmp <=  1 && tmp >=  0.9)
			return "3";
		else if(tmp <= 0.9 && tmp >= 0.8)
			return "4";
		else if(tmp <= 0.8 && tmp >= 0.7)
			return "3";
		else if(tmp <= 0.7 && tmp >= 0.2)
			return "0";
		else if(tmp <= 0.2 && tmp >= 0.1)
			return "1";
		else if(tmp <= 0.1 && tmp >= 0)
			return "2";

		else return "0";
	}
	/**
	 * Fait apparaitre un pois devant le tire-pois
	 */
	private void Tirer() {
		if(this.Reload.hasFinished()) {
			GameWorld.addPeas(this.getX(), this.getY());
			this.Reload = new Timer((int)(RELOAD_TIME * 1_000));
		}
	}


	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne la touche pour selectionner un tire-pois
	 * 
	 * @return KEY
	 */
	public static char getKey() {
		return key;
	}
	
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
	 * Retourne le chemin vers les sprites d'animation
	 * 
	 * @return SpriteAnim
	 */
	public static File getSpriteAnim() {
		return SpriteAnim;
	}

	/**
	 * Retourne le temps (en ms) pour recharger
	 * 
	 * @return RELOAD_TIME
	 */
	public static double getReloadTime() {
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
	 * Modifie la touche de selection du TirePois
	 * 
	 * @param key la touche voulu
	 */
	public static void setKey(char key) {
		PeasShooter.key = key;
	}
	
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
