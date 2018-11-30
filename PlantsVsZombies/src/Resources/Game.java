package Resources;

import Screens.GameScreen;
import Screens.GameWorld;
import Screens.MenuGameOver;
import Screens.MenuLevelComplete;
import Screens.MenuStart;

public class Game {

	private static GameScreen world = new MenuStart();
	private static boolean stopGame = false;

	public static void launch() {

		boolean mousePressed = false;

		// Creation de la scene

		// Reglage de la taille de la fenetre de jeu, en pixels (nb: un ecran fullHD = 1980x1050 pixels)
		StdDraw.setCanvasSize(1000, 1000);

		// Permet le double buffering, pour permettre l'animation
		StdDraw.enableDoubleBuffering();


		// La boucle principale de notre jeu
		while (!Game.stopGame) {
			// Efface la fenetre graphique
			StdDraw.clear();

			// Capture et traite les eventuelles interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				world.processUserInput(key);
			}

			if (StdDraw.isMousePressed() && !mousePressed) {
				mousePressed = true;
				world.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}
			if (!StdDraw.isMousePressed() && mousePressed) mousePressed = false;


			world.step();

			// Dessine la carte
			world.dessine();

			if (world instanceof GameWorld) {
				if(((GameWorld)world).LevelComplete())
					world = new MenuLevelComplete();
				else if(((GameWorld)world).gameLost())
					world = new MenuLevelComplete();
			}
			// Montre la fenetre graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);

		}
		System.exit(0);
	}

	public static boolean getStopGame() {
		return stopGame;
	}

	public static void setStopGame(boolean stopGame) {
		Game.stopGame = stopGame;
	}

	public static GameScreen getWorld() {
		return world;
	}

	public static void setWorld(GameScreen world) {
		Game.world = world;
	}

}
