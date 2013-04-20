package net.miscjunk.droidjam;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class JoinBandActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView bandList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_band);
        setupActionBar();
        bandList = (ListView)findViewById(R.id.bandList);
        Band bands[] = new Band[3];
        bands[0] = new Band(new Player("DeltaWhy"));
        bands[1] = new Band(new Player("atommarvel"));
        bands[2] = new Band(new Player("roaclark"));
        BandAdapter adapter = new BandAdapter(this, android.R.layout.simple_list_item_1, bands);
        bandList.setAdapter(adapter);
        bandList.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView l, View v, int position, long id) {
        Band band = (Band)l.getAdapter().getItem(position);
        Toast.makeText(this, band.getHost().getUsername(), Toast.LENGTH_SHORT).show();
    }
}
