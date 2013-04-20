package net.miscjunk.droidjam;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class InstrumentView extends View {
    public interface NoteListener {
        public void noteOn(int note, int velocity);
        public void noteOn(int note);
        public void noteOff(int note);
    }
    
    protected NoteListener listener;

	public InstrumentView(Context context) {
        super(context);
    }

    public InstrumentView(Context context, AttributeSet attribs) {
        super(context, attribs);
    }
    
    protected abstract void onDraw(Canvas canvas);

    public void setNoteListener(NoteListener listener) {
        this.listener = listener;
    }
    
    protected void noteOn(int note, int velocity) {
        if (this.listener != null)
            this.listener.noteOn(note, velocity);
    }
    
    protected void noteOn(int note) {
        if (this.listener != null)
            this.listener.noteOn(note);
    }
    
    protected void noteOff(int note) {
        if (this.listener != null)
            this.listener.noteOff(note);
    }
}
