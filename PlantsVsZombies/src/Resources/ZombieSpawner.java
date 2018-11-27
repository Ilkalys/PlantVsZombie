package Resources;

import java.util.Random;

import Resources.GameWorld;

public class ZombieSpawner {

	private int zombieQuantity;
	private Random rand = new Random();
	private Timer timer;
	
	ZombieSpawner(int difficulty){
		this.zombieQuantity = 20 * difficulty;
		this.timer = new Timer(20000);
	}
	
	public void step() {
		if (zombieQuantity ==0) {
			GameWorld.gameWon();
		}
		else
			if(timer.hasFinished()) {
				GameWorld.addZombie(1.2, (double)rand.nextInt(5)/10 +0.3, false);
				timer = new Timer(10000);
				zombieQuantity -=1;
		}
	}
	
}
