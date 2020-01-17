package com.sophiafema.home_easylife.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sophiafema.home_easylife.R;

public class Shutters extends View {





    /*
     * Constants used to save/restore the instance state.
     */
    private static final String STATE_PARENT = "parent";
    private static final String STATE_PERCENT = "angle";

    /**
     * {@code Paint} instance used to draw the color wheel.
     */
    private Paint mWindowBorderPaint;
    private Paint mWindowSurfacePaint;
    private Paint mShutterHandlePaint;


    /**
     * The width of the color wheel thickness.
     */
    private int mWindowThickness;

    /**
     * The radius of the color wheel.
     */
    private int mWindowWidth;
    private int mWindowHeight;
    private int mPreferredWindowWidth;
    private int mPreferredWindowHeight;

    private int mInnerWindowHeight;
    private int mInnerWindowWidth;

    /**
     * The radius of the center circle inside the color wheel.
     */
    private int mShutterWidth;
    private int mPreferredShutterWidth;
    private int mLouvreHeight;
    private int mPreferredLouvreHeight;


    /**
     * The rectangle enclosing the color wheel.
     */
    private RectF mWindowBorderRectangle = new RectF();
    private RectF mWindowSurfaceRectangle = new RectF();

    /**
     * The rectangle enclosing the center inside the color wheel.
     */
    private RectF mCenterRectangle = new RectF();
    private int mShutterPartAmount;
    private RectF[] mShutterPartRectangle;
    private RectF mShutterHandle = new RectF();

    /**
     * {@code true} if the user clicked on the pointer to start the move mode. <br>
     * {@code false} once the user stops touching the screen.
     *
     * @see #onTouchEvent(MotionEvent)
     */
    private boolean mUserIsMovingShutter = false;
    private boolean mShutterMovementIsEnabled = true;


    /**
     * Number of pixels the origin of this view is moved in X- and Y-direction.
     *
     * <p>
     * We use the center of this (quadratic) View as origin of our internal
     * coordinate system. Android uses the upper left corner as origin for the
     * View-specific coordinate system. So this is the value we use to translate
     * from one coordinate system to the other.
     * </p>
     *
     * <p>
     * Note: (Re)calculated in {@link #onMeasure(int, int)}.
     * </p>
     *
     * @see #onDraw(Canvas)
     */
    private float mTranslationOffsetX;
    private float mTranslationOffsetY;


    /**
     * Distance between pointer and user touch in Y-direction.
     */
    private float mSlopY;

    /**
     * The Louvre's position expressed as angle (in rad).
     */
    private float mPercent;

    /**
     * {@code Paint} instance used to draw the center with the new selected
     * color.
     */
    private Paint mShutterPaint;

    /**
     * {@code Paint} instance used to draw the halo of the center selected
     * colors.
     */
    private Paint mShutterBackgroundPaint;


    /**
     * {@code onPositionChangedListener} instance of the onPositionChangedListener
     */
    private OnPositionChangedListener onPositionChangedListener;


    /**
     * {@code onColorSelectedListener} instance of the onColorSelectedListener
     */
    private OnColorSelectedListener onColorSelectedListener;

