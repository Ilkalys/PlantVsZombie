package Resources;

import java.util.Random;

import Mob.Sun;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class SunSpawner {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	// Temps (en ms) avant qu'un soleil apparaisse
	private static final int SPAWNTIME = 6_500;
	// Timer charge de l'apparition des soleils
	private Timer timer;
	// Random
	private Random rand = new Random();

	
	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
		
	/**
	 * Constructeur
	 */
	public SunSpawner() {
		this.timer = new Timer(SPAWNTIME);
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
		double x = (double)rand.nextInt(9)/10 +0.1;
		double y = (double)rand.nextInt(5)/10 +0.3;
		if(timer.hasFinished() && Sun.somethingHere(GameWorld.getSuns(), x, y) == null) {
				GameWorld.addSun(x, y);
				timer = new Timer(SPAWNTIME);
		}
	}
	
}