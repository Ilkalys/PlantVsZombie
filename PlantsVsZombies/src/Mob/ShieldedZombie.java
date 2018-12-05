package Mob;

import java.io.File;

import Resources.StdDraw;

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

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, SPRITE_PATH.getAbsolutePath() + this.Animate() + ".png", 0.15, 0.15);
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le sprite d'un zombie blindes
	 * 
	 * @return SPRITE
	 */
	public static File getSpritePath() {
		return SPRITE_PATH;
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
