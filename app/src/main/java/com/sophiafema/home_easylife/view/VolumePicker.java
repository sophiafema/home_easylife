package com.sophiafema.home_easylife.view;

import android.content.Context;
import android.util.AttributeSet;

public class VolumePicker extends Picker {



    public VolumePicker(Context context) {
        super(context);
    }

    public VolumePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VolumePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void configBeforeInit() {
        setColors(new int[] {0xFF333333, 0xFF333333});
    }

    public void configAfterInit() {
        setShowWheelSelected(true);
    }


    @Override
    public void setTextFromValue(float value) {
        setCenterText("" + calculatePercentWithValue(value));
    }

}
