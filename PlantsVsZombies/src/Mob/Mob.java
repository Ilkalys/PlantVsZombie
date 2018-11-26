package Mob;
import java.util.List;

import Resources.Entite;
import Resources.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Mob extends Entite {
	
	// Points de vie
	private int life;	

	
	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur
	 * 
	 * @param x coordonne X du mob
	 * @param y coordonne Y du mob
	 */
	public Mob(double x, double y) {
		super(x, y);
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Verifie si un mob se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return boolean
	 */
	public static Mob somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Mob
			&& entites.get(i).getX() <= x+0.09
			&& entites.get(i).getX() >= x-0.09
			&& entites.get(i).getY() <= y+0.09
			&& entites.get(i).getY() >= y-0.09)
				return (Mob)entites.get(i);
		return null;
	}

	/**
	 * Retire des points de vie
	 * 
	 * @param damage nombre de points a retirer
	 */
	public void takeDamage(int damage) {
		this.life -= damage;
		if(this.life <= 0)
			GameWorld.removeEntite(this);
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le nombre de points de vie
	 * 
	 * @return life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Retourne true si le mob n'a plus de points de vie
	 * 
	 * @return boolean
	 */
	public boolean isDead() {
		return (this.life <= 0);
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Modifie les points de vie du mod
	 * 
	 * @param life nombre de points de vie
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
}
