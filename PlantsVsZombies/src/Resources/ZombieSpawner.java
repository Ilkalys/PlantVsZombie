package Resources;


import java.util.PriorityQueue;
import java.util.Random;

import Screens.GameWorld;

public class ZombieSpawner {

	private PriorityQueue<Integer> listeZombies = new PriorityQueue<Integer>();
	private Random rand = new Random();
	private Timer timer;
	
	public ZombieSpawner(int difficulty){
		this.timer = new Timer(20000);
		for(int i=0; i< GameWorld.getZombieQuantity() ; i++ ) {
			listeZombies.add(rand.nextInt(10));
		}
	}
	
	public void step() {
		if(timer.hasFinished() && !listeZombies.isEmpty()) {
				GameWorld.addZombie(1.2, (double)rand.nextInt(5)/10 +0.3, false);
				timer = new Timer(listeZombies.poll()*2000);
		}
	}
	
}
