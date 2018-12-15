package Mob;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import Resources.Entite;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class ExplosiveZombie extends Zombie {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le sprites d'un zombie explosif
	private static final File SPRITE_PATH = new File(ExplosiveZombie.class.getResource("/sprites/mob/explosiveZombie").toString());
	// Point de vie de depart d'un zombie explosif
	private static final int HPMAX = 80;
	// Nombre de degat qu'inflige un zombie explosif
	private static final int DAMAGE = 500;
	// Vitesse d'un zombie basique
	private static final double SPEED = 0.5;
	
	// Est a l'arret ou non
	private boolean stop;
	

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
	public ExplosiveZombie(double x, double y) {
		super(x, y, SPRITE_PATH.getPath() + "/explosiveZombie_walk_0.png", HPMAX);
		this.stop = false;
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
		Plant obstacle = Plant.somethingHere(GameWorld.getEntites(), this.getX() - 0.01 - (SPEED / 300), this.getY());
		if(obstacle == null)
			this.position.setX(this.position.getX() - (SPEED / 300));
		else
			this.stop = true;
	}

	/**
	 * Dessine l'entite, aux bonnes coordonnees
	 */
	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY() + 0.01, SPRITE_PATH.getPath()+ "/explosiveZombie_" + this.Animate() + ".png", 0.15, 0.15);
	}
	
	/**
	 * Determine quel sprite afficher pour l'animation
	 * 
	 * @return String
	 */
	private String Animate() {
		if(!this.stop) {
			if(this.getActualAnim() >= 20)
				this.setActualAnim(0);
			else
				this.setActualAnim(this.getActualAnim() + 1);
			if(this.getActualAnim() >= 15 && this.getActualAnim() <= 20)
				return "walk_1";
			else if(this.getActualAnim() >= 10 && this.getActualAnim() <= 15)
				return "walk_2";
			else if(this.getActualAnim() >= 5 && this.getActualAnim() <= 10)
				return "walk_3";
		}
		return "walk_0";
	}
	
	/**
	 * Verifie si un zombie est particulier est un une case precise
	 * 
	 * @param Plant Plante a regarder
	 * @param x coordonne X du milieu de la case ou regarder
	 * @param y coordonne Y du milieu de la case ou regarder
	 * @return true si la plante est a cette emplacement
	 */
	private boolean PlantHere(Plant Plant, double x, double y) {
		if(Plant.getX() <= x + 0.09 && Plant.getX() >= x - 0.09 && Plant.getY() <= y + 0.09 && Plant.getY() >= y - 0.09) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Retire des points de vie
	 * 
	 * @param damage nombre de points a retirer
	 */
	@Override
	public void takeDamage(int damage) {
		this.setLife(this.getLife() - damage);
		if(this.getLife() <= 0)
			explose();
	}

	/**
	 * Afflige des dégats à toutes les plantes autour du zombie puis la détruit
	 */
	public void explose() {
		GameWorld.addExplosion(this.getX(), this.getY());
		List<Entite> entites = GameWorld.getEntites();
		List<Entite> targets = new LinkedList<Entite>();
		for (int i = 0; i < entites.size(); i++) {
			if(entites.get(i) instanceof Plant) {
				if(this.PlantHere(((Plant)entites.get(i)), this.getX() - 0.1, this.getY())
				|| this.PlantHere(((Plant)entites.get(i)), this.getX() + 0.1, this.getY())
				|| this.PlantHere(((Plant)entites.get(i)), this.getX(), this.getY() - 0.1)
				|| this.PlantHere(((Plant)entites.get(i)), this.getX(), this.getY() + 0.1)) {
					targets.add(entites.get(i));
				}
			}
		}
		for (int i = 0; i < targets.size(); i++) {
			((Plant)targets.get(i)).takeDamage(DAMAGE);
		}
		SoundPlayer.PlaySE("explosion.wav");
		GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
	}
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le sprite d'un zombie explosif
	 * 
	 * @return SPRITE sprite d'un zombie explosif
	 */
	public static File getSpritePath() {
		return SPRITE_PATH;
	}
	
	/**
	 * Retourne le nombre de point de vie de depart d'un zombie explosif
	 * 
	 * @return HPMAX nombre de point de vie de depart d'un zombie explosif
	 */
	public static int getHPMax() {
		return HPMAX;
	}

	/**
	 * Retourne le nombre de damage du zombie
	 * 
	 * @return DAMAGE nombre de damage du zombie
	 */
	public static int getDamage() {
		return DAMAGE;
	}

	/**
	 * Retourne la vitesse du zombie
	 * 
	 * @return SPEED vitesse du zombie
	 */
	public static double getSpeed() {
		return SPEED;
	}
	
	/**
	 * Retourne le booleen charge de determiner si le zombie est a l'arret ou non
	 * 
	 * @return stop booleen charge de determiner si le zombie est a l'arret ou non
	 */
	public boolean getStop() {
		return this.stop;
	}

	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie le booleen charge de determiner si le zombie est a l'arret ou non
	 * 
	 * @param stop booleen charge de determiner si le zombie est a l'arret ou non
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
