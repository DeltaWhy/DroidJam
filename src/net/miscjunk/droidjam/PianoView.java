package net.miscjunk.droidjam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PianoView extends InstrumentView {

	private boolean[] whiteKeys;
	private boolean[] blackKeys;
	int width;
	int height;
	
	public PianoView(Context context) {
        super(context);
    	whiteKeys = new boolean[10];
    	blackKeys = new boolean[7];
    }

    public PianoView(Context context, AttributeSet attribs) {
        super(context, attribs);
    }
    
	protected void onDraw(Canvas c) {
		width = c.getWidth();
		height = c.getHeight();
		Paint blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		Paint whitePaint = new Paint();
		whitePaint.setARGB(255, 255, 255, 255);
		Paint drkgrayPaint = new Paint();
		drkgrayPaint.setARGB(255, 20, 20, 20);
		Paint lgtgrayPaint = new Paint();
		lgtgrayPaint.setARGB(255, 170, 170, 170);
		c.drawPaint(blackPaint);
		for (int i = 0; i < 10; i++) {
			int keywidth = width / 10;
			int left = i * keywidth;
			int right = (i + 1) * keywidth - 1;
			int top = height / 3 - 1;
			int bottom = height - 1;
			c.drawRect(new Rect(left, top, right, bottom), whiteKeys[i] ? whitePaint : lgtgrayPaint);
		}
		for (int i = 0; i < 9; i++) {
			int keycenter = (width / 10) * (i + 1);
			int keywidth = keycenter / 2;
			int left = keycenter - keywidth / 2;
			int right = keycenter + keywidth / 2;
			int top = height / 3 - 1;
			int bottom = height * 2 / 3 - 1;
			if (i != 2 && i != 6) {
				c.drawRect(new Rect(left, top, right, bottom), blackKeys[i] ? drkgrayPaint : blackPaint);
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		int x = event.AXIS_X;
		int y = event.AXIS_Y;
		if (y > width / 3 && y < width * 2 / 3) {
			x - 3 * width / 40
		}
		return true;
	}

}
