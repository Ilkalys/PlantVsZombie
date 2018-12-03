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
		this.setSpriteFilepath(new File("sprites\\mob\\shieldedZombie.png"));
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	

}
