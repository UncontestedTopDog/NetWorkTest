package com.example.administrator.networktest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangweiliang on 2018/3/5.
 */

public class SignalIndicator extends View {
    Paint mPaint;
    long signal = 5;
    private static final int DIVISION_LEVEL_DEFAULT = 5;
    int mDivisionLevel = DIVISION_LEVEL_DEFAULT;
    int mStrongColor;
    int mMiddleColor;
    int mWeakColor;
    int mNoSignalColor;
    Bitmap mNoSignalBitmap;


    public SignalIndicator(Context context) {
        this(context, null, R.styleable.SignalIndicatorStyle_signalIndicatorDefault);
    }

    public SignalIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.styleable.SignalIndicatorStyle_signalIndicatorDefault);
    }

    public SignalIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);

        // Load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SignalIndicator, defStyle, 0);

        // Check for extra features being enabled
//        mDivisionLevel = attributes.getInt(R.styleable.SignalIndicator_division_level, DIVISION_LEVEL_DEFAULT);
        mStrongColor = attributes.getColor(R.styleable.SignalIndicator_division_level, getResources().getColor(R.color.strong));
        mMiddleColor = attributes.getColor(R.styleable.SignalIndicator_division_level, getResources().getColor(R.color.middle));
        mWeakColor = attributes.getColor(R.styleable.SignalIndicator_division_level, getResources().getColor(R.color.weak));
        mNoSignalColor = attributes.getColor(R.styleable.SignalIndicator_division_level, getResources().getColor(R.color.no_signal));

    }

    public long getsignal() {
        return signal;
    }

    public void setsignal(long signal) {
        this.signal = signal;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int level = -1;
        if (signal == 999)
            level = -1;
        else if (signal < 40)
            level = 4;
        else if (signal < 80)
            level = 3;
        else if (signal < 120)
            level = 2;
        else if (signal < 160)
            level = 1;
        else level = 0;

        int width = getWidth();
        int height = getHeight();
        if (level < 2)
            mPaint.setColor(mWeakColor);
        else if (level == 2)
            mPaint.setColor(mMiddleColor);
        else
            mPaint.setColor(mStrongColor);
        mPaint.setTextSize(height / 3);
        canvas.drawText(signal + "", 0, height / 3, mPaint);
        for (int i = 0; i < 5; i++) {
            if (i > level)
                mPaint.setColor(mNoSignalColor);
            canvas.drawRect(new Rect((1 + 10 * i) * width / 51, (5 - i) * height / 10, (10 * (i + 1)) * width / 51, height), mPaint);
        }
        if (level == -1) {
            if (mNoSignalBitmap == null) {
                mNoSignalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_signal);
                int size = width<height ?width/2 : height /2 ;
                mNoSignalBitmap = Bitmap.createScaledBitmap(mNoSignalBitmap, size, size, false);
            }
            canvas.drawBitmap(mNoSignalBitmap, width - mNoSignalBitmap.getWidth() - width/51, height - mNoSignalBitmap.getHeight() - width/51 , null);
        }

    }
}
