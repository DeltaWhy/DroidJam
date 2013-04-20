package net.miscjunk.droidjam;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MenuActivity extends Activity implements View.OnClickListener {
    ViewSwitcher viewSwitcher;
    View usernameForm;
    EditText usernameField;
    View mainMenu;
    Button usernameButton;
    Button startButton;
    Button joinButton;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        viewSwitcher = (ViewSwitcher)findViewById(R.id.viewSwitcher1);
        usernameForm = findViewById(R.id.usernameForm);
        usernameField = (EditText)findViewById(R.id.usernameField);
        mainMenu = findViewById(R.id.mainMenu);
        usernameButton = (Button)findViewById(R.id.usernameButton);
        startButton = (Button)findViewById(R.id.startBand);
        joinButton = (Button)findViewById(R.id.joinBand);
        
        usernameButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.band, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(usernameButton)) {
            username = usernameField.getText().toString();
            if (username.trim().equals("")) {
                Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show();
            } else {
                viewSwitcher.showNext();
            }
        }
    }
}
