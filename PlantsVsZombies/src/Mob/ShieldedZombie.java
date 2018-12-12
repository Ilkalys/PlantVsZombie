package Mob;

import java.io.File;

import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class ShieldedZombie extends Zombie {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le sprites d'un zombie blinde
	private static final File SPRITE_PATH = new File("sprites/mob/shieldedZombie/shieldedZombie_");
	// Point de vie de depart d'un zombie blinde
	private static final int HPMAX = 560;
	// Nombre de degat qu'inflige un zombie blinde
	private static final int DAMAGE = 30;
	// Vitesse d'un zombie blinde
	private static final double SPEED = 0.25;
	
	// Temps avant la prochaine attaque, doit être supérieur à 1_000.
	private static final int ATTACK_TIME = 1_000;
	// Timer pour l'attaque d'un zombie blinde
	private Timer Attack;
	

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
	public ShieldedZombie(double x, double y) {
		super(x, y, SPRITE_PATH.getAbsolutePath() + "walk_0.png", HPMAX);
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
		Plant obstacle = Plant.somethingHere(GameWorld.getEntites(), this.getX() - 0.01 - (SPEED / 300), this.getY());
		if(obstacle == null)
			this.position.setX(this.position.getX() - (SPEED / 300));
		else if(this.Attack == null || this.Attack.hasFinished() ) {
			obstacle.takeDamage(DAMAGE);
			this.Attack = new Timer(ATTACK_TIME);
		}
	}

	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, SPRITE_PATH.getAbsolutePath() + this.Animate() + ".png", 0.15, 0.15);
	}

	/**
	 * Determine quel sprite afficher pour l'animation
	 * 
	 * @return String
	 */
	private String Animate() {
		if(this.Attack == null || this.Attack.hasFinished()) {
			if(this.getActualAnim() >= 20)
				this.setActualAnim(0);
			else
				this.setActualAnim(this.getActualAnim() + 1);
			if(this.getActualAnim() >= 15 && this.getActualAnim() <= 20)
				return "walk_1";
			else if(this.getActualAnim() >= 10 && this.getActualAnim() <= 15)
				return "walk_2";
			else if(this.getActualAnim() >= 5 && this.getActualAnim() <= 10)
				return "walk_3";
		}
		else if(this.Attack != null) {
			if(this.Attack.getActualTime()*1000 >= ATTACK_TIME - 350 && this.Attack.getActualTime()*1000 <= ATTACK_TIME - 100)
				return "attack_0";
			else if(this.Attack.getActualTime()*1000 >= ATTACK_TIME - 400 && this.Attack.getActualTime()*1000 <= ATTACK_TIME - 350)
				return "attack_1";
			else if(this.Attack.getActualTime()*1000 >= ATTACK_TIME -450 && this.Attack.getActualTime()*1000 <= ATTACK_TIME - 400)
				return "attack_2";
			else if(this.Attack.getActualTime()*1000 >= ATTACK_TIME - 500 && this.Attack.getActualTime()*1000 <= ATTACK_TIME - 450)
				return "attack_3";
			else if(this.Attack.getActualTime()*1000 >= ATTACK_TIME - 750 && this.Attack.getActualTime()*1000 <= ATTACK_TIME - 500 )
				return "attack_4";
			else if(this.Attack.getActualTime()*1000 >= ATTACK_TIME - 950 && this.Attack.getActualTime()*1000 <= ATTACK_TIME - 750)
				return "attack_5";
		}
		return "walk_0";
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le sprite d'un zombie blinde
	 * 
	 * @return SPRITE sprite d'un zombie blinde
	 */
	public static File getSpritePath() {
		return SPRITE_PATH;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un zombie blinde
	 * 
	 * @return HPMAX nombre de point de vie de depart d'un zombie blinde
	 */
	public static int getHPMax() {
		return HPMAX;
	}

	/**
	 * Retourne le nombre de damage du zombie
	 * 
	 * @return DAMAGE nombre de damage du zombie
	 */
	public static int getDamage() {
		return DAMAGE;
	}

	/**
	 * Retourne la vitesse du zombie
	 * 
	 * @return SPEED vitesse du zombie
	 */
	public static double getSpeed() {
		return SPEED;
	}

	/**
	 * Retourne le temps d'attaque d'un zombie
	 * 
	 * @return ATTACK_TIME temps d'attaque d'un zombie
	 */
	public static double getAttackTime() {
		return ATTACK_TIME;
	}

	/**
	 * Retourne le timer chargé de calculer le temps de rechargement pour attaquer
	 * 
	 * @return Attack timer chargé de calculer le temps de rechargement pour attaquer
	 */
	public Timer getAttack() {
		return this.Attack;
	}


	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Modifie le timer chargé de calculer le temps de rechargement pour attaquer
	 * 
	 * @param Attack nouveau timer
	 */
	public void setAttack(Timer timer) {
		this.Attack = timer;
	}
	
}
