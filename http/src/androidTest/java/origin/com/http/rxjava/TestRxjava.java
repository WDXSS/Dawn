package origin.com.http.rxjava;

import android.util.Log;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zc on 2018/4/10.
 */

public class TestRxjava {
    private static final String TAG = "TestRxjava";
    @Test
    private void iniRxjave(){
        //创建上游，Observable
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
                System.out.print("onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.print("onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.print("onError: ");
            }

            @Override
            public void onComplete() {
                System.out.print("onComplete: ");
            }
        };
        ////建立连接
        observable.subscribe(observer);

    }
}
