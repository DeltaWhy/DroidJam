package net.miscjunk.droidjam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BandAdapter extends ArrayAdapter<Band> {
    Band[] bands;
    
    public BandAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
    }

    public BandAdapter(Context context, int textViewResourceId, Band[] bands) {
        super(context, textViewResourceId, bands);
        this.bands = bands;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.band_list_item, null);
        }

        Band b = bands[position];
        if (b != null) {
            TextView hostName = (TextView) v.findViewById(R.id.bandHostName);

            if (hostName != null) {
                hostName.setText(b.getHost().getUsername());
            }
        }

        return v;
    }
}
