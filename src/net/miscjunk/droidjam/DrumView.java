package net.miscjunk.droidjam;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class DrumView extends InstrumentView {
	
	private class Coordinate {
		public int x, y;
	}

	private int width;
	private int height;
	
	private static final long BEATLENGTH = 100; 
	
	private static final int BASS = 36;
	private Coordinate bassCenter = new Coordinate();
	private static final int SNARE = 38;
	private Coordinate snareCenter = new Coordinate();
	private static final int CYMBAL = 42;
	private Coordinate cymbalCenter = new Coordinate();
	private int largeRadius;
	private static final int LOWTOM = 45;
	private Coordinate lowtomCenter = new Coordinate();
	private static final int MEDTOM = 47;
	private Coordinate medtomCenter = new Coordinate();
	private static final int HIGHTOM = 50;
	private Coordinate hightomCenter = new Coordinate();
	private int smallRadius;
	
	public DrumView(Context context) {
        super(context);
    }

    public DrumView(Context context, AttributeSet attribs) {
        super(context, attribs);
    }
    
	protected void onDraw(Canvas c) {
		width = c.getWidth();
		height = c.getHeight();
		largeRadius = Math.min(3 * height / 16, width / 8);
		smallRadius = (int)Math.sqrt(width * width + height * height) / 16;
		
		hightomCenter.x = width / 6 * 3; 
		hightomCenter.y = height / 6;
		medtomCenter.x = width / 6 * 2 * 15 / 16; 
		medtomCenter.y = height / 6 * 2 * 3 / 4;
		lowtomCenter.x = width / 6; 
		lowtomCenter.y = height / 6 * 3;

		cymbalCenter.x = width * 13 / 16;
		cymbalCenter.y = height / 4;
		bassCenter.x = width * 11 / 16;
		bassCenter.y = height * 3 / 4;
		snareCenter.x = width * 6 / 16;
		snareCenter.y = height * 3 / 4;
		
		Paint whitePaint = new Paint();
		whitePaint.setARGB(255, 255, 255, 255);
		c.drawCircle(hightomCenter.x, hightomCenter.y, smallRadius, whitePaint);
		c.drawCircle(medtomCenter.x, medtomCenter.y, smallRadius, whitePaint);
		c.drawCircle(lowtomCenter.x, lowtomCenter.y, smallRadius, whitePaint);
		c.drawCircle(bassCenter.x, bassCenter.y, largeRadius, whitePaint);
		c.drawCircle(snareCenter.x, snareCenter.y, largeRadius, whitePaint);
		c.drawCircle(cymbalCenter.x, cymbalCenter.y, largeRadius, whitePaint);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int)event.getX(event.getActionIndex());
		int y = (int)event.getY(event.getActionIndex());
		if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN || event.getActionMasked() == MotionEvent.ACTION_DOWN) {
			int type = -1;
			if (intersect(x, y, bassCenter.x, bassCenter.y, largeRadius)) {
				type = BASS;
			} else if (intersect(x, y, snareCenter.x, snareCenter.y, largeRadius)) {
				type = SNARE;
			} else if (intersect(x, y, cymbalCenter.x, cymbalCenter.y, largeRadius)) {
				type = CYMBAL;
			} else if (intersect(x, y, hightomCenter.x, hightomCenter.y, smallRadius)) {
				type = HIGHTOM;
			} else if (intersect(x, y, medtomCenter.x, medtomCenter.y, smallRadius)) {
				type = MEDTOM;
			} else if (intersect(x, y, lowtomCenter.x, lowtomCenter.y, smallRadius)) {
				type = LOWTOM;
			}
			
			final int finalType = type;
			if (type >= 0) {
				noteOn(type);
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						noteOff(finalType);
					}
				}, BEATLENGTH);
			}
		}
		return true;
	}
	
	public boolean intersect(int touchX, int touchY, int circleX, int circleY, int radius) {
		if (Math.pow(touchX - circleX, 2) + Math.pow(touchY - circleY, 2) < radius * radius)
			return true;
		return false;
	}
}
