package Mob;

import Resources.Game;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class BasicZombie extends Zombie {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le dossier de sprite
	private static final String SPRITEFILEPATH = Game.getSpritefilepath().toString() + "/mob/basicZombie";
	// Point de vie de depart d'un zombie basique
	private static final int HPMAX = 200;
	// Nombre de degat qu'inflige un zombie basique
	private static final int DAMAGE = 30;
	// Vitesse d'un zombie basique
	private static final double SPEED = 0.25;
	// Temps avant la prochaine attaque, doit �tre sup�rieur � 1_000.
	private static final int ATTACK_TIME = 1_000;
	// Timer pour l'attaque d'un zombie basique
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
	public BasicZombie(double x, double y) {
		super(x, y, SPRITEFILEPATH + "/basicZombie_walk_0.png", HPMAX);
		this.Attack = null;
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
		StdDraw.picture(this.getX(), this.getY() + 0.01, SPRITEFILEPATH + "/basicZombie_" + this.Animate() + ".png", 0.15, 0.15);
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
	 * Retourne le sprite d'un zombie basique
	 * 
	 * @return SPRITE sprite d'un zombie basique
	 */
	public static String getSPRITEFILEPATH() {
		return SPRITEFILEPATH;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un zombie basique
	 * 
	 * @return HPMAX nombre de point de vie de depart d'un zombie basique
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
	 * Retourne le timer charg� de calculer le temps de rechargement pour attaquer
	 * 
	 * @return Attack timer charg� de calculer le temps de rechargement pour attaquer
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
	 * Modifie le timer charg� de calculer le temps de rechargement pour attaquer
	 * 
	 * @param Attack nouveau timer
	 */
	public void setAttack(Timer timer) {
		this.Attack = timer;
	}
	
}
