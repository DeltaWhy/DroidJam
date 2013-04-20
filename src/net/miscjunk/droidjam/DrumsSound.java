package net.miscjunk.droidjam;

import android.content.Context;

public class DrumsSound extends InstrumentSound {
    public DrumsSound(Context context) {
        super(context);
    }

    @Override
    public void loadSounds() {
        this.soundIds[36] = sp.load(context, R.raw.drums_bass, 1);
        this.soundIds[42] = sp.load(context, R.raw.drums_cymbal, 1);
        this.soundIds[38] = sp.load(context, R.raw.drums_snare, 1);
        this.soundIds[50] = sp.load(context, R.raw.drums_tomhi, 1);
        this.soundIds[45] = sp.load(context, R.raw.drums_tomlow, 1);
        this.soundIds[47] = sp.load(context, R.raw.drums_tommid, 1);
    }
}
