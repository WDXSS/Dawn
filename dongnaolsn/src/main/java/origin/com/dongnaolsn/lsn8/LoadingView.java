package origin.com.dongnaolsn.lsn8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

/**
 * 学习：ValueAnimator
 * Created by zc on 2018/7/9.
 */

public class LoadingView extends View {
    private int screenWith;
    private int screenHeight;
    private Paint mPaint;
    private Paint mDstPaint;

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWith = w;
        screenHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布背景
        canvas.drawColor(Color.WHITE);
        //移动中心点
        canvas.translate(screenWith / 2, screenHeight / 2);

        // 画坐标线
        canvas.drawLine(-canvas.getWidth(), 0, canvas.getWidth(), 0, mPaint);
        canvas.drawLine(0, -canvas.getHeight(), 0, canvas.getHeight(), mPaint);

        onLoading(canvas);
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);

        mDstPaint = new Paint();
        mDstPaint.setColor(Color.RED);
        mDstPaint.setStrokeWidth(3);
        mDstPaint.setStyle(Paint.Style.STROKE);
    }

    private void onLoading(Canvas canvas) {
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);

        PathMeasure measure = new PathMeasure();
        measure.setPath(path, true);
        canvas.drawPath(path,mPaint);

        Path dst = new Path();
        //截取一段放到dst中
        measure.getSegment(0, 50, dst, true);
        canvas.drawPath(dst, mDstPaint);
        dst.reset();
        measure.getSegment(80, 120, dst, true);
        canvas.drawPath(dst, mDstPaint);

    }

}









































