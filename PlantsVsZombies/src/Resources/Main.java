package Resources;

public class Main {

	public static void main(String[] args) {

		// Creation de la scene
		GameWorld world = new GameWorld();

		// Reglage de la taille de la fenetre de jeu, en pixels (nb: un ecran fullHD = 1980x1050 pixels)
		StdDraw.setCanvasSize(1000, 1000);

		// Permet le double buffering, pour permettre l'animation
		StdDraw.enableDoubleBuffering();


		// La boucle principale de notre jeu
		while (!GameWorld.gameLost() && !GameWorld.gameWon()) {
			// Efface la fenetre graphique
			StdDraw.clear();

			// Capture et traite les eventuelles interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				world.processUserInput(key);
			}

			if (StdDraw.isMousePressed()) {
				world.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}


			world.step();

			// Dessine la carte
			world.dessine();

			// Montre la fenetre graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);
		}

		if (GameWorld.gameWon()) System.out.println("Game won !");
		if (GameWorld.gameLost()) System.out.println("Game lost...");
	}

}
