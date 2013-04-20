package net.miscjunk.droidjam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.widget.Chronometer;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;

public class MidiCreator {
	final int TEMPO = 120;
	final int CHANNEL = 0;
    MidiTrack tempoTrack = new MidiTrack();
    MidiTrack noteTrack = new MidiTrack();
    long startTime;
    
    
    //initializes tempotrack
	void init(){	   		
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);        
        Tempo t = new Tempo();
        t.setBpm(TEMPO);        
        tempoTrack.insertEvent(ts);
        tempoTrack.insertEvent(t);   
	}
	
	void beginRecording(){
        startTime = System.currentTimeMillis();	
	}
	
	void noteOn(int pitch, int velocity){		
		NoteOn on = new NoteOn(getTicks(), CHANNEL, pitch, velocity);	
		noteTrack.insertEvent(on);
	}
	
	void noteOff(int pitch, int velocity){		
		NoteOff off = new NoteOff(getTicks(), CHANNEL, pitch, velocity);	
		noteTrack.insertEvent(off);		
	}
	
	long getTicks(){
		long elapsedMilliseconds =System.currentTimeMillis()-startTime;
		elapsedMilliseconds = (long) (elapsedMilliseconds/2.604);
		return elapsedMilliseconds;
	}
	
	void finishRecording(Context context){        		
        // 3. Create a MidiFile with the tracks we created
        ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
        tracks.add(tempoTrack);
        tracks.add(noteTrack);
        
        MidiFile midi = new MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks);
        
        // 4. Write the MIDI data to a file
        File output = new File("/storage/sdcard0/"+"recording3.mid");//context.getFilesDir() + File.separator +  
        try {
                midi.writeToFile(output);
        } catch(IOException e) {
                System.err.println(e);
        }        
	}
	 
	
}
