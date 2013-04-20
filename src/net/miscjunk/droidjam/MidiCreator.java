package net.miscjunk.droidjam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;
import com.leff.midi.util.MidiUtil;

public class MidiCreator {
    protected Context context;
    protected String filename;
    final int TEMPO = 120;
    final int CHANNEL = 0;
    MidiTrack tempoTrack = new MidiTrack();
    MidiTrack noteTrack = new MidiTrack();
    long startTime;


    public MidiCreator(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);        
        Tempo t = new Tempo();
        t.setBpm(TEMPO);        
        tempoTrack.insertEvent(ts);
        tempoTrack.insertEvent(t);   
    }

    public void beginRecording(){
        startTime = System.currentTimeMillis();	
    }

    public void noteOn(int pitch, int velocity){		
        NoteOn on = new NoteOn(getTicks(), CHANNEL, pitch, velocity);	
        noteTrack.insertEvent(on);
    }

    public void noteOff(int pitch){		
        NoteOff off = new NoteOff(getTicks(), CHANNEL, pitch, 0);	
        noteTrack.insertEvent(off);		
    }

    protected long getTicks(){
        long elapsedMilliseconds =System.currentTimeMillis()-startTime;
        //elapsedMilliseconds = (long) (elapsedMilliseconds/2.604);
        //return elapsedMilliseconds;
        return (long)(elapsedMilliseconds/(60000.0/(TEMPO*MidiFile.DEFAULT_RESOLUTION)));
    }

    public void finishRecording(){        		
        ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
        tracks.add(tempoTrack);
        tracks.add(noteTrack);

        MidiFile midi = new MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks);

        File output = new File(filename);
        try {
            midi.writeToFile(output);
        } catch(IOException e) {
            System.err.println(e);
        }        
    }


}