    public Shutters(Context context) {
        super(context, null);
        init(null, 0);
    }
    public Shutters(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Shutters(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * An interface that is called whenever the color is changed. Currently it
     * is always called when the color is changes.
     *
     * @author lars
     *
     */
    public interface OnPositionChangedListener {
        public void onColorChanged(float value);
    }

    /**
     * An interface that is called whenever a new color has been selected.
     * Currently it is always called when the color wheel has been released.
     *
     */
    public interface OnColorSelectedListener {
        public void onColorSelected(int color);
    }


    public OnClickOnSurroundingArea onClickOnSurroundingArea;
    public interface OnClickOnSurroundingArea {
        public void onClick(float value);
    }
    public void setOnClickOnSurroundingArea(OnClickOnSurroundingArea listener) {
        this.onClickOnSurroundingArea = listener;
    }

    /**
     * Set a onPositionChangedListener
     *
     * @param listener {@code OnPositionChangedListener}
     */
    public void setOnPositionChangedListener(OnPositionChangedListener listener) {
        this.onPositionChangedListener = listener;
    }

    /**
     * Gets the onPositionChangedListener
     *
     * @return {@code OnPositionChangedListener}
     */
    public OnPositionChangedListener getOnPositionChangedListener() {
        return this.onPositionChangedListener;
    }


    /**
     * Color of the latest entry of the onPositionChangedListener.
     */
    private float oldPositionListenerColor;
    private double mGapPercentOfParts;

    private Bitmap picBitmap;

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.Shutters, defStyle, 0);
        final Resources b = getContext().getResources();

        mWindowThickness = a.getDimensionPixelSize(
                R.styleable.Shutters_window_border_thickness,
                b.getDimensionPixelSize(R.dimen.window_border_thickness));
        mPreferredWindowWidth = a.getDimensionPixelSize(
                R.styleable.Shutters_window_width,
                b.getDimensionPixelSize(R.dimen.window_width));

        mShutterPartAmount = a.getInteger(
                R.styleable.Shutters_shutter_part_amount,
                b.getInteger(R.integer.shutter_part_amount));
        mGapPercentOfParts = a.getInteger(
                R.styleable.Shutters_shutter_gap_percent_of_part,
                b.getInteger(R.integer.shutter_gap_percent_of_part));

        mShutterPartRectangle = new RectF[mShutterPartAmount];

        if(attrs != null) {
            int src_resource = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
            picBitmap = BitmapFactory.decodeResource(getResources(), src_resource);
        }

        a.recycle();




        mPercent = 0;

        mWindowBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWindowBorderPaint.setStyle(Paint.Style.STROKE);
        mWindowBorderPaint.setColor(getResources().getColor(R.color.windowBorder));
        mWindowBorderPaint.setStrokeWidth(mWindowThickness);

        mWindowSurfacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWindowSurfacePaint.setStyle(Paint.Style.FILL);
        mWindowSurfacePaint.setColor(getResources().getColor(R.color.windowBackground));


        //center
        mShutterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShutterPaint.setColor(getResources().getColor(R.color.shutterParts));
        mShutterPaint.setStyle(Paint.Style.FILL);

        mShutterBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShutterBackgroundPaint.setColor(getResources().getColor(R.color.shutterBackground));

        mShutterHandlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShutterHandlePaint.setStyle(Paint.Style.STROKE);
        mWindowBorderPaint.setStrokeWidth(mWindowThickness);
        mShutterHandlePaint.setColor(getResources().getColor(R.color.shutterHandle));

        for(int i = 0; i< mShutterPartRectangle.length; i++) {
            mShutterPartRectangle[i] = new RectF();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // All of our positions are using our internal coordinate system.
        // Instead of translating
        // them we let Canvas do the work for us.
        canvas.translate(mTranslationOffsetX, mTranslationOffsetY);

        //mColorWheelPath.addArc(mWindowBorderRectangle, calculateDegree(mGapAngle/2), 360 - calculateDegree(mGapAngle));



        //canvas.drawPath(mColorWheelPath, mWindowBorderPaint);

        canvas.drawRect(mWindowSurfaceRectangle, mWindowSurfacePaint);
        if(picBitmap!=null)
            canvas.drawBitmap(picBitmap, null, mWindowSurfaceRectangle, null);

        double shutterPartHeight = (mInnerWindowHeight+0.0)/ mShutterPartRectangle.length;
        double gap = shutterPartHeight/mGapPercentOfParts;
        double shutterPosition = ((mInnerWindowHeight-shutterPartHeight)*mPercent)+(mWindowThickness*0.5f)+shutterPartHeight;
        int x = (int)(((mInnerWindowHeight-shutterPartHeight)*mPercent)/shutterPartHeight)+1;


        //mCenterRectangle.set(-mShutterWidth, (int)(mWindowThickness*0.5), mShutterWidth, mLouvreHeight/2);
        mCenterRectangle.set(-mShutterWidth, (int)(mWindowThickness*0.5), mShutterWidth, (int) shutterPosition);
        canvas.drawRect(mCenterRectangle, mShutterBackgroundPaint);



        for(int i = 0; i<x; i++) {
            mShutterPartRectangle[i].set(-mShutterWidth, (float) (shutterPosition-shutterPartHeight*(i+1)+gap),
                    mShutterWidth,  (float) (shutterPosition-(shutterPartHeight*(i))));
            mShutterHandle.set(-mShutterWidth /6, (float) (shutterPosition-shutterPartHeight+gap+(shutterPartHeight/3)),
                    mShutterWidth /6,  (float) (shutterPosition-(shutterPartHeight/3)));
        }

        if(x< mShutterPartRectangle.length) {
            mShutterPartRectangle[x].set(-mShutterWidth, mWindowThickness*0.5f,
                    mShutterWidth,  (float) (shutterPosition-(shutterPartHeight*(x))));
        }
        else {
            x= mShutterPartRectangle.length-1;
            mShutterPartRectangle[mShutterPartRectangle.length-1].set(-mShutterWidth, mWindowThickness*0.5f,
                    mShutterWidth,  (float) (shutterPosition-(shutterPartHeight*(mShutterPartRectangle.length-1))));
        }

        for(int i = 0; i<x+1; i++) {
            canvas.drawRect(mShutterPartRectangle[i], mShutterPaint);
        }

        canvas.drawRoundRect(mShutterHandle, 30, 30, mShutterHandlePaint);
        canvas.drawRect(mWindowBorderRectangle, mWindowBorderPaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int intrinsicWidth = 2 * mPreferredWindowWidth;
        final int intrinsicHeight = 2 * mPreferredWindowHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(intrinsicWidth, widthSize);
        } else {
            width = intrinsicWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(intrinsicHeight, heightSize);
        } else {
            height = intrinsicHeight;
        }

        int min = Math.min(width, height);
        setMeasuredDimension(width, height);
        mTranslationOffsetX = width * 0.5f;
        mTranslationOffsetY = mWindowThickness;

        // fill the rectangle instances.
        //mWindowWidth = (int) ((width / 2 - mWindowThickness*2)/1.5);
        mWindowWidth = (int) ((width / 2 - mWindowThickness*2));
        mWindowHeight = height - mWindowThickness*2;
        mWindowBorderRectangle.set(-mWindowWidth, 0,
                mWindowWidth, mWindowHeight);

        mInnerWindowHeight = mWindowHeight-mWindowThickness;
        mInnerWindowWidth = (int) (mWindowWidth-mWindowThickness);
        mShutterWidth = (int) (mWindowWidth-mWindowThickness*0.5);
        mLouvreHeight = (int) (mWindowHeight-mWindowThickness*0.5);

        mWindowSurfaceRectangle.set(-mShutterWidth, (int) (mWindowThickness*0.5), mShutterWidth, mLouvreHeight);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);

        // Convert coordinates to our internal coordinate system
        float x = event.getX() - mTranslationOffsetX;
        float y = event.getY() - mTranslationOffsetY;


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Check whether the user pressed on the window.
                float pointerPositionY = calculatePointerPositionY(mPercent);

                if (x >= (-mWindowWidth)
                        && x <= (mWindowWidth)
                        && y >= (0)
                        && y <= (mWindowHeight)) {
                    //mSlopX = x - pointerPosition[0];
                    mSlopY = y - pointerPositionY;
                    mUserIsMovingShutter = true;
                    invalidate();
                }
                // Check whether the user pressed outside the wheel.
                else {
                    if (onClickOnSurroundingArea != null) {
                        onClickOnSurroundingArea.onClick(mPercent);
                    }
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mShutterMovementIsEnabled) {
                    if (mUserIsMovingShutter) {
                        float percent = (y - mSlopY)/mWindowHeight;
                        if(percent >= 1) {
                            mPercent = 1;
                        }
                        else if(percent <= 0) {
                            mPercent = 0;
                        }
                        else
                            mPercent = percent;

                        invalidate();
                    }
                    // If user did not press pointer or center, report event not handled
                    else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                }
                else {
                    invalidate();
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                mUserIsMovingShutter = false;

                if (onPositionChangedListener != null && mPercent != oldPositionListenerColor) {
                    onPositionChangedListener.onColorChanged(mPercent);
                    oldPositionListenerColor = mPercent;
                }

                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                if (onPositionChangedListener != null && mPercent != oldPositionListenerColor) {
                    onPositionChangedListener.onColorChanged(mPercent);
                    oldPositionListenerColor = mPercent;
                }
                break;
        }

        return true;
    }


