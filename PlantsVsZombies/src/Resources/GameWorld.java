package Resources;
import java.util.LinkedList;
import java.util.List;

import Mob.*;
import Sun.*;

/**
 * @author TERMIER Alexandre, GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class GameWorld {

	// L'ensemble des entites, pour gerer (notamment) l'affichage
	private static List<Entite> entites;
	// L'ensemble des Suns qui apparaitront.
	private static List<Entite> suns;
	//Pour savoir si la partie est gagnee ou pas
	private static boolean gameWon;
	// Idem pour savoir si le jeu est perdu (si le jeu n'est ni gagne ni perdu, il est en cours)
	private static boolean gameLost;

	// Porte-feuille du joueur
	private static SunWallet bank;
	// Dernier achat effectue ('s'=tournesol, 'p'=tire-pois, 'n'=noix, ' '=rien)
	private static char purchase;

	private static ZombieSpawner spawn;
	private static SunSpawner sunSpawn;
	private static int zombieQuantity;

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Constructeur, il faut initialiser notre monde virtuel
	 */
	public GameWorld() {
		gameWon=false;
		gameLost=false;
		bank = new SunWallet(0, 0, 50);
		spawn = new ZombieSpawner(1);
		sunSpawn = new SunSpawner();
		zombieQuantity =20;

		purchase = ' ';

		// On cree les collections
		entites = new LinkedList<Entite>();
		suns = new LinkedList<Entite>();
		entites.add(bank);

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
		case 't':
			if(purchase != 's') {
				System.out.println("Le joueur veut acheter un Tournesol...");
				if(Sunflower.getCooldown() == null || Sunflower.getCooldown().hasFinished())
					if(bank.enoughSun(50)) {
						purchase = 's';
						System.out.println("Tournesol selectionne !");
					} else System.out.println("Mais il ne possede pas assez de Sun.");
				else
					System.out.println("Mais le temps de recharge ne c'est pas effectue.");
			} else purchase = ' ';
			break;
		case 'p':
			if(purchase != 'p') {
				System.out.println("Le joueur veut acheter un Tire-Pois...");
				if(PeasShooter.getCooldown() == null || PeasShooter.getCooldown().hasFinished())
					if(bank.enoughSun(100)) {
						purchase = 'p';
						System.out.println("Tire-Pois selectionne !");
					} else System.out.println("Mais il ne possede pas assez de Sun.");
				else System.out.println("Mais le temps de recharge ne c'est pas effectue.");
			} else purchase = ' ';
			break;
		case 'n':
			if(purchase != 'n') {
				System.out.println("Le joueur veut acheter une Noix...");
				if(Nuts.getCooldown() == null || Nuts.getCooldown().hasFinished())
					if(bank.enoughSun(50)) {
						purchase = 'n';
						System.out.println("Noix selectionne !");
					} else System.out.println("Mais il ne possede pas assez de Sun.");
				else System.out.println("Mais le temps de recharge ne c'est pas effectue.");
			} else purchase = ' ';
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
		System.out.println("La souris a ete clique en : "+x+" - "+y);
		// Recuperation d'un sun
		Sun clique = Sun.somethingHere(suns, x, y);
		if(clique != null) {
			bank.add(Sun.getValue());
			suns.remove(clique);
		} else {
			// Selection d'un tournesol
			if(x >= 0.05 && x <= 0.15 && y >= 0.05 && y <= 0.15) {
				processUserInput('t');
			}
			// Selection d'un tire-pois
			if(x >= 0.25 && x <= 0.35 && y >= 0.05 && y <= 0.15) {
				processUserInput('p');
			}
			// Selection d'une noix
			if(x >= 0.45 && x <= 0.55 && y >= 0.05 && y <= 0.15) {
				processUserInput('n');
			}
			// Plantation
			if(x < 0.95 && x > 0.05 && y < 0.75 && y > 0.25) {
				double rx, ry;
				rx = (x % 0.1 <= 0.05)? x - (x % 0.1) : x - (x % 0.1) + 0.1;
				ry = (y % 0.1 <= 0.05)? y - (y % 0.1) : y - (y % 0.1) + 0.1;
				if(Mob.somethingHere(entites, rx, ry) == null) {
					switch (purchase) {
					case 's':
						bank.add(-50);
						entites.add(new Sunflower(rx, ry));
						Sunflower.restartCooldown();
						purchase = ' ';
						break;
					case 'p':
						bank.add(-100);
						entites.add(new PeasShooter(rx, ry));
						PeasShooter.restartCooldown();
						purchase = ' ';
						break;
					case 'n':
						bank.add(-50);
						entites.add(new Nuts(rx, ry));
						Nuts.restartCooldown();
						purchase = ' ';
						break;
					default:
						System.out.println("Pas de plante selectionne.");
						break;
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
		spawn.step();
		sunSpawn.step();
		if(!AnyZombie() && zombieQuantity == 0) {
			gameWon = true;
		}

	}
	/**
	 * Dessine les entites du jeu
	 */
	public void dessine() {

		StdDraw.setFont();
		StdDraw.setPenColor((purchase == 's')?StdDraw.RED : StdDraw.YELLOW);
		StdDraw.filledSquare(0.1, 0.1, 0.05 - ((Sunflower.getCooldown() == null)? 0 : Sunflower.getCooldown().getActualTime()/100));
		StdDraw.setPenColor((purchase == 'p')?StdDraw.RED : StdDraw.GREEN);
		StdDraw.filledSquare(0.3, 0.1, 0.05 - ((PeasShooter.getCooldown() == null)? 0 : PeasShooter.getCooldown().getActualTime()/100));
		StdDraw.setPenColor((purchase == 'n')?StdDraw.RED : StdDraw.ORANGE);
		StdDraw.filledSquare(0.5, 0.1, 0.05 - ((Nuts.getCooldown() == null)? 0 : Nuts.getCooldown().getActualTime()/400));
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square(0.1, 0.1, 0.05);		
		StdDraw.square(0.3, 0.1, 0.05);
		StdDraw.square(0.5, 0.1, 0.05);

		// Affiche les entites
		for (Entite entite : entites)
			entite.dessine();
		for (Entite Sun : suns)
			Sun.dessine();
	}

	/**
	 * Fait apparitre un nouveau Sun
	 */
	public static void addSun(double x, double y) {
		suns.add(new Sun(x,y));
	}

	public static void addZombie(double x, double y, boolean shielded) {
		if(zombieQuantity !=0) {
			if(shielded)
				entites.add(new ShieldedZombie(x,y));
			else
				entites.add(new BasicZombie(x,y));
			zombieQuantity--;
		}

	}

	public static void addPeas(double x, double y) {
		entites.add(new Peas(x,y));
	}

	public static void removeEntiteFrom(List<Entite> entitesList, Entite entite) {
		entitesList.remove(entite);
	}


	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne l'ensemble des entites de la scene
	 * 
	 * @return entites
	 */
	public static List<Entite> getEntites() {
		return entites;
	}

	public static List<Entite> getSuns() {
		return suns;
	}

	/**
	 * Retourne true si le jeu est gagne
	 * 
	 * @return gameWon
	 */
	public static boolean gameWon() {
		return gameWon;
	}

	/**
	 * Retourne true si le jeu est perdu
	 * 
	 * @return gameLost
	 */
	public static boolean gameLost() {
		return gameLost;
	}

	/**
	 * Retourne le porte-feuille du joueur
	 * 
	 * @return bank
	 */
	public static SunWallet getBank() {
		return bank;
	}

	public static char getPurchase() {
		return purchase;
	}

	public static boolean AnyZombie() {
		for (Entite entite : entites)
			if(entite instanceof Zombie)
				return true;
		return false;
	}

}
