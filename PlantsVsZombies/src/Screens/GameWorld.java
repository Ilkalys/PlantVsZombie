package Screens;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import Mob.BasicZombie;
import Mob.DeadMob;
import Mob.Dynamite;
import Mob.Explosion;
import Mob.ExplosiveZombie;
import Mob.Mob;
import Mob.Nuts;
import Mob.Peas;
import Mob.PeasShooter;
import Mob.ShieldedZombie;
import Mob.Sun;
import Mob.Sunflower;
import Mob.Zombie;
import Resources.Entite;
import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.SunSpawner;
import Resources.Timer;
import Resources.ZombieSpawner;

/**
 * @author TERMIER Alexandre, GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class GameWorld extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Chemin vers le dossier de sprite
	private static final String SPRITEFILEPATH = Game.getSpritefilepath().toString();

	// L'ensemble des entites, pour gerer (notamment) l'affichage
	private static List<Entite> entites;
	// L'ensemble des soleils qui apparaitront
	private static List<Entite> suns;

	// Gestionnaire des apparitions de zombies
	private static ZombieSpawner ZombieSpawn;
	// Quantit� de zombies restant a apparaitre
	private static int zombieQuantity;

	// Gestionnaire des apparitions de soleil
	private static SunSpawner SunSpawn;
	// Porte-feuille du joueur
	private static int wallet;
	// Plante selectionne
	private static String selectedPlant;

	// Affichage ou non des Informations
	private static boolean dispInfos;

	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Constructeur, il faut initialiser notre monde virtuel
	 */
	public GameWorld(int difficulte) {
		// Musique
		SoundPlayer.PlayBGM("Western.wav");

		// Remises � z�ro des timers
		Sunflower.setCooldown(new Timer(0));
		PeasShooter.setCooldown(new Timer(0));
		Nuts.setCooldown(new Timer(0));
		Dynamite.setCooldown(new Timer(0));

		// On cree les collections
		entites = new LinkedList<Entite>();
		suns = new LinkedList<Entite>();

		// Gestionnaire des appartions
		ZombieSpawn = new ZombieSpawner(difficulte);
		SunSpawn = new SunSpawner();

		selectedPlant = null;
		wallet = 50;
		zombieQuantity = ZombieSpawn.getLevel().size();
		dispInfos = false;
	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES PUBLIQUES
	 */
	//------------------------------------------------------------------------------

	/**  
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		//Verifie si la touche correspond � une plante et si c'est le cas appelle la fonction correspondante
		if(key == Sunflower.getKey())
			selectSunflower();
		else if(key == PeasShooter.getKey())
			selectPeasShooter();
		else if(key == Nuts.getKey())
			selectNuts();
		else if(key == Dynamite.getKey())
			selectDynamite();
		//Affiche ou d�saffiche les Informations si la touche correspondante est appuy�e
		else if(key == 'i')
			dispInfos = ! dispInfos;
	}


	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a ete clique)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		// G�re les Interactions Souris si le Menu Information est pr�sent
		if(dispInfos) {
			//Red�marre le niveau
			if(x <= 0.84 && x >= 0.559 && y >= 0.565 && y <= 0.685)
				Game.setWorld(new GameWorld(ZombieSpawner.getCurrentDifficulty()));
			//Retour au Menu Principal
			else if(x <= 0.85 && x >= 0.55 && y >= 0.42 && y <= 0.48)
				Game.setWorld(new MenuStart());
		}
		// G�re les Interactions Souris dans les autres cas
		else {
			// Recuperation d'un soleil
			Sun sunHere = Sun.somethingHere(suns, x, y);
			if(sunHere != null) {
				wallet += Sun.getValue();
				suns.remove(sunHere);
			} else {
				// Explosion d'une dynamite
				Dynamite dynamiteHere = Dynamite.somethingHere(entites, x, y);
				if(dynamiteHere != null)
					dynamiteHere.explose();
				else {
					// Selection d'un tournesol � la souris
					if(x >= 0.05 && x <= 0.15 && y >= 0.05 && y <= 0.15) {
						selectSunflower();
					}
					// Selection d'un tire-pois � la souris
					if(x >= 0.25 && x <= 0.35 && y >= 0.05 && y <= 0.15) {
						selectPeasShooter();
					}
					// Selection d'une noix � la souris
					if(x >= 0.45 && x <= 0.55 && y >= 0.05 && y <= 0.15) {
						selectNuts();
					}
					// Selection d'une dynamite � la souris
					if(x >= 0.65 && x <= 0.75 && y >= 0.05 && y <= 0.15) {
						selectDynamite();
					}
					// Plantation
					if(x < 0.95 && x > 0.05 && y < 0.75 && y > 0.25) {
						double rx, ry;
						rx = (x % 0.1 <= 0.05)? x - (x % 0.1) : x - (x % 0.1) + 0.1;
						ry = (y % 0.1 <= 0.05)? y - (y % 0.1) : y - (y % 0.1) + 0.1;
						// Verification que la case souhait� soit vide
						if(Mob.somethingHere(entites, rx, ry) == null) {
							if(selectedPlant == Sunflower.class.getName()) {
								wallet -= Sunflower.getPrice();
								entites.add(new Sunflower(rx, ry));
								selectedPlant = null;
							}
							if(selectedPlant == PeasShooter.class.getName()) {
								wallet -= PeasShooter.getPrice();
								entites.add(new PeasShooter(rx, ry));
								selectedPlant = null;
							}
							if(selectedPlant == Nuts.class.getName()) {
								wallet -= Nuts.getPrice();
								entites.add(new Nuts(rx, ry));
								selectedPlant = null;
							}
							if(selectedPlant == Dynamite.class.getName()) {
								wallet -= Dynamite.getPrice();
								entites.add(new Dynamite(rx, ry));
								selectedPlant = null;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Fait bouger/agir toutes les entites
	 */
	public void step() {
		//Appelle le Step de toutes les entit�s de entites
		for (int i = 0; i < entites.size(); i++) {
			if(entites.get(i) != null) {
				entites.get(i).step();
			}
		}
		//Appelle le Step de toutes les entit�s de suns
		for (int i = 0; i < suns.size(); i++) {
			if(suns.get(i) != null) {
				suns.get(i).step();
			}
		}
		//Appelle les steps de ZombieSpawn et SunSpawn
		ZombieSpawn.step();
		SunSpawn.step();
	}

	/**
	 * Dessine les entites du jeu
	 */
	@SuppressWarnings("static-access")
	public void dessine() {
		// Affichage fond
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(0.5, 0.5, SPRITEFILEPATH +"/bg/FondLevel.png", 1, 1);

		StdDraw.setFont(new Font("sans serif",10,20 * Game.getCanvasXY() /1000));

		// Affichage du nombre de zombies
		StdDraw.picture(0.9, 0.95, SPRITEFILEPATH + "/bg/PanneauScore.png", 0.2, 0.2);
		StdDraw.text(0.9, 0.932, "Niveau : " + ZombieSpawn.getCurrentDifficulty());
		StdDraw.text(0.9, 0.908, "Restants : " + zombieQuantity);

		// Affichage touche Infos
		StdDraw.text(0.1,0.015, "Infos = i");

		StdDraw.setFont(new Font("sans serif",10,40 * Game.getCanvasXY() /1000));

		// Affichage du porte-monnaie
		StdDraw.picture(0.9, 0.05, SPRITEFILEPATH + "/bg/PanneauMonnaie.png", 0.15, 0.15);
		StdDraw.text(0.93, 0.094, "" + wallet);
		StdDraw.picture(0.84, 0.1, Sun.getSpritefilepath(), 0.04, 0.04);


		// Affichage des icones
		if(selectedPlant == Sunflower.class.getName())
			StdDraw.picture(0.1, 0.1, SPRITEFILEPATH + "/bg/Selection.png", 0.1, 0.1);;
			StdDraw.picture(0.1, 0.1, Sunflower.getIcone(), 0.12, 0.12);
			double heightLoadSunFlo = ((Sunflower.getCooldown() == null)? 0 : Sunflower.getCooldown().getActualTime() / (Sunflower.getCooldownTime() / 100));
			StdDraw.picture(0.1, 0.1, SPRITEFILEPATH + "/bg/Fondu.png", heightLoadSunFlo, heightLoadSunFlo);

			if(selectedPlant == PeasShooter.class.getName())
				StdDraw.picture(0.3, 0.1, SPRITEFILEPATH + "/bg/Selection.png", 0.1, 0.1);
			StdDraw.picture(0.3, 0.1, PeasShooter.getIcone(), 0.12, 0.12);
			double heightLoadPeasSh = ((PeasShooter.getCooldown() == null)? 0 : PeasShooter.getCooldown().getActualTime() / (PeasShooter.getCooldownTime() / 100));
			StdDraw.picture(0.3, 0.1, SPRITEFILEPATH + "/bg/Fondu.png", heightLoadPeasSh, heightLoadPeasSh);

			if(selectedPlant == Nuts.class.getName())
				StdDraw.picture(0.5, 0.1, SPRITEFILEPATH + "/bg/Selection.png",0.1,0.1);
			StdDraw.picture(0.5, 0.1, Nuts.getIcone(), 0.12, 0.12);
			double heightLoadNuts = ((Nuts.getCooldown() == null)? 0 : Nuts.getCooldown().getActualTime() / (Nuts.getCooldownTime() / 100));
			StdDraw.picture(0.5, 0.1, SPRITEFILEPATH + "/bg/Fondu.png", heightLoadNuts, heightLoadNuts);

			if(selectedPlant == Dynamite.class.getName())
				StdDraw.picture(0.7, 0.1, SPRITEFILEPATH + "/bg/Selection.png",0.1,0.1);
			StdDraw.picture(0.7, 0.1, Dynamite.getIcone(), 0.12, 0.12);
			double heightLoadDynamite = ((Dynamite.getCooldown() == null)? 0 : Dynamite.getCooldown().getActualTime() / (Dynamite.getCooldownTime() / 100));
			StdDraw.picture(0.7, 0.1, SPRITEFILEPATH + "/bg/Fondu.png", heightLoadDynamite, heightLoadDynamite);


			// Affichage des prix
			StdDraw.setPenColor((wallet >= Sunflower.getPrice() && Sunflower.getCooldown().hasFinished())? StdDraw.BLACK : StdDraw.RED);
			StdDraw.square(0.1, 0.1, 0.05);	
			StdDraw.text(0.1, 0.17, Sunflower.getPrice() + "");
			StdDraw.setPenColor((wallet >= PeasShooter.getPrice() && PeasShooter.getCooldown().hasFinished())? StdDraw.BLACK : StdDraw.RED);
			StdDraw.square(0.3, 0.1, 0.05);
			StdDraw.text(0.3, 0.17, PeasShooter.getPrice() + "");
			StdDraw.setPenColor((wallet >= Nuts.getPrice() && Nuts.getCooldown().hasFinished())? StdDraw.BLACK : StdDraw.RED);
			StdDraw.square(0.5, 0.1, 0.05);
			StdDraw.text(0.5, 0.17, Nuts.getPrice() + "");
			StdDraw.setPenColor((wallet >= Dynamite.getPrice() && Dynamite.getCooldown().hasFinished())? StdDraw.BLACK : StdDraw.RED);
			StdDraw.square(0.7, 0.1, 0.05);
			StdDraw.text(0.7, 0.17, Dynamite.getPrice() + "");


			// Cadriage
			if(selectedPlant != null) {
				StdDraw.picture(0.50, 0.50, SPRITEFILEPATH + "/bg/Quadrillage.png",0.9,0.5);
			}

			// Affiche les entites de fa�on � avoir les SPRITEFILEPATH les plus haut le plus en profondeur
			for (float i = 1; i >= 0; i -= 0.1) {
				for (int j = 0; j < entites.size(); j++) {
					if(entites.get(j).getY() < i && entites.get(j).getY() >= (i - 0.1)) {
						entites.get(j).dessine();
					}
				}
			}
			// Affiche les soleils au dessus des autres SPRITEFILEPATH
			for (Entite Sun : suns)
				Sun.dessine();

			//Affiche les Infos si demand�
			if(dispInfos)
				dispInfos();
	}

	/**
	 * Fait apparaitre un nouveau soleil
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
	 * @param shielded le zombie est blinde ou non
	 */
	public static void addZombie(double x, double y, int type) {
		if(zombieQuantity != 0) {
			switch (type) {
			case 0:
				entites.add(new BasicZombie(x, y));
				break;
			case 1:
				entites.add(new ShieldedZombie(x, y));
				break;
			case 2:
				entites.add(new ExplosiveZombie(x, y));
				break;
			default:
				break;
			}
			zombieQuantity--;
		}
	}

	/**
	 * Fait apparaitre un nouveau pois
	 * @param x la position x du pois
	 * @param y la position y du pois
	 */
	public static void addPeas(double x, double y) {
		entites.add(new Peas(x,y));
	}

	/**
	 * Fait apparaitre un mob mort
	 * @param x la position x du mob
	 * @param y la position y du mob
	 * @param classe le type de mob
	 */
	public static void addDeadMob(double x, double y, String classe) {
		entites.add(new DeadMob(x,y,classe));
	}

	/**
	 * Fait apparaitre une explosion
	 * @param x la position x de l'explosion
	 * @param y la position y de l'explosion
	 */
	public static void addExplosion(double x, double y) {
		entites.add(new Explosion(x,y));
	}

	/**
	 * retire une entit�e d'une des listes d'entit�es
	 * @param entitesList la liste d'entit�es selectionn�e
	 * @param entite l'entit�e que l'on veut supprimer
	 */
	public static void removeEntiteFrom(List<Entite> entitesList, Entite entite) {
		if(entite instanceof Sun)
			wallet += Sun.getValue();
		entitesList.remove(entite);
	}

	/**
	 * Verifie qu'il reste des zombies vivants sur la scene
	 * 
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

	/**
	 * Verifie si le niveau est reussi
	 * 
	 * @return true si le niveau est gagne
	 */
	public boolean LevelComplete(){
		if(!AnyZombie() && zombieQuantity == 0) {
			return true;
		}
		return false;

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

	//------------------------------------------------------------------------------
	/*
	 **      METHODES PRIVEES
	 */
	//------------------------------------------------------------------------------

	/**
	 * Gere L'affichage du Menu d'Informations
	 */
	private void dispInfos() {
		StdDraw.setPenColor(StdDraw.WHITE);

		//Affichage du Texte
		StdDraw.text(0.3, 0.8, "Infos:");
		StdDraw.text(0.3, 0.7, "Sherif : Touche " + Sunflower.getKey());
		StdDraw.text(0.3, 0.6, "CowBoy : Touche " + PeasShooter.getKey());
		StdDraw.text(0.3, 0.5, "Tonneau : Touche " + Nuts.getKey());
		StdDraw.text(0.3, 0.4, "Dynamite : Touche " + Dynamite.getKey());

		// Affichage du bouton "Recommence le niveau"
		StdDraw.picture(0.7, 0.625, SPRITEFILEPATH + "/bg/Fondu.png",0.28,0.12);
		if(StdDraw.mouseX() <= 0.84 && StdDraw.mouseX() >= 0.559 && StdDraw.mouseY() >= 0.565 && StdDraw.mouseY() <= 0.685)
			StdDraw.picture(0.7, 0.625, SPRITEFILEPATH + "/bg/Fondu.png",0.28,0.12);
		StdDraw.text(0.7, 0.65, "Recommencer");
		StdDraw.text(0.7, 0.60, "le Niveau");

		// Affichage du bouton "Retour au Menu"
		StdDraw.picture(0.7, 0.45, SPRITEFILEPATH + "/bg/Fondu.png",0.3,0.06);
		if(StdDraw.mouseX() <= 0.85 && StdDraw.mouseX() >= 0.55 && StdDraw.mouseY() >= 0.42 && StdDraw.mouseY() <= 0.48)
			StdDraw.picture(0.7, 0.45, SPRITEFILEPATH + "/bg/Fondu.png",0.3,0.06);
		StdDraw.text(0.7, 0.45, "Retour au Menu");

		StdDraw.setPenColor(StdDraw.BLACK);
	}

	/**
	 * Selectionne ou deselectionne le tournesol
	 */
	private void selectSunflower() {
		if(selectedPlant != Sunflower.class.getName()) {
			if(Sunflower.getCooldown() == null || Sunflower.getCooldown().hasFinished())
				if(wallet >= Sunflower.getPrice())
					selectedPlant = Sunflower.class.getName();
		}
		else
			selectedPlant = null;
	}

	/**
	 * Selectionne ou deselectionne le tire-pois
	 */
	private void selectPeasShooter() {
		if(selectedPlant != PeasShooter.class.getName()) {
			if(PeasShooter.getCooldown() == null || PeasShooter.getCooldown().hasFinished())
				if(wallet >= PeasShooter.getPrice())
					selectedPlant = PeasShooter.class.getName();
		}
		else
			selectedPlant = null;
	}

	/**
	 * Selectionne ou deselectionne la noix
	 */
	private void selectNuts() {
		if(selectedPlant != Nuts.class.getName()) {
			if(Nuts.getCooldown() == null || Nuts.getCooldown().hasFinished())
				if(wallet >= Nuts.getPrice())
					selectedPlant = Nuts.class.getName();
		}
		else
			selectedPlant = null;
	}

	/**
	 * Selectionne ou deselectionne la dynamite
	 */
	private void selectDynamite() {
		if(selectedPlant != Dynamite.class.getName()) {
			if(Dynamite.getCooldown() == null || Dynamite.getCooldown().hasFinished())
				if(wallet >= Dynamite.getPrice())
					selectedPlant = Dynamite.class.getName();
		}
		else
			selectedPlant = null;
	}



	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne le chemin vers l'ensemble des SPRITEFILEPATH
	 * 
	 * @return SPRITEFILEPATH
	 */
	public String getSPRITEFILEPATH() {
		return SPRITEFILEPATH;
	}

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
	 * Retourne le gestionnaire des apparitions de zombies
	 * 
	 * @return ZombieSpawn
	 */
	public static ZombieSpawner getZombieSpawn() {
		return ZombieSpawn;
	}

	/**
	 * Retourne le gestionnaire des apparitions de soleils
	 * 
	 * @return SunSpawn
	 */
	public static SunSpawner getSunSpawn() {
		return SunSpawn;
	}

	/**
	 * Retourne la plante selectionne
	 * 
	 * @return selectedPlant
	 */
	public static String getSelectedPlant() {
		return selectedPlant;
	}

	/**
	 * Retourne le porte-feuille du joueur
	 * 
	 * @return wallet
	 */
	public static int getWallet() {
		return wallet;
	}

	/**
	 * Retourne la quantit� de zombies restant a apparaitre
	 * 
	 * @return zombieQuantity
	 */
	public static int getZombieQuantity() {
		return zombieQuantity;
	}


	//------------------------------------------------------------------------------
	/*
	 **      SETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Modifie l'ensemble des plantes et zombies de la scene
	 * 
	 * @param list la liste des plantes et zombies
	 */
	public static void setEntites(List<Entite> list) {
		entites = list;
	}

	/**
	 * Modifie l'ensemble des soleils de la scene
	 * 
	 * @param list la liste des soleils 
	 */
	public static void setSuns(List<Entite> list) {
		suns = list;
	}

	/**
	 * Modifie le gestionnaire des apparitions de zombies
	 * 
	 * @param ZombieSpawner nouveau spawner
	 */
	public static void setZombieSpawn(ZombieSpawner ZombieSpawner) {
		ZombieSpawn = ZombieSpawner;
	}

	/**
	 * Modifie le gestionnaire des apparitions de soleils
	 * 
	 * @return SunSpawner nouveau spawner
	 */
	public static void setSunSpawn(SunSpawner SunSpawner) {
		SunSpawn = SunSpawner;
	}

	/**
	 * Modifie la plante selectionne
	 * 
	 * @return string nouvelle plante
	 */
	public static void setSelectedPlant(String string) {
		selectedPlant = string;
	}

	/**
	 * Modifie le porte-feuille du joueur
	 * 
	 * @return value nouvelle valeur
	 */
	public static void setWallet(int value) {
		wallet = value;
	}

	/**
	 * Modifie la quantit� de zombies restant a apparaitre
	 * 
	 * @return value nouvelle valeur
	 */
	public static void setZombieQuantity(int value) {
		zombieQuantity = value;
	}

}
