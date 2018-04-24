package origin.com.http.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import origin.com.http.R;
import origin.com.http.rxjava.bean.Course;
import origin.com.http.rxjava.bean.Student;

/**
 * 变换操作符map <br>
 * {@linkplain #simple(View) map简单用法1} <br/>
 * {@linkplain #simple2(View) map加入线程用法} <br/>
 * flatMap  flatMap并不保证事件的顺序，concatMap,是有序的<br/>
 * {@linkplain #flatMap(View) flatMap 用法}<br/>
 * <p>
 * <a href="https://www.jianshu.com/p/128e662906af">第三讲</a> <br>
 * Created by zc on 2018/4/11.
 */

public class Rxjava03Activity extends AppCompatActivity {
    private static final String TAG = "Rxjava03Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_03);
    }

    /**
     * map 的基础用法1<br/>
     * 将int的Observable 转成 String 的Observable <br/>
     * <img width="640" height="310" src="https://upload-images.jianshu.io/upload_images/1008453-2a068dc6b726568a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/580" alt=""> 图片</img><br>
     *
     * @param view view
     */
    public void simple(View view) {
        Log.i(TAG, "simple: ");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        //map --> int 转 string 将int的Observable 转成 String 的Observable
        Observable<String> map = observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                Log.i(TAG, "map --int 转 string apply: " + integer);
                return "string = " + integer;
            }
        });
        //下游
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: s = " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };
        map.subscribe(observer);
    }

    /**
     * 连体写法 <br/>
     * map运行线程：<br/>
     * <ul>
     * <li>
     * map初始化在下游线程之前，运行在上游线程
     * </li>
     * <li>
     * map初始化在下游线程之后，运行在下游线程
     * </li>
     * </ul>
     */
    public void simple2(View view) {
        final List<String> list = new ArrayList<>();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe: Thread = " + Thread.currentThread().getName());
                emitter.onNext(1);
                Log.d(TAG, "subscribe: 1");
                emitter.onNext(2);
                Log.d(TAG, "subscribe: 2");
                emitter.onNext(3);
                Log.d(TAG, "subscribe: 3");
                emitter.onComplete();
                Log.d(TAG, "subscribe: onComplete()");
            }
        })
                .subscribeOn(Schedulers.newThread())//上游线程
//                .observeOn(AndroidSchedulers.mainThread())//下游线程,设置下游线程在map之前，map运行在下游线程
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Log.d(TAG, "apply: Thread = " + Thread.currentThread().getName() + ", int = " + integer);
                        return "s = " + integer;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//下游线程,设置下游线程在map之之后，map运行在上游游线程
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: s = " + s + ", Thread = " + Thread.currentThread().getName());
                        list.add(s);
                    }
                });
    }


    /**
     * FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里 <br/>
     * 图1<br/>
     * <img width="290" height="160"
     * src="https://upload-images.jianshu.io/upload_images/1008453-659c8c548805fdcd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/580" ></img> <br/>
     * 图2 无序的，注意： flatMap并不保证事件的顺序,concatMap,是有序的<br/>
     * <img width="326" height="382"
     * src="https://upload-images.jianshu.io/upload_images/1008453-2ccce5cf25e8023a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/647" ></img> <br/>
     */
    public void flatMap(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }

                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: s = " + s);
            }
        });
    }

    /**
     * <a href="https://mcxiaoke.gitbooks.io/rxdocs/content/operators/FlatMap.html" >flatMap官方介绍</a> <br>
     *
     * <a href="https://www.jianshu.com/p/52cd2d514528">map 和 flatMap</a> <br>
     * @param view
     */
    public void mapOrFlatMap(View view) {
//        Observable.just("1", "2").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.i(TAG, "accept: s = " + s);
//            }
//        });

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            student.setName("name + "+ i);
            List<Course> courses  = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Course course = new Course();
                course.setCourseName("课程 i = "+i + ", j = "+ j);
                courses.add(course);
            }
            student.setCourseList(courses);
            students.add(student);
        }

        Observable.fromIterable(students).flatMap(new Function<Student, ObservableSource<Course>>() {
            @Override
            public ObservableSource<Course> apply(Student student) throws Exception {
                return Observable.fromIterable(student.getCourseList());
            }
        }).subscribe(new Consumer<Course>() {
            @Override
            public void accept(Course course) throws Exception {
                Log.i(TAG, "accept: course.name = " + course.getCourseName());
            }
        });

    }
}



















