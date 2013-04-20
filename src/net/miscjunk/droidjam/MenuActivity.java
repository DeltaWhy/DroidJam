package net.miscjunk.droidjam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MenuActivity extends Activity {
    SharedPreferences prefs;
    ViewSwitcher viewSwitcher;
    EditText usernameField;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("DroidJam",Context.MODE_PRIVATE);
        username = prefs.getString("username", "");
        setContentView(R.layout.activity_menu);
        viewSwitcher = (ViewSwitcher)findViewById(R.id.viewSwitcher1);
        usernameField = (EditText)findViewById(R.id.usernameField);
        
        usernameField.setText(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.band, menu);
        return true;
    }

    /*
     * Event handlers!!
     */
    public void pickUsername(View v) {
        username = usernameField.getText().toString();
        if (username.trim().equals("")) {
            Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", username);
            editor.commit();
            viewSwitcher.showNext();
        }
    }
    
    public void startBand(View v) {
        Intent intent = new Intent(this, StartBandActivity.class);
        startActivity(intent);
    }
    
    public void joinBand(View v) {
        Intent intent = new Intent(this, JoinBandActivity.class);
        startActivity(intent);
    }
    
    public void practice(View v) {
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
    	
    }
}
