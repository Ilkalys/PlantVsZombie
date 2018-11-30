package Resources;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

	public SoundPlayer() {
		
	}
	public static void PlayBGM(String file) {
		try {
			File filepath = new File("sounds\\bgm\\" + file);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(filepath); 
			Clip clip = AudioSystem.getClip(); 
			clip.open(audioInputStream); 
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PlayBGS() {
		
	}
	
	public void PlayME() {
		
	}
	
	public void PlaySE() {
		
	}
	
}
