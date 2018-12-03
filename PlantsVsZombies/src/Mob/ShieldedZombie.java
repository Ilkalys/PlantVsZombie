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
	
	// Chelin vers le sprite du zombie
	private File SpriteFilepath;
	
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
		super(x, y);
		this.setLife(560);
		this.setDamage(30);
		this.setSpeed(0.0025);
		SpriteFilepath = new File("sprites\\mob\\shieldedZombie.png");
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public  void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SpriteFilepath.getAbsolutePath(), 0.2, 0.2);
	}
		
}
