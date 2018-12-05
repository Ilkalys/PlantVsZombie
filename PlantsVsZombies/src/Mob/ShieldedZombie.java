package Mob;

import java.io.File;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class ShieldedZombie extends Zombie {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Sprite d'un zombie blinde
	private static final File SPRITE_DEFAULT = new File("sprites/mob/shieldedZombie.png");
	// Point de vie de depart d'un zombie blinde
	private static final int HPMAX = 560;
	
	// Chemin vers les sprites d'animation
		private static final File SpriteAnim = new File("sprites/mob/shieldedZombie/shieldedZombie_");

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
	 * Retourne le sprite d'un zombie blinde
	 * 
	 * @return SPRITE
	 */
	public static File getSpriteDefault() {
		return SPRITE_DEFAULT;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un zombie blinde
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
