package Resources;

import java.io.File;

import Screens.GameScreen;
import Screens.GameWorld;
import Screens.MenuGameOver;
import Screens.MenuLevelComplete;
import Screens.MenuStart;
import Screens.MenuVictory;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Game {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	

	// Chemin vers les dossiers de sprites
	private static final File SPRITEFILEPATH  = new File(MenuStart.class.getResource("/sprites").toString());
	// Scene chargee a l'ecran
	private static GameScreen World = new MenuStart();
	// Determine si le jeu est fini ou non
	private static boolean stopGame = false;
	// Determine si la souris est enfoncee ou non
	private static boolean mousePressed = false;
	// Largeur de la fenetre
	private static int canvasXY = 1000;



	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------
	

	/**
	 * Lance l'application
	 */
	public static void launch() {
		// Reglage de la taille de la fenetre de jeu, en pixels
		StdDraw.setCanvasSize(canvasXY , canvasXY);

		// Permet le double buffering, pour permettre l'animation
		StdDraw.enableDoubleBuffering();

		// La boucle principale de notre jeu
		while (!Game.stopGame) {
			// Efface la fenetre graphique
			StdDraw.clear();

			// Capture et traite les eventuelles interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				World.processUserInput(key);
			}

			// Capture et traite les eventuelles interactions souris du joueur
			if (StdDraw.isMousePressed() && !mousePressed) {
				mousePressed = true;
				World.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}
			if (!StdDraw.isMousePressed() && mousePressed) mousePressed = false;

			// Changement de niveau
			if (World instanceof GameWorld) {
				if(((GameWorld)World).LevelComplete())
					if(ZombieSpawner.getCurrentDifficulty() == 10) {
						World = new MenuVictory();
					}
					else
					World = new MenuLevelComplete();
				else if(((GameWorld)World).gameLost())
					World = new MenuGameOver();
			}
			
			World.step();
			World.dessine();

			// Montre la fenetre graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);

		}
		System.exit(0);
	}

	
	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Renvoi canvasXY, les dimensions de la fenetre
	 * 
	 * @return canvasXY
	 */
	public static int getCanvasXY() {
		return canvasXY;
	}


	/**
	 * Retourne la scene actuelle
	 * 
	 * @return World
	 */
	public static GameScreen getWorld() {
		return World;
	}

	/**
	 * Retourne le booleen determinant si le non est fini ou non
	 * 
	 * @return stopGame
	 */
	public static boolean getStopGame() {
		return stopGame;
	}

	/**
	 * Retourne le booleen determinant si la souris est enfoncee ou non
	 * 
	 * @return mousePressed
	 */
	public static boolean getMousePressed() {
		return mousePressed;
	}

	public static File getSpritefilepath() {
		return SPRITEFILEPATH;
	}


	//------------------------------------------------------------------------------
	/*
	 **      SETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Change la valeur de canvasXY
	 * 
	 * @param canvasXY la nouvelle valeur de canvasXY
	 */
	public static void setCanvasXY(int canvasXY) {
		Game.canvasXY = canvasXY;
	}

	/**
	 * Modifie la scene actuelle
	 * 
	 * @param GameScreen nouvelle scene
	 */
	public static void setWorld(GameScreen GameScreen) {
		Game.World = GameScreen;
	}

	/**
	 * Modifie le booleen determinant si le non est fini ou non
	 * 
	 * @param stopGame nouvel etat
	 */
	public static void setStopGame(boolean stopGame) {
		Game.stopGame = stopGame;
	}
	
	/**
	 * Modifie le booleen determinant si la souris est enfoncee ou non
	 * 
	 * @param stopGame nouvel etat
	 */
	public static void setMousePressed(boolean mousePressed) {
		Game.mousePressed = mousePressed;
	}


}
