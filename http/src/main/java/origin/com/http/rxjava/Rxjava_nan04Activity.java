package origin.com.http.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import origin.com.http.R;

/**
 * Single，distinct，debounce，defer，last，merge，reduce，scan，window <br>
 *
 * {@linkplain #single(View) 操作符 Single}
 * <a href="https://www.jianshu.com/p/c08bfc58f4b6"> 第四讲</a> <br>
 *
 * Created by zc on 2018/4/28.
 */

public class Rxjava_nan04Activity extends AppCompatActivity{
    private static final String TAG = "Rxjava_nan04Activity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_nan04);
    }

    /**
     * Single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()。
     */
    public void single(View view){
        Single.just("112").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: s = "+ s);
            }
        });
    }

    /**
     * 去重，参考{@link Rxjava04Activity#distinct(View)}
     */
    public void distinct(){

    }

    private long last ;
    /**
     * 去除发送频率过快的项
     * debounce(time ,T) time 距离下一次触发的最小时间间隔
     */
    public void debounce(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onNext(6); // deliver
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext -- accept: integer = "+ integer);
                Log.d(TAG, "accept: debounce - time = " + (System.currentTimeMillis() - last));
                last = System.currentTimeMillis();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG,"debounce :" + integer + "\n");
                    }
                });

    }
}
