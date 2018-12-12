package Mob;

import java.io.File;
import java.util.List;

import Resources.Entite;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Mob extends Entite {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------	

	// Sprite de depart
	private File Sprite;
	// Points de vie de depart
	private int life;


	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Constructeur
	 * 
	 * @param x coordonne X
	 * @param y coordonne Y
	 * @param SpriteFilepath chemin vers le sprite de depart
	 * @param life point de vie de depart
	 */
	public Mob(double x, double y, String SpriteFilepath, int life) {
		super(x, y);
		this.Sprite = new File(SpriteFilepath);
		this.life = life;
	}


	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------

	/**
	 * Dessine le mob, aux bonnes coordonnees
	 */
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, Sprite.getAbsolutePath(), 0.15, 0.15);
	}

	/**
	 * Verifie si un mob se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return le mob trouve
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
	 * Retire des points de vie au mob
	 * 
	 * @param damage nombre de points a retirer
	 */
	public void takeDamage(int damage) {
		this.life -= damage;
		if(this.life <= 0) {
			SoundPlayer.PlaySE("death.wav");
			GameWorld.removeEntiteFrom(GameWorld.getEntites(),this);
			GameWorld.addDeadMob(this.getX(), this.getY(), this.getClass().getName());
		}
	}
	

	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne le sprite actuel
	 * 
	 * @return Sprite sprite actuel
	 */
	public File getSprite() {
		return this.Sprite;
	}

	/**
	 * Retourne le nombre de points de vie actuel
	 * 
	 * @return life nombre de points de vie actuel
	 */
	public int getLife() {
		return this.life;
	}


	//------------------------------------------------------------------------------
	/*
	 **      SETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Modifie le sprite
	 * 
	 * @param Sprite nouveau sprite
	 */
	public void setSprite(File Sprite) {
		this.Sprite = Sprite;
	}

	/**
	 * Modifie les points de vie du mob
	 * 
	 * @param life nouveau nombre de points de vie
	 */
	public void setLife(int life) {
		this.life = life;
	}

}
