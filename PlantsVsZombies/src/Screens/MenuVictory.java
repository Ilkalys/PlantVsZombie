package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;
import Resources.ZombieSpawner;

public class MenuVictory extends GameScreen {

	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------

	private File SpriteFilepath;
	private double durationSong = 22.857;
	private Timer launchSong;
	private double actualTime;
	// L'effet sonore a-t'il ete joue ou non
	private boolean sePlayded;
	private double bgYPosition = 0.25;

	public MenuVictory() {
		launchSong = new Timer((int)(4_000));
		SpriteFilepath = new File("sprites");
		this.sePlayded = false;
		SoundPlayer.StopBGM();
	}

	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
	}

	@Override
	public void step() {	
		if(SoundPlayer.getBGMClip() == null) {
			if(launchSong.hasFinished()) {
				SoundPlayer.PlayBGM("Win.wav");
				launchSong = new Timer((int)(durationSong * 1_000));
			}
		}
		else {
			actualTime = durationSong - launchSong.getActualTime();
			if(launchSong.hasFinished())
				launchSong = new Timer((int)(durationSong * 1_000));
		}

		if(launchSong.getActualTime() <= 0.5 && !this.sePlayded) {
			SoundPlayer.PlaySE("spot.wav");
			this.sePlayded = true;
		}
	}

	@Override
	public void dessine() {
		//fond
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(0.5, bgYPosition, SpriteFilepath.getAbsolutePath() + "/bg/MenuVictory.png", 1, 1.5);
		//affichage texte
		StdDraw.setPenColor(StdDraw.WHITE);

		if(SoundPlayer.getBGMClip() != null){
			StdDraw.setFont(new Font("sans serif",10,50));
			StdDraw.text(0.5, 0.97, "Félicitations!");
			StdDraw.setFont(new Font("sans serif",10,40));
			StdDraw.text(0.5, 0.9, "Vous avez terminé le Jeu !");
			StdDraw.picture(0.5, 0.62, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
		}
		//affichage Danse
		if( SoundPlayer.getBGMClip() != null) {
			animation();
		}
		else
			if(launchSong.getActualTime() <= 0.5 ) {
			StdDraw.picture(0.5, 0.62, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
			StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
			StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
		}
		else {
			if(4-launchSong.getActualTime() >= 1) {
				bgYPosition +=0.0085;
			}
			StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",1,1);
			StdDraw.picture(0.4, bgYPosition+0.13, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_shadow.png", 0.2, 0.2);
			StdDraw.picture(0.6, bgYPosition+0.13, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_shadow.png", 0.2, 0.2);
		}
		
	}

	public void animation() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_10.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_10.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_9.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_9.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_7.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_8.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_8.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_7.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*16 && actualTime <= 1.142*19){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*12 && actualTime <= 1.142*15) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*8 && actualTime <= 1.142*11){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_4.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_3.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*4 && actualTime <= 1.142*7)  {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571 ) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.4, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.8, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
		}
	}
}
