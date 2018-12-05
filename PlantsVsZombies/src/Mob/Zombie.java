package Mob;

import java.util.List;

import Resources.Entite;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Zombie extends Mob {

	// Nombre de degat qu'inflige un zombie
	private static final int DAMAGE = 30;
	// Vitesse d'un zombie
	private static final double SPEED = 0.25;
	// Temps avant la prochaine attaque
	private static final int ATTACK_TIME = 1_000;
	// Timer pour l'attaque d'un zombie
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
	public Zombie(double x, double y, String SpriteFilepath, int life) {
		super(x, y, SpriteFilepath, life);
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
			this.position.setX(this.position.getX() - (SPEED/300));
		else if(this.Attack == null || this.Attack.hasFinished() ) {
			obstacle.takeDamage(DAMAGE);
			this.Attack = new Timer(ATTACK_TIME);
		}
	}
	
	/**
	 * Verifie si un zombie se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return la zombie trouve
	 */
	public static Zombie somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Zombie
			&& entites.get(i).getX() <= x+0.09
			&& entites.get(i).getX() >= x-0.09
			&& entites.get(i).getY() <= y+0.09
			&& entites.get(i).getY() >= y-0.09)
				return (Zombie)entites.get(i);
		return null;
	}
	
	protected String Animate(){
		if(this.Attack != null) {
		if( this.Attack.getActualTime()*1000 >= 750)
			return "walk_0";
		else if(this.Attack.getActualTime()*1000 >= 650 && this.Attack.getActualTime()*1000 <= 750)
			return "attack_0";
		else if(this.Attack.getActualTime()*1000 >= 600 && this.Attack.getActualTime()*1000 <= 650)
			return "attack_1";
		else if(this.Attack.getActualTime()*1000 >= 550 && this.Attack.getActualTime()*1000 <= 600)
			return "attack_2";
		else if(this.Attack.getActualTime()*1000 >= 500 && this.Attack.getActualTime()*1000 <= 550)
			return "attack_3";
		else if(this.Attack.getActualTime()*1000 >= 250 && this.Attack.getActualTime()*1000 <= 500 )
			return "attack_4";
		else if(this.Attack.getActualTime()*1000 >= 50 && this.Attack.getActualTime()*1000 <= 250)
			return "attack_5";
		else return "walk_0";
		}
		else return "walk_0";
	}
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Retourne le nombre de damage du zombie
	 * 
	 * @return DAMAGE
	 */
	public static int getDamage() {
		return DAMAGE;
	}

	/**
	 * Retourne la vitesse du zombie
	 * 
	 * @return SPEED
	 */
	public static double getSpeed() {
		return SPEED;
	}

	/**
	 * Retourne le temps d'attaque d'un zombie
	 * 
	 * @return ATTACK_TIME
	 */
	public static double getAttackTime() {
		return ATTACK_TIME;
	}

	/**
	 * Retourne le timer chargé de calculer le temps de rechargement pour attaquer
	 * 
	 * @return Attack
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
