package Resources;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Timer {

	private Instant debut;
	private long duree; // duree du timer en millisecondes
	

	public Timer(long duree) {
		this.duree = duree;
		restart();
	}
	
	public void restart() {
		debut = Instant.now();
	}
	
	public boolean hasFinished() {
		return (debut.compareTo((Instant.now().minus(duree, ChronoUnit.MILLIS))) < 0);
	}
	
	public long getDuree() {
		return duree;
	}
	public double getActualTime() {
		double x = debut.getEpochSecond() - Instant.now().minus(duree, ChronoUnit.MILLIS).getEpochSecond();
		x += debut.getNano()/Math.pow(10, 9) - Instant.now().minus(duree, ChronoUnit.MILLIS).getNano()/Math.pow(10, 9);
		return (x >0)? x : 0 ;
	}


}
