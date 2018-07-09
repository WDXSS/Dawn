package origin.com.dongnaolsn.lsn8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

import origin.com.dongnaolsn.R;

/**
 * 核心要点:
 * <p>
 * 1.通过 tan 得值计算出图片旋转的角度，tan 是 tangent 的缩写，
 * 即中学中常见的正切， 其中tan0是邻边边长，tan1是对边边长，
 * 而Math中 atan2 方法是根据正切是数值计算出该角度的大小,得到的单位是弧度，所以上面又将弧度转为了角度。
 * 2.通过 Matrix 来设置图片对旋转角度和位移，这里使用的方法与前面讲解过对 canvas操作
 * 有些类似，对于 Matrix 会在后面专一进行讲解，敬请期待。
 * 3.页面刷新，页面刷新此处是在 onDraw 里面调用了
 * invalidate 方法来保持界面不断刷新，但并不提倡这么做，
 * 正确对做法应该是使用 线程 或者 ValueAnimator 来控制界面的刷新，
 * <p>
 *
 *   http://www.bubuko.com/infodetail-1600666.html
 * Created by zc on 2018/7/9.
 */

public class TanView extends View {

    private int screenWith;
    private int screenHeight;
    private Paint mPaint;

    public TanView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
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

//        testPosTan(canvas);
        testGetMatrix(canvas);
    }


    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    /**
     * 获取指定长度的位置坐标及该点切线值
     *
     * @param canvas
     */
    private void testPosTan(Canvas canvas) {
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, true);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 12;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();


        currentValue += 0.005;          // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }
        pathMeasure.getPosTan(pathMeasure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
        mMatrix.reset();               // 重置Matrix

        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mPaint);  // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint); // 绘制箭头
        invalidate();         // 重绘页面
    }

    /**
     * 这个方法是用于得到路径上某一长度的位置以及该位置的正切值的矩阵：
     * boolean getMatrix (float distance, Matrix matrix, int flags)
     */
    private void testGetMatrix(Canvas canvas) {
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, true);
//
//        pos = new float[2];
//        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 12;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();


        currentValue += 0.005;          // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

//        pathMeasure.getPosTan(pathMeasure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
//        mMatrix.reset();               // 重置Matrix

        // 获取当前位置的坐标以及趋势的矩阵
        pathMeasure.getMatrix(pathMeasure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);

//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片

//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)

        canvas.drawPath(path, mPaint);  // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint); // 绘制箭头
        invalidate();         // 重绘页面
    }
}
