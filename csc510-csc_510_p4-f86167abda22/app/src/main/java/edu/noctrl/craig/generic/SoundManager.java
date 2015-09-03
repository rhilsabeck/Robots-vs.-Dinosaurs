package edu.noctrl.craig.generic;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.deitel.cannongame.R;

public class SoundManager {
	// constants and variables for managing sounds
	public static int FIRE_ID = 0;
	public static int JET_HIT = 1;
	public static int ALIEN_HIT = 2;
	public static int BACKGROUND_SOUND = 3;
	public static int BACKGROUND_SOUND_STAGE2 = 4;
	
	
	protected SoundPool soundPool; // plays sound effects
	protected Context context;
	
	public SoundManager(Context context){
        SoundPool.Builder builder = new SoundPool.Builder();
        AudioAttributes.Builder atts = new AudioAttributes.Builder();
        atts.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        atts.setUsage(AudioAttributes.USAGE_GAME);
        atts.setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED);
        builder.setAudioAttributes(atts.build());
		builder.setMaxStreams(5);

        // initialize SoundPool to play the app's three sound effects
		soundPool = builder.build();
		this.context = context;
		initializeSounds();
	}
	
	public void releaseResources(){
		soundPool.release(); // release all resources used by the SoundPool
		soundPool = null;
	}
	
	protected void initializeSounds(){
        FIRE_ID = soundPool.load(context, R.raw.cannon_fire, 1);
        JET_HIT = soundPool.load(context, R.raw.blocker_hit, 1);
        ALIEN_HIT = soundPool.load(context, R.raw.target_hit, 1);
		BACKGROUND_SOUND = soundPool.load(context, R.raw.pol_star_commando_short, 1);
		BACKGROUND_SOUND_STAGE2 = soundPool.load(context, R.raw.pol_bomberguy_short, 1);
	}
	
	public void playSound(int sound){
		soundPool.play(sound, 1, 1, 1, 0, 1f);
	}

	public int playSoundLooped(int sound) {
		return soundPool.play(sound, 1, 1, 1, -1, 1f);
	}

	public void stopSoundLooped(int sound) {
		soundPool.stop(sound);
	}

}
