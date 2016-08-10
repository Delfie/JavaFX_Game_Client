package Utility;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.media.AudioClip;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class effect sound audio class
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Sound_Audio {
	ExecutorService soundPool = Executors.newFixedThreadPool(2);
	Map<String, AudioClip> soundEffectsMap = new HashMap<>();

	/**
	 * sound_Audio constructor
	 * 
	 * @param numberOfThreads
	 *            effect sound pool size (unit <-- play sound number at the same
	 *            time
	 */
	public Sound_Audio(int numberOfThreads) {
		soundPool = Executors.newFixedThreadPool(numberOfThreads);
	}

	/**
	 * load the sound
	 * 
	 * @param id
	 *            string type index
	 * @param url
	 *            loading file path
	 */
	public void load_Sound_Effects(String id, String url) {
		URL resource = getClass().getResource(url);
		AudioClip sound = new AudioClip(resource.toString());
		soundEffectsMap.put(id, sound);
	}

	/**
	 * play the sound named id(index)
	 * 
	 * @param id
	 */
	public void play_Effect_Sound(final String id) {
		Runnable soundPlay = new Runnable() {
			@Override
			public void run() {
				soundEffectsMap.get(id).play();
			}
		};
		soundPool.execute(soundPlay);
	}

	/**
	 * stop all of sound in this sound pool
	 */
	public void turnoff() {
		soundPool.shutdown();
	}

}