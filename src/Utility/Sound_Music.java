package Utility;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound_Music {

	private MediaPlayer mediaPlayer;

	public Sound_Music(String URL) {
		final URL resource = getClass().getResource(URL);
		final Media media = new Media(resource.toString());
		mediaPlayer = new MediaPlayer(media);
	}

	public void repeatStartMusic() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(javafx.util.Duration.ZERO);
			}
		});
		mediaPlayer.play();
	}

	public void startMusic() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(javafx.util.Duration.ZERO);
			}
		});
		mediaPlayer.play();
		
	}

	public void stopMusic() {
		mediaPlayer.stop();
	}

	public void setVolumeMusic(float fVolume) {
		mediaPlayer.setVolume(fVolume);
	}
}
