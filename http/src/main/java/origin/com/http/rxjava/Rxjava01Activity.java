package origin.com.http.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import origin.com.http.R;

/**
 * {@link #iniRxJave1()} 基础写法<br>
 * {@link #iniRxJave2()} 链式操作<br>
 * {@link #iniRxJave3()} subscribe多个重构方法<br>
 * <a href="https://www.jianshu.com/p/464fa025229e">RxJava 第一讲</a> <br>
 * Created by zc on 2018/4/10.
 */

public class Rxjava01Activity extends AppCompatActivity{
    private static final String TAG = "Rxjava01Activity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        iniRxJave1();
        iniRxJave2();
        iniRxJave3();
    }
    /**
     * 基础写法
     */
    private void iniRxJave1() {
        //创建上游，Observable(被观察者)
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();

            }
        });
        //创建下游 observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
//                Disposable.dispose()可以切断上游联系
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
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
        ////建立连接
        observable.subscribe(observer);
    }

    /**
     * <ul>
     * <li>RxJava引以为傲的链式操作</li>
     * <li>调用onComplete(),下游停止接受，上游不受影响继续下发</li>
     * <li>调用mDisposable.dispose();,下游将中断接受，上游不受影响继续下发</li>
     * </ul>
     */
    private void iniRxJave2() {
//        写就成了RxJava引以为傲的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "上游emit 1");
                emitter.onNext(1);
                Log.d(TAG, "上游emit 2");
                emitter.onNext(2);
                Log.d(TAG, "上游emit 3");
                emitter.onNext(3);
                Log.d(TAG, "上游emit complete");
                emitter.onComplete();//调用onComplete(),下游停止接受，上游不受影响
                Log.d(TAG, "上游emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;//调用mDisposable.dispose();,下游将中断接受，上游不受影响

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "下游onSubscribe: ");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "下游onNext: " + value);
                if (value == 2) {
                    Log.d(TAG, "dispose");
                    //调用mDisposable.dispose();,下游将中断接受，上游不受影响
                    mDisposable.dispose();
                    Log.d(TAG, "下游isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "下游onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "下游onComplete: ");
            }
        });
    }

    /**
     * subscribe多个重构方法 <br>
     * subscribe() 表示下游不关心任何事件,你上游尽管发你的数据去吧, 老子可不管你发什么.<br>
     * subscribe(Observer);<br>
     * subscribe(Consumer);//带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见, 因此我们如果只需要onNext事件可以这么写<br>
     */
    private void iniRxJave3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "上游emit 1");
                emitter.onNext(1);
                Log.d(TAG, "上游emit 2");
                emitter.onNext(2);
                Log.d(TAG, "上游emit 3");
                emitter.onNext(3);
                Log.d(TAG, "上游emit complete");
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: 下游 = " + integer);
            }
        });
    }
}
