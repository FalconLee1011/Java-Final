package pr.dawe.game.gfx;

import javax.sound.sampled.*;

public class Music {

	private Clip clip;

	public Music(String path) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

			clip = AudioSystem.getClip();
			clip.open(dais);
			

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void play() {
		if (clip == null)
			return;
		// stop();
		clip.setFramePosition(0);
		//clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void close() {
		stop();
		clip.close();
	}

	public void stop() {
		if (clip.isRunning())
			clip.stop();
	}
	public boolean isPlaying(){
		return clip.isRunning();
	}
}