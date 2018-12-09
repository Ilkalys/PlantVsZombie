package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;
import Resources.ZombieSpawner;

public class MenuLevelComplete extends GameScreen {

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

	public MenuLevelComplete() {
		launchSong = new Timer((int)(2_000));
		SpriteFilepath = new File("sprites");
		this.sePlayded = false;
		SoundPlayer.StopBGM();
	}

	@Override
	public void processUserInput(char key) {
	}

	@Override
	public void processMouseClick(double x, double y) {
		if(x <= 0.68 && x >= 0.52 && y >= 0.57 && y <= 0.63) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new GameWorld(ZombieSpawner.getCurrentDifficulty()+1));
		}
		else if(x <= 0.48 && x >= 0.32 && y >= 0.57 && y <= 0.63) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}

	}

	@Override
	public void step() {	
		if(SoundPlayer.getBGMClip() == null) {
			if(launchSong.hasFinished()) {
				SoundPlayer.PlayBGM("Ragtime.wav");
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
		StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/MenuLevelComplete.png", 1, 1);

		//affichage texte
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("sans serif",10,50));
		StdDraw.text(0.5, 0.93, "Congratulation!");
		StdDraw.setFont(new Font("sans serif",10,40));
		StdDraw.text(0.5, 0.88, "Niveau reussi !");

		//affichage boutons
		StdDraw.picture(0.6, 0.6,(StdDraw.mouseX() <= 0.68 && StdDraw.mouseX() >= 0.52 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?SpriteFilepath.getAbsolutePath() + "/button/start!_p.png" : SpriteFilepath.getAbsolutePath() + "/button/start!.png", 0.16, 0.06);	
		StdDraw.picture(0.4, 0.6,(StdDraw.mouseX() <= 0.48 && StdDraw.mouseX() >= 0.32 && StdDraw.mouseY() >= 0.57 && StdDraw.mouseY() <= 0.63)?SpriteFilepath.getAbsolutePath() + "/button/quitter_p.png" : SpriteFilepath.getAbsolutePath() + "/button/quitter.png", 0.16, 0.06);

		if(SoundPlayer.getBGMClip() != null)
			StdDraw.picture(0.5, 0.2, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);


		//affichage Danse
		if( SoundPlayer.getBGMClip() != null) {
			animation();
		}
		else if(launchSong.getActualTime() <= 0.5 ) {
			StdDraw.picture(0.5, 0.2, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
			StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
			StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
		}
		else {
			StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",1,1);
			StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_shadow.png", 0.2, 0.2);
			StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_shadow.png", 0.2, 0.2);
		}
	}

	public void animation() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_10.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_10.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_9.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_9.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_7.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_8.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_8.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_7.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*16 && actualTime <= 1.142*19){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*12 && actualTime <= 1.142*15) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*8 && actualTime <= 1.142*11){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_4.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_3.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*4 && actualTime <= 1.142*7)  {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571 ) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.4, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, 0.38, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
		}
	}
}
