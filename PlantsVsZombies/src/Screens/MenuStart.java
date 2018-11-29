package Screens;

import Resources.Game;
import Resources.StdDraw;

public class MenuStart extends GameScreen {

	public MenuStart() {
		
	}
	
	@Override
	public void processUserInput(char key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x <= 0.58 && x >= 0.42 && y >= 0.57 && y <= 0.63) {
			Game.setWorld(new MenuLevelComplete());
		}
		/*else if(x <= 0.58 && x >= 0.42 && y >= 0.37 && y <= 0.43) {
			
		}
		*/
	}

	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		
		StdDraw.setFont();
		StdDraw.picture(0.5, 0.6,(StdDraw.mouseX() <= 0.58 && StdDraw.mouseX() >= 0.42 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?"/Pictures/start!_p.png" : "/Pictures/start!.png", 0.16, 0.06);
		//StdDraw.picture(0.5, 0.4,(StdDraw.mouseX() <= 0.58 && StdDraw.mouseX() >= 0.42 && StdDraw.mouseY() >= 0.37 && StdDraw.mouseY() <= 0.43)?"/Pictures/quitter_p.png" : "/Pictures/quitter.png", 0.16, 0.06);

		
	}

}