    /**
     * Calculate the pointer's coordinates on the color wheel using the supplied
     * angle.
     *
     * @param percent The position of the pointer expressed as angle (in rad).
     *
     * @return The coordinates of the pointer's center in our internal
     *         coordinate system.
     */

    private float calculatePointerPositionY(float percent) {
        float y = (mWindowHeight * percent);
        return y;
    }



    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle state = new Bundle();
        state.putParcelable(STATE_PARENT, superState);
        state.putFloat(STATE_PERCENT, mPercent);
        //state.putInt(STATE_OLD_COLOR, mCenterOldColor);
        //state.putBoolean(STATE_SHOW_OLD_COLOR, mShowCenterOldColor);

        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;

        Parcelable superState = savedState.getParcelable(STATE_PARENT);
        super.onRestoreInstanceState(superState);

        mPercent = savedState.getFloat(STATE_PERCENT);
        //mShowCenterOldColor = savedState.getBoolean(STATE_SHOW_OLD_COLOR);
        int currentColor = (int) mPercent*100;
        //mPointerColor.setColor(currentColor);
    }

    //Getter and setter
    public void setPosition(float percent) {
        mPercent = percent;
        invalidate();
    }

    public float getPosition() {
        return mPercent;
    }

    public void setBackroundResource(int src_resource) {
        picBitmap = BitmapFactory.decodeResource(getResources(), src_resource);
        invalidate();
    }
}
