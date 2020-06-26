package peekaboo;

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

	public void loop() {// 循環撥放
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}

	public void play() {// 撥放
		if (clip == null)
			return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void close() {// 關閉
		stop();
		clip.close();
	}

	public void stop() {// 暫停
		if (clip.isRunning())
			clip.stop();

	}

	public void setVolume(int volume) {// 設定音量大小
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		double gain = (double) volume / 100;
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);
	}

	public boolean isPlaying() {
		if (clip.isRunning()) {
			return true;
		} else {
			return false;
		}
	}
}