package Mob;

import Resources.StdDraw;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class ZombieBlinde extends Zombie{
	
	public ZombieBlinde(double x, double y) {
		super(x,y,30,0.25);
		this.setLife(560);
	}
	
	// met a jour l'entite : déplacement, effectuer une action
	public  void step() {
		this.position.setX(this.position.getX() - this.getSpeed());
		if (this.position.getX() < 0.0) this.position.setX(1.0); 

	}
		
	// dessine l'entite, aux bonnes coordonnees
	public  void dessine() {
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.05);
	}
		
	
}
