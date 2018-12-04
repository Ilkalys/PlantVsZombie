package Mob;

import java.io.File;

import Resources.Game;
import Resources.StdDraw;
import Resources.Timer;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Nuts extends Plant {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Prix de la noix
	private static final int PRICE = 50;
	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	
	private int actualAnim;
	
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
	public Nuts(double x, double y){
		super(x, y);
		this.setLife(1500);
		Nuts.setCooldown(new Timer(0));
		actualAnim =0;
		this.setSpriteFilepath(new File("sprites/mob/nuts/nuts_"));
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
		if(actualAnim == 250) {
			actualAnim =0;
		}
		else actualAnim++;
	}
	
	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, this.getSpriteFilepath().getAbsolutePath() + anim() + ".png", 0.15, 0.15);
	}
	
	/**
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		Nuts.setCooldown(new Timer(20000));
	}
	
	private String anim(){
		if(actualAnim >= 100 && actualAnim<=105)
			return "1";
		else if(actualAnim >= 105 && actualAnim<=110)
			return "2";
		else if(actualAnim >= 110 && actualAnim<=115)
			return "3";
		else if(actualAnim >= 115 && actualAnim<=120)
			return "4";
		else if(actualAnim >= 120 && actualAnim<=125)
			return "5";
		else if(actualAnim >= 125 && actualAnim<=150)
			return "6";
		else if(actualAnim >= 150 && actualAnim<=155)
			return "5";
		else if(actualAnim >= 155 && actualAnim<=160)
			return "4";
		else if(actualAnim >= 160 && actualAnim<=165)
			return "3";
		else if(actualAnim >= 165 && actualAnim<=170)
			return "2";
		else if(actualAnim >= 170 && actualAnim<=175)
			return "1";
		else return "0";
		
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le prix de la noix
	 * 
	 * @return PRICE
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	/**
	 * Retourne le timer charg� de calculer le temps de rechargement de la plante
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
	 * Modifie le timer charg� de calculer le temps de rechargement de la plante
	 * 
	 * @param timer
	 */
	public static void setCooldown(Timer timer) {
		cooldown = timer;
	}
	
	
}
