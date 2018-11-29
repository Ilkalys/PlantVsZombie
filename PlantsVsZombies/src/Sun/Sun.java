package Sun;

import java.util.List;

import Resources.Entite;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Sun extends Entite {

	private static final int VALUE = 25;
	private double animStatus;
	private Timer lifeTime;

	public Sun(double x, double y) {
		super(x, y);
		animStatus = 0;
		this.lifeTime = new Timer(20000);
	}

	public static Sun somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Sun
			&& entites.get(i).getX() <= x+0.02
			&& entites.get(i).getX() >= x-0.02
			&& entites.get(i).getY() <= y+0.02
			&& entites.get(i).getY() >= y-0.02)
				return (Sun)entites.get(i);
		return null;
	}
	
	public void step() {
		if(animStatus >= 180)
			animStatus =0;
		else animStatus +=5;
		if(this.lifeTime.hasFinished()) {
			GameWorld.removeEntiteFrom(GameWorld.getSuns(),this);
			GameWorld.getBank().add(25);
		}
	}	

	// dessine l'entite, aux bonnes coordonnees
	public  void dessine() {
		StdDraw.picture(this.getX(), this.getY(), "/Sun/sun.png", 0.04, 0.04, animStatus);
	}

	
	public static int getValue() {
		return VALUE;
	}
	
}
