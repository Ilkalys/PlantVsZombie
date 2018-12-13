package Resources;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class ZombieSpawner {
	
	//------------------------------------------------------------------------------
	/*
	 **      CLASSE INTERNE
	 */
	//------------------------------------------------------------------------------
	
	private class ZombieInfos {
		// Temps avant l'apparition du prochain zombie
		private Integer timerValue;
		// Type du zombie : 0 = basique ; 1 = blinde ; 2 = explosif
		private int type;

		// Constructeur
		public ZombieInfos(Integer timer, int type) {
			this.timerValue = timer;
			this.type = type;
		}
		
		 // Getters
		public Integer getTimerValue() {
			return this.timerValue;
		}
		public int getType() {
			return this.type;
		}
	}

	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Vague de zombie
	private ConcurrentLinkedQueue<ZombieInfos> level;
	// Random
	private Random rand = new Random();
	// Timer avant le debut de la vague
	private Timer timer;
	// Difficulte
	private static int currentDifficulty = 0;

	
	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur
	 * 
	 * @param difficulty niveau souhaite
	 */
	public ZombieSpawner(int difficulty) {
		currentDifficulty = difficulty;
		this.timer = new Timer(20_000);
		switch (difficulty) {
		case 1:
			this.level = level1();
			break;
		case 2:
			this.level = level2();
			break;
		case 3:
			this.level = level3();
			break;
		case 4:
			this.level = level4();
			break;
		case 5:
			this.level = level5();
			break;
		case 6:
			this.level = level6();
			break;
		case 7:
			this.level = level7();
			break;
		case 8:
			this.level = level8();
			break;
		case 9:
			this.level = level9();
			break;
		case 10:
			this.level = level10();
			break;
		default:
			this.level = level1();
			break;
		}
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
		if(timer.hasFinished() && !level.isEmpty()) {
			ZombieInfos tmp = level.poll();
			GameWorld.addZombie(1.05, (double)rand.nextInt(5)/10 +0.3, tmp.getType());
			timer = new Timer(tmp.getTimerValue());
		}
	}

	/**
	 * Retourne les zombies du niveau 1
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level1() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 2
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level2() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(0, 0));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 3
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level3() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(30_000, 0));
		level.add(new ZombieInfos(30_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(0, 0));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 4
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level4() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(15_000, 0));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(35_000, 0));
		level.add(new ZombieInfos(0, 1));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 5
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level5() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(0, 0));
		level.add(new ZombieInfos(35_000, 0));
		level.add(new ZombieInfos(0, 1));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 6
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level6() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(50_000, 0));
		level.add(new ZombieInfos(5_000, 1));
		level.add(new ZombieInfos(5_000, 1));
		level.add(new ZombieInfos(5_000, 1));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 7
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level7() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(10_000, 2));
		level.add(new ZombieInfos(25_000, 0));
		level.add(new ZombieInfos(15_000, 1));
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(0, 2));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 8
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level8() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(10_000, 1));
		level.add(new ZombieInfos(30_000, 2));
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(0, 1));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 9
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level9() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(10_000, 2));
		level.add(new ZombieInfos(10_000, 2));
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(30_000, 0));
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(10_000, 2));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	/**
	 * Retourne les zombies du niveau 10
	 * 
	 * @return level
	 */
	private ConcurrentLinkedQueue<ZombieInfos> level10() {
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(10_000, 1));
		level.add(new ZombieInfos(10_000, 0));
		level.add(new ZombieInfos(10_000, 1));
		level.add(new ZombieInfos(10_000, 2));
		level.add(new ZombieInfos(0, 2));
		level.add(new ZombieInfos(0, 1));
		return level;
	}

	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne le niveau en cours
	 * 
	 * @return level
	 */
	public ConcurrentLinkedQueue<ZombieInfos> getLevel() {
		return this.level;
	}

	/**
	 * Retourne la difficulte actuelle
	 * 
	 * @return currentDifficulty
	 */
	public static int getCurrentDifficulty() {
		return currentDifficulty;
	}


}
