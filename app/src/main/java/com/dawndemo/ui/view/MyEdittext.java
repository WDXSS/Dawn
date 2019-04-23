package com.dawndemo.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * view 的 onSaveInstanceState 和 onRestoreInstanceState();
 * 调用条件：1.view必须设置id； 2。view设置setSaveEnabled(true);
 *
 * 问题：onSaveInstanceState调用了，onRestoreInstanceState没有调用
 */
public class MyEdittext extends android.support.v7.widget.AppCompatEditText {
    private static final String TAG = "MyEdittext";
    public MyEdittext(Context context) {
        super(context);
    }

    public MyEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.d(TAG, "view -----onSaveInstanceState: ");
        Bundle bundle = new Bundle();
        Parcelable superData = super.onSaveInstanceState();
        bundle.putParcelable("super_data", superData);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, "view ---- onRestoreInstanceState: ");
        Bundle bundle = (Bundle) state;
        Parcelable superData = bundle.getParcelable("super_data");

        super.onRestoreInstanceState(superData);
    }

}
