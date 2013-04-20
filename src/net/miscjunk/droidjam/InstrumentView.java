package net.miscjunk.droidjam;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class InstrumentView extends View {

	public InstrumentView(Context context) {
        super(context);
    }

    public InstrumentView(Context context, AttributeSet attribs) {
        super(context, attribs);
    }
    
    protected abstract void onDraw(Canvas canvas);

}
