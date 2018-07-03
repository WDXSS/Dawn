package origin.com.dongnaolsn.lsn8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 第一个测试：
 */
public class PathView extends View {
    private static final String TAG = "pathView";
    private Paint mDefPaint;
    private int mViewWidth;
    private int mViewHeight;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 平移坐标系
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
//        // 画坐标线
//        canvas.drawLine(-canvas.getWidth(), 0, canvas.getWidth(), 0, mDefPaint);
//        canvas.drawLine(0, -canvas.getHeight(), 0, canvas.getHeight(), mDefPaint);

        testForceClose(canvas);
    }

    private void initView(){
        //第一步 创建画笔
        mDefPaint = new Paint();
        mDefPaint.setColor(Color.RED);
        mDefPaint.setStrokeWidth(5);
        mDefPaint.setStyle(Paint.Style.STROKE);
    }

    private void testForceClose(Canvas canvas){
        //第一步  初始化 Path
        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
//        path.lineTo(200, 0);
//        path.rLineTo(200,200);

        PathMeasure pathMeasure1 = new PathMeasure();
        pathMeasure1.setPath(path,false);

        PathMeasure pathMeasure2 = new PathMeasure();
        pathMeasure2.setPath(path,true);
        Log.i(TAG, "forceClosed=false length = " + pathMeasure1.getLength());
        Log.i(TAG, "forceClosed=true length = " + pathMeasure2.getLength());

        canvas.drawPath(path,mDefPaint);
    }
}
