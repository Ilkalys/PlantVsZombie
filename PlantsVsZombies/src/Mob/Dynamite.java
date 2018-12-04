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

	// Nombre de degat qu'inflige un pois
	private static final int DAMAGE = 500;
	// Prix du tournesol
	private static final int PRICE = 250;
	// Temps avant de pouvoir replanter
	private static Timer cooldown;
	
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
		super(x, y);
		this.setLife(750);
		Dynamite.setCooldown(new Timer(0));
		this.setSpriteFilepath(new File("sprites/mob/dynamite.png"));
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
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
			&& entites.get(i).getX() <= x+0.09
			&& entites.get(i).getX() >= x-0.09
			&& entites.get(i).getY() <= y+0.09
			&& entites.get(i).getY() >= y-0.09)
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
	 * Redemarre le compteur de recharge pour l'achat
	 */
	public static void restartCooldown() {
		Dynamite.setCooldown(new Timer(30_000));
	}

	/**
	 * Afflige des dégats à tous les zombies autour de la dynamite puis la détruit
	 */
	public void explose() {
		Zombie zombieHere;
		// Zombie en haut
		zombieHere = Zombie.somethingHere(GameWorld.getEntites(), this.getX(), this.getY() - 0.1);
		if(zombieHere != null)
			zombieHere.takeDamage(DAMAGE);
		// Zombie a gauche
		zombieHere = Zombie.somethingHere(GameWorld.getEntites(), this.getX() - 0.1, this.getY());
		if(zombieHere != null)
			zombieHere.takeDamage(DAMAGE);
		// Zombie a droite
		zombieHere = Zombie.somethingHere(GameWorld.getEntites(), this.getX() + 0.1, this.getY());
		if(zombieHere != null)
			zombieHere.takeDamage(DAMAGE);
		// Zombie en bas
		zombieHere = Zombie.somethingHere(GameWorld.getEntites(), this.getX(), this.getY() + 0.1);
		if(zombieHere != null)
			zombieHere.takeDamage(DAMAGE);
		
		GameWorld.removeEntiteFrom(GameWorld.getEntites(),this);
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	
	/**
	 * Retourne le prix du tournesol
	 * 
	 * @return PRICE
	 */
	public static int getPrice() {
		return PRICE;
	}
	
	/**
	 * Retourne le nombre de damage du pois
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return DAMAGE;
	}
	
	/**
	 * Retourne le timer chargé de calculer le temps de rechargement de la plante
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
	 * Modifie le timer chargé de calculer le temps de rechargement de la plante
	 * 
	 * @param timer
	 */
	public static void setCooldown(Timer timer) {
		cooldown = timer;
	}


	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	
}
