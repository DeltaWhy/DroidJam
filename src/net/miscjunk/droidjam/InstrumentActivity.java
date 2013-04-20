package net.miscjunk.droidjam;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class InstrumentActivity extends Activity implements InstrumentView.NoteListener {
    public final String TAG = "InstrumentActivity";
    ViewGroup layout;
    InstrumentSound sound;
    InstrumentView instrumentView;
    MidiCreator midi;
    boolean recording;
    String filename;
    String bandId;
    String playerId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_instrument);
        layout = (ViewGroup)findViewById(R.id.instrumentLayout);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String instrument = null;
        if (extras != null) {
        	instrument = extras.getString("instrument");
            bandId = extras.getString("bandId");
            playerId = extras.getString("playerId");
        }
        if (instrument == null)
        	instrument = "keys";
        instrumentView = InstrumentFactory.makeInstrumentView(this, instrument);
        instrumentView.setNoteListener(this);
        layout.addView(instrumentView);
        sound = InstrumentFactory.makeInstrumentSound(this, instrument);
        filename = "/storage/sdcard0/droidjam"+(new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date())+".mid";
        midi = new MidiCreator(this, filename);
        midi.beginRecording();
        recording = true;
        if (extras != null)
        	recording = extras.getBoolean("recording", true);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        midi.finishRecording();
        
        CommService comm = new CommService();
        comm.uploadFile(CommService.API_BASE+"/bands/"+bandId+"/players/"+playerId+"/upload.mid", filename);
    }
    
    public void btnNoteOn(View v) {
        noteOn(100);
    }
    
    public void btnNoteOff(View v) {
        noteOff(100);
    }
    
    @Override
    public void noteOn(int note, int velocity) {
    	if (recording)
    		midi.noteOn(note, velocity);
        sound.noteOn(note, velocity);
    }
    
    @Override
    public void noteOn(int note) {
        noteOn(note, 100);
    }
    
    @Override
    public void noteOff(int note) {
    	if (recording)
    		midi.noteOff(note);
        sound.noteOff(note);
    }
}
