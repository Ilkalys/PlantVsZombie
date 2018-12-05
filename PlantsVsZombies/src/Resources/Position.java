package Resources;

/**
 * @author TERMIER Alexandre
 */
public class Position {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	// Coordonne X
	private double positionX;
	// Coordonne Y
	private double positionY;

	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------

	/**
	 * Constructeur
	 * 
	 * @param x coordonne X
	 * @param y coordonne Y
	 */
	public Position(double x, double y){
		positionX=x;
		positionY=y;
	}

	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Determine si deux position sont egales
	 * 
	 * @param p position a comparer avec celle de l'objet
	 * @return true si les positions dont egales
	 */
	public boolean equals(Position p){
		return (this.positionX==p.positionX && this.positionY==p.positionY);
	}

	/**
	 * Regroupe les informations sur la position en un String
	 * 
	 * @return null
	 */
	public String toString(){
		return null;
	}
	

	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne la coordonne X
	 * 
	 * @return positionX
	 */
	public double getX(){
		return positionX;
	}

	/**
	 * Retourne la coordonne Y
	 * 
	 * @return positionY
	 */
	public double getY(){
		return positionY;
	}


	//------------------------------------------------------------------------------
	/*
	 **      SETTERS
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Modifie la coordonne X
	 * 
	 * @param x nouvelle coordonne X
	 */
	public void setX(double x) {
		this.positionX = x;
	}

	/**
	 * Modifie la coordonne Y
	 * 
	 * @param y nouvelle coordonne Y
	 */
	public void setY(double y) {
		this.positionY = y;
	}
	
}
