package net.miscjunk.droidjam;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LobbyActivity extends Activity implements Observer {
	
    private SharedPreferences prefs;
    private Player player;
	private Band band;
	private int playerIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);  
		prefs = getSharedPreferences("DroidJam",Context.MODE_PRIVATE);
		String playerId = prefs.getString("playerId", "");
		if (playerId.isEmpty()) {
		    Log.e("LobbyActivity","Can't be launched without playerId!");
		    return;
		}
		player = Player.findById(playerId);
		if (player == null) {
		    Toast.makeText(this, "Network error.", Toast.LENGTH_LONG).show();
		    return;
		}
		
		setContentView(R.layout.activity_lobby);
		
		// TODO get band and set player index
		Bundle params = getIntent().getExtras();
		if (params == null) {
		    Log.e("LobbyActivity","Can't be launched without bandId!");
		    return;
		}
		
		String bandId = params.getString("bandId");
		band = Band.findById(bandId);
		if (band == null) {
		    Toast.makeText(this, "Network error.", Toast.LENGTH_LONG).show();
		    return;
		}
		
		band.addObserver(this);

		setFields();
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    if (!player.joinBand(band)) {
	        Toast.makeText(this, "Network error.", Toast.LENGTH_LONG).show();
	    }
	}
	
	@Override
	protected void onPause() {
	    super.onPause();
	    if (!player.leaveBand()) {
	        Toast.makeText(this, "Network error.", Toast.LENGTH_LONG).show();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lobby, menu);
		return true;
	}

	public void setFields() {
		Player[] players = band.getPlayers();
		for (int i = 0; i < Band.NUM_PLAYERS; i++) {
			Player current = players[i];
			if (current != null) {
	    		TextView view = (TextView)findViewById(getResources().getIdentifier("Username" + i, "id", getPackageName()));
	    		view.setText(current.getUsername());
	    		CheckBox box = (CheckBox)findViewById(getResources().getIdentifier("CheckBox" + i, "id", getPackageName()));
	    		box.setChecked(current.getReady());
	    		ImageButton button = (ImageButton)findViewById(getResources().getIdentifier("InstrumentButton" + i, "id", getPackageName()));
	    		changeImage(button, current.getInstrument());
			}
		}
		
		ImageButton instrButton = (ImageButton)findViewById(getResources().getIdentifier("InstrumentButton" + playerIndex, "id", getPackageName()));
		instrButton.setClickable(true);
	}
	
	public void instrumentToggle(View view) {
		Player[] players = band.getPlayers();
		players[playerIndex].toggleInstrument();
		ImageButton instrButton = (ImageButton)findViewById(getResources().getIdentifier("InstrumentButton" + playerIndex, "id", getPackageName()));
		changeImage(instrButton, players[playerIndex].getInstrument());
		band.update();
	}
	
	public void readyToggle(View view) {
		Player current =  band.getPlayers()[playerIndex];
		CheckBox checkBox = (CheckBox)findViewById(getResources().getIdentifier("CheckBox" + playerIndex, "id", getPackageName()));
		checkBox.setChecked(current.toggleReady());
		band.update();
	}
	
	public void update(Observable o, Object arg) {
		setFields();
	}

	public void changeImage(ImageButton button, Player.Instrument instrument) {
		if (instrument == Player.Instrument.KEYS) {
			button.setImageResource(R.drawable.icon_keys);
		} else if (instrument == Player.Instrument.DRUMS) {
			button.setImageResource(R.drawable.icon_drums);
		} else {
			button.setImageResource(R.drawable.ic_launcher);
		}
	}
}
