package Mob;

import java.io.File;

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

	// Touche pour selectionner une noix
	private static char key = 'n';
	// Icone de la noix
	private static final File ICONE = new File(Nuts.class.getResource("/sprites/mob/nuts/nuts_0.png").toString());
	// Point de vie de depart d'une noix
	private static final int HPMAX = 1_500;
	// Prix de la noix
	private static final int PRICE = 50;
	// Temps (en ms) avant de pouvoir replanter une noix
	private static final int COOLDOWN_TIME = 20_000;
	// Timer du replantage d'une noix
	private static Timer Cooldown;
	// Chemin vers les sprites d'animation
	private static final File SpriteAnim = new File("/sprites/mob/nuts");
	// Status de l'animation
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
	public Nuts(double x, double y) {
		super(x, y, ICONE.getPath(), HPMAX);
		setCooldown(new Timer(COOLDOWN_TIME));
		
		this.actualAnim = 0;
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
		
	}
	
	/**
	 * Dessine le mob, aux bonnes coordonnees
	 */
	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, SpriteAnim.getPath() + "/nuts_" + this.Animate() + ".png", 0.15, 0.15);
	}
	
	/**
	 * Calcul la prochaine image de l'animation
	 */
	private String Animate(){
		if(this.actualAnim == 250)
			this.actualAnim = 0;
		else
			this.actualAnim++;
		
		if(this.actualAnim >= 100 && this.actualAnim <= 105)
			return "1";
		else if(this.actualAnim >= 105 && this.actualAnim <= 110)
			return "2";
		else if(this.actualAnim >= 110 && this.actualAnim <= 115)
			return "3";
		else if(this.actualAnim >= 115 && this.actualAnim <= 120)
			return "4";
		else if(this.actualAnim >= 120 && this.actualAnim <= 125)
			return "5";
		else if(this.actualAnim >= 125 && this.actualAnim <= 150)
			return "6";
		else if(this.actualAnim >= 150 && this.actualAnim <= 155)
			return "5";
		else if(this.actualAnim >= 155 && this.actualAnim <= 160)
			return "4";
		else if(this.actualAnim >= 160 && this.actualAnim <= 165)
			return "3";
		else if(this.actualAnim >= 165 && this.actualAnim <= 170)
			return "2";
		else if(this.actualAnim >= 170 && this.actualAnim <= 175)
			return "1";
		else return "0";
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne la touche pour selectionner une noix
	 * 
	 * @return KEY touche pour selectionner une noix
	 */
	public static char getKey() {
		return key;
	}
	
	/**
	 * Retourne l'icone de la noix
	 * 
	 * @return ICONE icone de la noix
	 */
	public static File getIcone() {
		return ICONE;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'une noix
	 * 
	 * @return HPMAX nombre de point de vie de depart d'une noix
	 */
	public static int getHPMax() {
		return HPMAX;
	}
	
	/**
	 * Retourne le prix de la noix
	 * 
	 * @return PRICE prix de la noix
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	/**
	 * Retourne le temps (en ms) avant de pouvoir replanter une noix
	 * 
	 * @return COOLDOWN_TIME temps (en ms) avant de pouvoir replanter une noix
	 */
	public static int getCooldownTime() {
		return COOLDOWN_TIME;
	}
	
	/**
	 * Retourne le timer chargé de calculer le temps de rechargement pour planter une noix
	 * 
	 * @return Cooldown timer chargé de calculer le temps de rechargement pour planter une noix
	 */
	public static Timer getCooldown() {
		return Cooldown;
	}
	
	/**
	 * Retourne le chemin vers les sprites d'animation
	 * 
	 * @return SpriteAnim chemin vers les sprites d'animation
	 */
	public static File getSpriteAnim() {
		return SpriteAnim;
	}
	
	/**
	 * Retourne le status de l'animation
	 * 
	 * @return actualAnim status de l'animation
	 */
	public int getActualAnim() {
		return this.actualAnim;
	}
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie la touche de selection de la noix
	 * 
	 * @param key la touche voulu
	 */
	public static void setKey(char key) {
		Nuts.key = key;
	}
	
	/**
	 * Modifie le timer chargé de calculer le temps de rechargement pour planter un tire-pois
	 * 
	 * @param timer nouveau timer
	 */
	public static void setCooldown(Timer timer) {
		Cooldown = timer;
	}
	
	/**
	 * Modifie le status de l'animation
	 * 
	 * @param actualAnim nouvelle valeur
	 */
	public void setActualAnim(int actualAnim) {
		this.actualAnim = actualAnim;
	}
	
}
