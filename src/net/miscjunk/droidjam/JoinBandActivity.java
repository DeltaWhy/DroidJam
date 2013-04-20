package net.miscjunk.droidjam;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class JoinBandActivity extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_band);
        setupActionBar();
        BandAdapter adapter = new BandAdapter(this, android.R.layout.simple_list_item_1, Band.all());
        setListAdapter(adapter);
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.join_band, menu);
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
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Band band = (Band)l.getAdapter().getItem(position);
        String bandId = band.getId();
        Intent intent = new Intent(this, LobbyActivity.class);
        Bundle b = new Bundle();
        b.putString("bandId", bandId);
        intent.putExtras(b);
        startActivity(intent);
    }
}
