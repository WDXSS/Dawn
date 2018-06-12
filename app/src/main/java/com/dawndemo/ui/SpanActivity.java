package com.dawndemo.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2018/5/31.
 */

public class SpanActivity extends BaseActivity {
    private static final String TAG = "SpanActivity";
    private EditText mEditText;
    private TextView mTextView;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;

    private String s1 = "123456789";
    private String url = "https://hao.360.cn/?360safe";
    private String url2 = " d的减肥开始 https://hao.360.cn";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        mEditText = findViewById(R.id.edit1);
        mTextView = findViewById(R.id.text);
        mTextView2 = findViewById(R.id.text2);
        mTextView3 = findViewById(R.id.text3);
        mTextView4 = findViewById(R.id.text4);
        mTextView5 = findViewById(R.id.text5);

    }

    public void onButton(View view) {
        mTextView.setText(mEditText.getText());
        mTextView2.setText(s1 + "和 " + url);
        mTextView3.setText(s1 + "和 " + url + url2);
        mTextView4.setText(s1 + "和 " + url);
        mTextView5.setText(s1 + "和 " + url);
        setSpannal();

    }
    public void onClear(View view) {
        CharSequence charSequence = mTextView.getText();
        //设置 autoLink  后 charSequence instanceof Spannable == true
        if (charSequence instanceof Spannable) {
            SpannableStringBuilder style = new SpannableStringBuilder(charSequence);
            style.clearSpans();// should clear old spans
            String content = style.toString();
            mTextView.setText(content);
        }
    }

    /**
     * 1.未设置 autoLink ,自定义颜色
     */
    public void setSpannal() {
        CharSequence charSequence = mTextView.getText();

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(charSequence);
        stringBuilder.setSpan(new ForegroundColorSpan(Color.GREEN), 0, charSequence.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(stringBuilder);
    }

    /**
     * 1.设置了autoLink ,自定义颜色不起作用
     *
     * @param view
     */
    public void setSpannal1(View view) {
        CharSequence charSequence = mTextView2.getText();
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(charSequence);
        stringBuilder.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView2.setText(stringBuilder);
    }

    /**
     * 1.设置了autoLink 才能 URLSpan[] urls = sp.getSpans(); urls才不为0
     * 2.设置了autoLink ,自定义颜色不起作用
     *
     * @param view
     */
    public void setSpannal2(View view) {
        CharSequence charSequence = mTextView3.getText();

        mTextView3.setMovementMethod(LinkMovementMethod.getInstance());
        Log.d(TAG, "setSpannal2: charSequence instanceof Spannable " + (charSequence instanceof Spannable));
        if (charSequence instanceof Spannable) {
            int end = charSequence.length();
            Spannable sp = (Spannable) mTextView3.getText();
            //mTextView3设置了autoLink urls才不为0
            URLSpan[] urls = sp.getSpans(9, end, URLSpan.class);
            for (int i = 0; i < urls.length; i++) {
                Log.d(TAG, "setSpannal2: url " + urls[i].getURL());
            }
            SpannableStringBuilder style = new SpannableStringBuilder(charSequence);
            style.clearSpans();
            for (final URLSpan url : urls) {
                //最主要的一点
                CustomClickUrlSpan myURLSpan = new CustomClickUrlSpan(url.getURL(), new CustomClickUrlSpan.OnLinkClickListener() {
                    @Override
                    public void onLinkClick(View view) {
                        Log.d(TAG, "setSpannal2: 点击的url " + url.getURL());
                    }
                });
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.GREEN), sp.getSpanStart(url), sp.getSpanEnd(url), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            mTextView3.setText(style);
        }

    }

    /**
     * 未设置 autoLink时，设置自定义点击事件
     *
     * @param view
     */
    public void setSpannal3(View view) {
        CharSequence charSequence = mTextView4.getText();
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(charSequence);
        CustomClickUrlSpan myURLSpan = new CustomClickUrlSpan("", new CustomClickUrlSpan.OnLinkClickListener() {
            @Override
            public void onLinkClick(View view) {
                Log.d(TAG, "未设置 autoLink时，设置自定义点击事件: 点击的url ");
            }
        });
        stringBuilder.setSpan(myURLSpan, 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView4.setText(stringBuilder);
        //加上这句才会有点击响应。。
        mTextView4.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 设置 autoLink时，添加ImageSpan
     *
     * @param view
     */
    public void setSpannal4(View view) {
        CharSequence charSequence = mTextView5.getText();

        if (charSequence instanceof Spannable) {
            SpannableStringBuilder style = new SpannableStringBuilder(charSequence);
            style.clearSpans();// should clear old spans
            String content = style.toString();
        }

        if (charSequence instanceof Spannable) {
            SpannableStringBuilder style = new SpannableStringBuilder(charSequence);
            ImageSpan imageSpan = getImageSpan("987654321" ,mTextView5.getTextSize());
            style.setSpan(imageSpan,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextView5.setText(style);
        }
    }

    public ImageSpan getImageSpan(String name, float textsize) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setTextSize(textsize);
        Rect rect = new Rect();

        paint.getTextBounds(name, 0, name.length(), rect);
        // 获取字符串在屏幕上的长度
        int width = (int) (paint.measureText(name));
        final Bitmap bmp = Bitmap.createBitmap(width, rect.height(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawText(name, rect.left, rect.height() - rect.bottom, paint);
        return new ImageSpan(SpanActivity.this, bmp, ImageSpan.ALIGN_BOTTOM);
    }


    public static class CustomClickUrlSpan extends ClickableSpan {
        private String url;
        private OnLinkClickListener mListener;

        public CustomClickUrlSpan(String url, OnLinkClickListener listener) {
            this.url = url;
            this.mListener = listener;
        }

        public void updateDrawState(TextPaint ds) {
            //这个是设置url颜色
            ds.setColor(Color.YELLOW);
            //这个是设置是否有下划线
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View widget) {
            if (mListener != null) {
                mListener.onLinkClick(widget);
            }
        }

        /**
         * 跳转链接接口
         */
        public interface OnLinkClickListener {
            void onLinkClick(View view);
        }
    }


}
