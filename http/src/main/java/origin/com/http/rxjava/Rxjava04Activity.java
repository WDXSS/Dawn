package origin.com.http.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import origin.com.http.R;
import origin.com.http.rxjava.bean.Course;
import origin.com.http.rxjava.bean.Score;

/**
 * zip操作符，将多Observable 发送事件合并成一个事件<br/>
 * {@linkplain #simple(View) zip 简单用法} <br/>
 * <a href="https://www.jianshu.com/p/bb58571cdb64">第4讲</a> <br>
 * <p>
 * {@linkplain #concat(View) concat操作符--合并}<br/>
 * {@linkplain #distinct(View) distinct 操作符，去重} <br/>
 * {@linkplain #filter(View) filter 过滤器} <br/>
 * {@link #buffer(View)}    <br/>
 * {@link #timer(View) }     <br/>
 * {@link #interval(View)}   <br/>
 * {@link #doOnNext(View)}   <br/>
 * {@link #skip(View)}   <br/>
 * {@link #take(View)}    <br/>
 * <p>
 * <a href="https://www.jianshu.com/p/b39afa92807e"> Concat学习</a> <br>
 * <a href="https://www.jianshu.com/p/e9c79eacc8e3">distinct,filter，buffer，timer，interval，doOnNext，skip，take学习</a>  <br/>
 * Created by zc on 2018/4/24.
 */

public class Rxjava04Activity extends AppCompatActivity {
    private static final String TAG = "Rxjava04Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_04);

    }

    /**
     * 严格一一对应，course3 与下游没有对应，所以不被执行
     *
     * @param view
     */
    public void simple(View view) {
        //创建 上游1
        Observable<Course> observable1 = Observable.create(new ObservableOnSubscribe<Course>() {
            @Override
            public void subscribe(ObservableEmitter<Course> emitter) throws Exception {
                Course course1 = new Course();
                course1.setCourseName("语文");
                emitter.onNext(course1);
                Course course2 = new Course();
                course2.setCourseName("数学");
                emitter.onNext(course2);

                //严格一一对应，course3 与下游没有对应，所以不被执行
                Course course3 = new Course();
                course3.setCourseName("英语");
                emitter.onNext(course3);
                emitter.onComplete();
            }
        });
        //创建 上游2
        Observable<Score> observable2 = Observable.create(new ObservableOnSubscribe<Score>() {
            @Override
            public void subscribe(ObservableEmitter<Score> emitter) throws Exception {
                Score score1 = new Score();
                score1.setScore("98");
                emitter.onNext(score1);
                Score score2 = new Score();
                score2.setScore("89");
                emitter.onNext(score2);
                emitter.onComplete();
            }
        });
        //合并上游1 和上游2
        Observable.zip(observable1, observable2, new BiFunction<Course, Score, Course>() {
            @Override
            public Course apply(Course course, Score score) throws Exception {
                Log.i(TAG, "合并前的数据 apply: ,课程 = " + course.getCourseName() + ", 成绩 = " + score.getScore());
                course.setScore(score);

                return course;
            }
        }).subscribe(new Observer<Course>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Course course) {
                Log.i(TAG, "合并后 onNext: course = " + course.getCourseName() + ", 成绩 = " + course.getScore().getScore());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });

    }

    /**
     * concat 操作符，合并两个上游,成一个上游 <br/>
     * 注意和zip操作符的区别<br/>
     *
     * @param view
     */
    public void concat(View view) {
        Observable<String> observable = Observable.concat(Observable.just("1", "2"),
                Observable.just("3", "4", "5"));
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: s = " + s);
            }
        });
    }

    /**
     * distinct 操作符--去重 <br/>
     */
    public void distinct(View view) {
        Observable.just( 1, 2, 2, 3, 3, 4, 5)
                .distinct()//去重
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept: integer =" + integer);
                    }
                });
    }

    /**
     * filter 操作符--过滤器
     */
    public void filter(View view) {
        Observable.just(12, 11, 6, 7, 9, 15, 17)
                //添加过滤器
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "accept: integer = " + integer);
            }
        });
    }

    /**
     * buffer(count,skip) 截取一截，长度count，每次跳过skip 长度
     *
     * @param view
     */
    public void buffer(View view) {

    }

    /**
     * 延迟
     *
     * @param view
     */
    public void timer(View view) {

    }

    /**
     * interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
     *
     * @param view
     */
    public void interval(View view) {

    }

    /**
     * doOnNext 在下游接收事件之前触发
     *
     * @param view
     */
    public void doOnNext(View view) {

    }

    /**
     * skip(int) 跳过多少个事件
     *
     * @param view
     */
    public void skip(View view) {

    }

    /**
     * take(int) 取多少个事件
     *
     * @param view
     */
    public void take(View view) {

    }
}
