package com.sophiafema.home_easylife.view;

import android.content.Context;
import android.util.AttributeSet;

public class TemperaturePicker extends Picker {



    public TemperaturePicker(Context context) {
        super(context);
    }

    public TemperaturePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TemperaturePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void configBeforeInit() {
        setColors(new int[] {0xFF1D39B1, 0xFF6A2346, 0xFF881D1A});
    }


    @Override
    public void setTextFromValue(float value) {
        setCenterText("" + calculateTempFromPercent(calculatePercentWithValue(value)) + "°C");
    }

    public double calculateTempFromPercent(float percent) {
        double temp = percent/8.333333 +16;
        return Math.round(temp*10)/10.0;
    }

}
