package Resources;

import java.util.Random;

import Resources.GameWorld;

public class ZombieSpawner {

	private Random rand = new Random();
	private Timer timer;
	
	ZombieSpawner(int difficulty){
		this.timer = new Timer(20000);
	}
	
	public void step() {
		if(timer.hasFinished()) {
				GameWorld.addZombie(1.2, (double)rand.nextInt(5)/10 +0.3, false);
				timer = new Timer(10000);
		}
	}
	
}
