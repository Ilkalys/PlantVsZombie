package Resources;

import java.util.Random;

import Resources.GameWorld;
import Sun.Sun;

public class SunSpawner{

	private Random rand = new Random();
	private Timer timer;

	SunSpawner(){
		this.timer = new Timer(6500);
	}

	public void step() {
		double x = (double)rand.nextInt(9)/10 +0.1;
		double y = (double)rand.nextInt(5)/10 +0.3;
		if(timer.hasFinished() && Sun.somethingHere(GameWorld.getSuns(), x, y) == null) {
				GameWorld.addSun(x, y);
				timer = new Timer(6500);
		}

	}
}