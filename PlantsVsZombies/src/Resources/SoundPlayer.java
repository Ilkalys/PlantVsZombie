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
	
	// Clips pour chaque type de sons
	private static Clip BGMClip, BGSClip, MEClip, SEClip;
	
	
	//------------------------------------------------------------------------------
	/*
	 **      CONSTRUCTEUR
	 */
	//------------------------------------------------------------------------------
	
	/**
	 * Constructeur de la classe, met tous les attributs à null.
	 */
	public SoundPlayer() {
		BGMClip = null; BGSClip = null; MEClip = null; SEClip = null;
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
	 * Joue un bruitage de fond
	 * 
	 * @param file nom du ficher (avec extension)
	 */
	public static void PlayBGS(String file) {
		try {
			File BGSFilepath = new File("sounds/bgs/" + file);
			AudioInputStream BGSStream = AudioSystem.getAudioInputStream(BGSFilepath);
			if(BGSClip != null) BGSClip.stop();
			BGSClip = AudioSystem.getClip();
			BGSClip.open(BGSStream);
			BGSClip.loop(Clip.LOOP_CONTINUOUSLY);
			BGSClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Joue une musique evenementielle
	 * 
	 * @param file nom du ficher (avec extension)
	 */
	public static void PlayME(String file) {
		try {
			File MEFilepath = new File("sounds/me/" + file);
			AudioInputStream MEStream = AudioSystem.getAudioInputStream(MEFilepath);
			MEClip = AudioSystem.getClip();
			MEClip.open(MEStream);
			MEClip.start();
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
			SEClip = AudioSystem.getClip();
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
	
	/**
	 * Arrete le bruitage de fond
	 */
	public static void StopBGS() {
		if(BGMClip != null) {
			BGMClip.stop();
			BGMClip = null;
		}
	}
	
	/**
	 * Arrete tous les sons
	 */
	public static void stopAll() {
		StopBGM();
		StopBGS();
		if(MEClip != null) {
			MEClip.stop();
			MEClip = null;
		}
		if(SEClip != null) {
			SEClip.stop();
			SEClip = null;
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
	 * Retourne le Clip de bruitage de fond
	 * 
	 * @return BGSClip
	 */
	public static Clip getBGSClip() {
		return BGSClip;
	}

	/**
	 * Retourne le Clip de musique evenementielle
	 * 
	 * @return MEClip
	 */
	public static Clip getMEClip() {
		return MEClip;
	}

	/**
	 * Retourne le Clip de bruitage evenementielle
	 * 
	 * @return SEClip
	 */
	public static Clip getSEClip() {
		return SEClip;
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

	/**
	 * Retourne le Clip de bruitage de fond
	 * 
	 * @param bGMClip clip de bruitage de fond
	 */
	public static void setBGSClip(Clip bGSClip) {
		BGSClip = bGSClip;
	}

	/**
	 * Retourne le Clip de musique evenementielle
	 * 
	 * @param bGMClip clip de musique evenementielle
	 */
	public static void setMEClip(Clip mEClip) {
		MEClip = mEClip;
	}

	/**
	 * Retourne le Clip de bruitage evenementielle
	 * 
	 * @param bGMClip clip de bruitage evenementielle
	 */
	public static void setSEClip(Clip sEClip) {
		SEClip = sEClip;
	}
	
}
