package Screens;

import Resources.Game;
import Resources.StdDraw;

public class MenuStart extends GameScreen {
	
	private double ecart;

	public MenuStart() {
		ecart = 0;
	}
	
	@Override
	public void processUserInput(char key) {
		// TODO Auto-generated method stub
		
	}

	public void processMouseClick(double x, double y) {
		if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68) {
			Game.setWorld(new MenuLevelComplete());
		}
	}

	public void step() {	
		ecart += 0.001;
		if (ecart >= 1.2)
			ecart = -0.2;
	}

	@Override
	public void dessine() {
		
		StdDraw.setFont();
		StdDraw.picture(0.5, 0.5,"/Pictures/Accueil1.png", 1, 1);
		StdDraw.picture(0+ecart, 0.8,"/Pictures/CloudDown.png", 0.51, 0.51);
		StdDraw.picture(1-ecart, 0.97,"/Pictures/CouldUp.png", 0.51, 0.51);
		StdDraw.picture(0.509, 0.57,"/Pictures/Affichage.png", 0.51, 0.51);
		StdDraw.text(0.509, 0.65,"Nouvelle Partie");

		
	}

}
