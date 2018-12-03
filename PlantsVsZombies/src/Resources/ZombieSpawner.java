package Resources;


import java.util.HashMap;
import java.util.Random;

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

	private HashMap<Integer,ZombieInfos> level = new HashMap<Integer,ZombieInfos>();
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
				GameWorld.addZombie(1.2, (double)rand.nextInt(5)/10 +0.3, level.get(actualZombie).getIsShielded());
				timer = new Timer(level.get(actualZombie).getTimerValue());
				level.remove(actualZombie);
				actualZombie ++;
		}
	}
	
	public HashMap<Integer,ZombieInfos> level1(){
		HashMap<Integer,ZombieInfos> level = new HashMap<Integer,ZombieInfos>();
		level.put(1,new ZombieInfos(5000,false));
		level.put(2,new ZombieInfos(5000,false));
		level.put(3,new ZombieInfos(5000,false));
		level.put(4,new ZombieInfos(5000,false));
		level.put(5,new ZombieInfos(5000,false));
		level.put(6,new ZombieInfos(5000,false));
		level.put(7,new ZombieInfos(5000,false));
		level.put(8,new ZombieInfos(5000,false));
		level.put(9,new ZombieInfos(0,false));
		level.put(10,new ZombieInfos(5000,false));
		level.put(11,new ZombieInfos(5000,false));
		level.put(12,new ZombieInfos(5000,true));
		level.put(13,new ZombieInfos(5000,false));
		level.put(14,new ZombieInfos(5000,false));
		level.put(15,new ZombieInfos(5000,false));
		level.put(16,new ZombieInfos(5000,false));
		level.put(17,new ZombieInfos(5000,false));
		level.put(18,new ZombieInfos(5000,true));
		level.put(19,new ZombieInfos(0,true));
		level.put(20,new ZombieInfos(0,true));




		return level;
	}

	
	public HashMap<Integer, ZombieInfos> getLevel() {
		return level;
	}

	public void setLevel(HashMap<Integer, ZombieInfos> level) {
		this.level = level;
	}
	
}
