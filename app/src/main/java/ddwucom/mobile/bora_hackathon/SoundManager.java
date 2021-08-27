package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager {
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    private AudioManager audioManager;
    private Context context;

    public SoundManager(Context context, SoundPool soundPool) {
        this.context = context;
        this.soundPool = soundPool;
        soundPoolMap = new HashMap<Integer, Integer>();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void addSound(int index, int soundId) {
        soundPoolMap.put(index, soundPool.load(context, soundId, 1));
    }

    public int playSound(int index) {
        int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        return soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
    }

    public void stopSound(int streamId) {
        soundPool.pause(streamId);
    }

    public void resumeSound(int streamId) {
        soundPool.resume(streamId);
    }
}
