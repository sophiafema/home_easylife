package com.sophiafema.home_easylife.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sophiafema.home_easylife.R;


public abstract class Picker extends View {



    /*
     * Constants used to save/restore the instance state.
     */
    private static final String STATE_PARENT = "parent";
    private static final String STATE_ANGLE = "angle";
    //private static final String STATE_OLD_COLOR = "color";
    private static final String STATE_SHOW_OLD_COLOR = "showColor";

    /**
     * Colors to construct the color wheel using {@link SweepGradient}.
     */
    private int[] COLORS;


    private Paint mColorWheelPaint;
    private Paint mColorWheelDisabledPaint;
    private Paint mColorWheelSelectedPaint;
    private Paint mColorWheelSelectedDisabledPaint;
    private int mSelectedColor;
    private int mWheelDisabledColor;

    private boolean mShowWheelSelected;


    private Paint mPointerHaloPaint;
    private Paint mPointerHaloDisabledPaint;
    private Paint mPointerColor;

    private Paint mTextPaint;
    private Paint mTextHigherPaint;
    private Paint mTextLowerPaint;
    private int mTextColor;

    private int mColorWheelThickness;
    private int mColorWheelSelectedThickness;

    private int mColorWheelRadius;
    private int mPreferredColorWheelRadius;

    private int mColorCenterRadius;
    private int mPreferredColorCenterRadius;

    private int mColorCenterHaloRadius;
    private int mPreferredColorCenterHaloRadius;

    private int mColorPointerRadius;
    private int mColorPointerHaloRadius;

    private RectF mColorWheelRectangle = new RectF();
    private RectF mCenterRectangle = new RectF();

    Path mColorWheelPath = new Path();
    Path mColorWheelSelectedPath = new Path();

    private boolean mUserIsMovingPointer = false;

    private boolean mWheelIsEnabled = true;

    private boolean mShowCenterOldColor;
    private int mCenterNewColor;

    /**
     * Number of pixels the origin of this view is moved in X- and Y-direction.
        @see #onDraw(Canvas)
     */
    private float mTranslationOffsetX;
    private float mTranslationOffsetY;


    private float mSlopX;
    private float mSlopY;

    private float mAngle;

    /**
     * Position of gap as angle (in rad)
     */
    private float mGapAngle;

    private Paint mCenterNewPaint;

    private boolean mTouchAnywhereOnColorWheelEnabled = true;


    boolean mDirectionCounterClockWise;


    private OnColorChangedListener onColorChangedListener;

    private float mValue;

    private String mCenterText = "";
    private String mCenterHigherText = "";
    private String mCenterLowerText = "";


    private OnColorSelectedListener onColorSelectedListener;

    public Picker(Context context) {
        super(context);
        setSaveEnabled(true);
        configBeforeInit();
        init(null, 0);
        configAfterInit();
    }

