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
	private Integer actualZombie;

	public ZombieSpawner(int difficulty){
		this.timer = new Timer(20000);
		if(difficulty ==1) {
			level = level1();
		}
		actualZombie = 1;
	}

	public void step() {
		if(timer.hasFinished() && !level.isEmpty()) {
			ZombieInfos tmp = level.poll();
			GameWorld.addZombie(1.2, (double)rand.nextInt(5)/10 +0.3, tmp.getIsShielded());
			timer = new Timer(tmp.getTimerValue());
			actualZombie ++;
		}
	}

	public ConcurrentLinkedQueue<ZombieInfos> level1(){
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
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,true));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,false));
		level.add(new ZombieInfos(5000,true));
		level.add(new ZombieInfos(0,true));
		level.add(new ZombieInfos(0,true));




		return level;
	}


	public ConcurrentLinkedQueue<ZombieInfos> getLevel() {
		return level;
	}

	public void setLevel(ConcurrentLinkedQueue<ZombieInfos> level) {
		this.level = level;
	}

}
