package Resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * @author GAUGET--BERLIOZ Matthieu, COCHET Julien
 */
public class SoundPlayer {
	
	//------------------------------------------------------------------------------
	/*
	 **      ATTRIBUTS
	 */
	//------------------------------------------------------------------------------
	
	// Clip pour la musique de fond
	private static Clip BGMClip;
	// Musique joue
	private static String BGMPlayed;
	
	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur de la classe, met tous les attributs � null.
	 */
	public SoundPlayer() {
		BGMClip = null;
		BGMPlayed = null;
	}
	
	
	//------------------------------------------------------------------------------
	/*
	 **      METHODES
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Joue une musique de fond
	 * 
	 * @param file nom du ficher (avec extension)
	 */
	public static void PlayBGM(String file) {
		try {
			BGMPlayed = file;
			AudioInputStream BGMStream =  AudioSystem.getAudioInputStream(SoundPlayer.class.getResource("/sounds/bgm/" + file));
			if(BGMClip != null) BGMClip.stop();
			BGMClip = AudioSystem.getClip();
			BGMClip.open(BGMStream);
			BGMClip.loop(Clip.LOOP_CONTINUOUSLY);
			BGMClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Joue un bruitage evenementiel
	 * 
	 * @param file nom du ficher (avec extension)
	 */
	public static void PlaySE(String file) {
		try {
			AudioInputStream SEStream =  AudioSystem.getAudioInputStream(SoundPlayer.class.getResource("/sounds/se/" + file));
			Clip SEClip = AudioSystem.getClip();
			SEClip.open(SEStream);
			SEClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Arrete la musique de fond
	 */
	public static void StopBGM() {
		if(BGMClip != null) {
			BGMClip.stop();
			BGMClip = null;
			BGMPlayed = null;
		}
	}
	
	//------------------------------------------------------------------------------
	/*
	 **      GETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Retourne le Clip de musique de fond
	 * 
	 * @return BGMClip
	 */
	public static Clip getBGMClip() {
		return BGMClip;
	}
	
	/**
	 * Retourne la musique de fond joue
	 * 
	 * @return BGMPlayed
	 */
	public static String getBGMPlayed() {
		return BGMPlayed;
	}
	
	//------------------------------------------------------------------------------
	/*
	 **      SETTERS
	 */
	//------------------------------------------------------------------------------

	/**
	 * Modifie le clip de musique de fond
	 * 
	 * @param BGMClip clip de musique de fond
	 */
	public static void setBGMClip(Clip BGMClip) {
		SoundPlayer.BGMClip = BGMClip;
	}
	
	/**
	 * Modifie la musique de fond joue
	 * 
	 * @param BGMPlayed musique de fond
	 */
	public static void setBGMPlayed(String BGMPlayed) {
		SoundPlayer.BGMPlayed = BGMPlayed;
	}
	
}
