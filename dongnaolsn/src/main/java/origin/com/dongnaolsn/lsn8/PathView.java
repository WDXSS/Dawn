package origin.com.dongnaolsn.lsn8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
    private Paint mPaint;
    private Paint mPaint0;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
        //设置画布背景
        canvas.drawColor(Color.WHITE);
        // 平移坐标系
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        // 画坐标线
        canvas.drawLine(-canvas.getWidth(), 0, canvas.getWidth(), 0, mPaint);
        canvas.drawLine(0, -canvas.getHeight(), 0, canvas.getHeight(), mPaint);

//        testForceClose(canvas);
//        testGetSegment(canvas);
//        testGetSegmentMoveTo(canvas);
        testNextContour(canvas);
    }



    /**
     * 调到下一个
     *
     * @param canvas
     */
    private void testNextContour(Canvas canvas) {

        Path path1 = new Path();
        //Path.Direction.CCW 逆时针
        path1.addRect(-200, -200, 200, 200, Path.Direction.CW);
        PathMeasure pathMeasure1 = new PathMeasure();
        pathMeasure1.setPath(path1, true);

        Path path2 = new Path();
        //Path.Direction.CCW 逆时针
        path2.addRect(-100, -100, 100, 100, Path.Direction.CW);
        PathMeasure pathMeasure2 = new PathMeasure();
        pathMeasure2.setPath(path2, true);


        Path path = new Path();
        //组合path1 和path2
        path.op(path1, path2, Path.Op.XOR);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, true);

        float lens1 = pathMeasure.getLength();
        //调到下一个
//        pathMeasure.nextContour();
        float lens2 = pathMeasure.getLength();

        Log.d(TAG, "testNextContour: lens1.length = " + lens1);
        Log.d(TAG, "testNextContour: lens2.length = " + lens2);
        Log.d(TAG, "testNextContour: pathMeasure.length = " + pathMeasure.getLength());

        canvas.drawPath(path, mPaint);
        Log.d(TAG, "testNextContour: pathMeasure1.length = " + pathMeasure1.getLength());
        Log.d(TAG, "testNextContour: pathMeasure2.length = " + pathMeasure2.getLength());
    }

    private void initView() {

        mDefPaint = new Paint();
        mDefPaint.setColor(Color.BLACK);
        mDefPaint.setStrokeWidth(1);
        mDefPaint.setStyle(Paint.Style.STROKE);

        //第一步 创建画笔
        mPaint0 = new Paint();
        mPaint0.setColor(Color.BLUE);
        mPaint0.setStrokeWidth(5);
        mPaint0.setStyle(Paint.Style.STROKE);

        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    /**
     * 测试ForceClose的值
     *
     * @param canvas
     */
    private void testForceClose(Canvas canvas) {
        //第一步  初始化 Path
        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);
//        path.rLineTo(200, 200);
        //第二步 创建PathMeasure
        PathMeasure pathMeasure1 = new PathMeasure();
        pathMeasure1.setPath(path, false);

        PathMeasure pathMeasure2 = new PathMeasure();
//       true 表示起点和终点闭合了,但不是说path图形闭合了
        pathMeasure2.setPath(path, true);
        //ForceClose = false length = 600;
        Log.i(TAG, "forceClosed=false length = " + pathMeasure1.getLength());
        //ForceClose = true length = 800;
        Log.i(TAG, "forceClosed=true length = " + pathMeasure2.getLength());

        canvas.drawPath(path, mDefPaint);
    }

    /**
     * 路径截取，且不移动开始点
     *
     * @param canvas
     */
    private void testGetSegment(Canvas canvas) {
        Path path = new Path();
        // 创建Path并添加了一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path dst = new Path();
        // 将 Path 与 PathMeasure 关联
        PathMeasure measure = new PathMeasure(path, false);

        // 截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
//        startD:开始截取位置距离path起点的位置,这不是一个坐标值,是没有负数的
//        stopD:结束点距离path起点的位置,同理上,这个是小于等于path的总长度(pathmeasure.getLength())
//        startWidthMoveTo:表示起点位置是否使用moveTo()
        measure.getSegment(200, 600, dst, false);

        canvas.drawPath(path, mPaint);
        // 绘制 dst
        canvas.drawPath(dst, mPaint0);
        //startWidthMoveTo true/false
        Log.d(TAG, "testGetSegment: startWithMoveTo = true dst.length = " + measure.getLength());
    }

    /**
     * 路径截取，移动开始点
     *
     * @param canvas
     */
    private void testGetSegmentMoveTo(Canvas canvas) {
        Path path = new Path();
        // 创建Path并添加了一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path dst = new Path();
//        dst.lineTo(-300, -300);
        // 将 Path 与 PathMeasure 关联
        PathMeasure measure = new PathMeasure(path, false);

        // 截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path
        measure.getSegment(200, 600, dst, true);
        //measure.getSegment(200, 600, dst, true);
        PathMeasure dstMeasure = new PathMeasure(dst, false);

        canvas.drawPath(path, mPaint);
        // 绘制 dst
        canvas.drawPath(dst, mPaint0);
        Log.d(TAG, "testGetSegment: startWithMoveTo = true dst.length = " + dstMeasure.getLength());
    }
}
