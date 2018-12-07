package Mob;

import java.io.File;

import Resources.Entite;
import Resources.StdDraw;
import Resources.Timer;
import Screens.GameWorld;

public class DeadMob extends Entite{

	private Timer despawnTimer;
	private int despawnTime = 1000;
	
	// Chemin vers les sprites d'animation
	private final File SpriteAnim;

	public DeadMob(double x, double y, String classe) {
		super(x, y);
		despawnTimer = new Timer(despawnTime);
		SpriteAnim = new File("sprites/mob/deadMob/" + classe.substring(4) + "_death_");
	}

	@Override
	public void step() {
		if(despawnTimer.hasFinished())
			GameWorld.removeEntiteFrom(GameWorld.getEntites(), this);
	}

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), SpriteAnim.getAbsolutePath() + Animate() +".png", 0.15, 0.15);;

	}

	public String Animate() {
		double tmp = despawnTime/1000 - despawnTimer.getActualTime();
		if(tmp > 0.6)
			return "3";
		else if(tmp > 0.4)
			return "2";
		else if(tmp > 0.2)
			return "1";
		else
			return "0";
	}
}
