package Resources;


import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import Screens.GameWorld;

public class ZombieSpawner {
	private class ZombieInfos{
		private Integer timerValue;
		private Boolean isShielded;

		public ZombieInfos(Integer timer, boolean isSh){
			this.timerValue = timer;
			this.isShielded = isSh;
		}
		public Integer getTimerValue() {
			return this.timerValue;
		}
		public Boolean getIsShielded() {
			return this.isShielded;
		}
	}

	private ConcurrentLinkedQueue<ZombieInfos> level;
	private Random rand = new Random();
	private Timer timer;
	private static int currentDifficulty =0;

	public ZombieSpawner(int difficulty){
		this.currentDifficulty = difficulty;
		this.timer = new Timer(20000);
		if(difficulty ==1) {
			level = level1();
		}
		else if(difficulty ==2) {
			level = level3();
		}
		else if(difficulty ==3) {
			level = level3();
		}
	}

	

	public void step() {
		if(timer.hasFinished() && !level.isEmpty()) {
			ZombieInfos tmp = level.poll();
			GameWorld.addZombie(1.2, (double)rand.nextInt(5)/10 +0.3, tmp.getIsShielded());
			timer = new Timer(tmp.getTimerValue());
		}
	}

	private ConcurrentLinkedQueue<ZombieInfos> level1(){
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(10000,false));
		level.add(new ZombieInfos(0,false));
		level.add(new ZombieInfos(0,false));
		return level;
	}
	
	private ConcurrentLinkedQueue<ZombieInfos> level2(){
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(8000,false));
		level.add(new ZombieInfos(8000,false));
		level.add(new ZombieInfos(8000,false));
		level.add(new ZombieInfos(8000,false));
		level.add(new ZombieInfos(8000,false));
		level.add(new ZombieInfos(0,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,false));
		level.add(new ZombieInfos(7000,true));
		level.add(new ZombieInfos(0,true));
		return level;
	}

	private ConcurrentLinkedQueue<ZombieInfos> level3(){
		ConcurrentLinkedQueue<ZombieInfos> level = new ConcurrentLinkedQueue<ZombieInfos>();
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(0,false));
		level.add(new ZombieInfos(4000,false));
		level.add(new ZombieInfos(4000,false));
		level.add(new ZombieInfos(4000,true));
		level.add(new ZombieInfos(4000,false));
		level.add(new ZombieInfos(4000,false));
		level.add(new ZombieInfos(4000,false));
		level.add(new ZombieInfos(3000,false));
		level.add(new ZombieInfos(3000,false));
		level.add(new ZombieInfos(3000,true));
		level.add(new ZombieInfos(0,true));
		level.add(new ZombieInfos(0,true));
		return level;
	}

	public ConcurrentLinkedQueue<ZombieInfos> getLevel() {
		return level;
	}

	public static int getCurrentDifficulty() {
		return currentDifficulty;
	}


}
