package origin.com.http.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import origin.com.http.R;

/**
 * 请求上游的次数 <br/>
 * <a href="https://www.jianshu.com/p/36e0f7f43a51">第9讲</a> <br/>
 * {@linkplain #synchronizedMethod(View)} 同步 下游设置的requested<br/>
 * {@linkplain #asynchronization(View)} 异步 <br/>
 * {@linkplain #requst96(View) } 下游请求96个事件后，上游自动触发 request(n)<br/>
 * Created by zc on 2018/4/27.
 */

public class Rxjava09Activity extends AppCompatActivity {
    private static final String TAG = "Rxjava09Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava_09);
    }

    private Subscription synSubscription;

    /**
     * 同步方法中，请求个数<br/>
     *
     * @param view
     */
    public void synchronizedMethod(View view) {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "上游 subscribe: 请求数 = " + e.requested());
                long req = e.requested();
                for (int i = 0; i < req; i++) {
                    Log.i(TAG, "i上游 subscribe: 请求数 = " + e.requested() + ", i = " + i);
                    e.onNext(i);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG, "onSubscribe: ");
                synSubscription = s;
                s.request(3);//请求3个
                s.request(3);//多请求3个
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: integer  = " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };
        flowable.subscribe(subscriber);
    }

    public void addRequest(View view) {
        synSubscription.request(3);
    }

    /**
     * 可以看到，当上下游工作在不同的线程里时，
     * 每一个线程里都有一个requested，而我们调用request（1000）时，
     * 实际上改变的是下游主线程中的requested，
     * 而上游中的requested的值是由RxJava内部调用request(n)去设置的，
     * 这个调用会在合适的时候自动触发
     *
     * @param view
     */
    public void asynchronization(View view) {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "上游 subscribe: 请求数 = " + emitter.requested());
                boolean flag;
                for (int i = 0; ; i++) {
                    flag = false;
                    while (emitter.requested() == 0) {
                        if (!flag) {
                            Log.d(TAG, "Oh no! I can't emit value!");
                            flag = true;
                        }
                    }
                    emitter.onNext(i);
                    Log.d(TAG, "emit " + i + " , requested = " + emitter.requested());
                    Thread.sleep(200);
                }
            }
        }, BackpressureStrategy.BUFFER);

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG, "onSubscribe: ");
                synSubscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: integer  = " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };
        flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    /**
     * 当下游每消费96个事件便会自动触发内部的request()去设置上游的requested的值啊<br/>
     * 当下游消费掉第96个事件之后，上游又开始发事件了<br/>
     *
     * @param view
     */
    public void requst96(View view) {
        synSubscription.request(96);
    }
}
