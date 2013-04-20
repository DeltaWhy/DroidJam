package net.miscjunk.droidjam;

import android.content.Context;

public class InstrumentFactory {

	public static InstrumentView makeInstrumentView(Context context, Player.Instrument instrument) {
		switch(instrument) {
		case KEYS:
			return new PianoView(context);
		case DRUMS:
			return new DrumView(context);
		default:
			return new PianoView(context);
		}
	}
	
	public static InstrumentView makeInstrumentView(Context context, String instrument) {
		if (instrument.equals("keys"))
			return new PianoView(context);
		if (instrument.equals("drums"))
			return new DrumView(context);
		return new PianoView(context);
	}
	
	public static InstrumentSound makeInstrumentSound(Context context, Player.Instrument instrument) {
		switch(instrument) {
		case KEYS:
			return new SquareSound(context);
		default:
			return new SquareSound(context);
		}
	}
	
	public static InstrumentSound makeInstrumentSound(Context context, String instrument) {
		if (instrument.equals("keys"))
			return new SquareSound(context);
		if (instrument.equals("drums"))
			return new SquareSound(context);
		return new SquareSound(context);
	}

}
