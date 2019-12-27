package com.sophiafema.home_easylife.view;

import android.content.Context;
import android.util.AttributeSet;

public class BrightnessPicker extends Picker implements Picker.OnClickIntoCenter {


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
        String textTrue = "Turn ON";
        String textFalse = "Turn OFF";
        if(pIsZero) {
            setCenterText(textTrue);
        }
        else {
            setCenterText(textFalse);
        }
    }

    @Override
    public void onClick(float value) {
        boolean isEnabled = getWheelIsEnabled();
        String textTrue = "Turn ON";
        String textFalse = "Turn OFF";
        if(!(getValuePercent()<1)) {
            if (isEnabled) {
                setCenterText(textTrue);
            } else {
                setCenterText(textFalse);
            }

            toggleWheelIsEnabled();
        }
        else {
            setValueToPercent(100);
            setCenterText(textFalse);
        }
    }

}
