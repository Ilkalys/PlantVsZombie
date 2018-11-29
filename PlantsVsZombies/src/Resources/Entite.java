package Resources;


public abstract class Entite {
	
	// la position de l'entite
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
	

	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return this.position.getY();
	}
	
	
	public void setPosition(Position p){
		this.position = p;
	}
	
	public abstract void step();
	
	public abstract void dessine();
	

}
