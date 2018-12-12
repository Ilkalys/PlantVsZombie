package Mob;

import java.io.File;

import Resources.Entite;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Explosion extends Entite {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Sprite de l'explosion
	private static final File SPRITE = new File("sprites/mob/dynamite/explosion.png");
	// Temps de vie de l'explosion
	private static final int LIFE_TIME = 100;
	// Timer du temps de vie
	private Timer Life;
	// Taille du sprite
	private double spriteSize;

	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	
	public Explosion(double x, double y) {
		super(x, y);
		this.Life = new Timer(LIFE_TIME);
		this.spriteSize = 0;
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Met a jour l'entite : deplacement, effectuer une action
	 */
	public void step() {
		if(this.Life.hasFinished()) {
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
		}
	}
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public void dessine() {
		if(this.spriteSize < 0.3)
			this.spriteSize += 0.1;
		StdDraw.picture(this.getX(), this.getY(), SPRITE.getAbsolutePath(), spriteSize, spriteSize);
	}

	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le sprite de l'explosion
	 * 
	 * @return ICONE sprite de l'explosion
	 */
	public static File getSprite() {
		return SPRITE;
	}
	/**
	 * Retourne le temps de vie du soleil
	 * 
	 * @return LIFE_TIME temps de vie du soleil
	 */
	public static int getLifeTime() {
		return LIFE_TIME;
	}
	
	/**
	 * Retourne le timer pour le temps de vie d'un soleil
	 * 
	 * @return Life timer pour le temps de vie d'un soleil
	 */
	public Timer getLife() {
		return this.Life;
	}
	
	/**
	 * Retourne la taille du sprite
	 * 
	 * @return spriteSize taille du sprite
	 */
	public double getSpirteSize() {
		return this.spriteSize;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie le timer pour le temps de vie d'un soleil
	 * 
	 * @param timer nouveau timer
	 */
	public void setLife(Timer timer) {
		this.Life = timer;
	}
	
	/**
	 * Modifie la taille du sprite
	 * 
	 * @param spriteSize
	 */
	public void setSpirteSize(double spriteSize) {
		this.spriteSize = spriteSize;
	}
	
}
