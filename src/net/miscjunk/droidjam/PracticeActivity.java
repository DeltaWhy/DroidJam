package net.miscjunk.droidjam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class PracticeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_practice, menu);
		return true;
	}
	
	public void practiceDrums(View view) {
		Bundle bundle = new Bundle();
		bundle.putBoolean("recording", false);
		bundle.putString("instrument", "drums");
		Intent intent = new Intent(this, InstrumentActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	public void practiceKeys(View view) {
		Bundle bundle = new Bundle();
		bundle.putBoolean("recording", false);
		bundle.putString("instrument", "keys");
		Intent intent = new Intent(this, InstrumentActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
