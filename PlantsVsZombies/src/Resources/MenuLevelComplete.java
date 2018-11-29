package Resources;

import java.awt.Font;

public class MenuLevelComplete extends GameScreen {

	public MenuLevelComplete() {

	}

	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x <= 0.68 && x >= 0.52 && y >= 0.57 && y <= 0.63) {
			Game.setWorld(new MenuGameOver());
		}
		else if(x <= 0.48 && x >= 0.32 && y >= 0.57 && y <= 0.63) {
			Game.setWorld(new MenuStart());
		}
		 
	}

	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.9, "Congratulation");
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.8, "Level Complete!");
		StdDraw.picture(0.6, 0.6,(StdDraw.mouseX() <= 0.68 && StdDraw.mouseX() >= 0.52 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?"/Pictures/start!_p.png" : "/Pictures/start!.png", 0.16, 0.06);
		StdDraw.picture(0.4, 0.6,(StdDraw.mouseX() <= 0.48 && StdDraw.mouseX() >= 0.32 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?"/Pictures/quitter_p.png" : "/Pictures/quitter.png", 0.16, 0.06);


	}

}
