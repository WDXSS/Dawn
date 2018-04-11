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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import origin.com.http.R;

/**
 * Rxjava2 线程
 * {@linkplain #defThread() 默认在同一个线程}<br>
 * {@linkplain #createThread() 新的线程}<br>
 * {@linkplain #stuadeSchedulers() Schedulers提供的子线程}<br>
 * <a href="https://www.jianshu.com/p/8818b98c44e2">RxJava 第二讲</a> <br>
 * Created by zc on 2018/4/11.
 */

public class Rxjava02Activity extends AppCompatActivity {
    private static final String TAG = "Rxjava02Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_02);
        findViewById(R.id.btn_def).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defThread();
            }
        });
        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createThread();
            }
        });
    }

    /**
     * 上游和下游默认在一个线程
     */
    private void defThread() {
        //创建上游
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //反射器
                Log.i(TAG, "上游subscribe: emitter 线程 = " + Thread.currentThread().getName());
                Log.i(TAG, "subscribe: 上游 放入值 = " + 1);
                emitter.onNext(1);
                Log.i(TAG, "subscribe: 上游 放入值 = " + 2);
                emitter.onNext(2);
            }
        });
        //创建下游
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG, "下游 onNext: 线程 = " + Thread.currentThread().getName());
                Log.i(TAG, "下游 onNext: 接收值 = " + o);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        //上下游建立关系
        observable.subscribe(observer);

    }

    /**
     * 上游指定了两次线程, 但只有第一次指定的有效 <br>
     * 下游调用一次observeOn() 线程便会切换一次 <br>
     * doOnNext();? <br>
     */
    private void createThread() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "上游 subscribe: 线程= " + Thread.currentThread().getName());
                Log.d(TAG, "上游 subscribe: 放入值" + 1);
                e.onNext(1);
                Log.d(TAG, "上游 subscribe: 放入值" + 2);
                e.onNext(2);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "下游游 onNext: 线程= " + Thread.currentThread().getName());
                Log.d(TAG, "下游游 onNext:接收值" + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        //上下游关系
        observable
                .subscribeOn(Schedulers.newThread())//设置上游的所在线程
                .observeOn(AndroidSchedulers.mainThread())//设置下游所在的线程
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(mainThread)--accept: integer = " + integer);
                        Log.d(TAG, "After observeOn(mainThread)--accept: 线程= " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())//设置下游所在的线程
                .doOnNext(new Consumer<Integer>() {//doOnNext()是在observer之前
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(newThread)--accept: integer = " + integer);
                        Log.d(TAG, "After observeOn(newThread)--accept: 线程= " + Thread.currentThread().getName());
                    }
                })
                .subscribe(observer);
    }

    /**
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作 <br>
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作<br>
     * Schedulers.newThread() 代表一个常规的新线程 <br>
     * AndroidSchedulers.mainThread() 代表Android的主线程<br>
     */
    private void stuadeSchedulers() {
//        Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
//        Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
//        Schedulers.newThread() 代表一个常规的新线程
//        AndroidSchedulers.mainThread() 代表Android的主线程

    }
}
