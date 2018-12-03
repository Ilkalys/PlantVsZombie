package Mob;

import java.io.File;

import Resources.*;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class BasicZombie extends Zombie {
	
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
	public BasicZombie(double x, double y) {
		super(x, y);
		this.setLife(200);
		this.setDamage(30);
		this.setSpeed(0.0025);
		SpriteFilepath = new File("sprites\\mob\\basicZombie.png");
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
