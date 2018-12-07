package Mob;

import java.io.File;
import java.util.List;

import Resources.*;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Dynamite extends Plant {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Touche pour selectionner une dynamite
	private static char KEY = 'd';
	// Icone de la dynamite
	private static final File ICONE = new File("sprites/mob/dynamite/dynamite.png");
	// Point de vie de depart d'une dynamite
	private static final int HPMAX = 750;
	// Prix de la dynamite
	private static final int PRICE = 150;
	// Temps (en ms) avant de pouvoir replanter une dynamite
	private static final int COOLDOWN_TIME = 20_000;
	// Timer du replantage d'une dynamite
	private static Timer Cooldown;
	
	// Nombre de degat qu'inflige une dynamite
	private static final int DAMAGE = 500;
	
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
	public Dynamite(double x, double y) {
		super(x, y, ICONE.getAbsolutePath(), HPMAX);
		setCooldown(new Timer(COOLDOWN_TIME));
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
	 * Verifie si une dynamite se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return la plante a l'endroit demande
	 */
	public static Dynamite somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Dynamite
			&& entites.get(i).getX() <= x+0.05
			&& entites.get(i).getX() >= x-0.05
			&& entites.get(i).getY() <= y+0.05
			&& entites.get(i).getY() >= y-0.05)
				return (Dynamite)entites.get(i);
		return null;
	}

	/**
	 * Retire des points de vie
	 * 
	 * @param damage nombre de points a retirer
	 */
	@Override
	public void takeDamage(int damage) {
		this.setLife(this.getLife() - damage);
		if(this.getLife() <= 0)
			explose();
	}

	/**
	 * Afflige des dégats à tous les zombies autour de la dynamite puis la détruit
	 */
	public void explose() {
		GameWorld.addExplosion(this.getX(), this.getY());
		List<Entite> entites = GameWorld.getEntites();
		for (int i = 0; i < entites.size(); i++) {
			if(entites.get(i) instanceof Zombie) {
				if(this.ZombieHere(((Zombie)entites.get(i)), this.getX() - 0.1, this.getY())
				|| this.ZombieHere(((Zombie)entites.get(i)), this.getX() + 0.1, this.getY())
				|| this.ZombieHere(((Zombie)entites.get(i)), this.getX(), this.getY() - 0.1)
				|| this.ZombieHere(((Zombie)entites.get(i)), this.getX(), this.getY() + 0.1)) {
					((Zombie)entites.get(i)).takeDamage(DAMAGE);
				}
			}
		}
		SoundPlayer.PlaySE("explosion.wav");
		GameWorld.removeEntiteFrom(GameWorld.getEntites(),this);
	}
	
	/**
	 * Verifie si un zombie est particulier est un une case precise
	 * 
	 * @param Zombie Zombie a regarder
	 * @param x coordonne X du milieu de la case ou regarder
	 * @param y coordonne Y du milieu de la case ou regarder
	 * @return true si le zombie est a cet emplacement
	 */
	private boolean ZombieHere(Zombie Zombie, double x, double y) {
		if(Zombie.getX() <= x + 0.09 && Zombie.getX() >= x - 0.09 && Zombie.getY() <= y + 0.09 && Zombie.getY() >= y - 0.09) {
			return true;
		} else {
			return false;
		}
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne la touche pour selectionner une dynamite
	 * 
	 * @return KEY
	 */
	public static char getKey() {
		return KEY;
	}
	
	/**
	 * Retourne l'icone de la dynamite
	 * 
	 * @return ICONE
	 */
	public static File getIcone() {
		return ICONE;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'une dynamite
	 * 
	 * @return HPMAX
	 */
	public static int getHPMax() {
		return HPMAX;
	}
	
	/**
	 * Retourne le prix de la dynamite
	 * 
	 * @return PRICE
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	/**
	 * Retourne le temps (en ms) avant de pouvoir replanter une dynamite
	 * 
	 * @return COOLDOWN_TIME
	 */
	public static int getCooldownTime() {
		return COOLDOWN_TIME;
	}
	
	/**
	 * Retourne le timer chargé de calculer le temps de rechargement pour planter une dynamite
	 * 
	 * @return Cooldown
	 */
	public static Timer getCooldown() {
		return Cooldown;
	}
	
	/**
	 * Retourne le nombre de degat qu'inflige une dynamite
	 * 
	 * @return DAMAGE
	 */
	public static int getDamage() {
		return DAMAGE;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Modifie la touche pour selectionner une dynamite
	 * 
	 * @return KEY
	 */
	public static void setKey(char key) {
		KEY = key;
	}
	
	/**
	 * Modifie le timer chargé de calculer le temps de rechargement pour planter une dynamite
	 * 
	 * @param timer nouveau timer
	 */
	public static void setCooldown(Timer timer) {
		Cooldown = timer;
	}
	
}
