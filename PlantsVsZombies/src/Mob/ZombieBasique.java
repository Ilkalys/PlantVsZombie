package Mob;

import Resources.GameWorld;
import Resources.StdDraw;

public class ZombieBasique extends Zombie{

	public ZombieBasique(double x, double y) {
		super(x,y,30,0.25);
		this.setLife(200);
	}
		
	// dessine l'entite, aux bonnes coordonnees
	public  void dessine() {
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}
		
	
}