    public Picker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSaveEnabled(true);
        configBeforeInit();
        init(attrs, 0);
        configAfterInit();
    }

    public Picker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSaveEnabled(true);
        configBeforeInit();
        init(attrs, defStyle);
        configAfterInit();
    }

    /**
     * An interface that is called whenever the color is changed. Currently it
     * is always called when the color is changes.
     *
     * @author lars
     *
     */
    public interface OnColorChangedListener {
        public void onColorChanged(float value);
    }

    /**
     * An interface that is called whenever a new color has been selected.
     * Currently it is always called when the color wheel has been released.
     *
     */
    public interface OnColorSelectedListener {
        public void onColorSelected(float color);
    }


    public OnClickIntoCenter onClickIntoCenter;
    public interface OnClickIntoCenter {
        public void onClick(boolean isEnabled);
    }
    public void setOnClickIntoCenter(OnClickIntoCenter listener) {
        this.onClickIntoCenter = listener;
    }

    public OnClickOnSurroundingArea onClickOnSurroundingArea;
    public interface OnClickOnSurroundingArea {
        public void onClick(float value);
    }
    public void setOnClickOnSurroundingArea(OnClickOnSurroundingArea listener) {
        this.onClickOnSurroundingArea = listener;
    }

    /**
     * Set a onColorChangedListener
     *
     * @param listener {@code OnColorChangedListener}
     */
    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.onColorChangedListener = listener;
    }

    /**
     * Gets the onColorChangedListener
     *
     * @return {@code OnColorChangedListener}
     */
    public OnColorChangedListener getOnColorChangedListener() {
        return this.onColorChangedListener;
    }

    /**
     * Set a onColorSelectedListener
     *
     * @param listener {@code OnColorSelectedListener}
     */
    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.onColorSelectedListener = listener;
    }


    public OnColorSelectedListener getOnColorSelectedListener() {
        return this.onColorSelectedListener;
    }

    private float oldChangedListenerColor;

    private float oldSelectedListenerValue;


    @SuppressLint("ResourceType")
    protected void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.Picker, defStyle, 0);
        final Resources b = getContext().getResources();

        mColorWheelThickness = a.getDimensionPixelSize(
                R.styleable.Picker_color_wheel_thickness,
                b.getDimensionPixelSize(R.dimen.color_wheel_thickness));
        mColorWheelSelectedThickness = a.getDimensionPixelSize(
                R.styleable.Picker_color_wheel_selected_thickness,
                b.getDimensionPixelSize(R.dimen.color_wheel_selected_thickness));
        mColorWheelRadius = a.getDimensionPixelSize(
                R.styleable.Picker_color_wheel_radius,
                b.getDimensionPixelSize(R.dimen.color_wheel_radius));
        mPreferredColorWheelRadius = mColorWheelRadius;
        mColorCenterRadius = a.getDimensionPixelSize(
                R.styleable.Picker_color_center_radius,
                b.getDimensionPixelSize(R.dimen.color_center_radius));
        mPreferredColorCenterRadius = mColorCenterRadius;
        mColorCenterHaloRadius = a.getDimensionPixelSize(
                R.styleable.Picker_color_center_halo_radius,
                b.getDimensionPixelSize(R.dimen.color_center_halo_radius));
        mPreferredColorCenterHaloRadius = mColorCenterHaloRadius;
        mColorPointerRadius = a.getDimensionPixelSize(
                R.styleable.Picker_color_pointer_radius,
                b.getDimensionPixelSize(R.dimen.color_pointer_radius));
        mColorPointerHaloRadius = a.getDimensionPixelSize(
                R.styleable.Picker_color_pointer_halo_radius,
                b.getDimensionPixelSize(R.dimen.color_pointer_halo_radius));
        mShowWheelSelected = a.getBoolean(R.styleable.Picker_show_wheel_selected,
                b.getBoolean(R.bool.show_wheel_selected));
        mTextColor = a.getColor(R.styleable.Picker_color_text, b.getColor(R.color.colorText));
        mSelectedColor = a.getColor(R.styleable.Picker_color_selected, b.getColor(R.color.colorSelectedWheel));
        mWheelDisabledColor = a.getColor(R.styleable.Picker_color_disabled, b.getColor(R.color.colorDisabledWheel));
        int mBackgroundColor = a.getColor(R.styleable.Picker_color_disabled, b.getColor(R.color.colorPrimaryDark));

        mGapAngle = 1;

        a.recycle();

        configValue(calculateValueWithPercent(0));
        Shader s = new SweepGradient(0, 0, COLORS, null);
        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setShader(s);
        mColorWheelPaint.setStyle(Paint.Style.STROKE);
        mColorWheelPaint.setStrokeWidth(mColorWheelThickness);

        mColorWheelDisabledPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelDisabledPaint.setColor(mWheelDisabledColor);
        mColorWheelDisabledPaint.setStyle(Paint.Style.STROKE);
        mColorWheelDisabledPaint.setStrokeWidth(mColorWheelThickness);

        mColorWheelSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelSelectedPaint.setColor(mSelectedColor);
        mColorWheelSelectedPaint.setStyle(Paint.Style.STROKE);
        mColorWheelSelectedPaint.setStrokeWidth(mColorWheelSelectedThickness);

        mColorWheelSelectedDisabledPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelSelectedDisabledPaint.setColor(mWheelDisabledColor);
        mColorWheelSelectedDisabledPaint.setStyle(Paint.Style.STROKE);
        mColorWheelSelectedDisabledPaint.setStrokeWidth(mColorWheelSelectedThickness);

        //Text
        mTextPaint = new Paint();
        //mTextPaint.setTextSize(200);
        mTextPaint.setColor(mTextColor);

        mTextHigherPaint = new Paint();
        //mTextPaint.setTextSize(200);
        mTextHigherPaint.setColor(mTextColor);

        mTextLowerPaint = new Paint();
        //mTextPaint.setTextSize(200);
        mTextLowerPaint.setColor(mTextColor);


        //Pointer
        mPointerHaloPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointerHaloPaint.setColor(Color.GRAY);
        mPointerHaloPaint.setAlpha(0x50);

        mPointerHaloDisabledPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointerHaloDisabledPaint.setColor(mWheelDisabledColor);
        mPointerHaloDisabledPaint.setStyle(Paint.Style.FILL);

        mPointerColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointerColor.setColor(calculateColor(mValue));

        //center
        mCenterNewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterNewPaint.setColor(mBackgroundColor);
        mCenterNewPaint.setStyle(Paint.Style.FILL);

        mCenterNewColor = calculateColor(mValue);
        mShowCenterOldColor = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // All of our positions are using our internal coordinate system.
        // Instead of translating
        // them we let Canvas do the work for us.
        canvas.translate(mTranslationOffsetX, mTranslationOffsetY);
        canvas.rotate(90);

        canvas.drawCircle(0, 0, mColorCenterRadius, mCenterNewPaint);
        mColorWheelPath.addArc(mColorWheelRectangle, calculateDegree(mGapAngle/2), 360 - calculateDegree(mGapAngle));



        if(mWheelIsEnabled) {
            canvas.drawPath(mColorWheelPath, mColorWheelPaint);
        }

        else {
            canvas.drawPath(mColorWheelPath, mColorWheelDisabledPaint);
        }

        if(mShowWheelSelected) {
            if (mDirectionCounterClockWise) {
                mColorWheelSelectedPath.reset();
            }
            mPointerColor.setColor(mSelectedColor);
            mColorWheelSelectedPath.addArc(mColorWheelRectangle, calculateDegree(mGapAngle/2), calculateDegree(mValue - mGapAngle/2));
            canvas.drawPath(mColorWheelSelectedPath, mColorWheelSelectedPaint);
        }

        canvas.rotate(-90);

        float[] pointerPosition = calculatePointerPosition(mAngle);
        canvas.drawCircle(pointerPosition[0], pointerPosition[1], mColorPointerHaloRadius, mPointerHaloPaint);

        if(mWheelIsEnabled) {
            canvas.drawCircle(pointerPosition[0], pointerPosition[1],
                    mColorPointerRadius, mPointerColor);
        }
        else {
            canvas.drawCircle(pointerPosition[0], pointerPosition[1],
                    mColorPointerRadius, mPointerHaloDisabledPaint);
        }




        String text = mCenterText;
        float textWidth = mTextPaint.measureText(text);
        float textHeight = mTextPaint.getTextSize();
        canvas.drawText(text, (-textWidth/2), (float) 0 + textHeight/3, mTextPaint);

        String textLower = mCenterLowerText;
        float textLowerWidth = mTextPaint.measureText(textLower);
        float textLowerHeight = mTextPaint.getTextSize();
        String textHigher = mCenterHigherText;
        float textHigherWidth = mTextPaint.measureText(textHigher);
        float textHigherHeight = mTextPaint.getTextSize();
        canvas.drawText(textHigher, (-textHigherWidth/2), (float) 0 + textHigherHeight/3 - (textLowerHeight/2), mTextPaint);
        canvas.drawText(textLower, (-textLowerWidth/2), (float) 0 + textLowerHeight/3 + (textHigherHeight/2), mTextPaint);
    }

    public float calculatePercentWithValue(float value) {
        float percent = (float) ((value - mGapAngle/2) / (Math.PI*2-mGapAngle))*100;
        return Math.round(percent);
    }
    public float calculateValueWithPercent(float percent) {
        return (float) ((percent/100) * (Math.PI*2-mGapAngle) + mGapAngle/2);
    }
    private float calculateDegree(float rad) {
        return (float) (rad * (180/Math.PI));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int intrinsicSize = 2 * (mPreferredColorWheelRadius + mColorPointerHaloRadius);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(intrinsicSize, widthSize);
        } else {
            width = intrinsicSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(intrinsicSize, heightSize);
        } else {
            height = intrinsicSize;
        }


        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        mTranslationOffsetX = width * 0.5f;
        mTranslationOffsetY = height * 0.5f;

        // fill the rectangle instances.
        mColorWheelRadius = min / 2 - mColorWheelThickness - mColorPointerHaloRadius;
        mColorWheelRectangle.set(-mColorWheelRadius, -mColorWheelRadius,
                mColorWheelRadius, mColorWheelRadius);

        mColorCenterRadius = (int) mColorWheelRadius;
        mColorCenterHaloRadius = (int) ((float) mPreferredColorCenterHaloRadius * ((float) mColorWheelRadius / (float) mPreferredColorWheelRadius));
        mCenterRectangle.set(-mColorCenterRadius, -mColorCenterRadius,
                mColorCenterRadius, mColorCenterRadius);

        mTextPaint.setTextSize(mColorCenterRadius/3);
    }

    private int ave(int s, int d, float p) {
        return s + Math.round(p * (d - s));
    }

    /**
     * Calculate the color using the supplied angle.
     * @param angle The selected color's position expressed as angle (in rad).
     * @return The ARGB value of the color on the color wheel at the specified
     *         angle.
     */
    private int calculateColor(float angle) {
        float unit = (float) ((angle) / (Math.PI*2));
        //System.out.println(unit);
        if (unit < 0) {
            unit += 1;
        }

        if (unit <= 0) {
            return COLORS[0];
        }
        if (unit >= 1) {
            return COLORS[COLORS.length - 1];
        }

        float p = unit * (COLORS.length - 1);
        int i = (int) p;
        p -= i;

        int c0 = COLORS[i];
        int c1 = COLORS[i + 1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), p);
        int r = ave(Color.red(c0), Color.red(c1), p);
        int g = ave(Color.green(c0), Color.green(c1), p);
        int b = ave(Color.blue(c0), Color.blue(c1), p);

        return Color.argb(a, r, g, b);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);

        // Convert coordinates to our internal coordinate system
        float x = event.getX() - mTranslationOffsetX;
        float y = event.getY() - mTranslationOffsetY;


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Check whether the user pressed on the pointer.
                    float[] pointerPosition = calculatePointerPosition(mAngle);
                    if (x >= (pointerPosition[0] - mColorPointerHaloRadius)
                            && x <= (pointerPosition[0] + mColorPointerHaloRadius)
                            && y >= (pointerPosition[1] - mColorPointerHaloRadius)
                            && y <= (pointerPosition[1] + mColorPointerHaloRadius)) {
                        mSlopX = x - pointerPosition[0];
                        mSlopY = y - pointerPosition[1];
                        mUserIsMovingPointer = true;
                        invalidate();
                    }
                    // Check whether the user pressed on the center.
                    else if (x >= -mColorCenterRadius && x <= mColorCenterRadius
                            && y >= -mColorCenterRadius && y <= mColorCenterRadius
                            && mShowCenterOldColor) {
                        /*mCenterHaloPaint.setAlpha(0x50);*/
                        /*setColor(getOldCenterColor());*/
                        if (onClickIntoCenter != null) {
                            onClickIntoCenter.onClick(mWheelIsEnabled);
                        }
                        invalidate();
                    }
                    // Check whether the user pressed anywhere on the wheel.
                    else if (Math.sqrt(x * x + y * y) <= mColorWheelRadius + mColorPointerHaloRadius
                            && Math.sqrt(x * x + y * y) >= mColorWheelRadius - mColorPointerHaloRadius
                            && mTouchAnywhereOnColorWheelEnabled) {
                        mUserIsMovingPointer = false;
                        invalidate();
                    }

                    // Check whether the user pressed outside the wheel.
                    else if (Math.sqrt(x * x + y * y) > mColorWheelRadius) {
                        if (onClickOnSurroundingArea != null) {
                            onClickOnSurroundingArea.onClick(mValue);
                        }
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    // If user did not press pointer or center, report event not handled
                    else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(mWheelIsEnabled) {
                        if (mUserIsMovingPointer) {
                            float angle = (float) Math.atan2(x - mSlopX, y - mSlopY);

                            if ((angle >= (mGapAngle/2) || angle <= (-mGapAngle/2))) {
                                float value = calcValue(angle);
                                //in which direction is user moving pointer -> used for part around gap
                                if (value < mValue)
                                    mDirectionCounterClockWise = true;
                                else
                                    mDirectionCounterClockWise = false;
                                mAngle = angle;
                                mValue = value;
                            } else if (mDirectionCounterClockWise) {
                                mAngle = -mGapAngle/2;
                                mValue = calcValue(mAngle);
                            } else {
                                mAngle = (mGapAngle/2);
                                mValue = calcValue(mAngle);
                            }

                            mPointerColor.setColor(calculateColor(mValue));
                            setTextFromValue(mValue);
                            if (onColorChangedListener != null && mValue != oldChangedListenerColor) {
                                onColorChangedListener.onColorChanged(mValue);
                                oldChangedListenerColor  = mValue;
                            }

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
                    mUserIsMovingPointer = false;
                    //mCenterHaloPaint.setAlpha(0x00);

                    float percent = calculatePercentWithValue(mValue);
                    if (onColorSelectedListener != null && percent != oldSelectedListenerValue) {
                        onColorSelectedListener.onColorSelected(percent);
                        oldSelectedListenerValue = percent;
                    }

                    invalidate();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    percent = calculatePercentWithValue(mValue);
                    if (onColorSelectedListener != null && percent != oldSelectedListenerValue) {
                        onColorSelectedListener.onColorSelected(percent);
                        oldSelectedListenerValue = percent;
                    }
                    break;
            }

        return true;
    }

    public float calcValue(float angle) {
        float value;
        if(angle<0) {
            value = -angle;
        }
        else {
            value = (float) (2*Math.PI-angle);
        }
        return value;
    }
    public float calcAngle(float value) {
        float angle;
        if(value<0) {
            angle = -value;
        }
        else {
            angle = (float) (2*Math.PI-value);
        }
        return angle;
    }

    /**
     * Calculate the pointer's coordinates on the color wheel using the supplied
     * angle.
     *
     * @param angle The position of the pointer expressed as angle (in rad).
     *
     * @return The coordinates of the pointer's center in our internal
     *         coordinate system.
     */
    private float[] calculatePointerPosition(float angle) {
        float x = (float) (mColorWheelRadius * Math.sin(angle));
        float y = (float) (mColorWheelRadius * Math.cos(angle));
        return new float[] { x, y };
    }



    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle state = new Bundle();
        state.putParcelable(STATE_PARENT, superState);
        state.putFloat(STATE_ANGLE, mAngle);
        state.putBoolean(STATE_SHOW_OLD_COLOR, mShowCenterOldColor);

        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;

        Parcelable superState = savedState.getParcelable(STATE_PARENT);
        super.onRestoreInstanceState(superState);

        mAngle = savedState.getFloat(STATE_ANGLE);
        mValue = calcValue(mAngle);
        mShowCenterOldColor = savedState.getBoolean(STATE_SHOW_OLD_COLOR);
        int currentColor = calculateColor(mValue);
        mPointerColor.setColor(currentColor);
        invalidate();
    }

    public void setValueToPercent(float percent) {
        float value = calculateValueWithPercent(percent);
        configValue(value);
        mPointerColor.setColor(calculateColor(mValue));
        oldSelectedListenerValue = value;
        invalidate();
    }

    private void configValue(float value) {
        mValue = value;
        mAngle = calcAngle(mValue);
        setTextFromValue(value);
    }


    public void setWheelIsEnabled(boolean isEnabled) {

        this.mWheelIsEnabled = isEnabled;
        invalidate();
    }
    public boolean getWheelIsEnabled() {
        return this.mWheelIsEnabled;
    }

    public void setShowWheelSelected(boolean isEnabled) {
        this.mShowWheelSelected = isEnabled;
        invalidate();
    }

    public void toggleWheelIsEnabled() {
        this.mWheelIsEnabled = !mWheelIsEnabled;
    }

    public float getValuePercent() {
        return calculatePercentWithValue(mValue);
    }

    public void setCenterText(String text) {
        this.mCenterText = text;
    }

    public void setCenterHigherText(String text) {
        this.mCenterHigherText = text;
    }

    public void setCenterLowerText(String text) {
        this.mCenterLowerText = text;
    }

    public void setColors(int[] colors) {
        COLORS = colors;
        invalidate();
    }

    public abstract void configBeforeInit();
    public void configAfterInit() { }

    public abstract void setTextFromValue(float value);
}
