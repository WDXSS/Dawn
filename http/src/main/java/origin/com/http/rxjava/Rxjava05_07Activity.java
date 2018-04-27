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
 * 上下游流速不均衡<br/>
 * <a href="https://www.jianshu.com/p/0f2d6c2387c9">第5讲</a> <br>
 * <a href="https://www.jianshu.com/p/e4c6d7989356">第6讲</a> <br>
 * <a href="https://www.jianshu.com/p/9b1304435564">第7讲</a> <br>
 * Flowable (上游)，Subscriber下游 <br>
 * {@linkplain #flowable(View) Flowable的基础用法} <br>
 *
 * Created by zc on 2018/4/25.
 */

public class Rxjava05_07Activity extends AppCompatActivity{
    private static final String TAG = "Rxjava05_07Activity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava_05_07);

    }
    /**
     * Flowable 基础用法<br/>
     * {@link BackpressureStrategy#ERROR}BackpressureStrategy.ERROR 上下游流速不均衡时抛出异常
     * BackpressureStrategy.BUFFER
     *
     * Subscription.request(int) 下游接收上游的个数
     * @param view
     */
    public void  flowable(View view){
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "subscribe: onNext = "+1);
                emitter.onNext(1);
                Log.i(TAG, "subscribe: onNext = "+2);
                emitter.onNext(2);
                Log.i(TAG, "subscribe: onNext = "+3);
                emitter.onNext(3);
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR);//增加了一个参数

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG, "onSubscribe: ");
//                s.request(Long.MAX_VALUE);  //注意这句代码
                s.request(1);//下游接收的个数
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: integer = "+ integer);
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


}
