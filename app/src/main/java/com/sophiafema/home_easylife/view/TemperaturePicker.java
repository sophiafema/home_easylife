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
        setColors(new int[] {0xFF153881, 0xFFf63a13});
    }


    @Override
    public void setTextFromValue(float value) {
        setCenterText("" + calculateTempFromPercent(calculatePercentWithValue(value)));
    }

    public double calculateTempFromPercent(float percent) {
        double temp = percent/7.14285 +18;
        return Math.round(temp*10)/10.0;
    }

    public void setPercentFromTemp(float temp) {
        double percent = temp*7.14285 -18;
        setValueToPercent((float) percent);
    }



}
