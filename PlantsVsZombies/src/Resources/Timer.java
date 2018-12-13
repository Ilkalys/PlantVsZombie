package Resources;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author TERMIER Alexandre
 */
public class Timer {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	// Debut du timer
	private Instant debut;
	// Duree du timer en millisecondes
	private long duree;
	

	//------------------------------------------------------------------------------
	/*
	**      CONSTRUCTEUR
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur
	 * 
	 * @param duree duree du timer en millisecondes
	 */
	public Timer(long duree) {
		this.duree = duree;
		restart();
	}

	
	//------------------------------------------------------------------------------
	/*
	**      METHODES
	*/
	//------------------------------------------------------------------------------
	
	/**
	 * Redemmare le timer
	 */
	public void restart() {
		debut = Instant.now();
	}
	
	/**
	 * Indique si le temps est ecoule ou non
	 * 
	 * @return boolean
	 */
	public boolean hasFinished() {
		return (debut.compareTo((Instant.now().minus(duree, ChronoUnit.MILLIS))) < 0);
	}

	
	//------------------------------------------------------------------------------
	/*
	**      GETTERS
	*/
	//------------------------------------------------------------------------------

	/**
	 * Reourne la duree du timer en millisecondes
	 * 
	 * @return duree
	 */
	public long getDuree() {
		return duree;
	}
	
	/**
	 * Retourne le temps ecoule
	 * 
	 * @return double
	 */
	public double getActualTime() {
		double x = debut.getEpochSecond() - Instant.now().minus(duree, ChronoUnit.MILLIS).getEpochSecond();
		x += debut.getNano()/Math.pow(10, 9) - Instant.now().minus(duree, ChronoUnit.MILLIS).getNano()/Math.pow(10, 9);
		return (x >0)? x : 0 ;
	}

}
