package net.miscjunk.droidjam;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class StartBandActivity extends Activity {
    EditText bandNameField;
    String bandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_band);
        // Show the Up button in the action bar.
        setupActionBar();
        bandNameField = (EditText)findViewById(R.id.bandNameField);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_band, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createBand(View v) {
        bandName = bandNameField.getText().toString();
        if (bandName.trim().equals("")) {
            Toast.makeText(this, "Please enter a band name.", Toast.LENGTH_SHORT).show();
        } else {
            Band band = new Band(bandName);
            if (band.create()) {
                Intent intent = new Intent(this, LobbyActivity.class);
                Bundle b = new Bundle();
                b.putString("bandId", band.getId());
                intent.putExtras(b);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Network error.", Toast.LENGTH_LONG).show();
            }
        }
    }
    
}
