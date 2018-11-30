package Mob;

import java.io.File;

import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class PeasShooter extends Plant {
	
	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	// Temps de rechargement pour tirer
	private Timer reload;
	// Prix du pire-pois
	private static final int PRICE = 100;
	// Chelin vers le sprite du tire-pois
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
	public PeasShooter(double x, double y) {
		super(x, y);
		this.setLife(300);
		cooldown = new Timer(0);
		this.reload = new Timer(1500);
		SpriteFilepath = new File("sprites\\mob\\peasShooter.png");
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
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public  void dessine() {
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
	 * Retourne le prix du tire-pois
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

}
