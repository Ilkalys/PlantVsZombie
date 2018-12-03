package Mob;
import java.io.File;
import java.util.List;

import Resources.Entite;
import Resources.StdDraw;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Mob extends Entite {
	
	// Points de vie
	private int life;	

	// Chelin vers le sprite de la plante
	private File spriteFilepath;
	
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
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, this.spriteFilepath.getAbsolutePath(), 0.15, 0.15);
	}
	
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
			GameWorld.removeEntiteFrom(GameWorld.getEntites(),this);
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

	/**
	 * Retourne le chemin du sprite
	 * 
	 * @return spriteFilepath
	 */
	public File getSpriteFilepath() {
		return spriteFilepath;
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

	/**
	 * Change le sprite du mob
	 * 
	 * @param spriteFilepath chemin du nouveau sprite
	 */
	public void setSpriteFilepath(File spriteFilepath) {
		this.spriteFilepath = spriteFilepath;
	}
	
}
