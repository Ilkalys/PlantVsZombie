package Screens;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import Mob.*;
import Resources.*;
import Sun.*;

/**
 * @author TERMIER Alexandre, GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class GameWorld extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// L'ensemble des entites, pour gerer (notamment) l'affichage
	private static List<Entite> entites;
	// L'ensemble des Suns qui apparaitront.
	private static List<Entite> suns;
	// Plante selectionne
	private static String selectedPlant;
	// Gestionnaire des apparitions de soleil
	private static SunSpawner sunSpawn;
	// Porte-feuille du joueur
	private static SunWallet bank;
	// Gestionnaire des apparitions de zombies
	private static ZombieSpawner zombieSpawn;
	// Quantité de zombies du niveau
	private static int zombieQuantity;

	// Touche pour selectionner un tournesol
	private static final char SUNFLOWER_KEY = 't';
	// Touche pour selectionner un tire-pois
	private static final char PEASSHOOTER_KEY = 'p';
	// Touche pour selectionner une noix
	private static final char NUTS_KEY = 'n';
	private File SpriteFilepath = new File("sprites");



	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Constructeur, il faut initialiser notre monde virtuel
	 */
	public GameWorld(int difficulte) {
		super();

		//Musique
		SoundPlayer.PlayBGM("Western.wav");
		
		// Remises à zéro des timers
		Sunflower.setCooldown(new Timer(0));
		PeasShooter.setCooldown(new Timer(0));
		Nuts.setCooldown(new Timer(0));

		// On cree les collections
		entites = new LinkedList<Entite>();
		suns = new LinkedList<Entite>();

		selectedPlant = null;
		sunSpawn = new SunSpawner();
		bank = new SunWallet(0, 0, 50);
		entites.add(bank);
		zombieSpawn = new ZombieSpawner(difficulte);
		zombieQuantity = zombieSpawn.getLevel().size();
	}


	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------


	/**  
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		switch (key) {
		case SUNFLOWER_KEY:
			selectSunflower();
			break;
		case PEASSHOOTER_KEY:
			selectPeasShooter();
			break;
		case NUTS_KEY:
			selectNuts();
			break;
		default:
			System.out.println("Touche non prise en charge.");
			break;
		}
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		System.out.println("La souris a ete clique en (" + x + ";" + y + ")");
		// Recuperation d'un soleil
		Sun sunHere = Sun.somethingHere(suns, x, y);
		if(sunHere != null) {
			bank.add(Sun.getValue());
			suns.remove(sunHere);
		} else {
			// Selection d'un tournesol à la souris
			if(x >= 0.05 && x <= 0.15 && y >= 0.05 && y <= 0.15) {
				selectSunflower();
			}
			// Selection d'un tire-pois à la souris
			if(x >= 0.25 && x <= 0.35 && y >= 0.05 && y <= 0.15) {
				selectPeasShooter();
			}
			// Selection d'une noix à la souris
			if(x >= 0.45 && x <= 0.55 && y >= 0.05 && y <= 0.15) {
				selectNuts();
			}
			// Plantation
			if(x < 0.95 && x > 0.05 && y < 0.75 && y > 0.25) {
				double rx, ry;
				rx = (x % 0.1 <= 0.05)? x - (x % 0.1) : x - (x % 0.1) + 0.1;
				ry = (y % 0.1 <= 0.05)? y - (y % 0.1) : y - (y % 0.1) + 0.1;
				// Verification que la case souhaité soit vide
				if(Mob.somethingHere(entites, rx, ry) == null) {
					if(selectedPlant == Sunflower.class.getName()) {
						bank.add(-50);
						entites.add(new Sunflower(rx, ry));
						Sunflower.restartCooldown();
						selectedPlant = null;
					}
					if(selectedPlant == PeasShooter.class.getName()) {
						bank.add(-100);
						entites.add(new PeasShooter(rx, ry));
						PeasShooter.restartCooldown();
						selectedPlant = null;
					}
					if(selectedPlant == Nuts.class.getName()) {
						bank.add(-50);
						entites.add(new Nuts(rx, ry));
						Nuts.restartCooldown();
						selectedPlant = null;
					}
					if(selectedPlant == null) {
						System.out.println("Pas de plante selectionne.");
					}
				}
				else System.out.println("Il y a deja quelque chose ici.");
			}
		}
	}

	/**
	 * Fait bouger/agir toutes les entites
	 */
	public void step() {

		for (int i = 0; i < entites.size(); i++) {
			if(entites.get(i) != null) {
				entites.get(i).step();
			}
		}
		for (int i = 0; i < suns.size(); i++) {
			if(suns.get(i) != null) {
				suns.get(i).step();
			}
		}
		zombieSpawn.step();
		sunSpawn.step();


	}
	/**
	 * Dessine les entites du jeu
	 */
	@SuppressWarnings("static-access")
	public void dessine() {

		StdDraw.setFont();
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() +"/bg/FondLevel.png", 1, 1);
		StdDraw.picture(0.9, 0.05, SpriteFilepath.getAbsolutePath() +"/bg/PanneauMonnaie.png", 0.15, 0.15);
		StdDraw.picture(0.9, 0.95, SpriteFilepath.getAbsolutePath() +"/bg/PanneauScore.png", 0.2, 0.2);
		StdDraw.text(0.9, 0.932, "Level : " + zombieSpawn.getCurrentDifficulty());
		StdDraw.text(0.9, 0.908, "Remaining : " + zombieQuantity);

		if(selectedPlant == Sunflower.class.getName())
			StdDraw.picture(0.1, 0.1, SpriteFilepath.getAbsolutePath() + "/bg/Selection.png",0.1,0.1);;
			StdDraw.picture(0.1, 0.1, SpriteFilepath.getAbsolutePath() +"/mob/sunflower.png", 0.12, 0.12);
			double heightLoadSunFlo = ((Sunflower.getCooldown() == null)? 0 : Sunflower.getCooldown().getActualTime()/50);
			StdDraw.picture(0.1, 0.1, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",heightLoadSunFlo,heightLoadSunFlo);

			if(selectedPlant == PeasShooter.class.getName())
				StdDraw.picture(0.3, 0.1, SpriteFilepath.getAbsolutePath() + "/bg/Selection.png",0.1,0.1);
			StdDraw.picture(0.3, 0.1, SpriteFilepath.getAbsolutePath() +"/mob/peasShooter.png", 0.12, 0.12);
			double heightLoadPeasSh = ((PeasShooter.getCooldown() == null)? 0 : PeasShooter.getCooldown().getActualTime()/50);
			StdDraw.picture(0.3, 0.1, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",heightLoadPeasSh,heightLoadPeasSh);

			if(selectedPlant == Nuts.class.getName())
				StdDraw.picture(0.5, 0.1, SpriteFilepath.getAbsolutePath() + "/bg/Selection.png",0.1,0.1);
			StdDraw.picture(0.5, 0.1, SpriteFilepath.getAbsolutePath() +"/mob/nuts.png", 0.12, 0.12);
			double heightLoadNuts = ((Nuts.getCooldown() == null)? 0 : Nuts.getCooldown().getActualTime()/200);
			StdDraw.picture(0.5, 0.1, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",heightLoadNuts,heightLoadNuts);


			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(0.1, 0.1, 0.05);	
			StdDraw.text(0.1, 0.17, Sunflower.getPrice()+"");
			StdDraw.square(0.3, 0.1, 0.05);
			StdDraw.text(0.3, 0.17, PeasShooter.getPrice()+"");
			StdDraw.square(0.5, 0.1, 0.05);
			StdDraw.text(0.5, 0.17, Nuts.getPrice()+"");

			// Cadriage
			if(selectedPlant != null) {
				for (double i = 0.70; i >= 0.10; i -= 0.2) {
					for (double j = 0.1; j <= 0.9; j += 0.2) {
						StdDraw.picture(j, i, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png", 0.1, 0.1);
					}
				}
				for (double i = 0.60; i >= 0.20; i -= 0.2) {
					for (double j = 0.2; j <= 0.8; j += 0.2) {
						StdDraw.picture(j, i, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png", 0.1, 0.1);
					}
				}
			}
			// Affiche les entites de façon à avoir les sprites les plus haut le plus en profondeur
			for (float i = 1; i >= 0; i -= 0.1) {
				for (int j = 0; j < entites.size(); j++) {
					if(entites.get(j).getY() < i && entites.get(j).getY() >= i-0.1) {
						entites.get(j).dessine();
					}
				}
			}
			for (Entite Sun : suns)
				Sun.dessine();
	}

	/**
	 * Selectionne ou deselectionne le tournesol
	 */
	private void selectSunflower() {
		if(selectedPlant != Sunflower.class.getName()) {
			System.out.println("Le joueur souhaite selectionner un tounesol...");
			if(Sunflower.getCooldown() == null || Sunflower.getCooldown().hasFinished())
				if(bank.enoughSun(Sunflower.getPrice())) {
					System.out.println("Tournesol selectionne !");
					selectedPlant = Sunflower.class.getName();
				}
				else
					System.out.println("Mais il ne possede pas assez de soleil.");
			else
				System.out.println("Mais le temps de recharge ne c'est pas effectue.");
		}
		else {
			System.out.println("Pas de plante selectionne.");
			selectedPlant = null;
		}
	}

	/**
	 * Selectionne ou deselectionne le tire-pois
	 */
	private void selectPeasShooter() {
		if(selectedPlant != PeasShooter.class.getName()) {
			System.out.println("Le joueur souhaite selectionner un tire-pois...");
			if(PeasShooter.getCooldown() == null || PeasShooter.getCooldown().hasFinished())
				if(bank.enoughSun(PeasShooter.getPrice())) {
					System.out.println("Tire-pois selectionne !");
					selectedPlant = PeasShooter.class.getName();
				}
				else
					System.out.println("Mais il ne possede pas assez de soleil.");
			else
				System.out.println("Mais le temps de recharge ne c'est pas effectue.");
		}
		else {
			System.out.println("Pas de plante selectionne.");
			selectedPlant = null;
		}
	}

	/**
	 * Selectionne ou deselectionne la noix
	 */
	private void selectNuts() {
		if(selectedPlant != Nuts.class.getName()) {
			System.out.println("Le joueur souhaite selectionner une noix...");
			if(Nuts.getCooldown() == null || Nuts.getCooldown().hasFinished())
				if(bank.enoughSun(Nuts.getPrice())) {
					System.out.println("Noix selectionnee !");
					selectedPlant = Nuts.class.getName();
				}
				else
					System.out.println("Mais il ne possede pas assez de soleil.");
			else
				System.out.println("Mais le temps de recharge ne c'est pas effectue.");
		}
		else {
			System.out.println("Pas de plante selectionne.");
			selectedPlant = null;
		}
	}

	/**
	 * Fait apparaitre un nouveau Soleil(Sun)
	 * @param x la position x du soleil
	 * @param y la position y du soleil
	 */
	public static void addSun(double x, double y) {
		suns.add(new Sun(x,y));
	}

	/**
	 * Fait apparaitre un nouveau Zombie(Sun)
	 * @param x la position x du zombie
	 * @param y la position y du zombie
	 * @param shielded le zombie est-il blindé?
	 */
	public static void addZombie(double x, double y, boolean shielded) {
		if(zombieQuantity !=0) {
			if(shielded)
				entites.add(new ShieldedZombie(x,y));
			else
				entites.add(new BasicZombie(x,y));
			zombieQuantity--;
		}

	}

	/**
	 * Fait apparaitre un nouveau Pois(Peas)
	 * @param x la position x du pois
	 * @param y la position y du pois
	 */
	public static void addPeas(double x, double y) {
		entites.add(new Peas(x,y));
	}

	/**
	 * retire une entitée d'une des listes d'entitées
	 * @param entitesList la liste d'entitées selectionnée
	 * @param entite l'entitée que l'on veut supprimer
	 */
	public static void removeEntiteFrom(List<Entite> entitesList, Entite entite) {
		entitesList.remove(entite);
	}

	/**
	 * Retourne true si le jeu est perdu
	 * 
	 * @return gameLost
	 */
	public boolean gameLost() {
		if (entites != null)
			for(Entite entite  :entites)
				if(entite.getX() < 0) 
					return true;
		return false;
	}

	public boolean LevelComplete(){
		if(!AnyZombie() && zombieQuantity == 0) {
			return true;
		}
		return false;

	}

	/**
	 * Verifie qu'il reste des zombies vivants sur la scène
	 * @return si il en reste
	 */
	public boolean AnyZombie() {
		if (entites != null) {
			for (Entite entite : entites)
				if(entite instanceof Zombie)
					return true;
			return false;
		}
		return true;
	}


	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne l'ensemble des plantes et zombies de la scene
	 * 
	 * @return entites la liste des plantes et zombies
	 */
	public static List<Entite> getEntites() {
		return entites;
	}

	/**
	 * Retourne l'ensemble des soleils de la scene
	 * 
	 * @return suns la liste des soleils 
	 */
	public static List<Entite> getSuns() {
		return suns;
	}

	/**
	 * Retourne le porte-feuille du joueur
	 * 
	 * @return bank
	 */
	public static SunWallet getBank() {
		return bank;
	}


	public static int getZombieQuantity() {
		return zombieQuantity;
	}


	public static void setZombieQuantity(int zombieQuantity) {
		GameWorld.zombieQuantity = zombieQuantity;
	}


}
