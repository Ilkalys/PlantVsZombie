package Mob;

import java.io.File;

import Resources.StdDraw;

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
	private static final File SPRITE_DEFAULT = new File("sprites/mob/basicZombie/basicZombie_walk_0.png");
	// Point de vie de depart d'un zombie basique
	private static final int HPMAX = 200;
	
	// Chemin vers les sprites d'animation
	private static final File SpriteAnim = new File("sprites/mob/basicZombie/basicZombie_");

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

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, SpriteAnim.getAbsolutePath() + this.Animate() + ".png", 0.15, 0.15);
	}
	
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
