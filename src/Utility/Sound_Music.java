package Utility;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class mediaplayer for big size music such as BGM or OST
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Sound_Music {

	private MediaPlayer mediaPlayer;

	/**
	 * Sound_Music constructor
	 * 
	 * @param URL
	 *            loading file path
	 */
	public Sound_Music(String URL) {
		final URL resource = getClass().getResource(URL);
		final Media media = new Media(resource.toString());
		mediaPlayer = new MediaPlayer(media);
	}

	/**
	 * repeatly play the sound
	 */
	public void repeatStartMusic() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(javafx.util.Duration.ZERO);
			}
		});
		mediaPlayer.play();
	}

	/**
	 * play the sound just one time
	 */
	public void startMusic() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(javafx.util.Duration.ZERO);
			}
		});
		mediaPlayer.play();

	}

	/**
	 * turn off the music
	 */
	public void stopMusic() {
		mediaPlayer.stop();
	}

	/**
	 * set the music sound volume
	 * 
	 * @param fVolume
	 *            - float
	 */
	public void setVolumeMusic(float fVolume) {
		mediaPlayer.setVolume(fVolume);
	}
}
