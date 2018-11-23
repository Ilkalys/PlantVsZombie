package Resources;
import java.util.LinkedList;
import java.util.List;

import Mob.*;
import Soleil.*;

/**
 * @author TERMIER Alexandre, GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class GameWorld {

	// L'ensemble des entites, pour gerer (notamment) l'affichage
	private static List<Entite> entites;
	//Pour savoir si la partie est gagnee ou pas
	private static boolean gameWon;
	// Idem pour savoir si le jeu est perdu (si le jeu n'est ni gagne ni perdu, il est en cours)
	private static boolean gameLost;
	
	// Porte-feuille du joueur
	private static PorteSoleil bank;
	// Dernier achat effectue ('s'=tournesol, 'p'=tire-pois, 'n'=noix, ' '=rien)
	private static char purchase;

	
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
		bank = new PorteSoleil(0, 0, 50);
		purchase = ' ';

		// On cree les collections
		entites = new LinkedList<Entite>();
		entites.add(bank);
		
		// On rajoute les entites
		entites.add(new ZombieBasique(1, 0.5));
		entites.add(new Soleil(0.5, 0.5));
		entites.add(new Soleil(0.1, 0.5));
		entites.add(new Soleil(0.5, 0.1));
		entites.add(new Soleil(0.2, 0.2));
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
			System.out.println("Le joueur veut acheter un Tournesol...");
			if(Sunflower.cooldown == null || Sunflower.cooldown.hasFinished())
				if(bank.enoughSun(50)) {
					purchase = 's';
					System.out.println("Tournesol selectionne !");
				} else System.out.println("Mais il ne possede pas assez de soleil.");
			else
				System.out.println("Mais le temps de recharge ne c'est pas effectue.");
			break;
		case 'p':
			System.out.println("Le joueur veut acheter un Tire-Pois...");
			if(PeasShooter.cooldown == null || PeasShooter.cooldown.hasFinished())
				if(bank.enoughSun(100)) {
					purchase = 'p';
					System.out.println("Tire-Pois selectionne !");
				} else System.out.println("Mais il ne possede pas assez de soleil.");
			else System.out.println("Mais le temps de recharge ne c'est pas effectue.");
			break;
		case 'n':
			System.out.println("Le joueur veut acheter une Noix...");
			if(Nuts.cooldown == null || Nuts.cooldown.hasFinished())
				if(bank.enoughSun(50)) {
					purchase = 'n';
					System.out.println("Noix selectionne !");
				} else System.out.println("Mais il ne possede pas assez de soleil.");
			else System.out.println("Mais le temps de recharge ne c'est pas effectue.");
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
		// Recuperation d'un soleil
		Soleil clique = Soleil.somethingHere(entites, x, y);
		if(clique != null) {
			bank.add(Soleil.VALUE);
			entites.remove(clique);
		}
		// Plantation
		if(x < 0.95 && x > 0.05 && y < 0.75 && y > 0.25) {
			double rx, ry;
			rx = (x % 0.1 <= 0.05)? x - (x % 0.1) : x - (x % 0.1) + 0.1;
			ry = (y % 0.1 <= 0.05)? y - (y % 0.1) : y - (y % 0.1) + 0.1;
			if(!Mob.somethingHere(entites, rx, ry)) {
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
	
	/**
	 * Fait bouger/agir toutes les entites
	 */
	public void step() {
		for (Entite entite : entites)
			entite.step();
	}

	/**
	 * Dessine les entites du jeu
	 */
	public void dessine() {
		// Ici vous pouvez afficher du decors
		// TODO

		// Affiche les entites
		for (Entite entite : entites)
			entite.dessine();
	}
	
	/**
	 * Fait apparitre un nouveau soleil
	 */
	public void addSun() {
		entites.add(new Soleil(0.3,0.3));
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
	public static PorteSoleil getBank() {
		return bank;
	}
	
	public static char getPurchase() {
		return purchase;
	}

}
