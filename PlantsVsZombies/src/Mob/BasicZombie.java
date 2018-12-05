package Mob;

import java.io.File;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class BasicZombie extends Zombie {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Sprite d'un zombie basique
	private static final File SPRITE_DEFAULT = new File("sprites/mob/basicZombie.png");
	// Point de vie de depart d'un zombie basique
	private static final int HPMAX = 200;

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
		super(x, y, SPRITE_DEFAULT.getAbsolutePath(), HPMAX);
	}
		
	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------

	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le sprite d'un zombie basique
	 * 
	 * @return SPRITE
	 */
	public static File getSpriteDefault() {
		return SPRITE_DEFAULT;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un zombie basique
	 * 
	 * @return HPMAX
	 */
	public static int getHPMax() {
		return HPMAX;
	}
	

	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	
}
