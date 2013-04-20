package net.miscjunk.droidjam;

import android.content.Context;

public class SquareSound extends InstrumentSound {
    public SquareSound(Context context) {
        super(context);
    }

    @Override
    public void loadSounds() {
        this.soundIds[60] = sp.load(context, R.raw.square_c4, 1);
        this.soundIds[61] = sp.load(context, R.raw.square_cs4, 1);
        this.soundIds[62] = sp.load(context, R.raw.square_d4, 1);
        this.soundIds[63] = sp.load(context, R.raw.square_ds4, 1);
        this.soundIds[64] = sp.load(context, R.raw.square_e4, 1);
        this.soundIds[65] = sp.load(context, R.raw.square_f4, 1);
        this.soundIds[66] = sp.load(context, R.raw.square_fs4, 1);
        this.soundIds[67] = sp.load(context, R.raw.square_g4, 1);
        this.soundIds[68] = sp.load(context, R.raw.square_gs4, 1);
        this.soundIds[69] = sp.load(context, R.raw.square_a4, 1);
        this.soundIds[70] = sp.load(context, R.raw.square_as4, 1);
        this.soundIds[71] = sp.load(context, R.raw.square_b4, 1);
        this.soundIds[72] = sp.load(context, R.raw.square_c5, 1);
        this.soundIds[73] = sp.load(context, R.raw.square_cs5, 1);
        this.soundIds[74] = sp.load(context, R.raw.square_d5, 1);
        this.soundIds[75] = sp.load(context, R.raw.square_ds5, 1);
        this.soundIds[76] = sp.load(context, R.raw.square_e5, 1);
    }
}
