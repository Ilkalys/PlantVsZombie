package Resources;

import java.io.File;
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
	
	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur de la classe, met tous les attributs à null.
	 */
	public SoundPlayer() {
		BGMClip = null;
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
			File BGMFilepath = new File("sounds/bgm/" + file);
			AudioInputStream BGMStream = AudioSystem.getAudioInputStream(BGMFilepath);
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
			File SEFilepath = new File("sounds/se/" + file);
			AudioInputStream SEStream = AudioSystem.getAudioInputStream(SEFilepath);
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
	
	//------------------------------------------------------------------------------
	/*
	 **      SETTERS
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Retourne le Clip de musique de fond
	 * 
	 * @param bGMClip clip de musique de fond
	 */
	public static void setBGMClip(Clip bGMClip) {
		BGMClip = bGMClip;
	}
	
}
