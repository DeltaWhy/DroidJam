package net.miscjunk.droidjam;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.support.v4.app.NavUtils;
import android.util.Log;

public class InstrumentActivity extends Activity implements InstrumentView.NoteListener {
    public final String TAG = "InstrumentActivity";
    ViewGroup layout;
    InstrumentSound sound;
    InstrumentView instrumentView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_instrument);
        layout = (ViewGroup)findViewById(R.id.instrumentLayout);
        //TODO dynamically load an InstrumentView
        //TODO dynamically load sound samples
        instrumentView = new PianoView(this);
        instrumentView.setNoteListener(this);
        layout.addView(instrumentView);
        sound = new SquareSound(this);
        /*sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundIds[69] = sp.load(this, R.raw.piano_a4, 1);*/
    }
    
    public void btnNoteOn(View v) {
        noteOn(69);
    }
    
    public void btnNoteOff(View v) {
        noteOff(69);
    }
    
    @Override
    public void noteOn(int note, int velocity) {
        //TODO write to the MIDI file
        /*if (soundIds[note] != -1) {
            streamIds[note] = sp.play(soundIds[note], 1.0F, 1.0F, 1, -1, 1.0F);
        } else {
            Log.w(TAG, "tried to play unloaded note "+note);
        }*/
        sound.noteOn(note, velocity);
    }
    
    @Override
    public void noteOn(int note) {
        noteOn(note, 64);
    }
    
    @Override
    public void noteOff(int note) {
        sound.noteOff(note);
    }
}
