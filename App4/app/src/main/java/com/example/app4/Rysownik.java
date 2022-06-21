package com.example.app4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.net.CookieHandler;

public class Rysownik extends View {
    private Path mPath;
    private Paint mPaint;
    private float x;
    private float y;

    private Bitmap mBitmapa = null;


    public Rysownik(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        x = event.getX();
        y = event.getY();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                mPath.addCircle(x, y,5, Path.Direction.CCW);
                mPath.moveTo(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mPath.moveTo(x,y);
                mPath.addCircle(x, y,5, Path.Direction.CCW);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //mBitmapa = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        //canvas.drawARGB(255, 255,0,255);
        //canvas.drawBitmap(mBitmapa, 0,0, null);

        canvas.drawPath(mPath, mPaint);
    }

    public void Czysc(){
        mPath.reset();
        invalidate();
    }

    public void setRed() {
        mPaint.setColor(Color.RED);
    }

    public void setGreen() {
        mPaint.setColor(Color.GREEN);
    }

    public void setBlue() {
        mPaint.setColor(Color.BLUE);
    }
}
