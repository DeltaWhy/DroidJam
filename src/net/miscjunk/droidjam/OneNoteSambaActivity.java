package net.miscjunk.droidjam;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;

public class OneNoteSambaActivity extends Activity {
        MidiCreator music = new MidiCreator(this, "/storage/sdcard0/droidjam"+(new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date())+".mid");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_note_samba);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.one_note_samba, menu);
		return true;
	}
	
	public void stop(View view){		
		music.finishRecording();
	}
	public void start(View view){		
		music.beginRecording();	
	}
	public void noteon(View view){		
		music.noteOn(80, 100);
	}
	public void noteoff(View view){		
		music.noteOn(80, 100);
	}

}
