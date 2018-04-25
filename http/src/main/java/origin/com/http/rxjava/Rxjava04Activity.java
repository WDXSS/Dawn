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
import origin.com.http.R;
import origin.com.http.rxjava.bean.Course;
import origin.com.http.rxjava.bean.Score;

/**
 * zip操作符，将多Observable 发送事件合并成一个事件<br/>
 *{@linkplain #simple(View) zip 简单用法} <br/>
 * <a href="https://www.jianshu.com/p/bb58571cdb64">第4讲</a> <br>
 * Created by zc on 2018/4/24.
 */

public class Rxjava04Activity extends AppCompatActivity{
    private static final String TAG = "Rxjava04Activity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_04);

    }

    /**
     * 严格一一对应，course3 与下游没有对应，所以不被执行
     * @param view
     */
    public void simple(View view){
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
                Log.i(TAG, "合并前的数据 apply: ,课程 = "+ course.getCourseName() + ", 成绩 = "+ score.getScore());
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
                Log.i(TAG, "合并后 onNext: course = "+ course.getCourseName() + ", 成绩 = "+ course.getScore().getScore());
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
}
