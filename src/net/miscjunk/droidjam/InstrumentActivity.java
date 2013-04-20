package net.miscjunk.droidjam;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    MidiCreator midi;
    
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
        midi = new MidiCreator(this, "/storage/sdcard0/droidjam"+(new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date())+".mid");
        midi.beginRecording();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        midi.finishRecording();
    }
    
    public void btnNoteOn(View v) {
        noteOn(69);
    }
    
    public void btnNoteOff(View v) {
        noteOff(69);
    }
    
    @Override
    public void noteOn(int note, int velocity) {
        midi.noteOn(note, velocity);
        sound.noteOn(note, velocity);
    }
    
    @Override
    public void noteOn(int note) {
        noteOn(note, 64);
    }
    
    @Override
    public void noteOff(int note) {
        midi.noteOff(note);
        sound.noteOff(note);
    }
}
