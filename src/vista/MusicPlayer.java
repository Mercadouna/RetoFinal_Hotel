package vista;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import exceptions.*;

public class MusicPlayer {

	private static Clip clip;

	public static void play() throws MusicPlayerException {
		boolean canPlay = clip == null || !clip.isRunning();
		if (canPlay) {
			try {
				URL url = MusicPlayer.class.getResource("/music/Back.wav");
				if (url != null) {
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					clip.start();
				}
			} catch (Exception e) {
				throw new MusicPlayerException("Error al reproducir la musica de fondo");
			}
		}
	}

	public static void stop() {
		if (clip != null && clip.isRunning()) {
			clip.stop();
			clip.close();
		}
	}
}
