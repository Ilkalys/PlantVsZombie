package Soleil;

import java.util.List;


import Resources.Entite;
import Resources.Main;
import Resources.StdDraw;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class Soleil extends Entite {

	private static final int VALUE = 25;
	private double statusAnim;

	public Soleil(double x, double y) {
		super(x, y);
		statusAnim = 0;
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
		if(statusAnim >= 180)
			statusAnim =0;
		else statusAnim +=5;
	}	

	// dessine l'entite, aux bonnes coordonnees
	public  void dessine() {
		StdDraw.picture(this.getX(), this.getY(), "/Soleil/sun.png", 0.04, 0.04, statusAnim);
	}

	
	public static int getValue() {
		return VALUE;
	}
	
}
