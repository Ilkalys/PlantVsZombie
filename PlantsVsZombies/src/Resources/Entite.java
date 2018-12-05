package Resources;

/**
 * @author TERMIER Alexandre
 */
public abstract class Entite {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------	

	// La position de l'entite
	protected Position position;
	
	
	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur
	 * 
	 * @param x coordonne X de l'entité
	 * @param y coordonne Y de l'entité
	 */
	public Entite(double x, double y) {
		position = new Position(x, y);
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------

	/**
	 * Met a jour l'entite : deplacement, effectuer une action
	 */
	public abstract void step();

	/**
	 * Dessine le mob, aux bonnes coordonnees
	 */
	public abstract void dessine();
	

	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Retourne la coordonne X de l'entité
	 * 
	 * @return position.positionX
	 */
	public double getX() {
		return position.getX();
	}

	/**
	 * Retourne la coordonne Y de l'entité
	 * 
	 * @return position.positionY
	 */
	public double getY() {
		return this.position.getY();
	}
	

	//------------------------------------------------------------------------------
	/*
	**      SETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Modifie la position de l'entite
	 * 
	 * @param p nouvelle position
	 */
	public void setPosition(Position p){
		this.position = p;
	}
	
}
