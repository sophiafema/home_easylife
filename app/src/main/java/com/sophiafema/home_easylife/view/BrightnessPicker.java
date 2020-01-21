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
        setColors(new int[] {0xFF000000, 0xFF3A4000,0xFFDEC000, 0xFFDEC000, 0xFFDEC000, 0xFFEDDD00,  0xFFEDDD00, 0xFFFFEE00});
        setOnClickIntoCenter(this);
    }


    @Override
    public void setTextFromValue(float value) {
        boolean pIsZero = calculatePercentWithValue(value)<1;
        setText(!pIsZero);
    }

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

    @Override
    public void onClick(boolean isEnabled) {
        String textTurnOn = "AN";
        String textTurnOffe = "AUS";
        String schalten = "schalten";
        if(!(getValuePercent()<1)) {
            if(!isEnabled) {
                setCenterHigherText(textTurnOffe);
            }
            else {
                setCenterHigherText(textTurnOn);
            }
            if (onClickInCenter != null) {
                onClickInCenter.onClick(!isEnabled);
            }
            toggleWheelIsEnabled();
        }
        else {
            setValueToPercent(100);
            setCenterHigherText(textTurnOffe);
            if (onClickInCenter != null) {
                onClickInCenter.onClick(false);
            }
        }
        setCenterLowerText(schalten);
    }

    @Override
    public void setWheelIsEnabled(boolean isEnabled) {
        super.setWheelIsEnabled(isEnabled);
        setText(isEnabled);
        invalidate();
    }



}
