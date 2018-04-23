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
import io.reactivex.functions.Function;
import origin.com.http.R;

/**
 * 变换操作符map <br>
 *     {@linkplain #simple(View) map简单用法1}
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
     * <img width="640" height="310" src="https://upload-images.jianshu.io/upload_images/1008453-2a068dc6b726568a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/580" alt=""> 图片</img><br>
     * @param view view
     */
    public void simple(View view){
        Log.i(TAG, "simple: ");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        //map --> int 转 string
        Observable <String> map = observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                Log.i(TAG, "map --int 转 string apply: " + integer);
                return  "string = " + integer;
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
                Log.i(TAG, "onNext: s = "+ s);
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


}
