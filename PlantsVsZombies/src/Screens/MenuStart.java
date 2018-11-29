package Screens;

import Resources.Game;
import Resources.StdDraw;
import Resources.Timer;

public class MenuStart extends GameScreen {

	private double ecart;
	private boolean start;
	private Timer fondu;

	public MenuStart() {
		ecart = 0;
	}

	@Override
	public void processUserInput(char key) {		
	}

	public void processMouseClick(double x, double y) {
		if(x <= 0.589 && x >= 0.429 && y >= 0.62 && y <= 0.68) {
			start = true;
		}
	}

	public void step() {	
		if (!start) {
			ecart += 0.001;
			if (ecart >= 1.2)
				ecart = -0.2;
		}
		else {
			if(fondu == null)
				fondu = new Timer(2000);
			else if(fondu.hasFinished())
				Game.setWorld(new GameWorld());
		}
			
			
	}

	@Override
	public void dessine() {

		StdDraw.setFont();
		StdDraw.picture(0.5, 0.5,(start)?"/Pictures/Accueil2.png":"/Pictures/Accueil1.png", 1, 1);
		StdDraw.picture(0+ecart, 0.8,"/Pictures/CloudDown.png", 0.51, 0.51);
		StdDraw.picture(1-ecart, 0.97,"/Pictures/CouldUp.png", 0.51, 0.51);
		StdDraw.picture(0.509, 0.57,"/Pictures/Affichage.png", 0.51, 0.51);
		StdDraw.text(0.509, 0.65,"Nouvelle Partie");
		if(start)
			StdDraw.picture(0.5, 0.5, "/Pictures/Fondu.png",5,5);


	}

}
