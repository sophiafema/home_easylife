package com.sophiafema.home_easylife.view;

import android.content.Context;
import android.util.AttributeSet;

public class BrightnessPicker extends Picker implements Picker.OnClickIntoCenter {


    public OnClickInCenter onClickInCenter;
    public interface OnClickInCenter {
        public void onClick(boolean isEnabled);
    }
    public void setOnClickIntoCenter(OnClickInCenter listener) {
        this.onClickInCenter = listener;
    }


    public BrightnessPicker(Context context) {
        super(context);
    }

    public BrightnessPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BrightnessPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void configBeforeInit() {
        setColors(new int[] {0xFF121212, 0xFF323011, 0xFF67620E, 0xFF908606, 0xFFAEA202,  0xFFD2C400,0xFFE5D500 , 0xFFFFED2E});
        setOnClickIntoCenter(this);
    }


    @Override
    public void setTextFromValue(float value) {
        boolean pIsZero = calculatePercentWithValue(value)<1;
        setText(!pIsZero);
    }

    /**
     * changes text depending on value between anschalten and ausschalten
     * @param isEnabled
     */
    private void setText(boolean isEnabled) {
        String textTurnOn = "AN";
        String textTurnOffe = "AUS";
        String schalten = "schalten";
        if(isEnabled) {
            setCenterHigherText(textTurnOffe);
        }
        else {
            setCenterHigherText(textTurnOn);
        }
        setCenterLowerText(schalten);
    }

    /**
     * changes text and color of wheel
     * @param isEnabled
     */
    @Override
    public void onClick(boolean isEnabled) {
        if(!(getValuePercent()<1)) {
            setText(!isEnabled);
            if (onClickInCenter != null) {
                onClickInCenter.onClick(!isEnabled);
            }
            toggleWheelIsEnabled();
        }
        else {
            setValueToPercent(100);
            setText(true);
            if (onClickInCenter != null) {
                onClickInCenter.onClick(false);
            }
        }
    }

    @Override
    public void setWheelIsEnabled(boolean isEnabled) {
        super.setWheelIsEnabled(isEnabled);
        setText(isEnabled);
        invalidate();
    }
}
