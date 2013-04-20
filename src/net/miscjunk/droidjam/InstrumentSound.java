package net.miscjunk.droidjam;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public abstract class InstrumentSound {
    protected final String TAG = "InstrumentSound";
    protected Context context;
    protected SoundPool sp;
    protected int soundIds[] = new int[128];
    protected int streamIds[] = new int[128];
    
    public InstrumentSound(Context context) {
        this.context = context;
        this.sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        for (int i=0; i < soundIds.length; i++) soundIds[i] = -1;
        for (int i=0; i < streamIds.length; i++) streamIds[i] = -1;
        loadSounds();
    }
    
    public abstract void loadSounds();
    
    public void noteOn(int note, int velocity) {
        if (soundIds[note] != -1) {
            if (streamIds[note] != -1) noteOff(note);
            streamIds[note] = sp.play(soundIds[note], 1.0F, 1.0F, 1, -1, 1.0F);
        } else {
            Log.w(TAG, "tried to play unloaded note "+note);
        }
    }
    
    public void noteOff(int note) {
        if (streamIds[note] != -1) {
            sp.stop(streamIds[note]);
            streamIds[note] = -1;
        } else {
            Log.w(TAG, "tried to stop non-playing note "+note);
        }
    }
}
