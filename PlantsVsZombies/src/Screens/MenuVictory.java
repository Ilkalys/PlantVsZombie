package Screens;

import java.awt.Font;
import java.io.File;

import Resources.Game;
import Resources.SoundPlayer;
import Resources.StdDraw;
import Resources.Timer;

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
	private double persoYPosition;
	private boolean alreadyPlay = false;


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
		if(alreadyPlay ) {
			SoundPlayer.PlaySE("fire.wav");
			Game.setWorld(new MenuStart());
		}
	}

	@Override
	public void step() {	
		persoYPosition = bgYPosition + 0.13;
		if(SoundPlayer.getBGMClip() == null) {
			if(launchSong.hasFinished()) {
				SoundPlayer.PlayBGM("Win.wav");
				launchSong = new Timer((int)(durationSong * 1_000));
			}
		}
		else {
			actualTime = durationSong - launchSong.getActualTime();
			if(launchSong.hasFinished()) {
				alreadyPlay = true;
				launchSong = new Timer((int)(durationSong * 1_000));
			}
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
			StdDraw.picture(0.5, persoYPosition - 0.18, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
		}

		if(alreadyPlay) {
			animationDrum();
			animationDoubleBass();
			animationViolin();
			animationJewsharp();
			StdDraw.setFont(new Font("sans serif",15,30));
			StdDraw.text(0.5, 0.05,"Cliquez pour Quitter");
		}
		else {
			if(actualTime >= 1.142*4)
				animationDoubleBass();
			else
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_shadow.png", 0.2, 0.2);
			if(actualTime >= 1.142*8)
				animationViolin();
			else
				StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_shadow.png", 0.2, 0.2);
			if(actualTime >= 1.142*12)
				animationJewsharp();
			else
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_shadow.png", 0.2, 0.2);

			StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_shadow.png", 0.2, 0.2);
		}
		
		//affichage Danse
		if( SoundPlayer.getBGMClip() != null) {
			animationDanse();
			animationPiano();
		}
		else
			if(launchSong.getActualTime() <= 0.5 ) {
				StdDraw.picture(0.5, persoYPosition - 0.18, SpriteFilepath.getAbsolutePath() + "/bg/dancefloor.png", 0.5, 0.5);
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_shadow.png", 0.2, 0.2);
			}
			else {
				if(3-launchSong.getActualTime() >= 1) {
					bgYPosition +=0.0085;
				}
				StdDraw.picture(0.5, 0.5, SpriteFilepath.getAbsolutePath() + "/bg/Fondu.png",1,1);
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_shadow.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_shadow.png", 0.2, 0.2);
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_shadow.png", 0.2, 0.2);
			}

	}

	private void animationDanse() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_10.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_10.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_9.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_9.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_7.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_8.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_8.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_7.png", 0.2, 0.2);
			}

		}
		else if(actualTime >= 1.142*16 && actualTime <= 1.142*19){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*12 && actualTime <= 1.142*15) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_6.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_6.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_5.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_5.png", 0.2, 0.2);
			}
		}
		else if(actualTime >= 1.142*8 && actualTime <= 1.142*11){
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_4.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_3.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
		}
		else  if(actualTime >= 1.142*4 && actualTime <= 1.142*7)  {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_2.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_2.png", 0.2, 0.2);
			}
		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571 ) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_0.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_0.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.4, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/matthieu/matthieu_1.png", 0.2, 0.2);
				StdDraw.picture(0.6, persoYPosition, SpriteFilepath.getAbsolutePath() + "/mob/julien/julien_1.png", 0.2, 0.2);
			}
		}
	}

	private void animationPiano() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_2.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.5, persoYPosition - 0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.5, persoYPosition  -0.2, SpriteFilepath.getAbsolutePath() + "/mob/musical/Piano_3.png", 0.2, 0.2);
			}
		}
	}

	private void animationDoubleBass() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.3, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.3, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_1.png", 0.2, 0.2);
			}
			else {
				StdDraw.picture(0.3, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/doublebass_2.png", 0.2, 0.2);
			}
		}
	}

	private void animationViolin() {
		if(actualTime % 1.142 >= 0.8565) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_2.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 >= 0.571) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_1.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 >= 0.2855) {
			StdDraw.picture(0.7, persoYPosition - 0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_2.png", 0.2, 0.2);
		}
		else if(actualTime % 1.142 <= 0.2855) {
			StdDraw.picture(0.7, persoYPosition  -0.25, SpriteFilepath.getAbsolutePath() + "/mob/musical/violin_1.png", 0.2, 0.2);
		}
	}

	private void animationJewsharp() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_2.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.2, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_0.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.2, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/jewsharp_1.png", 0.2, 0.2);
			}
		}
	}

	private void animationDrum() {
		if((actualTime >= 1.142*3 && actualTime <= 1.142*4) || (actualTime >= 1.142*7 && actualTime <= 1.142*8) ||(actualTime >= 1.142*11 && actualTime <= 1.142*12) || (actualTime >= 1.142*15 && actualTime <= 1.142*16) ||(actualTime >= 1.142*19 && actualTime <= 1.142*20)) {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}

		}
		else {
			if(actualTime % 1.142 >= 0.8565) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.571) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 >= 0.2855) {
				StdDraw.picture(0.8, persoYPosition - 0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_3.png", 0.2, 0.2);
			}
			else if(actualTime % 1.142 <= 0.2855) {
				StdDraw.picture(0.8, persoYPosition  -0.3, SpriteFilepath.getAbsolutePath() + "/mob/musical/drum_4.png", 0.2, 0.2);
			}
		}
	}
}
