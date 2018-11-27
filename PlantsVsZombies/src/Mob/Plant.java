package Mob;

import java.util.List;

import Resources.*;

/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public abstract class Plant extends Mob {

	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur
	 * 
	 * @param x coordonne X de la plante
	 * @param y coordonne Y de la plante
	 */
	public Plant(double x, double y) {
		super(x,y);
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Verifie si une plante se trouve a un endroit precis parmi une liste d'entites donnee
	 * 
	 * @param entites liste des entites a verifier
	 * @param x coordonne X a verifier
	 * @param y coordonne Y a verifier
	 * @return la plante � l'endroit demand�
	 */
	public static Plant somethingHere(List<Entite> entites, double x, double y) {
		for(int i =0; i<entites.size(); i++)
			if(entites.get(i) instanceof Plant
			&& entites.get(i).getX() <= x+0.09
			&& entites.get(i).getX() >= x-0.09
			&& entites.get(i).getY() <= y+0.09
			&& entites.get(i).getY() >= y-0.09)
				return (Plant)entites.get(i);
		return null;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	
	
	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------


}
