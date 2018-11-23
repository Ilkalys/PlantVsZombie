package Mob;

import Resources.GameWorld;

public abstract class Zombie extends Mob{

	private int damage;
	private double speed;
	
	public Zombie(double x, double y, int damage, double speed) {
		super(x, y);
		this.damage = damage;
		this.speed = speed/100;
	}
	
	// met a jour l'entite : déplacement, effectuer une action
	public  void step() {
		if(!Plant.somethingHere(GameWorld.getEntites(), this.getX() - 0.01 - this.getSpeed(), this.getY())) {
			this.position.setX(this.position.getX() - this.getSpeed());
			if (this.position.getX() < 0.0) this.position.setX(1.0);
		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}
