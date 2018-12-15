package Mob;

import java.util.List;

import Resources.Entite;
import Resources.Game;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Sun extends Entite {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le dossier de sprite de BackGround
	private static final String SPRITEFILEPATH = Game.getSpritefilepath().toString() + "/mob/sun.png";

	// Valeur du soleil
	private static final int VALUE = 25;
	// Temps de vie du soleil
	private static final int LIFE_TIME = 20_000;
	// Timer du temps de vie
	private Timer Life;

	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	
	public Sun(double x, double y) {
		super(x, y);
		this.Life = new Timer(LIFE_TIME);
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
			GameWorld.removeEntiteFrom(GameWorld.getSuns(), this);
		}
	}
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public  void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SPRITEFILEPATH, 0.1, 0.1);
	}

	/**
	 * Verifie si un mob se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return le soleil trouv� ou null
	 */
	public static Sun somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Sun
			&& entites.get(i).getX() <= x+0.02
			&& entites.get(i).getX() >= x-0.02
			&& entites.get(i).getY() <= y+0.02
			&& entites.get(i).getY() >= y-0.02)
				return (Sun)entites.get(i);
		return null;
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne l'icone du soleil
	 * 
	 * @return SPRITEFILEPATH l' icone du soleil
	 */
	public static String getSpritefilepath() {
		return SPRITEFILEPATH;
	}


	/**
	 * Retourne la valeur du soleil
	 * 
	 * @return VALUE valeur du soleil
	 */
	public static int getValue() {
		return VALUE;
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
	
}
