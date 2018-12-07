package Mob;

import java.util.List;

import Resources.Entite;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Zombie extends Mob {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

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
	 * @param x coordonne X du zombie
	 * @param y coordonne Y du zombie
	 */
	public Zombie(double x, double y, String SpriteFilepath, int life) {
		super(x, y, SpriteFilepath, life);
		this.actualAnim = 0;
	}


	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------

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

	
	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne le status de l'animation
	 * 
	 * @return actualAnim
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
	 * Modifie le status de l'animation
	 * 
	 * @param actualAnim nouveau status
	 */
	public void setActualAnim(int actualAnim) {
		this.actualAnim = actualAnim;
	}

}
