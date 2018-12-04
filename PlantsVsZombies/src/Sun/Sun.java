package Sun;

import java.io.File;
import java.util.List;

import Resources.Entite;
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
	
	// Valeur du soleil
	private static final int VALUE = 25;
	// Temps de vie du soleil
	private static final int LIFE_TIME = 20_000;
	// Timer du temps de vie
	private Timer lifeTimer;
	// Chemin vers le sprite
	private File SpriteFilepath;

	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	
	public Sun(double x, double y) {
		super(x, y);
		this.lifeTimer = new Timer(LIFE_TIME);
		SpriteFilepath = new File("sprites/mob/sun.png");
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
		if(this.lifeTimer.hasFinished()) {
			GameWorld.removeEntiteFrom(GameWorld.getSuns(),this);
			GameWorld.getBank().add(VALUE);
		}
	}
	
	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	public  void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SpriteFilepath.getAbsolutePath(), 0.1, 0.1);
	}

	/**
	 * Verifie si un mob se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return le soleil trouvé ou null
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
	 * Retourne la valeur du soleil
	 * 
	 * @return VALUE
	 */
	public static int getValue() {
		return VALUE;
	}

	/**
	 * Retourne le temps de vie du soleil
	 * 
	 * @return LIFE_TIME
	 */
	public static int getLifeTime() {
		return LIFE_TIME;
	}
	
}
