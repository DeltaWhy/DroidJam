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
			int keywidth = width / 20;
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
		int note = 0;
		int noteIndex = 0;
		boolean white = false;
		int x = event.AXIS_X;
		int y = event.AXIS_Y;
		if (y > height / 3 && y < height * 2 / 3) {
			int q = (x - 3 * width / 40) / (width / 20);
			switch(q) {
			case 0:
				note = 61;
				noteIndex = 0;
				break;
			case 2:
				note = 63;
				noteIndex = 1;
				break;
			case 6:
				note = 66;
				noteIndex = 2;
				break;
			case 8:
				note = 68;
				noteIndex = 3;
				break;
			case 10:
				note = 70;
				noteIndex = 4;
				break;
			case 14:
				note = 73;
				noteIndex = 5;
				break;
			case 16:
				note = 75;
				noteIndex = 6;
				break;
			}
		} else if (y > height / 3) {
			noteIndex = x / (width / 10);
			note = 60 + 2 * noteIndex;
			if (noteIndex > 2)
				note--;
			if (noteIndex > 6)
				note--;
			white = true;
		}
		
		if (note > 0) {
			boolean pressed = false;
			if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
				noteOn(note);
				pressed = true;
			} else {
				noteOff(note);
			}
			if (white)
				whiteKeys[noteIndex] = pressed;
			else
				blackKeys[noteIndex] = pressed;
		}
		
		return true;
	}

}
