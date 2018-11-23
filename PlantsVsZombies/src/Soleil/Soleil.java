package Soleil;

import java.util.List;

import Resources.Entite;
import Resources.StdDraw;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Soleil extends Entite {

	public static final int VALUE = 25;
	
	public Soleil(double x, double y) {
		super(x, y);
	}

	public static Soleil somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Soleil
			&& entites.get(i).getX() <= x+0.02
			&& entites.get(i).getX() >= x-0.02
			&& entites.get(i).getY() <= y+0.02
			&& entites.get(i).getY() >= y-0.02)
				return (Soleil)entites.get(i);
		return null;
	}
	
	public void step() {
		
	}	
	
	// dessine l'entite, aux bonnes coordonnees
	public  void dessine() {
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.filledSquare(this.position.getX(), this.position.getY(), 0.02);
	}

}
