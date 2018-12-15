package Mob;

import Resources.Entite;
import Resources.Game;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class DeadMob extends Entite {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Temps (en ms) avant que le mob disparaisse
	private static final int DESPAWN_TIME = 1_000;
	// Timer charge du temps de vie
	private Timer despawnTimer;
	// Chemin vers les sprites d'animation
	private final String SpriteAnim;

	
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
	 * @param classe classe mourrante
	 */
	public DeadMob(double x, double y, String classe) {
		super(x, y);
		despawnTimer = new Timer(DESPAWN_TIME);
		SpriteAnim = Game.getSpritefilepath().toString() + "/mob/deadMob/" + classe.substring(4) + "_death_";
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Met a jour l'entite : deplacement, effectuer une action
	 */
	@Override
	public void step() {
		if(despawnTimer.hasFinished())
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
	}

	/**
	 * Dessine le mob, aux bonnes coordonnees
	 */
	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SpriteAnim + Animate() +".png", 0.15, 0.15);;

	}

	/**
	 * Calcul la prochaine image de l'animation
	 */
	public String Animate() {
		double tmp = DESPAWN_TIME/1000 - despawnTimer.getActualTime();
		if(tmp > 0.6)
			return "3";
		else if(tmp > 0.4)
			return "2";
		else if(tmp > 0.2)
			return "1";
		else
			return "0";
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le temps (en ms) avant que le mob disparaisse
	 * 
	 * @return DESPAWN_TIME temps (en ms) avant que le mob disparaisse
	 */
	public static int getDespawnTime() {
		return DESPAWN_TIME;
	}

	/**
	 * Retourne le timer charge du temps de vie
	 * 
	 * @return despawnTimer timer charge du temps de vie
	 */
	public Timer getDespawnTimer() {
		return this.despawnTimer;
	}
	
	/**
	 * Retourne le chemin vers les sprites d'animation
	 * 
	 * @return SpriteAnim chemin vers les sprites d'animation
	 */
	public String getSpriteAnim() {
		return this.SpriteAnim;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Modifie le timer charge du temps de vie
	 * 
	 * @param timer timer charge du temps de vie
	 */
	public void setDespawnTimer(Timer timer) {
		this.despawnTimer = timer;
	}
	
}
