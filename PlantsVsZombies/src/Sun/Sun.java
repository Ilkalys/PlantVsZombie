package Sun;

import java.io.File;
import java.util.List;

import Resources.Entite;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Sun extends Entite {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	private static final int VALUE = 25;
	private Timer lifeTime;
	private File SpriteFilepath;

	public Sun(double x, double y) {
		super(x, y);
		this.lifeTime = new Timer(20000);
		SpriteFilepath = new File("sprites/mob/sun.png");
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
		if(this.lifeTime.hasFinished()) {
			GameWorld.removeEntiteFrom(GameWorld.getSuns(),this);
			GameWorld.getBank().add(25);
		}
	}	

	// dessine l'entite, aux bonnes coordonnees
	public  void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SpriteFilepath.getAbsolutePath(), 0.1, 0.1);
	}

	
	public static int getValue() {
		return VALUE;
	}
	
}
