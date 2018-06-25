package com.dawndemo.animal;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.util.KeyboardStatusDetector;
import com.dawndemo.util.keyboardutil.IPanelHeightTarget;
import com.dawndemo.util.keyboardutil.KeyboardUtil;

/**
 * 软键盘
 * http://www.open-open.com/lib/view/open1461126365005.html
 * Created by zc on 2018/6/14.
 */

public class TestAnimalActivity extends BaseActivity {
    private static final String TAG = "TestAnimalActivity";
    EditText mEditText;
    private Button mButton;
    boolean isState = false;
    private InputMethodManager mImm;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "dispatchKeyEvent: event.getAction()" + event.getAction());
        Log.d(TAG, "dispatchKeyEvent: event.getKeyCode() = " + event.getKeyCode());
        Log.i(TAG, "dispatchKeyEvent: InputMethodManager = " + mImm.isActive());
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animal);
        addViewTreeObserver();
        mEditText = findViewById(R.id.edit_input);
        mButton = findViewById(R.id.move_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (isState) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(mEditText, "translationX", 0),
                            ObjectAnimator.ofFloat(mEditText, "translationY", 0)
                    );
                    animatorSet.start();
                } else {
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(mEditText, "translationX", 0),
                            ObjectAnimator.ofFloat(mEditText, "translationY", -380)
                    );
                    animatorSet.start();
                }
                isState = !isState;

                Intent intent = new Intent();
                intent.setClass(TestAnimalActivity.this, TestDialogActivity.class);
                startActivity(intent);
            }
        });


        mImm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
//        mImm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
//        mImm.getInputMethodWindowVisibleHeight();

        KeyboardStatusDetector detector = new KeyboardStatusDetector();
        detector.registerActivity(this);
        detector.setmVisibilityListener(new KeyboardStatusDetector.KeyboardVisibilityListener() {
            @Override
            public void onVisibilityChanged(boolean keyboardVisible) {
                Log.d(TAG, "onVisibilityChanged: keyboardVisible = " + keyboardVisible);
            }
        });

        KeyboardUtil.attach(this, new IPanelHeightTarget() {
            @Override
            public void refreshHeight(int panelHeight) {
                Log.i(TAG, "refreshHeight: panelHeight = " + panelHeight);
            }

            @Override
            public int getHeight() {
                return 0;
            }

            @Override
            public void onKeyboardShowing(boolean showing) {
                KeyboardUtil.getKeyboardHeight(TestAnimalActivity.this);
            }
        });
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    private void addViewTreeObserver() {
        ViewTreeObserver observer = getWindow().getDecorView().getViewTreeObserver();
        observer.addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.i(TAG, "onDraw: ");
            }
        });

        observer.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                Log.d(TAG, "onGlobalFocusChanged: ");
            }
        });
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i(TAG, "onGlobalLayout: ");
            }
        });
//        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                Log.i(TAG, "onPreDraw: ");
//                return true;
//            }
//        });
        observer.addOnTouchModeChangeListener(new ViewTreeObserver.OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean isInTouchMode) {
                Log.i(TAG, "onTouchModeChanged: ");
            }
        });
        observer.addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
            @Override
            public void onWindowAttached() {
                Log.i(TAG, "onWindowAttached: ");
            }

            @Override
            public void onWindowDetached() {
                Log.i(TAG, "onWindowDetached: ");
            }
        });
        observer.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                Log.i(TAG, "onWindowFocusChanged: ");
            }
        });
    }
}
