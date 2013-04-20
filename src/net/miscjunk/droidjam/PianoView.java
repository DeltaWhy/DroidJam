package net.miscjunk.droidjam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class PianoView extends InstrumentView {

	private boolean[] whiteKeys;
	private boolean[] blackKeys;
	private int[] notesHeld;
	int width;
	int height;
	
	public PianoView(Context context) {
        super(context);
    	whiteKeys = new boolean[10];
    	blackKeys = new boolean[7];
    	notesHeld = new int[4];
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
		drkgrayPaint.setARGB(255, 50, 50, 50);
		Paint lgtgrayPaint = new Paint();
		lgtgrayPaint.setARGB(255, 170, 170, 170);
		c.drawPaint(blackPaint);
		for (int i = 0; i < 10; i++) {
			int keywidth = width / 10;
			int left = i * keywidth;
			int right = (i + 1) * keywidth - 1;
			int top = height / 3 - 1;
			int bottom = height - 1;
			c.drawRect(new Rect(left, top, right, bottom), whiteKeys[i] ? lgtgrayPaint : whitePaint);
		}
		for (int i = 0; i < 7; i++) {
			int adjustedIndex = i;
			if (i > 1)
				adjustedIndex++;
			if (i > 4)
				adjustedIndex++;
			int keycenter = (width / 10) * (adjustedIndex + 1);
			int keywidth = width / 20;
			int left = keycenter - keywidth / 2;
			int right = keycenter + keywidth / 2;
			int top = height / 3 - 1;
			int bottom = height * 2 / 3 - 1;
			c.drawRect(new Rect(left, top, right, bottom), blackKeys[i] ? blackPaint : drkgrayPaint);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
	    if (!(event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_UP
	    		|| event.getActionMasked() == MotionEvent.ACTION_MOVE)) return true;
		int note = -1;
		int noteIndex = 0;
		boolean white = false;
		int x = (int)event.getRawX();
		int y = (int)event.getRawY();
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
		}
		if (note == -1 && y > height / 3) {
			noteIndex = x / (width / 10);
			note = 60 + 2 * noteIndex;
			if (noteIndex > 2)
				note--;
			if (noteIndex > 6)
				note--;
			white = true;
		}
		
		if (note >= 0) {
			boolean pressed = false;
			if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
				noteOn(note);
				notesHeld[0] = note;
				pressed = true;
			} else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
				noteOff(note);
				notesHeld[0] = -1;
			} else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
				if (notesHeld[0] != note) {
					int oldIndex = convertNoteIndexToKeyIndex(notesHeld[0]);
					if (oldIndex >= 10)
						whiteKeys[oldIndex - 10] = false;
					else
						blackKeys[oldIndex] = false;
					noteOff(notesHeld[0]);
					notesHeld[0] = note;
					noteOn(notesHeld[0]);
				}
				pressed = true;
			}
			if (white)
				whiteKeys[noteIndex] = pressed;
			else
				blackKeys[noteIndex] = pressed;
			invalidate();
		}
		
		return true;
	}
	
	public int convertNoteIndexToKeyIndex(int keyIndex) {
		switch(keyIndex) {
		case 60:
			return 10;
		case 61:
			return 0;
		case 62:
			return 11;
		case 63:
			return 1;
		case 64:
			return 12;
		case 65:
			return 13;
		case 66:
			return 2;
		case 67:
			return 14;
		case 68:
			return 3;
		case 69:
			return 15;
		case 70:
			return 4;
		case 71:
			return 16;
		case 72:
			return 17;
		case 73:
			return 5;
		case 74:
			return 18;
		case 75:
			return 6;
		case 76:
			return 19;
		}
		return 0;
	}
}
